package com.vastikaEIS.dao;

import java.util.List;

import javax.management.relation.Role;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.Permissions;
import com.vastikaEIS.domain.RolePermissions;
import com.vastikaEIS.domain.Roles;

@Repository
@Transactional
public class RoleDao {
	@Autowired
	private SessionFactory sf;

	public long addRole(Roles role) {
		try {
			Roles newrole = (Roles) sf.getCurrentSession().merge(role);
			if (newrole != null) {
				return newrole.getId();
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public Roles getRoleById(long id) {
		return (Roles) sf.getCurrentSession().get(Roles.class, id);

	}

	public List<Roles> listAll() {
		Query query = sf.getCurrentSession().createQuery("from Roles r");
		return query.list();
	}

	public boolean deleteRole(Roles roles) {
		try {
			sf.getCurrentSession().delete(roles);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Permissions> listAllPermissions() {
		Query query = sf.getCurrentSession().createQuery("from Permissions p");
		return query.list();
	}

	public long assignRolePermissions(List<Long> permissions, long roleId) {
		try {
			Query query = sf.getCurrentSession()
					.createQuery("delete from RolePermissions rp where rp.roles.id=:roleId");
			query.setParameter("roleId", roleId);
			query.executeUpdate();
			Roles roles = (Roles) sf.getCurrentSession().load(Roles.class, roleId);
			for (long permissionId : permissions) {
				Permissions permission = (Permissions) sf.getCurrentSession().load(Permissions.class, permissionId);
				RolePermissions rolePermission = new RolePermissions();
				rolePermission.setPermissions(permission);
				rolePermission.setRoles(roles);
				sf.getCurrentSession().save(rolePermission);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<RolePermissions> getRoleMembersByRoleId(long roleId) {
		Query query = sf.getCurrentSession().createQuery("from RolePermissions rp where rp.roles.id =:roleId");
		query.setParameter("roleId", roleId);
		return query.list();

	}
}
