package com.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "guide")
@BatchSize(size = 2)
public class GuideBatch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "staff_id", nullable = false)
	private String staffId;

	private String name;
	private Integer salary;

	@OneToMany(mappedBy = "guide", cascade = { CascadeType.PERSIST }, fetch = FetchType.LAZY)
	private Set<StudentBatch> students = new HashSet<StudentBatch>();

	public GuideBatch() {
	}

	public GuideBatch(String staffId, String name, Integer salary) {
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
	}

	public Set<StudentBatch> getStudents() {
		return students;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public String getStaffId() {
		return staffId;
	}

	public Integer getSalary() {
		return salary;
	}

	public void addStudent(StudentBatch student) {
		students.add(student);
		student.setGuide(this);
	}
}
