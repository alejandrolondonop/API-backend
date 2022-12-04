package com.prueba.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
		var list = new ArrayList<AffiliateDTO>();
		list.add(new AffiliateDTO());

		when(affiliateServiceMock.getList()).thenReturn(list);

		var response = affiliatesController.getAll();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testGetAllAffiliatesNoContent() {
		when(affiliateServiceMock.getList()).thenReturn(Collections.emptyList());
		var response = affiliatesController.getAll();
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testGetAffiliateByIdNotFound() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).getById(2);

		var response = affiliatesController.getById(2);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	public void testGetAffiliateByIdOk() {

		AffiliateDTO affiliate = new AffiliateDTO();
		when(affiliateServiceMock.getById(1)).thenReturn(affiliate);

		var response = affiliatesController.getById(1);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testPostAffiliatesCreated() {
		AffiliateDTO affiliate = new AffiliateDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setAge(0);
		affiliate.setEmail("mailtest");

		var response = affiliatesController.post(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testPostAffiliatesNotFound() {
		doNothing().when(affiliateServiceMock).post(null);
		AffiliateDTO affiliateDTO = new AffiliateDTO();

		affiliateDTO.setId(0);
		affiliateDTO.setName(null);
		affiliateDTO.setAge(0);
		affiliateDTO.setEmail(null);

		var response = affiliatesController.post(affiliateDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testPutAffiliatesCreated() {
		AffiliateDTO affiliate = new AffiliateDTO();

		affiliate.setId(0);
		affiliate.setName("test");
		affiliate.setAge(0);
		affiliate.setEmail("mailtest");

		var response = affiliatesController.put(affiliate);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	public void testPutAffiliatesNotFound() throws Exception {
		doNothing().when(affiliateServiceMock).put(null);
		AffiliateDTO affiliateDTO = new AffiliateDTO();

		affiliateDTO.setId(0);
		affiliateDTO.setName(null);
		affiliateDTO.setAge(0);
		affiliateDTO.setEmail(null);

		var response = affiliatesController.put(affiliateDTO);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	

	@Test
	public void testDeleteOk() {
		int id = 1;

		var response = affiliatesController.delete(id);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testDeleteNoContent() {
		doThrow(new RuntimeException()).when(affiliateServiceMock).delete(2);

		var response = affiliatesController.delete(2);
		Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

	}

}
