package com.vastikaEIS.dto.request;

import java.io.Serializable;

public class CustomUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
