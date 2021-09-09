package com.jpa.client;

import java.util.Set;

import javax.persistence.EntityManager;

import com.jpa.entity.Guide;
import com.jpa.entity.Student;
import com.jpa.util.JPAUtil;

public class EqualsAndHashCode {

	public static void main(String[] args) {
		EntityManager entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager1.getTransaction().begin();

		Student student = entityManager1.find(Student.class, 63L);

		entityManager1.getTransaction().commit();
		entityManager1.close();

		EntityManager entityManager2 = JPAUtil.getEntityManagerFactory().createEntityManager();
		entityManager2.getTransaction().begin();

		Guide guide = entityManager2.find(Guide.class, 62L);
		Set<Student> students = guide.getStudents();

		System.out.println(students.contains(student));

		entityManager2.getTransaction().commit();
		entityManager2.close();

		// Guide with id=2L and Student with id=2L, both are being managed by the same
		// EntityManager
		/*
		 * EntityManager entityManager3 =
		 * JPAUtil.getEntityManagerFactory().createEntityManager();
		 * entityManager1.getTransaction().begin();
		 * 
		 * Student student = entityManager3.find(Student.class, 2L);
		 * 
		 * Guide guide = entityManager3.find(Guide.class, 2L); Set<Student> students =
		 * guide.getStudents();
		 * 
		 * System.out.println(students.contains(student));
		 * 
		 * entityManager3.getTransaction().commit(); entityManager3.close();
		 */

		JPAUtil.shutdown();
	}
}