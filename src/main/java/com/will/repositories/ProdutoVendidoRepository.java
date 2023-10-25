package com.will.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.will.entities.ProdutoVendido;

@Repository
public interface ProdutoVendidoRepository extends JpaRepository<ProdutoVendido, Integer>{

}
