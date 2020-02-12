package com.amaropticals.model;

import java.util.List;

public class StockModelListResponse extends CommonResponseModel{
	List<StockModel> stockModelList;

	public List<StockModel> getStockModelList() {
		return stockModelList;
	}

	public void setStockModelList(List<StockModel> stockModelList) {
		this.stockModelList = stockModelList;
	}
}
