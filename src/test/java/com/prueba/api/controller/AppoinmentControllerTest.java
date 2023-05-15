package com.prueba.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.http.ResponseEntity;

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
	public void testGetAllAppoinmentsNoContent() {
		when(appoinmentServiceMock.getList()).thenReturn(Collections.emptyList());

		var response = (var) appoinmentController.getList();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetAllAppoinmentsOk() {
		var list = (var) new ArrayList<AppoinmentDTO>();
		((List<AppoinmentDTO>) list).add(new AppoinmentDTO());

		when(appoinmentServiceMock.getList()).thenReturn((List<AppoinmentDTO>) list);

		var response = (var) appoinmentController.getList();
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetAppoinmentByIdNotFound() {
		doThrow(new RuntimeException()).when(appoinmentServiceMock).getById(1);

		var response = (var) appoinmentController.getById(1);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetAppoinmentByIdOk() {

		AppoinmentDTO appoinment = new AppoinmentDTO();
		when(appoinmentServiceMock.getById(1)).thenReturn(appoinment);

		var response = (var) appoinmentController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}

	@Test
	public void testGetAppoinmentByDateNotFound() {
		when(appoinmentServiceMock.getByDate("")).thenReturn(Collections.emptyList());

		var response = (var) appoinmentController.getbydate("");
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetAppoinmentByDateOk() {

		List<AppoinmentDTO> appoinment = new ArrayList<AppoinmentDTO>();
		appoinment.add(new AppoinmentDTO());
		when(appoinmentServiceMock.getByDate("2022-05-10")).thenReturn(appoinment);

		var response = (var) appoinmentController.getbydate("2022-05-10");
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPostAppoinmentCreated() {
		AppoinmentDTO appoinment = new AppoinmentDTO();

		appoinment.setId(0);
		appoinment.setDate("2022-05-10");
		appoinment.setHour("13:45");
		appoinment.setIdAffiliates(1);
		appoinment.setIdTest(1);

		var response = (var) appoinmentController.post(appoinment);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPostAppoinmentNotFound() {
		doNothing().when(appoinmentServiceMock).post(null);
		AppoinmentDTO appoinment = new AppoinmentDTO();

		appoinment.setId(0);
		appoinment.setDate("2022-05-10");
		appoinment.setHour("13:45");
		appoinment.setIdAffiliates(1);
		appoinment.setIdTest(1);

		var response = (var) appoinmentController.post(appoinment);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPutAppoinmentCreated() {
		AppoinmentDTO appoinment = new AppoinmentDTO();

		appoinment.setId(0);
		appoinment.setDate("2022-05-10");
		appoinment.setHour("13:45");
		appoinment.setIdAffiliates(1);
		appoinment.setIdTest(1);

		var response = (var) appoinmentController.post(appoinment);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPutAppoinmentNotFound() {
		doNothing().when(appoinmentServiceMock).post(null);
		AppoinmentDTO appoinment = new AppoinmentDTO();

		appoinment.setId(0);
		appoinment.setDate("2022-05-10");
		appoinment.setHour("13:45");
		appoinment.setIdAffiliates(1);
		appoinment.setIdTest(1);

		var response = (var) appoinmentController.post(appoinment);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}
	
	
	@Test
	public void testDeleteAppoinmenttOk() {
		int id = 1;

		var response = (var) appoinmentController.delete(id);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());

	}

	@Test
	public void testDeleteAppoinmentNoContent() {
		doThrow(new RuntimeException()).when(appoinmentServiceMock).delete(1);

		var response = (var) appoinmentController.delete(2);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<AppoinmentDTO>>) response).getStatusCode());
	}

}
