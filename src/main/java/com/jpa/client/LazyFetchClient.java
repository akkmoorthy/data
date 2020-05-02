package com.jpa.client;

import java.util.Set;

import javax.persistence.EntityManager;

import com.jpa.entity.Guide;
import com.jpa.entity.Student;
import com.jpa.util.JPAUtil;

public class LazyFetchClient {
	public static void main(String[] args) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			// Lazy Collection Fetching with default settings(fetch=FetchType.LAZY for
			// collection associations)
			Guide guide = entityManager.find(Guide.class, 62L);
			Set<Student> students = guide.getStudents();
			int numberOfStudents = students.size();

			System.out.println("numberOfStudents==" + numberOfStudents);
			// Eager Fetching with default settings (fetch=FetchType.EAGER for single point
			// associations)
			// Student student = em.find(Student.class, 2L);

			entityManager.getTransaction().commit();
//			entityManager.close();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

	}
}