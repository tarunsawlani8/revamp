package com.amaropticals.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amaropticals.dao.StocksDAO;
import com.amaropticals.filehandling.JSONFileHandler;
import com.amaropticals.model.AddOrUpdateStockRequest;
import com.amaropticals.model.AddOrUpdateStockResponse;
import com.amaropticals.model.StockLogModel;
import com.amaropticals.model.StockModel;

@RequestMapping("/stocks")
@RestController
public class StockController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StocksDAO stocksDAO;
	
	@Value("${amaropticals.stocks.path}")
	private String stockPath;

	@RequestMapping(value = "/getAllStocks", method = RequestMethod.GET)
	public List<StockModel> getAllStocks() {
		String sql = "SELECT * from opticals_stocks;";
		List<StockModel> modelList = stocksDAO.findStocks(sql);
	//	Iterator<StockModel> itr = model.iterator();
	//	List<StockModel> getList = new ArrayList<>();
		/*while (itr.hasNext()) {
			getList.add((StockModel) JSONFileHandler.readJsonFile(
					"C:/Users/Sonu/Desktop/stocks", "", itr.next().getJsonFileName(),
					StockModel.class));
		}*/
		return modelList;
	}
	
	@RequestMapping(value = "/getStocksType", method = RequestMethod.GET)
	public List<String> getStocksType() {
		String sql = "SELECT DISTINCT(product_type) from opticals_stocks;";
		List<String> modelList = stocksDAO.query(sql);
		return modelList;
	}

	@RequestMapping(value = "/getStocksbyType/{type}", method = RequestMethod.GET)
	public List<StockModel> getSotcksbyType(
			@PathVariable("type") String productType) {
		String sql = "SELECT * from opticals_stocks WHERE product_type LIKE "
				+ " '" + productType + "';";

		return stocksDAO.findStocks(sql);

	}

	@RequestMapping(value = "/getStocksbyId/{productId}", method = RequestMethod.GET)
	public StockModel getStocksbyId(@PathVariable("productId") Integer productId) {
		/*String sql = "SELECT * from opticals_stocks WHERE product_id="
				+ productId + ";";*/
		
		StockModel model = (StockModel)JSONFileHandler.readJsonFile(stockPath, "", productId+".json", StockModel.class);

		return model;
	}

	@RequestMapping(value = "/getStocksbyCode/{code}", method = RequestMethod.GET)
	public ResponseEntity<StockModel> getStocksbyCode(
			@PathVariable("code") String productCode) {
		return null;
	}

	@RequestMapping(value = "/updateStocks", method = RequestMethod.POST)
	public AddOrUpdateStockResponse updateStocks(
			@RequestBody AddOrUpdateStockRequest request) {

		StockModel model = (StockModel) JSONFileHandler.readJsonFile(
				stockPath, "", request.getProductId() + ".json",
				StockModel.class);

		String sql = "UPDATE opticals_stocks SET product_qty= ? ,update_timestamp=?  WHERE product_id=?";
		stocksDAO.addOrUpdateStocks(sql,
				model.getQuantity() + request.getQuantityChange(),
				Timestamp.valueOf(LocalDateTime.now()),
				request.getProductId());

		model.setQuantity(model.getQuantity() + request.getQuantityChange());
		StockLogModel log = new StockLogModel();
		log.setQuantityChange(request.getQuantityChange());
		log.setReason(request.getReason());
		log.setRefId(request.getRefId());
		log.setUpdateDate(String.valueOf(Timestamp.valueOf(LocalDateTime.now())));
		log.setUser(request.getUser());

		model.getStockLogsList().add(log);
		JSONFileHandler.writeJsonFile(stockPath, "",
				model.getJsonFileName(), model);

		LOGGER.info(
				"Updating stocks for productId={}, quantity={} and user={} ",
				request.getProductId(), request.getQuantityChange(),
				request.getUser());
		AddOrUpdateStockResponse response = new AddOrUpdateStockResponse();
		response.setStatus("success");
		response.setModel(model);
		return response;
	}

	@RequestMapping(value = "/addStocks", method = RequestMethod.POST)
	public AddOrUpdateStockResponse addStocks(@RequestBody StockModel request) {

		LOGGER.info(
				"Adding new  stocks for productId={},productName={} ,quantity={} ",
				request.getProductId(), request.getProductName(),
				request.getQuantity());

		String sql = "INSERT INTO opticals_stocks ( product_id,product_type,product_sub_type,product_name ,product_desc"
				+ ",product_code ,product_qty ,update_timestamp  ,json_file_name ) VALUES (?,?,?,?,?,?,?,?,?)";
		stocksDAO.addOrUpdateStocks(sql, request.getProductId(),
				request.getProductType(), request.getProductSubType(),
				request.getProductName(), request.getProductDesc(),
				request.getCode(), request.getQuantity(),
				Timestamp.valueOf(LocalDateTime.now()),
				request.getProductId()+".json");
		request.setJsonFileName(request.getProductId()+".json");
		StockLogModel log = new StockLogModel();
		log.setQuantityChange(request.getQuantity());
		log.setReason("New Stock Added");
		log.setRefId("start");
		log.setUpdateDate(request.getUpdateDate());
		log.setUser("user");
		request.setStockLogsList(Arrays.asList(log));
		JSONFileHandler.writeJsonFile(stockPath, "",
				request.getJsonFileName(), request);
		AddOrUpdateStockResponse response = new AddOrUpdateStockResponse();
		response.setModel(request);
		response.setStatus("success");
		return response;
	}
}
