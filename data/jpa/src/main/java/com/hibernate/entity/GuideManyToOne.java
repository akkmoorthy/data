package com.hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Guide")
public class GuideManyToOne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "staff_id", nullable = false)
	private String staffId;

	private String name;
	private Integer salary;

	@OneToMany(mappedBy = "guide", cascade = { CascadeType.PERSIST })
	private Set<StudentManyToOne> students = new HashSet<StudentManyToOne>();

	public GuideManyToOne() {
	}

	public GuideManyToOne(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}

	public Set<StudentManyToOne> getStudents() {
		return students;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void addStudent(StudentManyToOne student) {
		students.add(student);
		student.setGuide(this);
	}

}