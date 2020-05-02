package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Child;
import com.hibernate.entity.Parent;
import com.hibernate.entity.ParentPrimaryKey;
import com.hibernate.util.HibernateUtil;

public class CompositeForeignKeyClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// persisting
		ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Charlotte", "Crawford");
		Parent parent = new Parent(parentPrimaryKey);

		Child child1 = new Child("Ruby");
		Child child2 = new Child("Groovy");

		parent.addChild(child1);
		parent.addChild(child2);

		session.persist(parent);

		session.getTransaction().commit();
		session.close();

		HibernateUtil.shutdown();
	}
}