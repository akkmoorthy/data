package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Message;
import com.hibernate.util.HibernateUtil;

public class HelloWorldClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Message message = new Message("Welcome Krishna! Hello World with Hibernate & JPA Annotations");

		session.save(message);

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
	}
}
