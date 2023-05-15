package com.prueba.api.dto;




public class AppoinmentDTO {

	private int id;
	private String date;
	private String hour;
	private int idTest;
	private int idAffiliates;
	
	
	public AppoinmentDTO() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public int getIdTest() {
		return idTest;
	}


	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}


	public int getIdAffiliates() {
		return idAffiliates;
	}


	public void setIdAffiliates(int idAffiliates) {
		this.idAffiliates = idAffiliates;
	}
	
	
	
	
}
