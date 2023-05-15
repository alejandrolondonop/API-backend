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

@Table(name = "test", schema = "public")
public class Test {

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
	private List<Appoinment> lstAppoiment = new ArrayList<>();

	public Test() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Appoinment> getLstAppoiment() {
		return lstAppoiment;
	}

	public void setLstAppoiment(List<Appoinment> lstAppoiment) {
		this.lstAppoiment = lstAppoiment;
	}
	
	
	

}
