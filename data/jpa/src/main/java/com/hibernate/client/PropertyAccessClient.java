package com.hibernate.client;

import java.util.Date;

import org.hibernate.Session;

import com.hibernate.entity.UserPropertyAccess;
import com.hibernate.util.HibernateUtil;

public class PropertyAccessClient {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();

			UserPropertyAccess user = new UserPropertyAccess();
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
			session.close();
		}
		HibernateUtil.shutdown();
	}
}
