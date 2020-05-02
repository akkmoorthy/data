package com.hibernate.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String street;
	private String city;
	private String zipcode;

	public Address() {
	}

	public Address(String addressLine1, String addressLine2, String zipCode) {
		this.street = addressLine1;
		this.city = addressLine2;
		this.zipcode = zipCode;

	}
}