package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "heroesresume")
public class HeroesResume implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Character status;

	@OneToOne
	private FileUpload fileUpload;

	@NotEmpty
	private String title;

	// @NotEmpty
	// @Email
	private String email;

	@ManyToOne()
	@JoinColumn(name = "heroesId")
	private Heroes heroes;

	private String resumeContact;

	private String visibility;

	@ManyToOne
	@JoinColumn(name = "modifiedBy", referencedColumnName = "id", nullable = true)
	private User modifiedBy;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date modifiedDate;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Heroes getHeroes() {
		return heroes;
	}

	public void setHeroes(Heroes heroes) {
		this.heroes = heroes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String tittle) {
		this.title = tittle;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResumeContact() {
		return resumeContact;
	}

	public void setResumeContact(String resumeContact) {
		this.resumeContact = resumeContact;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
