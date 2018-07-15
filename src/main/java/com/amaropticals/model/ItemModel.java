package com.amaropticals.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



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
	private String totalCost;
	
	
	private List<LensModel> leftEye;
	private List<LensModel> rightEye;
	
	@XmlElement(name="productid")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@XmlElement(name="buyquantity")
	public int getBuyQuantity() {
		return buyQuantity;
	}
	public void setBuyQuantity(int quantity) {
		this.buyQuantity = quantity;
	}
	
	@XmlElement(name="productname")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@XmlElement(name="taskid")
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@XmlElement(name="deliverydate")
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	@XmlElement(name="lensactive")
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
	
	@XmlElement(name="unitprice")
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	

	@XmlElementWrapper(name="lefteyes")
	@XmlElement(name="lefteye")
	public List<LensModel> getLeftEye() {
		return leftEye;
	}
	public void setLeftEye(List<LensModel> leftEye) {
		this.leftEye = leftEye;
	}
	
	@XmlElementWrapper(name="righteyes")
	@XmlElement(name="righteye")
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
	
	@XmlElement(name="totalcost")
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	

}
