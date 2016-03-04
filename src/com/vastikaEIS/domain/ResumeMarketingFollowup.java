package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "resumemarketingfollowup")
public class ResumeMarketingFollowup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Temporal(javax.persistence.TemporalType.DATE)
	@NotNull(message = "invalid date")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date followupDate;

	private String followupSummary;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "invalid date")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date nextFollowupDate;

	public long getId() {
		return id;
	}

	public Date getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(Date followupDate) {
		this.followupDate = followupDate;
	}

	public String getFollowupSummary() {
		return followupSummary;
	}

	public void setFollowupSummary(String followupSummary) {
		this.followupSummary = followupSummary;
	}

	public Date getNextFollowupDate() {
		return nextFollowupDate;
	}

	public void setNextFollowupDate(Date nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}

}
