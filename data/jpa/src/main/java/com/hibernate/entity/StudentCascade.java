package com.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class StudentCascade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "enrollment_id", nullable = false)
	private String enrollmentId;

	private String name;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "guide_id")
	private Guide guide;

	public StudentCascade() {
	}

	public StudentCascade(String enrollmentId, String name, Guide guide) {
		this.enrollmentId = enrollmentId;
		this.name = name;
		this.guide = guide;
	}

}