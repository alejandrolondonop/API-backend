package com.prueba.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.api.dto.AppoinmentDTO;
import com.prueba.api.entitys.Affiliate;
import com.prueba.api.entitys.Appoinment;
import com.prueba.api.entitys.Test;
import com.prueba.api.repository.AppoinmentsRepository;

@Service
public class AppoinmentsServiceImpl implements AppoinmentsService {

	@Autowired
	private AppoinmentsRepository appoinmentRepository;

	private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatDateAppoinment = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

	@Override
	public List<AppoinmentDTO> getList() {
		List<Appoinment> lstAppoinment = new ArrayList<>();
		lstAppoinment = appoinmentRepository.findAll();

		List<AppoinmentDTO> lstAppoinmentDTO = new ArrayList<>();
		AppoinmentDTO appoinmentDTO;

		for (Appoinment ap : lstAppoinment) {
			appoinmentDTO = new AppoinmentDTO();

			appoinmentDTO.setDate(formatDate.format(ap.getDate()));
			appoinmentDTO.setHour(formatTime.format(ap.getHour()));
			appoinmentDTO.setId(ap.getId());
			appoinmentDTO.setIdAffiliates(ap.getAffiliate().getId());
			appoinmentDTO.setIdTest(ap.getTest().getId());
			lstAppoinmentDTO.add(appoinmentDTO);
		}
		return lstAppoinmentDTO;
	}

	@Override
	public AppoinmentDTO getById(int id) {
		Optional<Appoinment> optionalAppoinment = appoinmentRepository.findById(id);
		Appoinment appoinment = optionalAppoinment.isPresent() ? optionalAppoinment.get() : null;

		AppoinmentDTO appoinmentDTO = new AppoinmentDTO();

		appoinmentDTO.setDate(formatDate.format(appoinment.getDate()));
		appoinmentDTO.setHour(formatTime.format(appoinment.getHour()));
		appoinmentDTO.setId(appoinment.getId());
		appoinmentDTO.setIdAffiliates(appoinment.getAffiliate().getId());
		appoinmentDTO.setIdTest(appoinment.getTest().getId());

		return appoinmentDTO;
	}

	@Override
	public void post(AppoinmentDTO appoinmentDTO) {
		Appoinment appoinment = new Appoinment();

		appoinment.setId(appoinmentDTO.getId());
		try {
			appoinment.setDate(formatDate.parse(appoinmentDTO.getDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			appoinment.setHour(formatTime.parse(appoinmentDTO.getHour()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Affiliate affiliate = new Affiliate();
		Test test = new Test();
		test.setId(appoinmentDTO.getIdTest());
		affiliate.setId(appoinmentDTO.getIdAffiliates());
		appoinment.setAffiliate(affiliate);
		appoinment.setTest(test);

		appoinmentRepository.save(appoinment);

	}

	@Override
	public void put(AppoinmentDTO appoinmentDTO) throws Exception {
		AppoinmentDTO appoinment = getById(appoinmentDTO.getId());

		if (appoinment == null) {
			throw new Exception("El registro no se encontró");
		}

		Appoinment appoinmentEntity = new Appoinment();

		appoinmentEntity.setId(appoinmentDTO.getId());
		try {
			appoinmentEntity.setDate(formatDate.parse(appoinmentDTO.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			appoinmentEntity.setHour(formatTime.parse(appoinmentDTO.getHour()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Affiliate affiliate = new Affiliate();
		Test test = new Test();
		test.setId(appoinmentDTO.getIdTest());
		affiliate.setId(appoinmentDTO.getIdAffiliates());
		appoinmentEntity.setAffiliate(affiliate);
		appoinmentEntity.setTest(test);

		appoinmentRepository.save(appoinmentEntity);

	}

	@Override
	public void delete(int id) {
		appoinmentRepository.deleteById(id);

	}

	@Override
	public List<AppoinmentDTO> getByDate(String date) {
		List<Appoinment> lstAppoinment = new ArrayList<>();
		try {
			lstAppoinment = appoinmentRepository.findByDate(formatDateAppoinment.parse(date));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		List<AppoinmentDTO> lstAppoinmentDTO = new ArrayList<>();
		AppoinmentDTO appoinmentDTO;

		for (Appoinment ap : lstAppoinment) {
			appoinmentDTO = new AppoinmentDTO();

			appoinmentDTO.setDate(formatDate.format(ap.getDate()));
			appoinmentDTO.setHour(formatTime.format(ap.getHour()));
			appoinmentDTO.setId(ap.getId());
			appoinmentDTO.setIdAffiliates(ap.getAffiliate().getId());
			appoinmentDTO.setIdTest(ap.getTest().getId());
			lstAppoinmentDTO.add(appoinmentDTO);
		}
		return lstAppoinmentDTO;
	}

	@Override
	public List<AppoinmentDTO> getByAffiliate(int idAffiliate) {
		List<Appoinment> lstAppoinment = new ArrayList<>();
		Affiliate affiliate = new Affiliate();
		affiliate.setId(idAffiliate);
		lstAppoinment = appoinmentRepository.findByAffiliate(affiliate);

		List<AppoinmentDTO> lstAppoinmentDTO = new ArrayList<>();
		AppoinmentDTO appoinmentDTO;

		for (Appoinment ap : lstAppoinment) {
			appoinmentDTO = new AppoinmentDTO();

			appoinmentDTO.setDate(formatDate.format(ap.getDate()));
			appoinmentDTO.setHour(formatTime.format(ap.getHour()));
			appoinmentDTO.setId(ap.getId());
			appoinmentDTO.setIdAffiliates(ap.getAffiliate().getId());
			appoinmentDTO.setIdTest(ap.getTest().getId());

			lstAppoinmentDTO.add(appoinmentDTO);
		}
		return lstAppoinmentDTO;
	}

}
