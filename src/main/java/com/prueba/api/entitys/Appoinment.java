package com.prueba.api.entitys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "appoinments", schema = "public")
public class Appoinment {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "hour")
	private Date hour;
	
	@JoinColumn(name = "id_affiliate")
	@ManyToOne(fetch = FetchType.LAZY)
	private Affiliate affiliate;
	
	@JoinColumn(name = "id_test")
	@ManyToOne(fetch = FetchType.LAZY)
	private Test test;
	
	
	
}
