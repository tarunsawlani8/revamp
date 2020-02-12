package com.amaropticals.model;

import java.util.List;

public class TaskModelList extends CommonResponseModel {
	
	List<TaskModel> taskModelList;

	public List<TaskModel> getTaskModelList() {
		return taskModelList;
	}

	public void setTaskModelList(List<TaskModel> taskModelList) {
		this.taskModelList = taskModelList;
	}

}
