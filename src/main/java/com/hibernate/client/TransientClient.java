package com.hibernate.client;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.entity.UserTransient;
import com.hibernate.util.HibernateUtil;

public class TransientClient {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();

			UserTransient user = new UserTransient();
			user.setBirthDate(new Date());
			user.setCreatedBy("kevin");
			user.setCreatedDate(new Date());
			user.setEmailAddress("kmb385@yahoo.com");
			user.setFirstName("Kevin");
			user.setLastName("Bowersox");
			user.setLastUpdatedBy("kevin");
			user.setLastUpdatedDate(new Date());

			session.save(user);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
}
