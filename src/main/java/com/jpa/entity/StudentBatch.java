package com.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "student")
public class StudentBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "enrollment_id", nullable = false)
	private String enrollmentId;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "guide_id")
	private GuideBatch guide;

	public StudentBatch() {
	}

	public StudentBatch(String enrollmentId, String name) {
		this.enrollmentId = enrollmentId;
		this.name = name;
	}

	public StudentBatch(String enrollmentId, String name, GuideBatch guide) {
		this.enrollmentId = enrollmentId;
		this.name = name;
		this.guide = guide;
	}

	public GuideBatch getGuide() {
		return guide;
	}

	public void setGuide(GuideBatch guide) {
		this.guide = guide;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(enrollmentId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StudentBatch))
			return false;
		StudentBatch other = (StudentBatch) obj;
		return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", enrollmentId=" + enrollmentId + ", name=" + name + ", guide=" + guide + "]";
	}
}