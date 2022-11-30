package com.prueba.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.api.entitys.Test;

public interface TestRepository extends JpaRepository<Test, Integer> {

}
