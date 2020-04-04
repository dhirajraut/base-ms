package com.galaxy.spring.model;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* Lombok Annotations. */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SampleEntityVO {

	@ApiParam(hidden = true)
	private long id;

	private String firstName;
	
	private String lastName;

	private String companyName;
	
	private String address;
	
	private String city;
	
	private String country;
	
	private String state;
	
	private int zip;
	
	private String phone1;
	
	private String phone2;
	
	private String email;
	
	private String web;
}
