package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer>{

}
