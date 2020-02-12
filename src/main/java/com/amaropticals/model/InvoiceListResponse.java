package com.amaropticals.model;

import java.util.List;

public class InvoiceListResponse extends CommonResponseModel{
	
	List<CreateInvoiceRequest> invoiceList;

	public List<CreateInvoiceRequest> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<CreateInvoiceRequest> invoiceList) {
		this.invoiceList = invoiceList;
	}

	
	

}
