package com.prueba.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.api.dto.AffiliateDTO;
import com.prueba.api.service.AffiliatesService;

@RestController
@RequestMapping("/api/controller")
public class AffiliatesController {

	@Autowired
	AffiliatesService affiliatesService;

	@GetMapping("affiliates")
	public ResponseEntity<List<AffiliateDTO>> getAll() {

		List<AffiliateDTO> lstAffiliate = affiliatesService.getList();

		if (lstAffiliate.isEmpty()) {
			return new ResponseEntity<>(lstAffiliate, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstAffiliate, HttpStatus.OK);
	}

	@GetMapping("affiliates/{id}")
	public ResponseEntity<AffiliateDTO> getById(@PathVariable("id") int id) {

		AffiliateDTO affiliateDTO = affiliatesService.getById(id);

		if (affiliateDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(affiliateDTO, HttpStatus.OK);

	}

	@PostMapping("affiliates")
	public ResponseEntity<Void> post(@RequestBody AffiliateDTO affiliateDTO) {
		try {
			affiliatesService.post(affiliateDTO);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("affiliates")
	public ResponseEntity<Void> put(@RequestBody AffiliateDTO affiliateDTO) {
		return post(affiliateDTO);
	}

	@DeleteMapping("affiliates/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			affiliatesService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
