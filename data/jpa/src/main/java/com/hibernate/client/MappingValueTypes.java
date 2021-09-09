package com.hibernate.client;

import org.hibernate.Session;

import com.hibernate.entity.Address;
import com.hibernate.entity.Friend;
import com.hibernate.util.HibernateUtil;

public class MappingValueTypes {
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// persisting
		Friend friend = new Friend("Mark Anderson", "markanderson@pluswhere.com");

		friend.getNicknames().add("Marky");
		friend.getNicknames().add("Marco");
		friend.getNicknames().add("Markster");

		// session.persist(friend);

		// collection of embeddable Address object
		/*
		 * Friend friend = new Friend("Mark Anderson", "markanderson@pluswhere.com");
		 */
		friend.getAddresses().add(new Address("street1", "city1", "zipcode1"));
		friend.getAddresses().add(new Address("street2", "city2", "zipcode2"));
		friend.getAddresses().add(new Address("street3", "city3", "zipcode3"));
		System.out.println("friend===" + friend);
		session.persist(friend);

		// retrieving
		Friend friendObj = (Friend) session.get(Friend.class, friend.getId());
		System.out.println(friendObj);

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();
	}
}
