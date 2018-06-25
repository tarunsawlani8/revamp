package com.amaropticals.test;

import java.util.Arrays;

import com.amaropticals.common.CommonUtils;
import com.amaropticals.filehandling.JSONFileHandler;
import com.amaropticals.model.CreateInvoiceRequest;
import com.amaropticals.model.StockLogModel;
import com.amaropticals.model.StockModel;

public class TestJsonFileHandler {
	
	public static void main(String[] args) {
		
		/*StockModel model = new StockModel();
		model.setCode("AA");
		StockLogModel a1 = new StockLogModel();
		a1.setQuantityChange(11);
		
		StockLogModel a2 = new StockLogModel();
		a2.setQuantityChange(12);
		
		
		model.setStockLogsList(Arrays.asList(a1,a2));
		JSONFileHandler.writeJsonFile("C:/Users/Sonu/Desktop", "", "example.json", model );
	StockModel m = (StockModel)	JSONFileHandler.readJsonFile("C:/Users/Sonu/Desktop", "", "example.json", StockModel.class );
	
	System.out.println(m.getCode());
	System.out.println(m.getStockLogsList().get(0).getQuantityChange());
	System.out.println(m.getStockLogsList().get(1).getQuantityChange());
*/	
		CreateInvoiceRequest r = new CreateInvoiceRequest();
		r.setInvoiceId(123);
		r.setName("a");
		r.setContact("9762211018");
		r.setDeliveryDate("2");
		CommonUtils.sendMessages(r, "READY FOR PICKUP");
		
	}

}
