package com.vastikaEIS.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vastikaEIS.dao.ContactDao;
import com.vastikaEIS.dao.UserDao;
import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.User;
import com.vastikaEIS.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	@Autowired
	private UserDao userDao;

	@Override
	public boolean addContact(Contacts contact) {
		return contactDao.addContact(contact);
	}

	public void updateContact(Contacts contact, long contactId, User user) {
		contactDao.updateContact(contact, contactId, user);
	}

	@Override
	public List<Contacts> getAllContacts() {
		// TODO Auto-generated method stub
		return contactDao.getAllContacts();
	}

	@Override
	public List<Contacts> getContactById(long id) {
		User user = userDao.findUserById(id);
		return contactDao.getContactById(user);
	}

	@Override
	public Contacts getRecentContact(long id) {
		User user = userDao.findUserById(id);
		return contactDao.getRecentContact(user);

	}

}
