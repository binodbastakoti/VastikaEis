package com.vastikaEIS.dto.response;

import java.util.List;

public class JsonIdMessage {

	private long id;
	private String message;
	private List<String> errorMessages;
	private String[] errorCodes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public String[] getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(String[] errorCodes) {
		this.errorCodes = errorCodes;
	}

}
