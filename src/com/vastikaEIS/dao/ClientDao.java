package com.vastikaEIS.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vastikaEIS.domain.Address;
import com.vastikaEIS.domain.Client;

@Repository
@Transactional
public class ClientDao {
	@Autowired
	private SessionFactory sf;

	public boolean saveClient() {
		Client client2 = new Client();
		Address address2 = new Address();
		List<Address> addresses = new ArrayList<Address>();
		address2.setAddressLine1("sdfjdksfjkdls");
		address2.setCity("jksdjfkds");
		address2.setEndDate(new Date());
		address2.setStartDate(new Date());
		address2.setState("sdfdss");
		address2.setStreet("sdfds");
//		address2.setUserType('C');
		address2.setZipCode("dsfdsfsd");
		addresses.add(address2);
		client2.setAddress(addresses);
		client2.setEmail("a@y.com");
		client2.setName("sjdfklsdj");
		client2.setContact(null);
		client2.setStatus("Y");
		client2.setClientInterestUser(null);
		sf.getCurrentSession().save(address2);
		long id = (long) sf.getCurrentSession().save(client2);
		if (id > 0) {
			return true;
		}
		return false;
	}
}
