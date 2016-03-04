package com.vastikaEIS.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "role_permissions")
public class RolePermissions {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne()
	@JoinColumn(name = "roles_id", referencedColumnName = "id")
	private Roles roles;
	@ManyToOne()
	@JoinColumn(name = "permissions_id", referencedColumnName = "id")
	private Permissions permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

}
