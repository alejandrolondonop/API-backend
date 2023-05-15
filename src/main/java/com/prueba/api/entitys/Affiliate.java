package com.prueba.api.entitys;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "affiliates", schema = "public")
public class Affiliate {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age",nullable = false )
	private int age;

	@Column(name = "email", nullable = false)
	private String email;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "affiliate")
	private List<Appoinment> lstAppoiment = new ArrayList<>();

	public Affiliate() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Appoinment> getLstAppoiment() {
		return lstAppoiment;
	}

	public void setLstAppoiment(List<Appoinment> lstAppoiment) {
		this.lstAppoiment = lstAppoiment;
	}
	
	

}
