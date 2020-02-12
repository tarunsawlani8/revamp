package com.amaropticals.model;

public abstract class CommonResponseModel {
	
	private AOError error;

	public AOError getError() {
		return error;
	}

	public void setError(AOError error) {
		this.error = error;
	}

}
