package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank
	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userId")
	private List<Address> address;

	@OneToMany
	@JoinColumn(name = "userId")
	private List<Contacts> contact;
	
	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String status;

	@OneToMany(mappedBy = "client")
	private List<ClientInterestHeroes> clientInterestUser;

	public long getId() {
		return id;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Contacts> getContact() {
		return contact;
	}

	public void setContact(List<Contacts> contact) {
		this.contact = contact;
	}

	public List<ClientInterestHeroes> getClientInterestUser() {
		return clientInterestUser;
	}

	public void setClientInterestUser(List<ClientInterestHeroes> clientInterestUser) {
		this.clientInterestUser = clientInterestUser;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
