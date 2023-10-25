package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.VisitaPiscina;

@Repository
public interface VisitaPiscinaRepository extends JpaRepository<VisitaPiscina, Integer>{

}
