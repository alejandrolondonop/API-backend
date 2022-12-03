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

import lombok.Data;

@Entity
@Data
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

}
