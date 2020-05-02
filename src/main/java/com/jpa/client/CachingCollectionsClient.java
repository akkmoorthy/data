package com.jpa.client;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import com.jpa.entity.Guide;
import com.jpa.util.JPAUtil;

public class CachingCollectionsClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EntityManager emf = null;

		try {
			emf = JPAUtil.getEntityManagerFactory().createEntityManager();
			emf.getTransaction().begin();

			Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
			stats.setStatisticsEnabled(true);

			EntityManager em1 = JPAUtil.getEntityManagerFactory().createEntityManager();
			em1.getTransaction().begin();

			Guide guide1 = em1.find(Guide.class, 2L);
			int size1 = guide1.getStudents().size();

			em1.getTransaction().commit();
			em1.close();

			EntityManager em2 = JPAUtil.getEntityManagerFactory().createEntityManager();
			em2.getTransaction().begin();

			Guide guide2 = em2.find(Guide.class, 2L);
			int size2 = guide2.getStudents().size();

			em2.getTransaction().commit();
			em2.close();

			System.out.println("Guide: " + stats.getSecondLevelCacheStatistics("entity.Guide"));
			System.out.println("Student: " + stats.getSecondLevelCacheStatistics("entity.Student"));
		} catch (Exception e) {
			if (emf.getTransaction() != null) {
				emf.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (emf != null) {
				emf.close();
			}
			JPAUtil.shutdown();
		}

	}
}
