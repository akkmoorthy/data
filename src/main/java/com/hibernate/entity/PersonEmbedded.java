
package com.hibernate.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_embedded")
public class PersonEmbedded {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	//If the AttributeOverrides is removed then it will throw a SQLGrammarException
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "home_street")),
			@AttributeOverride(name = "city", column = @Column(name = "home_city")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode")) })
	private Address homeAddress;

	//If the AttributeOverrides is removed then it will throw a SQLGrammarException
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
			@AttributeOverride(name = "city", column = @Column(name = "billing_city")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "billing_zipcode")) })
	private Address billingAddress;

	public PersonEmbedded() {
	}

	public PersonEmbedded(String name, Address homeAddress, Address billingAddress) {
		this.name = name;
		this.homeAddress = homeAddress;
		this.billingAddress = billingAddress;
	}

}
