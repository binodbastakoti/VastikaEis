package com.vastikaEIS.dto.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class HeroFullNameJsonData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fullName;
	private long id;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
