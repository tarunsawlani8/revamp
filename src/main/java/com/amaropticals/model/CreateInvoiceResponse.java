package com.amaropticals.model;

public class CreateInvoiceResponse extends CommonResponse {
	private CreateInvoiceRequest response;

	public CreateInvoiceRequest getResponse() {
		return response;
	}

	public void setResponse(CreateInvoiceRequest response) {
		this.response = response;
	}
}
