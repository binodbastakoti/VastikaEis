package com.vastikaEIS.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "userdesignation")
public class UserDesignation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userDesignationId;

	@NotNull
	private String type;

	@ManyToOne()
	@JoinColumn(name = "userId")
	private User user;

	public int getUserDesignationId() {
		return userDesignationId;
	}

	public void setUserDesignationId(int userDesignationId) {
		this.userDesignationId = userDesignationId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
