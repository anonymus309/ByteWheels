package com.bytemark.bytewheels.service;

public class ServiceError  {

	private int error_id;
	private String message;
	public int getErrorId() {
		return error_id;
	}
	public void setErrorId(int id) {
		this.error_id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
