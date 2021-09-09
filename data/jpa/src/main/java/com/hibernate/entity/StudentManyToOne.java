
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
@Table(name="Student")
public class StudentManyToOne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "enrollment_id", nullable = false)
	private String enrollmentId;

	private String name;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "guide_id")
	private GuideManyToOne guide;

	public StudentManyToOne() {
	}

	public StudentManyToOne(String enrollmentId, String name, GuideManyToOne guide) {
		this.enrollmentId = enrollmentId;
		this.name = name;
		this.guide = guide;
	}

	public GuideManyToOne getGuide() {
		return guide;
	}

	public void setGuide(GuideManyToOne guide) {
		this.guide = guide;
	}

}