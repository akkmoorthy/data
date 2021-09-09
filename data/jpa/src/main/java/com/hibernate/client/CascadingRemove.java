package com.hibernate.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Guide;
import com.hibernate.entity.StudentCascade;
import com.hibernate.util.HibernateUtil;

public class CascadingRemove {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			// persisting Student object
			Guide guide = new Guide("2000IM10901", "Henegoan", 2000);
			StudentCascade student = new StudentCascade("2014AL50456", "Ram George", guide);
			session.persist(student);

			// deleting Student object
			StudentCascade studentCascade = (StudentCascade) session.get(StudentCascade.class, 32L);
			session.delete(studentCascade);

			txn.commit();
		} catch (Exception e) {
			if (txn != null) {
				txn.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		HibernateUtil.shutdown();
	}
}
