package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Employee;
import com.hibernate.entity.EmployeeEntity;
import com.hibernate.entity.EmployeeStatus;
import com.hibernate.util.HibernateUtil;

public class MappinEnumClient {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// persisting
		EmployeeEntity employee1 = new EmployeeEntity("Josh Stockham", "2014JA11001", EmployeeStatus.FULL_TIME);
		EmployeeEntity employee2 = new EmployeeEntity("Ammie Corrio", "2014AI21543", EmployeeStatus.PART_TIME);
		EmployeeEntity employee3 = new EmployeeEntity("Ernie Stenseth", "2014ET25100", EmployeeStatus.CONTRACT);

		session.persist(employee1);
		session.persist(employee2);
		session.persist(employee3);

		EmployeeEntity employee = (EmployeeEntity) session.get(EmployeeEntity.class, employee1.getId());
		System.out.println(employee);

		session.getTransaction().commit();
		session.close();

		HibernateUtil.shutdown();
	}
}