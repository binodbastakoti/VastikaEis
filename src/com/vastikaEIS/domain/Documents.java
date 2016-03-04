package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "documents")
public class Documents implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty
	private String description;
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	@OneToOne
	private FileUpload fileUpload;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "heroesId")
	private Heroes heroes;

	@ManyToOne()
	@JoinColumn(name = "documentType")
	private DocumentType documentType;

	private Character status;
	
	@ManyToOne
	@JoinColumn(name = "modifiedBy", referencedColumnName = "id", nullable = true)
	private User modifiedBy;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date modifiedDate;

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public long getId() {
		return id;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Heroes getHeroes() {
		return heroes;
	}

	public void setHeroes(Heroes heroes) {
		this.heroes = heroes;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
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
