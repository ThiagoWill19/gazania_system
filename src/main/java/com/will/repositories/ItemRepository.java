package com.will.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.will.entities.Item;
import com.will.entities.enums.CategoriaItem;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	List<Item> findByCategoria(CategoriaItem categoria);

}
