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
	public void testGetAllTestNoContent() {
		when(testServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = (var) testController.getAll();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

	@Test
	public void testGetAllTestOk() {
		var list = (var) new ArrayList<TestDTO>();
		((List<TestDTO>) list).add(new TestDTO());

		when(testServiceMock.getList()).thenReturn((List<TestDTO>) list);

		var response = (var) testController.getAll();
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetTestByIdNotFound() {
		doThrow(new RuntimeException()).when(testServiceMock).getById(2);

		var response = (var) testController.getById(2);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetTestByIdOk() {

		TestDTO test = new TestDTO();
		when(testServiceMock.getById(1)).thenReturn(test);

		var response = (var) testController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

	@Test
	public void testPostTestCreated() {
		TestDTO affiliate = new TestDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setDescription("Test description");

		var response = (var) testController.post(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

	@Test
	public void testPostTestNotFound() {
		doNothing().when(testServiceMock).post(null);
		TestDTO testDTO = new TestDTO();

		testDTO.setName(null);

		var response = (var) testController.post(testDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPutTestCreated() {
		TestDTO affiliate = new TestDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setDescription("Test description");

		var response = (var) testController.put(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

	@Test
	public void testPutTestNotFound() throws Exception {
		doNothing().when(testServiceMock).put(null);
		TestDTO testDTO = new TestDTO();

		testDTO.setName(null);

		var response = (var) testController.put(testDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

	@Test
	public void testDeleteTestOk() {
		int id = 1;

		var response = (var) testController.delete(id);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());

	}

	@Test
	public void testDeleteTestNoContent() {
		doThrow(new RuntimeException()).when(testServiceMock).delete(1);

		var response = (var) testController.delete(2);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<TestDTO>>) response).getStatusCode());
	}

}
