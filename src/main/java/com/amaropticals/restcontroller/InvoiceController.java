package com.amaropticals.restcontroller;

import java.sql.Date;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.common.AOConstants;
import com.amaropticals.common.CommonUtils;
import com.amaropticals.dao.StocksDAO;
import com.amaropticals.filehandling.JSONFileHandler;
import com.amaropticals.model.AddOrUpdateStockRequest;
import com.amaropticals.model.AddOrUpdateStockResponse;
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

	@RequestMapping(value = "/createInvoice", method = RequestMethod.POST)
	public CreateInvoiceResponse createInvoice(
			@RequestBody CreateInvoiceRequest request) {

		request.setInvoiceId(CommonUtils.getNextInvoiceId());
		request.setJsonFileName(request.getInvoiceId() + ".json");
		String sql = "INSERT INTO opticals_invoices (invoice_id, name , email , contact, delivery_date, total_amount,"
				+ " initial_amount, update_timestamp, json_file_name) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
		stocksDAO.addOrUpdateInvoice(sql, request.getInvoiceId(),
				request.getName(), request.getEmail(), request.getContact(),
				Date.valueOf(request.getDeliveryDate()),
				request.getTotalAmount(), request.getInitialAmount(),
				Timestamp.valueOf(request.getUpdateDate()),
				request.getJsonFileName());
		JSONFileHandler.writeJsonFile("C:/Users/Sonu/Desktop/invoices", String.valueOf(request.getInvoiceId()).substring(0, 6),
				request.getJsonFileName(), request);
		updateStocks(request);

		CreateInvoiceResponse response = new CreateInvoiceResponse();
		response.setStatus("success");
		return response;
	}

	@RequestMapping(value = "/getInvoice/{invoiceId}", method = RequestMethod.GET)
	public AddOrUpdateStockResponse getInvoice(
			@PathVariable("invoiceId") long invoiceId) {

		return null;
	}

	@Async
	private void updateStocks(CreateInvoiceRequest request) {
		LOGGER.info("Updating stocks for invoiceId={}", request.getInvoiceId());
		int taskCount = 1;
		for (ItemModel item : request.getItemsList()) {
			if (item.isLensActive()) {
				TaskModel model = new TaskModel();
				model.setTaskId(request.getInvoiceId() + "-" + taskCount);
				model.setTaskStatus(AOConstants.TASK_IN_PROGRESS);
				model.setDeliveryDate(item.getDeliveryDate());
				model.setUpdateTime(request.getDeliveryDate());
				taskController.createTasks(model);
				taskCount++;
			}

			AddOrUpdateStockRequest model = new AddOrUpdateStockRequest();
			model.setProductId(item.getProductId());
			model.setQuantity(-item.getQuantity());
			model.setReason("Invoice");
			model.setRefId(String.valueOf(request.getInvoiceId()));
			model.setUpdateDate(request.getUpdateDate());
			model.setUser("user");
			stockController.updateStocks(model);
		}

	}
}
