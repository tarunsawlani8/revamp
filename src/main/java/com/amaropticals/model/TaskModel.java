package com.amaropticals.model;

public class TaskModel {
private String taskId;
private String taskStatus;
private String deliveryDate;
private String updateTime;
private String user;
public String getTaskId() {
	return taskId;
}
public void setTaskId(String taskId) {
	this.taskId = taskId;
}
public String getTaskStatus() {
	return taskStatus;
}
public void setTaskStatus(String taskStatus) {
	this.taskStatus = taskStatus;
}
public String getDeliveryDate() {
	return deliveryDate;
}
public void setDeliveryDate(String deliveryDate) {
	this.deliveryDate = deliveryDate;
}
public String getUpdateTime() {
	return updateTime;
}
public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}

}
