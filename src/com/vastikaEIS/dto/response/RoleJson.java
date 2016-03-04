package com.vastikaEIS.dto.response;

import java.util.List;

public class RoleJson {
	private long id;
	private String name;
	private List<Long> permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Long> permissions) {
		this.permissions = permissions;
	}

}
