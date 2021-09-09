package com.jpa.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jpa.entity.Guide;
import com.jpa.entity.Student;
import com.jpa.util.JPAUtil;

public class NPlus1SelectProblem {
	public static void main(String[] args) {

		EntityManager entityManager1 = null;

		try {
			entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager1.getTransaction().begin();

			// Adding data to guide and student table by adding a Guide and associating a
			// Student with it
			Guide guide = new Guide("2000DO10777", "David Crow", 3000);
			Student student = new Student("2014RG50347", "Rahul Singh");

			guide.addStudent(student);

			entityManager1.persist(guide);

			// Loading all the student objects
			Query query = entityManager1.createQuery("select student from Student student");
			List<Student> students = query.getResultList();

			for (Student stud : students) {
				System.out.println("students = " + stud);
			}

			// Loading all the students with their associated Guide objects with the Student
			// objects selectively (whenever you need to load them eagerly)
			query = entityManager1.createQuery("select student from Student student left join fetch student.guide");
			students = query.getResultList();

			for (Student stud : students) {
				// students who do not have a guide will not be loaded
				if (stud.getGuide() != null) {
					System.out.println("Student = " + stud);
				}
			}

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