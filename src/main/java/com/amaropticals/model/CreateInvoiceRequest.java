package com.amaropticals.model;

import java.util.List;

public class CreateInvoiceRequest {

	private long invoiceId;
	private String name;
	private String email;
	private String contact;
	private String refDr;
	private String updateDate;
	private String deliveryDate;
	private String totalAmount;
	private String initialAmount;
	private String pendingAmount;
	private String jsonFileName;
	public String getJsonFileName() {
		return jsonFileName;
	}
	public void setJsonFileName(String jsonFileName) {
		this.jsonFileName = jsonFileName;
	}
	private List<ItemModel> itemsList;
	public long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getRefDr() {
		return refDr;
	}
	public void setRefDr(String refDr) {
		this.refDr = refDr;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getInitialAmount() {
		return initialAmount;
	}
	public void setInitialAmount(String initalAmount) {
		this.initialAmount = initalAmount;
	}
	public String getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public List<ItemModel> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemModel> itemsList) {
		this.itemsList = itemsList;
	}
	
	
}
