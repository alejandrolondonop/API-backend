package com.prueba.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.api.dto.AffiliateDTO;
import com.prueba.api.entitys.Affiliate;
import com.prueba.api.repository.AffiliatesRepository;

@Service
public class AffiliatesServiceImpl implements AffiliatesService {

	@Autowired
	private AffiliatesRepository affiliatesRepository;

	@Override
	public List<AffiliateDTO> getList() {
		List<Affiliate> lstAffiliate = new ArrayList<Affiliate>();
		lstAffiliate = affiliatesRepository.findAll();

		List<AffiliateDTO> lstAffiliateDTO = new ArrayList<>();
		AffiliateDTO affiliateDTO;

		for (Affiliate afi : lstAffiliate) {
			affiliateDTO = new AffiliateDTO();

			affiliateDTO.setId(afi.getId());
			affiliateDTO.setName(afi.getName());
			affiliateDTO.setAge(afi.getAge());
			affiliateDTO.setEmail(afi.getEmail());

			lstAffiliateDTO.add(affiliateDTO);
		}
		return lstAffiliateDTO;
	}

	@Override
	public AffiliateDTO getById(int id) {
		Optional<Affiliate> optionalAffiliate = affiliatesRepository.findById(id);
		Affiliate affiliate = optionalAffiliate.isPresent() ? optionalAffiliate.get() : null;

		AffiliateDTO affiliateDTO = new AffiliateDTO();

		affiliateDTO.setId(affiliate.getId());
		affiliateDTO.setName(affiliate.getName());
		affiliateDTO.setAge(affiliate.getAge());
		affiliateDTO.setEmail(affiliate.getEmail());

		return affiliateDTO;
	}

	@Override
	public void post(AffiliateDTO affiliateDTO) {
		Affiliate affiliate = new Affiliate();

		affiliate.setId(affiliateDTO.getId());
		affiliate.setName(affiliateDTO.getName());
		affiliate.setAge(affiliateDTO.getAge());
		affiliate.setEmail(affiliateDTO.getEmail());

		affiliatesRepository.save(affiliate);
	}

	@Override
	public void delete(int id) {
		affiliatesRepository.deleteById(id);

	}

	@Override
	public void put(AffiliateDTO affiliateDTO) throws Exception {
		AffiliateDTO affiliate = getById(affiliateDTO.getId());

		if (affiliate == null) {
			throw new Exception("El registro no se encontró");
		}

		Affiliate affiliateEntity = new Affiliate();

		affiliateEntity.setId(affiliateDTO.getId());
		affiliateEntity.setName(affiliateDTO.getName());
		affiliateEntity.setAge(affiliateDTO.getAge());
		affiliateEntity.setEmail(affiliateDTO.getEmail());

		affiliatesRepository.save(affiliateEntity);

	}

}
