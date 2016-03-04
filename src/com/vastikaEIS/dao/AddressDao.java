package com.vastikaEIS.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.constant.Constant;
import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Skill;
import com.vastikaEIS.domain.SkillCategory;
import com.vastikaEIS.domain.User;

@Repository
@Transactional
public class AddressDao {
	@Autowired
	private SessionFactory sf;

	public boolean addAddress(Address addr) {
		addr.setStatus(Constant.ACTIV);
		long id = (long) sf.getCurrentSession().save(addr);
		if (id > 0) {
			return true;
		}
		return false;
	}

	public Address findAddressById(long id) {
		return (Address) sf.getCurrentSession().load(Address.class, id);
	}

	// changed user to userId
	public boolean updateAddress(Address address) {

		Address newAddress = (Address) sf.getCurrentSession().merge(address);
		if (newAddress != null) {
			return true;
		}
		return false;
	}

	public long deleteAddress(long addressId) {
		Address address = findAddressById(addressId);
		address.setStatus(Constant.DELETED);
		return address.getId();
	}

	public List<Address> getAddressByUserId(User user) {
		Query query = sf.getCurrentSession().createQuery("from Address where user =:user and status=:active");
		query.setParameter("user", user);
		query.setParameter("active", Constant.ACTIV);
		return query.list();
	}

	public Address getRecentAddress(User user) {
		Query query = sf.getCurrentSession().createQuery("from Address a where a.user=:user ");
		query.setParameter("user", user);
		return (Address) query.uniqueResult();
	}
}
