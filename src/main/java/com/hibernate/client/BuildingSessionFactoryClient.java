package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.util.HibernateUtil;
import com.jpa.util.JPAUtil;

public class BuildingSessionFactoryClient {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.buildSessionFactory().openSession();
		session.beginTransaction();
		session.close();
		
		JPAUtil.shutdown();
	}
}