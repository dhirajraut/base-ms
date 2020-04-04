package com.galaxy.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Lombok Annotations. */
@Getter
@Setter
@NoArgsConstructor
@ToString

/* JPA Annotations. */
@Entity
@Table (name = "sample_entity")
public class SampleEntity {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column (nullable = false, name = "first_name")
	private String firstName;
	
	@Column (nullable = true, name = "last_name")
	private String lastName;
	
	@Column (nullable = true, name = "company_name")
	private String companyName;
	
	@Column (nullable = true)
	private String address;
	
	@Column (nullable = true)
	private String city;
	
	@Column (nullable = true)
	private String county;
	
	@Column (nullable = true)
	private String state;
	
	@Column (nullable = true)
	private int zip;
	
	@Column (nullable = true)
	private String phone1;
	
	@Column (nullable = true)
	private String phone2;

	@Column (nullable = false, unique = true)
	private String email;

	@Column (nullable = false)
	private String web;
}
