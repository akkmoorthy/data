package com.jpa.client;

import javax.persistence.EntityManager;

import com.jpa.entity.Message;
import com.jpa.util.JPAUtil;

public class FirstLevelCachingClient {
	public static void main(String[] args) {

		EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager.getTransaction().begin();
		Message message1 = entityManager.find(Message.class, 110L);
		Message message2 = entityManager.find(Message.class, 110L);
		entityManager.getTransaction().commit();
		entityManager.close();

		EntityManager entityManager2 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager2.getTransaction().begin();
		Message message3 = entityManager2.find(Message.class, 110L);
		entityManager2.getTransaction().commit();
		entityManager2.close();

		EntityManager entityManager3 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager3.getTransaction().begin();
		Message message4 = entityManager3.find(Message.class, 110L);
		entityManager3.getTransaction().commit();
		entityManager3.close();

		JPAUtil.shutdown();
	}
}