package com.will.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{
	
	List<Servico> findByPeriodoContaining(String Periodo);

}
