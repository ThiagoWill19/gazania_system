package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.Demonstrativo;

@Repository
public interface DemonstrativoRepository extends JpaRepository<Demonstrativo, Integer> {
	
	Demonstrativo findByPeriodo(String periodo);
}
