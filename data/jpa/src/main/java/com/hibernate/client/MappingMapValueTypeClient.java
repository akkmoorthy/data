package com.hibernate.client;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.BankMap;
import com.hibernate.util.HibernateUtil;

public class MappingMapValueTypeClient {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();

			BankMap bank = new BankMap();
			bank.setName("Federal Trust");
			bank.setAddressLine1("33 Wall Street");
			bank.setAddressLine2("Suite 233");
			bank.setCity("New York");
			bank.setState("NY");
			bank.setZipCode("12345");
			bank.setInternational(false);
			bank.setCreatedBy("Kevin");
			bank.setCreatedDate(new Date());
			bank.setLastUpdatedBy("Kevin");
			bank.setLastUpdatedDate(new Date());
			bank.getContacts().put("MANAGER", "Joe");
			bank.getContacts().put("TELLER", "Mary");
			session.save(bank);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}

}
