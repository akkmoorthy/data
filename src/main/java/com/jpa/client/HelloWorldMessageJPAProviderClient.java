package com.jpa.client;

import javax.persistence.EntityManager;

import com.jpa.entity.Message;
import com.jpa.util.JPAUtil;

public class HelloWorldMessageJPAProviderClient {
	public static void main(String[] args) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();

		Message message = new Message("Hello Man! Welcome to JPA"); // transient state
		entityManager.persist(message); // persistent state

		entityManager.getTransaction().commit();
		entityManager.close();

		message.setText("Hi"); // modifying the detached state of message

		EntityManager entityManager2 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager2.getTransaction().begin();

		// the returned mergedMessage is a persistent object
		// any changes to mergedMessage will be dirty checked when the txn2 will be
		// committed and updated in the database
		Message mergedMessage = entityManager2.merge(message);

		entityManager2.getTransaction().commit();
		entityManager2.close();

		EntityManager entityManager3 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager3.getTransaction().begin();

		Message msg = new Message("Howdy");
		// transient state

		entityManager3.persist(msg);
		// persistent state

		entityManager3.detach(msg);

		// entityManager3.getTransaction().commit();
		entityManager3.close();
		JPAUtil.shutdown();
	}
}