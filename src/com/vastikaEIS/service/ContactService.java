package com.vastikaEIS.service;

import java.util.List;

import com.vastikaEIS.domain.Contacts;
import com.vastikaEIS.domain.User;

public interface ContactService {
	public boolean addContact(Contacts contact);

	public void updateContact(Contacts contact, long contactId, User user);

	public List<Contacts> getContactById(long id);

	public List<Contacts> getAllContacts();

	public Contacts getRecentContact(long id);
}
