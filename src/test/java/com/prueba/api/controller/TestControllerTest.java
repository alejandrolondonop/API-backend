package com.prueba.api.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.prueba.api.dto.TestDTO;
import com.prueba.api.service.TestService;

import lombok.var;

@ExtendWith(MockitoExtension.class)
class TestControllerTest {

	@InjectMocks
	private TestController testController = new TestController();

	@Mock
	private TestService testServiceMock;

	@Test
	public void testGetAllTest_NO_CONTENT() {
		when(testServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = testController.getAll();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testGetAllTest_OK() {
		var list = new ArrayList<TestDTO>();
		list.add(new TestDTO());

		when(testServiceMock.getList()).thenReturn(list);

		var response = testController.getAll();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testGetTestById_NOT_FOUND() {
		when(testServiceMock.getById(1)).thenReturn(null);

		var response = testController.getById(1);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	}

	@Test
	public void testGetTestById_OK() {

		TestDTO test = new TestDTO();
		when(testServiceMock.getById(1)).thenReturn(test);

		var response = testController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testPostTest_CREATED() {
		TestDTO affiliate = new TestDTO();
		
		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setDescription("Test description");
		
		
		
		var response = testController.post(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@Test
	public void testPostTest_NOT_FOUND() {
		TestDTO testDTO = new TestDTO();
		
		testDTO.setName(null);
		
		var response = testController.post(testDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testDeleteTest_OK() {
		int id = 1;
		
		var response = testController.delete(id);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		
	}

}
