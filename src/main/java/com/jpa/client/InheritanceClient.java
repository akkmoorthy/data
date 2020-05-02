
package com.jpa.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.jpa.entity.Animal;
import com.jpa.entity.Cat;
import com.jpa.entity.Dog;
import com.jpa.util.JPAUtil;

public class InheritanceClient {
	public static void main(String[] args) {

		EntityManager entityManager = null;

		try {
			entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
			entityManager.getTransaction().begin();

			// Persisting a Cat and a Dog object
			Cat cat = new Cat();
			cat.setName("Lucy");
			Dog dog = new Dog();
			dog.setName("Oliver");
			entityManager.persist(cat);
			entityManager.persist(dog);

			// Polymorphic Query
			Query query = entityManager.createQuery("select animal from Animal animal");
			List<Animal> animals = query.getResultList();
			for (Animal animal : animals) {
				System.out.println(animal);
			}

			// Querying Derived Class (Dog)
			List<Dog> dogs = entityManager.createQuery("select dog from Dog dog").getResultList();
			for (Dog d : dogs) {
				System.out.println(d);
			}

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		JPAUtil.shutdown();
	}
}