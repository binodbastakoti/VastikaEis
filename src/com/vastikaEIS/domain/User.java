package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Devil
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private String password;
	
	@Transient
	public String confirmPassword;
	private boolean enabled;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	private String middleName;
	
	@NotBlank
	@Email
	private String email;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "invalid date")
	@Past(message = "invalid date")
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date dob;
	
	private Character gender;
	
	private String tokenValue;
	// userType=H(Heroes) , R(recruiter) ,O(Other)
	
	private Character userType;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "createdBy", referencedColumnName = "id", nullable = true)
	private User createdBy;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "mm/dd/yyyy")
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "modifiedBy", referencedColumnName = "id", nullable = true)
	private User modifiedBy;

	@OneToMany(mappedBy = "user")
	private List<RoleMembers> roleMembers;

	@OneToMany(mappedBy = "user")
	private List<Contacts> contacts;

	@OneToMany(mappedBy = "user")
	private List<Address> userAddresses;

	@OneToMany(mappedBy = "user")
	private List<ReminderNote> reminderNotes;
	// internal recruiter market heroes resume
	@OneToMany(mappedBy = "recruiter")
	private List<ResumeMarketing> resumeMarketing;

	@OneToOne(mappedBy = "user")
	private Heroes heroes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<RoleMembers> getRoleMembers() {
		return roleMembers;
	}

	public void setRoleMembers(List<RoleMembers> roleMembers) {
		this.roleMembers = roleMembers;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	public Character getUserType() {
		return userType;
	}

	public void setUserType(Character userType) {
		this.userType = userType;
	}

	public List<Contacts> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public List<Address> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public List<ReminderNote> getReminderNotes() {
		return reminderNotes;
	}

	public void setReminderNotes(List<ReminderNote> reminderNotes) {
		this.reminderNotes = reminderNotes;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public List<ResumeMarketing> getResumeMarketing() {
		return resumeMarketing;
	}

	public void setResumeMarketing(List<ResumeMarketing> resumeMarketing) {
		this.resumeMarketing = resumeMarketing;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Heroes getHeroes() {
		return heroes;
	}

	public void setHeroes(Heroes heroes) {
		this.heroes = heroes;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
