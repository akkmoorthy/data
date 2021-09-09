
package com.hibernate.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Guide;
import com.hibernate.entity.Student;
import com.hibernate.util.HibernateUtil;

public class ManyToOneClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			Guide guide = new Guide("2000MO10789", "Mike Lawson", 1000);
			Student student1 = new Student("2014JT501231", "Mohan Kumar", guide);
			Student student2 = new Student("2014JT501232", "Suresh Mohan", guide);
			session.save(guide);
			session.save(student1);
			session.save(student2);

			Guide guide1 = (Guide) session.get(Guide.class, 7L);
			Student student3 = new Student("2014JT501233", "John Smith", guide1);

			session.save(guide1);
			session.save(student3);

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