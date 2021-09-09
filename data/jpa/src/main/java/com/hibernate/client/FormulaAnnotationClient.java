package com.hibernate.client;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import com.hibernate.entity.UserFormula;
import com.hibernate.util.HibernateUtil;

public class FormulaAnnotationClient {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.getTransaction().begin();

			UserFormula user = new UserFormula();
			user.setBirthDate(getMyBirthday());
			user.setCreatedBy("kevin");
			user.setCreatedDate(new Date());
			user.setEmailAddress("kmb385@yahoo.com");
			user.setFirstName("Kevin");
			user.setLastName("Bowersox");
			user.setLastUpdatedBy("kevin");
			user.setLastUpdatedDate(new Date());

			session.save(user);
			session.getTransaction().commit();
			
			session.refresh(user);
			
			System.out.println(user.getAge());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
	
	private static Date getMyBirthday(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1988);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DATE, 10);
		return calendar.getTime();
	}

}
