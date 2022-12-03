package com.prueba.api.service;

import java.util.List;

import com.prueba.api.dto.TestDTO;

public interface TestService {

	List<TestDTO> getList();

	TestDTO getById(int id);

	void post(TestDTO testDTO);
	
	void put (TestDTO testDTO) throws Exception;

	void delete(int id);
}
