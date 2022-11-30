package com.prueba.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.api.entitys.Affiliate;
import com.prueba.api.entitys.Appoinment;





public interface AppoinmentsRepository extends JpaRepository<Appoinment, Integer>{
	
	@Query("from Appoinment a where DATE(a.date) = :date")
	public List<Appoinment> findByDate(@Param("date") Date date);

	public List<Appoinment> findByAffiliate(Affiliate idAffiliate);
	
}
