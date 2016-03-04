package com.vastikaEIS.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Devil
 */
@Entity
@Table(name = "roles")
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	@OneToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
	private List<RolePermissions> rolePermissions;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RolePermissions> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(List<RolePermissions> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	public void setId(long id) {
		this.id = id;
	}

}
