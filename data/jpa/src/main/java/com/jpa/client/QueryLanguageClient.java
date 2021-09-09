package com.jpa.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jpa.entity.Guide;
import com.jpa.entity.Student;
import com.jpa.util.JPAUtil;

public class QueryLanguageClient {
	public static void main(String[] args) {
		EntityManager entityManager1 = null;

		try {
			entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager1.getTransaction().begin();

			// Querying Entities
			Query query = entityManager1.createQuery("select guide from Guide guide");
			List<Guide> guides = query.getResultList();
			for (Guide guide : guides) {
				System.out.println("Querying Entities = " + guide);
			}

			Query query1 = entityManager1.createQuery("select guide.name from Guide guide");
			List<String> names = query1.getResultList();
			for (String name : names) {
				System.out.println("Querying Entities - Guide Naem = "+name);
			}

			// Reporting Queries
			query = entityManager1.createQuery("select guide.name, guide.salary from Guide guide");
			List<Object[]> resultList = query.getResultList();
			for (Object[] objects : resultList) {
				System.out.println("Reporting Queries - Object[] {objects[0]: " + objects[0] + ", objects[1]: " + objects[1] + "}");
			}

			// ############################

			// Dynamic Queries
			String name = "Rod Henegoan"; // simulating dynamic query
			query = entityManager1.createQuery("select guide from Guide guide where guide.name = '" + name + "'");
			Guide guide = (Guide) query.getSingleResult();
			System.out.println("Dynamic Queries = " + guide);

			query = entityManager1.createQuery("select guide from Guide guide where guide.name = :name");
			query.setParameter("name", "Rod Henegoan");
			guide = (Guide) query.getSingleResult();
			System.out.println("Dynamic Queries = "+ guide);

			// ############################

			// Chaining Method Calls
			guide = (Guide) entityManager1.createQuery("select guide from Guide guide where guide.name = :name")
					.setParameter("name", "Rod Henegoan").getSingleResult();
			System.out.println("Chaining Method Calls = " + guide);

			// ############################

			// Wildcards
			query = entityManager1.createQuery("select guide from Guide guide where guide.name like 'm%'");
			guides = query.getResultList();
			for (Guide guid : guides) {
				System.out.println("Wildcards = " + guid);
			}

			// ############################

			// Native SQL Queries
			query = entityManager1.createNativeQuery("select * from Guide", Guide.class);
			guides = query.getResultList();
			for (Guide guid : guides) {
				System.out.println("Native SQL Queries = " + guid);
			}

			// ############################

			// Named Queries
//			guides = entityManager1.createNamedQuery("findByGuide").setParameter("name", "Mike Lawson").getResultList();
//			for (Guide guid : guides) {
//				System.out.println("Named Queries = " + guid);
//			}

			int numOfGuides = entityManager1.createQuery("select guide from Guide guide").getResultList().size();
			System.out.println("[numOfGuides: " + numOfGuides + "]");

			// ############################

			// Aggregate Functions
			query = entityManager1.createQuery("select count(guide) from Guide guide");
			long numOfGuide = (Long) query.getSingleResult();
			System.out.println("Aggregate Functions - [numOfGuides: " + numOfGuide + "]");

			query = entityManager1.createQuery("select max(guide.salary) from Guide guide");
			Integer maximumSalary = (Integer) query.getSingleResult();
			System.out.println("Aggregate Functions - [maximumSalary: " + maximumSalary + "]");

			query = entityManager1.createQuery("select guide from Guide guide where guide.salary = 2000");
			guides = query.getResultList();
			for (Guide guid : guides) {
				System.out.println("Aggregate Functions = " + guid);
			}

			// ############################

			// Persisting a Student object
			Student student = new Student("2014BE50789", "Bruce Lee");
			entityManager1.persist(student);

			// ############################

			// Joining Associations
			query = entityManager1.createQuery("select student from Student student join student.guide guide");
			List<Student> students = query.getResultList();
			for (Student stud : students) {
				System.out.println("Joining Associations - inner join = " + stud);
			}

			query = entityManager1.createQuery("select student from Student student left join student.guide guide");
			students = query.getResultList();
			for (Student stud : students) {
				System.out.println("Joining Associations - left join " + stud);
			}

			query = entityManager1.createQuery("select student from Student student right join student.guide guide");
			students = query.getResultList();
			for (Student stud : students) {
				System.out.println("Joining Associations - right join = " + stud);
			}

			// Fetching Associations
			query = entityManager1.createQuery("select guide from Guide guide join guide.students student");
			guides = query.getResultList();
			System.out.println("Fetching Associations - join = " + guides);

			query = entityManager1.createQuery("select guide from Guide guide join fetch guide.students student");
			guides = query.getResultList();
			System.out.println("Fetching Associations - join fetch = " + guides);

			entityManager1.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager1.getTransaction() != null) {
				entityManager1.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager1 != null) {
				entityManager1.close();
			}
			JPAUtil.shutdown();
		}

	}
}