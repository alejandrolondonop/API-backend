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

import com.prueba.api.dto.TestDTO;
import com.prueba.api.service.TestService;

@RestController
@RequestMapping("/api/controller")
public class TestController {

	@Autowired
	TestService testService;

	@GetMapping("tests")
	public ResponseEntity<List<TestDTO>> getAll() {

		List<TestDTO> lstTest = testService.getList();
		if (lstTest.isEmpty()) {
			return new ResponseEntity<>(lstTest, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lstTest, HttpStatus.OK);
	}

	@GetMapping("tests/{id}")
	public ResponseEntity<TestDTO> getById(@PathVariable("id") int id) {

		try {
			TestDTO testDTO = testService.getById(id);
			return new ResponseEntity<>(testDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("tests")
	public ResponseEntity<Void> post(@RequestBody TestDTO testDTO) {
		try {
			testService.post(testDTO);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("tests")
	public ResponseEntity<Void> put(@RequestBody TestDTO testDTO) {
		try {
			testService.put(testDTO);
			return new ResponseEntity<>(null, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("tests/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			testService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
