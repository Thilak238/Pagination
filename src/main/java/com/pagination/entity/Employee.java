package com.pagination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
	
	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "city")
	private String city;
}
