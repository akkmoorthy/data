package com.jpa.client;

import javax.persistence.EntityManager;

import com.jpa.entity.Guide;
import com.jpa.util.JPAUtil;

public class SecondLevelCachingClient {
	public static void main(String[] args) {
		EntityManager emf = null;

		try {
			emf = JPAUtil.getEntityManagerFactory().createEntityManager();
			emf.getTransaction().begin();

			//System.out.println(emf.getCache().contains(Guide.class, 2L)); // Is Guide[id=2] available in second-level
																			// cache?
			Guide guide1 = emf.find(Guide.class, 2L);

			emf.getTransaction().commit();
			emf.close();

			EntityManager em2 = JPAUtil.getEntityManagerFactory().createEntityManager();
			em2.getTransaction().begin();

			//System.out.println(emf.getCache().contains(Guide.class, 2L)); // Is Guide[id=2] available in second-level
																			// cache?
			Guide guide2 = em2.find(Guide.class, 2L);

			em2.getTransaction().commit();
			em2.close();
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
