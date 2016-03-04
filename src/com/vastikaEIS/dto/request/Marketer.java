package com.vastikaEIS.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class Marketer implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<CustomUser> assignedRecruiter;
	private List<CustomUser> notAssignedRecuiter;

	public List<CustomUser> getAssignedRecruiter() {
		return assignedRecruiter;
	}

	public void setAssignedRecruiter(List<CustomUser> assignedRecruiter) {
		this.assignedRecruiter = assignedRecruiter;
	}

	public List<CustomUser> getNotAssignedRecuiter() {
		return notAssignedRecuiter;
	}

	public void setNotAssignedRecuiter(List<CustomUser> notAssignedRecuiter) {
		this.notAssignedRecuiter = notAssignedRecuiter;
	}

}
