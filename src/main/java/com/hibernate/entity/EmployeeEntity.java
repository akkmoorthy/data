package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeEntity")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(name = "employee_id", unique = true)
	private String employeeId;

	@Enumerated
	// @Enumerated(EnumType.STRING)
	// @Enumerated(EnumType.ORDINAL)
	@Column(name = "employee_status")
	private EmployeeStatus employeeStatus;

	public EmployeeEntity() {
	}

	public EmployeeEntity(String name, String employeeId, EmployeeStatus employeeStatus) {
		this.name = name;
		this.employeeId = employeeId;
		this.employeeStatus = employeeStatus;
	}

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", employeeId=" + employeeId + ", employeeStatus="
				+ employeeStatus + "]";
	}

	public Long getId() {
		return id;
	}

}