package com.will.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.entities.Gasto;
import com.will.entities.enums.CategoriaDosGastos;

public interface GastoRepository extends JpaRepository<Gasto, Integer>{

	List<Gasto> findByCategoria(CategoriaDosGastos categoria);
	
}
