package com.prueba.api.service;

import java.util.List;

import com.prueba.api.dto.AppoinmentDTO;

public interface AppoinmentsService {

	List<AppoinmentDTO> getList();

	AppoinmentDTO getById(int id);

	void post(AppoinmentDTO appoinmentDTO);

	void put(AppoinmentDTO appoinmentDTO) throws Exception;

	void delete(int id);

	List<AppoinmentDTO> getByDate(String date);

	List<AppoinmentDTO> getByAffiliate(int idAffiliate);

}
