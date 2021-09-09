package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Employee;
import com.hibernate.util.HibernateUtil;

public class CreateData {
	public static void main(String[] args) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Employee emp = new Employee();
		emp.setEmpName("Deepak Mohan");
		emp.setEmpMobileNos("1111111");
		emp.setEmpAddress("Chennai - India");
		session.save(emp);
		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		System.out.println("Successfully inserted");
	}

}