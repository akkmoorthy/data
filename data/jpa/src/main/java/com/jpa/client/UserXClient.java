package com.jpa.client;

import java.util.List;

import javax.persistence.EntityManager;

import com.jpa.util.JPAUtil;

public class UserXClient {
	public static void main(String[] args) {

		EntityManager entityManager1 = null;

		entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager1.getTransaction().begin();

		List<Object[]> resultList = entityManager1.createQuery("select guide.name, guide.salary from Guide as guide")
				.getResultList();

		for (Object[] objects : resultList) {
			System.out.println("Object[] {objects[0]: " + objects[0] + ", objects[1]: " + objects[1] + "}");
		}

		long sumOfSalaries = (Long) entityManager1.createQuery("select sum(guide.salary) from Guide as guide")
				.getSingleResult();
		System.out.println("[sumOfSalaries: " + sumOfSalaries + "]");

		// Updating all guides by raising their salaries 4 times with WRITE Lock
		entityManager1.createQuery("update Guide as guide set guide.salary = guide.salary * 4").executeUpdate();

		entityManager1.getTransaction().commit();
		entityManager1.close();

		JPAUtil.shutdown();
	}
}
