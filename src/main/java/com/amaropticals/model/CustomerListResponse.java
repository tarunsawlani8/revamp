package com.amaropticals.model;

import java.util.List;

public class CustomerListResponse extends CommonResponseModel{

	List<CustomerModel> customerModelList;

	public List<CustomerModel> getCustomerModelList() {
		return customerModelList;
	}

	public void setCustomerModelList(List<CustomerModel> customerModelList) {
		this.customerModelList = customerModelList;
	}
}
