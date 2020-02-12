package com.amaropticals.model;

public class CreateInvoiceResponse extends CommonResponseModel {
	private CreateInvoiceRequest response;

	public CreateInvoiceRequest getResponse() {
		return response;
	}

	public void setResponse(CreateInvoiceRequest response) {
		this.response = response;
	}
}
