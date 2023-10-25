package com.will.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	boolean existsByNome(String nome);
	
	List<Cliente>findByNomeContainingIgnoreCase(String name);
}
