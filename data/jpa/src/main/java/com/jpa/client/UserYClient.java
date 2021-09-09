package com.jpa.client;

import javax.persistence.EntityManager;

import com.jpa.entity.Guide;
import com.jpa.util.JPAUtil;

public class UserYClient {
	public static void main(String[] args) {

		EntityManager entityManager1 = null;

		entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager1.getTransaction().begin();

		Guide guide = entityManager1.find(Guide.class, 62L);

		System.out.println("Guide = " + guide);

		entityManager1.getTransaction().commit();
		entityManager1.close();

		guide.setSalary(4000);

		EntityManager entityManager2 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager2.getTransaction().begin();

		Guide mergedGuide = entityManager2.merge(guide);
		System.out.println("Updated Guide = " + guide);

		entityManager2.getTransaction().commit();
		entityManager2.close();

		JPAUtil.shutdown();
	}
}
