package com.jpa.client;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import com.jpa.entity.Student;
import com.jpa.util.JPAUtil;

public class CachingAssociationsClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManager em1 = null;

		try {
			em1 = JPAUtil.getEntityManagerFactory().createEntityManager();
			em1.getTransaction().begin();

			Statistics stats = em1.unwrap(SessionFactory.class).getStatistics();
			stats.setStatisticsEnabled(true);

			Student student1 = em1.find(Student.class, 4L);
			String guide1Name = student1.getGuide().getName();

			em1.getTransaction().commit();
			em1.close();

			EntityManager em2 = JPAUtil.getEntityManagerFactory().createEntityManager();
			em2.getTransaction().begin();

			Student student2 = em2.find(Student.class, 4L);
			String guide2Name = student2.getGuide().getName();

			em2.getTransaction().commit();
			em2.close();

			System.out.println("Student: " + stats.getSecondLevelCacheStatistics("entity.Student"));
			System.out.println("Guide: " + stats.getSecondLevelCacheStatistics("entity.Guide"));
		} catch (Exception e) {
			if (em1.getTransaction() != null) {
				em1.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (em1 != null) {
				em1.close();
			}
			JPAUtil.shutdown();
		}

	}
}
