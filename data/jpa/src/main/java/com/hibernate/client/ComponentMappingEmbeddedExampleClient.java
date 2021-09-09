package com.hibernate.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Address;
import com.hibernate.entity.Person;
import com.hibernate.entity.PersonEmbedded;
import com.hibernate.util.HibernateUtil;

public class ComponentMappingEmbeddedExampleClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			Address homeAddress = new Address("200 E Main St", "Gandhi Nagar", "560056");
			Address billingAddress = new Address("200 E Main St", "Nehru Nagar", "560078");
			PersonEmbedded person = new PersonEmbedded("Ruby", homeAddress, billingAddress);

			session.save(person);

			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		HibernateUtil.shutdown();
	}
}