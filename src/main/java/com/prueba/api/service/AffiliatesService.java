package com.prueba.api.service;

import java.util.List;

import com.prueba.api.dto.AffiliateDTO;

public interface AffiliatesService {

	List<AffiliateDTO> getList();

	AffiliateDTO getById(int id);

	void post(AffiliateDTO affiliateDTO);

	void put(AffiliateDTO affiliateDTO) throws Exception;

	void delete(int id);
}
