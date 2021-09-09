package com.hibernate.client;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.hibernate.entity.Employee;
import com.hibernate.util.HibernateUtil;

public class GetAllData {
	public static void main(String[] args) throws Exception {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		CriteriaQuery<Employee> cq = session.getCriteriaBuilder().createQuery(Employee.class);
		cq.from(Employee.class);
		List<Employee> employeeList = session.createQuery(cq).getResultList();

		for (Employee employee : employeeList) {
			System.out.println("ID: " + employee.getId());
			System.out.println("Name: " + employee.getEmpName());
		}

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
		System.out.println("Data printed");
	}
}