package com.hibernate.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;

	@ElementCollection
	@CollectionTable(name = "friend_nickname", joinColumns = @JoinColumn(name = "friend_id"))
	@Column(name = "nickname")
	private Collection<String> nicknames = new ArrayList<String>();

	// collection of embeddable Address object [with default address specfic column
	// names being overriden using @AttributeOverride]
	@ElementCollection
	@CollectionTable(name = "friend_address", joinColumns = @JoinColumn(name = "friend_id"))
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "address_street")),
			@AttributeOverride(name = "city", column = @Column(name = "address_city")),
			@AttributeOverride(name = "zipcode", column = @Column(name = "address_zipcode")) })
	private Collection<Address> addresses = new ArrayList<Address>();

	public Friend() {
	}

	public Friend(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", name=" + name + ", email=" + email + ", addresses=" + addresses + "]";
	}

	public Collection<String> getNicknames() {
		return nicknames;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public Long getId() {
		return id;
	}

}