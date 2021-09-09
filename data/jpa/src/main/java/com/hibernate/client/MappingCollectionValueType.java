package com.hibernate.client;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.entity.Address;
import com.hibernate.entity.AddressValueType;
import com.hibernate.entity.User;
import com.hibernate.entity.UserCollectionCompositeTypes;
import com.hibernate.util.HibernateUtil;

public class MappingCollectionValueType {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Transaction transaction = session.beginTransaction();
			
			UserCollectionCompositeTypes user = new UserCollectionCompositeTypes();
		
			AddressValueType address = new AddressValueType();
			AddressValueType address2 = new AddressValueType();
			setAddressFields(address);
			setAddressFields2(address2);
			user.getAddress().add(address);
			user.getAddress().add(address2);
			setUserFields(user);

			session.save(user);
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
			HibernateUtil.shutdown();
		}
	}

	private static void setUserFields(UserCollectionCompositeTypes user) {
		user.setAge(22);
		user.setBirthDate(new Date());
		user.setCreatedBy("kmb");
		user.setCreatedDate(new Date());
		user.setEmailAddress("kmb385");
		user.setFirstName("Kevin");
		user.setLastName("bowersox");
		user.setLastUpdatedBy("kevin");
		user.setLastUpdatedDate(new Date());
	}

	private static void setAddressFields(AddressValueType address) {
		address.setAddressLine1("Line 1");
		address.setAddressLine2("Line 2");
		address.setCity("New York");
		address.setState("NY");
		address.setZipCode("12345");
	}

	private static void setAddressFields2(AddressValueType address) {
		address.setAddressLine1("Line 3");
		address.setAddressLine2("Line 4");
		address.setCity("Corning");
		address.setState("NY");
		address.setZipCode("12345");
	}
	
}