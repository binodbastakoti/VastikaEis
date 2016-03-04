package com.vastikaEIS.dto.response;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.management.relation.Role;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.util.MessageProperties;

public class JsonMap extends HashMap<String, Object> {
	public String getProperties(String key) {
		MessageProperties messageProperties = new MessageProperties();
		Properties properties = messageProperties.getPropValues();
		return properties.getProperty(key);
	}

	public void setMessages(String message) {
		this.put(Constant.MESSAGE, getProperties(message));
	}

	public void setId(Long id) {
		this.put(Constant.ID, id);
	}

	public void setName(String name) {
		this.put(Constant.NAME, name);
	}

	public void setStatus(String message) {
		this.put(Constant.STATUS, getProperties(message));
	}

	public void setSuccessStatus() {
		this.put(Constant.STATUS, getProperties("successStatus"));
	}

	public void setFailedStatus() {
		this.put(Constant.STATUS, getProperties("failureStatus"));
	}

	public void setValidationFailedStatus(String message) {
		this.put(Constant.STATUS, getProperties(message));
	}

	public void setPermissionJson(List<String> permissions) {
		this.put(Constant.PERMISSIONS, permissions);
	}

	public void setRoleJson(List<Role> role) {
		this.put(Constant.ROLES, role);
	}

	public void setFileType(String fileType) {
		this.put(Constant.FILE_TYPE, fileType);
	}
}
