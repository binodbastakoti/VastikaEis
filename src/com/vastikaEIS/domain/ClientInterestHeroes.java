package com.vastikaEIS.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "clientinterestheroes")
public class ClientInterestHeroes implements Serializable {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

//	@ManyToOne
//	@JoinColumn(name = "heroesId")
//	private Heroes heroes;

	@ManyToOne
	@JoinColumn(name = "clientId")
	private Client client;

	@NotBlank
	private String role;

	private String jobSummary;

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getJobSummary() {
		return jobSummary;
	}

	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}

	public long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJob_summary() {
		return jobSummary;
	}

	public void setJob_summary(String job_summary) {
		this.jobSummary = job_summary;
	}

}
