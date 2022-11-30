package com.prueba.api.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.prueba.api.dto.AppoinmentDTO;
import com.prueba.api.service.AppoinmentsService;

@RestController
@RequestMapping("/api/controller")
public class AppoinmentController {

	@Autowired
	AppoinmentsService appoinmentsService;

	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatDateAppoinment = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

	@GetMapping("appoinments")
	public ResponseEntity<List<AppoinmentDTO>> getList() {

		List<AppoinmentDTO> lstAppoinment = appoinmentsService.getList();
		if (lstAppoinment.isEmpty()) {
			return new ResponseEntity<>(lstAppoinment, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstAppoinment, HttpStatus.OK);
	}

	@GetMapping("appoinments/{id}")
	public ResponseEntity<AppoinmentDTO> getById(@PathVariable("id") int id) {

		AppoinmentDTO appoinmentDTO = appoinmentsService.getById(id);

		if (appoinmentDTO == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(appoinmentDTO, HttpStatus.OK);

	}

	@PostMapping("appoinments")
	public ResponseEntity<Void> post(@RequestBody AppoinmentDTO appoinmentDTO) {
		try {
			appoinmentsService.post(appoinmentDTO);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("appoinments")
	public ResponseEntity<Void> put(@RequestBody AppoinmentDTO appoinmentDTO) {
		return post(appoinmentDTO);
	}

	@DeleteMapping("appoinments/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			appoinmentsService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@GetMapping("appoinments/date/{date}")
	public ResponseEntity<List<AppoinmentDTO>> getbydate(
			@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") String date) {

		List<AppoinmentDTO> lstAppoinmentDTO = appoinmentsService.getByDate(date);

		if (lstAppoinmentDTO.isEmpty()) {
			return new ResponseEntity<>(lstAppoinmentDTO, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstAppoinmentDTO, HttpStatus.OK);

	}

	@GetMapping("appoinments/affiliate/{idAffiliate}")
	public ResponseEntity<List<AppoinmentDTO>> getByIdAffiliate(@PathVariable("idAffiliate") int idAffiliate) {

		List<AppoinmentDTO> lstAppoinmentDTO = appoinmentsService.getByAffiliate(idAffiliate);
		if (lstAppoinmentDTO.isEmpty()) {
			return new ResponseEntity<>(lstAppoinmentDTO, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstAppoinmentDTO, HttpStatus.OK);

	}
}
