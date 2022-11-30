package com.prueba.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.api.entitys.Affiliate;



public interface AffiliatesRepository  extends JpaRepository<Affiliate, Integer> {

}
