package com.amaropticals.model;

import java.util.List;

public class ItemModel {
	
	private int productId;
	private int buyQuantity;
	private String productName;
	private String taskId;
	private String deliveryDate;
	private boolean lensActive;
	private String purchaseCode;
	private double unitPrice;
	private String productDesc;
	private String code;
	
	
	private List<LensModel> leftEye;
	private List<LensModel> rightEye;
	
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
	public List<LensModel> getLeftEye() {
		return leftEye;
	}
	public void setLeftEye(List<LensModel> leftEye) {
		this.leftEye = leftEye;
	}
	public List<LensModel> getRightEye() {
		return rightEye;
	}
	public void setRightEye(List<LensModel> rightEye) {
		this.rightEye = rightEye;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	

}
