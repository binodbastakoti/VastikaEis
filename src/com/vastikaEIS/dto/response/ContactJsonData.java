package com.vastikaEIS.dto.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ContactJsonData {

	private long id;
	private String contactNumber;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date endDate;
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date startDate;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
}
