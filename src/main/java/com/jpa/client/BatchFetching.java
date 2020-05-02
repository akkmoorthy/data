package com.jpa.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jpa.entity.Student;
import com.jpa.entity.StudentBatch;
import com.jpa.util.JPAUtil;

public class BatchFetching {
	public static void main(String[] args) {

		EntityManager entityManager1 = null;

		try {
			entityManager1 = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager1.getTransaction().begin();

			// Loading all the students lazily; the guides associated with students are
			// "not" being accessed
			Query query = entityManager1.createQuery("select student from StudentBatch student");
			List<StudentBatch> students = query.getResultList();

			for (StudentBatch student : students) {
				System.out.println("Students = " + student);
			}

			// Loading all the students lazily using @BatchSize(size=2); the guides
			// associated with students are being accessed
			query = entityManager1.createQuery("select student from StudentBatch student");
			students = query.getResultList();

			for (StudentBatch student : students) {
				if (student.getGuide() != null) {
					System.out.println("Students with batch size = " + student);
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