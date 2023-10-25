package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.DadosFixos;

@Repository
public interface DadosFixosRepository extends JpaRepository<DadosFixos, Integer>{

}
