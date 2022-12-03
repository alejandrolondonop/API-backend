package com.prueba.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.prueba.api.dto.AffiliateDTO;
import com.prueba.api.dto.AppoinmentDTO;
import com.prueba.api.service.AppoinmentsService;

import lombok.var;

@ExtendWith(MockitoExtension.class)
class AppoinmentControllerTest {

	@InjectMocks
	private AppoinmentController appoinmentController = new AppoinmentController();
	
	@Mock
	private AppoinmentsService appoinmentServiceMock;
	
	@Test
	public void testGetAllAppoinments_NO_CONTENT() {
		when(appoinmentServiceMock.getList()).thenReturn(Collections.emptyList());
		
		var response = appoinmentController.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}
	
	@Test
	public void testGetAllAppoinments_OK() {
		var list = new ArrayList<AppoinmentDTO>();
		list.add(new AppoinmentDTO());
		
		when(appoinmentServiceMock.getList()).thenReturn(list);
		
		var response = appoinmentController.getList();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}
	
	@Test
	public void testGetAppoinmentById_NOT_FOUND() {
		when(appoinmentServiceMock.getById(1)).thenReturn(null);
		
		var response = appoinmentController.getById(1);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		
	}
	
	@Test
	public void testGetAppoinmentById_OK() {
		
		AppoinmentDTO appoinment = new AppoinmentDTO(); 
		when(appoinmentServiceMock.getById(1)).thenReturn(appoinment);
		
		var response = appoinmentController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testGetAppoinmentByDate_NOT_FOUND() {
		when(appoinmentServiceMock.getByDate("")).thenReturn(Collections.emptyList());
		
		var response = appoinmentController.getbydate("");
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		
	}
	
	@Test
	public void testGetAppoinmentByDate_OK() {
		
		List<AppoinmentDTO> appoinment = new ArrayList<AppoinmentDTO>(); 
		appoinment.add(new AppoinmentDTO());
		when(appoinmentServiceMock.getByDate("2022-05-10")).thenReturn(appoinment);
		
		var response = appoinmentController.getbydate("2022-05-10");
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
