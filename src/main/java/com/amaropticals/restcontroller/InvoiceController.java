package com.amaropticals.restcontroller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.StringUtils;
import org.apache.fop.apps.FOPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.common.AOConstants;
import com.amaropticals.common.CommonUtils;
import com.amaropticals.common.PDFUtils;
import com.amaropticals.dao.StocksDAO;
import com.amaropticals.filehandling.JSONFileHandler;
import com.amaropticals.model.AddOrUpdateStockRequest;
import com.amaropticals.model.CreateInvoiceRequest;
import com.amaropticals.model.CreateInvoiceResponse;
import com.amaropticals.model.ItemModel;
import com.amaropticals.model.TaskModel;

@RequestMapping("/invoices")
@RestController
public class InvoiceController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StocksDAO stocksDAO;

	@Autowired
	private StockController stockController;

	@Autowired
	private TaskController taskController;

	@Value("${amaropticals.invoices.path}")
	private String invoicePath;

	@RequestMapping(value = "/createInvoice", method = RequestMethod.POST)
	public CreateInvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest request) {

		request.setInvoiceId(CommonUtils.getNextInvoiceId());
		request.setJsonFileName(request.getInvoiceId() + ".json");

		request.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()).toString());

		if (StringUtils.isBlank(request.getDeliveryDate())) {
			request.setDeliveryDate(request.getUpdateDate().substring(0, 10));

		} else {
			request.setDeliveryDate(request.getDeliveryDate().substring(0, 10));

		}

		String sql = "INSERT INTO opticals_invoices (invoice_id, name , email , contact, delivery_date, total_amount,"
				+ " initial_amount, update_timestamp, json_file_name) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
		stocksDAO.addOrUpdateInvoice(sql, request.getInvoiceId(), request.getName(), request.getEmail(),
				request.getContact(), Date.valueOf(request.getDeliveryDate()), request.getTotalAmount(),
				request.getInitialAmount(), request.getUpdateDate(), request.getInvoiceId() + ".json");
		JSONFileHandler.writeJsonFile(invoicePath, String.valueOf(request.getInvoiceId()).substring(0, 4),
				request.getJsonFileName(), request);
		if (!request.isWithoutDetailsInvoice()) {
		checkAndPopulateTasksAndDate(request);
		}
		updateStocks(request);

		CreateInvoiceResponse response = new CreateInvoiceResponse();
		response.setStatus("success");
		response.setResponse(request);
		return response;
	}

	@RequestMapping(value = "/getInvoicesByMonth/{yymm}", method = RequestMethod.GET)
	public List<CreateInvoiceRequest> getInvoicesByMonth(@PathVariable("yymm") long yymm) {

		String startInvoice = yymm + "0100";
		String endInvoice = yymm + "3200";

		String sql = "SELECT * FROM opticals_invoices WHERE invoice_id > " + Long.valueOf(startInvoice)
				+ " AND invoice_id < " + Long.valueOf(endInvoice) + ";";

		return stocksDAO.findInvoices(sql);
	}

	@RequestMapping(value = "/getInvoice/{invoiceId}", method = RequestMethod.GET)
	public CreateInvoiceRequest getInvoice(@PathVariable("invoiceId") long invoiceId) {

		CreateInvoiceRequest model = (CreateInvoiceRequest) JSONFileHandler.readJsonFile(invoicePath,
				String.valueOf(invoiceId).substring(0, 4), String.valueOf(invoiceId) + ".json",
				CreateInvoiceRequest.class);
		return model;
	}

	@RequestMapping(value = "/downloadInvoice/{invoiceId}", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> downloadInvoice(@PathVariable("invoiceId") long invoiceId)
			throws FOPException, IOException, TransformerException {

		CreateInvoiceRequest model = getInvoice(invoiceId);
		LOGGER.info("Read model from json file, invoiceId={}", model.getInvoiceId());
		PDFUtils utils = new PDFUtils();
		byte[] out = utils.convertToPDF(model);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Content-Disposition", "attachment;filename="+String.valueOf(invoiceId)+".pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");


		headers.setContentLength(out.length);

		ByteArrayInputStream aa = new ByteArrayInputStream(out);
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
				new InputStreamResource(aa), headers, HttpStatus.OK);
		return response;
	}

	private void checkAndPopulateTasksAndDate(CreateInvoiceRequest request) {

		LOGGER.info("Checking for lens purchase for invoiceId={}", request.getInvoiceId());
		int count = 1;

		List<ItemModel> lensList = request.getPurchaseItems().stream().filter(item -> true == item.isLensActive())
				.collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(lensList)) {
			for (ItemModel model : lensList) {

				model.setTaskId(request.getInvoiceId() + "-" + count);
				model.setDeliveryDate(request.getDeliveryDate());
				TaskModel taskModel = new TaskModel();
				taskModel.setName(request.getName());
				taskModel.setTaskId(model.getTaskId());
				taskModel.setTaskStatus(AOConstants.TASK_IN_PROGRESS);
				taskModel.setDeliveryDate(model.getDeliveryDate());
				taskModel.setUpdateTime(request.getUpdateDate());
				taskController.createTasks(taskModel);

				count++;
				LOGGER.info("Lens task identifed for lens purchase for invoiceId={}, task={}", request.getInvoiceId(),
						model.getTaskId());

			}
			//CommonUtils.sendMessages(request, "");
		}

	}

	@Async
	private void updateStocks(CreateInvoiceRequest request) {
		LOGGER.info("Updating stocks for invoiceId={}", request.getInvoiceId());

		for (ItemModel item : request.getPurchaseItems()) {
			if (!item.isLensActive()) {
				AddOrUpdateStockRequest model = new AddOrUpdateStockRequest();
				model.setProductId(item.getProductId());
				model.setQuantityChange(-item.getBuyQuantity());
				model.setReason("Invoice");
				model.setRefId(String.valueOf(request.getInvoiceId()));
				model.setUpdateDate(request.getUpdateDate());
				model.setUser("user");
				stockController.updateStocks(model);
			}

		}

	}
}
