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
import com.prueba.api.service.AffiliatesService;

import lombok.var;

@ExtendWith(MockitoExtension.class)
class AffiliatesControllerTest {

	@InjectMocks
	private AffiliatesController affiliatesController = new AffiliatesController();

	@Mock
	private AffiliatesService affiliateServiceMock;

	@Test
	public void testGetAllAffiliatesOk() {
		var list = (var) new ArrayList<AffiliateDTO>();
		((ArrayList<AffiliateDTO>) list).add(new AffiliateDTO());

		when(affiliateServiceMock.getList()).thenReturn((List<AffiliateDTO>) list);

		var response = (var) affiliatesController.getAll();
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());

	}

	@Test
	public void testGetAllAffiliatesNoContent() {
		when(affiliateServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = (var) affiliatesController.getAll();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}

	@Test
	public void testGetAffiliateByIdNotFound() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).getById(2);

		var response = (var) affiliatesController.getById(2);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}

	@Test
	public void testGetAffiliateByIdOk() {

		AffiliateDTO affiliate = new AffiliateDTO();
		when(affiliateServiceMock.getById(1)).thenReturn(affiliate);

		var response = (var) affiliatesController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());

	}

	@Test
	public void testPostAffiliatesCreated() {
		AffiliateDTO affiliate = new AffiliateDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setAge(0);
		affiliate.setEmail("mailtest");

		var response = (var) affiliatesController.post(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}

	@Test
	public void testPostAffiliatesNotFound() {
		doNothing().when(affiliateServiceMock).post(null);
		AffiliateDTO affiliateDTO = new AffiliateDTO();

		affiliateDTO.setId(0);
		affiliateDTO.setName(null);
		affiliateDTO.setAge(0);
		affiliateDTO.setEmail(null);

		var response = (var) affiliatesController.post(affiliateDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}
	
	@Test
	public void testPutAffiliatesCreated() {
		AffiliateDTO affiliate = new AffiliateDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setAge(0);
		affiliate.setEmail("mailtest");

		var response = (var) affiliatesController.put(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}

	@Test
	public void testPutAffiliatesNotFound() throws Exception {
		doNothing().when(affiliateServiceMock).put(null);
		AffiliateDTO affiliateDTO = new AffiliateDTO();

		affiliateDTO.setId(0);
		affiliateDTO.setName(null);
		affiliateDTO.setAge(0);
		affiliateDTO.setEmail(null);

		var response = (var) affiliatesController.put(affiliateDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());
	}
	
	

	@Test
	public void testDeleteOk() {
		int id = 1;

		var response = (var) affiliatesController.delete(id);
		Assertions.assertEquals(HttpStatus.OK, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());

	}

	@Test
	public void testDeleteNoContent() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).delete(2);

		var response = (var) affiliatesController.delete(2);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, ((ResponseEntity<List<AffiliateDTO>>) response).getStatusCode());

	}

}
