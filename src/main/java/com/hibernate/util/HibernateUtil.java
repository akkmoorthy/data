package com.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.User;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static SessionFactory sessionFactory1;

	public static SessionFactory getSessionFactory() {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("main/resources/hibernate.cfg.xml").build();
				Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				sessionFactory = metaData.getSessionFactoryBuilder().build();
			}
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}
		return sessionFactory;
	}

	// Below is another way to build session factory
	public static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class);
			sessionFactory1 = configuration.buildSessionFactory(new StandardServiceRegistryBuilder().build());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("There was an error building the factory");
		}
		return sessionFactory1;
	}

	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		if (sessionFactory1 != null) {
			sessionFactory1.close();
		}
	}
}