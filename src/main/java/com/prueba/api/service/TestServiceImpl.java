package com.prueba.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.api.dto.TestDTO;
import com.prueba.api.entitys.Test;
import com.prueba.api.repository.TestRepository;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestRepository testRepository;

	@Override
	public List<TestDTO> getList() {
		List<Test> lstTest = new ArrayList<Test>();
		lstTest = testRepository.findAll();

		List<TestDTO> lstTestDTO = new ArrayList<>();
		TestDTO testDTO;

		for (Test tst : lstTest) {
			testDTO = new TestDTO();

			testDTO.setId(tst.getId());
			testDTO.setName(tst.getName());
			testDTO.setDescription(tst.getDescription());

			lstTestDTO.add(testDTO);
		}
		return lstTestDTO;
	}

	@Override
	public TestDTO getById(int id) {
		Optional<Test> optionalTest = testRepository.findById(id);
		Test test = optionalTest.isPresent() ? optionalTest.get() : null;
		
		TestDTO testDTO = new TestDTO();

		testDTO.setId(test.getId());
		testDTO.setName(test.getName());
		testDTO.setDescription(test.getDescription());
		
		return testDTO;
	}

	@Override
	public void post(TestDTO testDTO) {
		Test test = new Test();

		test.setId(testDTO.getId());
		test.setName(testDTO.getName());
		test.setDescription(testDTO.getDescription());

		testRepository.save(test);
		
	}

	@Override
	public void delete(int id) {
		testRepository.deleteById(id);
		
	}

}
