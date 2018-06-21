package com.amaropticals.model;

public class ItemModel {
	
	private int productId;
	private int buyQuantity;
	private String productName;
	private String taskId;
	private String deliveryDate;
	private boolean lensActive;
	private String purchaseCode;
	private double unitPrice;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int quantity) {
		this.buyQuantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public boolean isLensActive() {
		return lensActive;
	}
	public void setLensActive(boolean lensActive) {
		this.lensActive = lensActive;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	
	

}