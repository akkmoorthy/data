package com.hibernate.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.GuideManyToOne;
import com.hibernate.entity.StudentManyToOne;
import com.hibernate.util.HibernateUtil;

public class BidirectionalManytoOneClient {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction txn = session.getTransaction();
		try {
			txn.begin();

			/*
			 * GuideManyToOne guide1 = new GuideManyToOne("2000MO10789", "Mike Lawson",
			 * 10000); GuideManyToOne guide2 = new GuideManyToOne("2000IM10901", "Ian Lamb",
			 * 20000);
			 * 
			 * StudentManyToOne student1 = new StudentManyToOne("2014JT501231",
			 * "John Smith", guide1); StudentManyToOne student2 = new
			 * StudentManyToOne("2014AL504562", "Amy Gill", guide1);
			 * 
			 * guide1.getStudents().add(student1); guide1.getStudents().add(student2);
			 * 
			 * session.persist(guide1); session.persist(guide2);
			 */

			// Updating inverse end
			/*
			 * GuideManyToOne guide = (GuideManyToOne) session.get(GuideManyToOne.class,
			 * 62L); StudentManyToOne student = (StudentManyToOne)
			 * session.get(StudentManyToOne.class, 67L); guide.getStudents().add(student);
			 */

			// Updating owner
			/*
			 * GuideManyToOne guide = (GuideManyToOne) session.get(GuideManyToOne.class,
			 * 62L); StudentManyToOne student = (StudentManyToOne)
			 * session.get(StudentManyToOne.class, 67L); student.setGuide(guide);
			 */

			// Updating inverse end (after adding addStudent(Student) in Guide entity)
			/*GuideManyToOne guide = (GuideManyToOne) session.get(GuideManyToOne.class, 65L);
			StudentManyToOne student = (StudentManyToOne) session.get(StudentManyToOne.class, 67L);
			guide.addStudent(student);*/

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