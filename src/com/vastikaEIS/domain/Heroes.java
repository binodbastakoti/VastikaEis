package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "heroes")
@org.hibernate.annotations.GenericGenerator(name = "user-primarykey", strategy = "foreign", parameters = { @org.hibernate.annotations.Parameter(name = "property", value = "user") })
public class Heroes implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "user-primarykey")
	private Long id;

	// two weeks/immediate
	private String availability;
	// Active/In Project/No longer with us/In training/Marketing
	private String status;
	// F1, F1-CPT, F1-OPT, H1, GC, EAD, US Citizen
	private String visaStatus;
	// Y/N
	private Character primeOK;
	private String skillSpecialization;

	// @OneToMany(mappedBy = "heroes")
	// private List<ClientInterestHeroes> clientInterestUser;

	@OneToMany(mappedBy = "uploadedBy", cascade = CascadeType.ALL)
	private List<FileUpload> fileUploads;

	@OneToMany(mappedBy = "heroes")
	private List<HeroesResume> userResumes;

	@OneToMany(mappedBy = "heroes")
	private List<HeroesSkills> heroesSkills;

	@OneToOne
	@JoinColumn(name = "skillCategory")
	private SkillCategory skillCategory;

	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Character getPrimeOK() {
		return primeOK;
	}

	public void setPrimeOK(Character primeOK) {
		this.primeOK = primeOK;
	}

	public String getSkillSpecialization() {
		return skillSpecialization;
	}

	public void setSkillSpecialization(String skillSpecialization) {
		this.skillSpecialization = skillSpecialization;
	}


	public List<FileUpload> getFileUploads() {
		return fileUploads;
	}

	public String getVisaStatus() {
		return visaStatus;
	}

	public void setVisaStatus(String visaStatus) {
		this.visaStatus = visaStatus;
	}

	public void setFileUploads(List<FileUpload> fileUploads) {
		this.fileUploads = fileUploads;
	}

	public List<HeroesResume> getUserResumes() {
		return userResumes;
	}

	public void setUserResumes(List<HeroesResume> userResumes) {
		this.userResumes = userResumes;
	}

	public SkillCategory getSkillCategory() {
		return skillCategory;
	}

	public void setSkillCategory(SkillCategory skillCategory) {
		this.skillCategory = skillCategory;
	}

	public List<HeroesSkills> getHeroesSkills() {
		return heroesSkills;
	}

	public void setHeroesSkills(List<HeroesSkills> heroesSkills) {
		this.heroesSkills = heroesSkills;
	}

}
