package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Parent;
import com.hibernate.entity.ParentPrimaryKey;
import com.hibernate.util.HibernateUtil;

public class CompositePrimaryKeyClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// persisting
		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
		Parent parent = new Parent(parentPrimaryKey);
		session.persist(parent);

		// retrieving
		/*
		 * ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
		 * Parent parent = (Parent) session.get(Parent.class, parentPrimaryKey);
		 */

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
	}
}
