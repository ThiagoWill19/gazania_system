package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.ServicoAdicional;

@Repository
public interface ServicoAdicionalRepository extends JpaRepository<ServicoAdicional, Integer>{

}
