package com.vastikaEIS.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.util.SecurityUtil;

@Repository
@Transactional
public class ContactDao {
	@Autowired
	private SessionFactory sf;

	@Autowired
	private SecurityUtil securityUtil;

	public boolean addContact(Contacts contact) {
		long id = (long) sf.getCurrentSession().save(contact);
		if (id > 0) {
			return true;
		}
		return false;
	}

	public List<Contacts> getContactById(User user) {

		Query query = sf.getCurrentSession().createQuery("from Contacts where user =:user");
		query.setParameter("user", user);
		return query.list();
	}

	public List<Contacts> getAllContacts() {
		Query str = sf.getCurrentSession().createQuery("from Contacts");
		return str.list();
	}

	// from Contacts where user =:user and endDate=select Max(endDate) from
	// Contacts where user = :user)
	public Contacts getRecentContact(User user) {
		Query query = sf.getCurrentSession().createQuery("from Contacts where user =:user");
		query.setParameter("user", user);

		return (Contacts) query.uniqueResult();
	}

	public void updateContact(Contacts contact, long contactId, User user) {
		sf.getCurrentSession().merge(contact);
	}

}
