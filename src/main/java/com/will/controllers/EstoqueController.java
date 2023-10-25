package com.will.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.Item;
import com.will.entities.enums.CategoriaItem;
import com.will.repositories.ItemRepository;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public ModelAndView getAll() {
		ModelAndView mv = new ModelAndView("estoquePage");
		mv.addObject("itens", itemRepository.findAll());
		return mv;
	}
	
	@PostMapping
	public String save(Item item) {
		itemRepository.save(item);
		return "redirect:/estoque";
	}
	
	
	@PostMapping("/buscar")
	public ModelAndView buscarPorCategoria(String categoria) {
		ModelAndView mv = new ModelAndView();

		if(categoria.equals("TODAS")){
			mv.setViewName("redirect:/estoque");
			return mv;
		}
		
		mv.setViewName("estoquePage");
		mv.addObject("itens", itemRepository
			.findByCategoria(CategoriaItem.valueOf(categoria)));

		return mv;
	}
	
	@GetMapping("/entrada/{id}")
	public String entrada(@PathVariable("id") int itemId) {
		
		Item item = itemRepository.findById(itemId).get();
		
		item.setQuantidade(item.getQuantidade() + 1);
		
		itemRepository.save(item);
		
		return "redirect:/estoque";
		
	}
	
	@GetMapping("/retirada/{id}")
	public String retirada(@PathVariable("id") int itemId) {
		
		Item item = itemRepository.findById(itemId).get();
		
		item.setQuantidade(item.getQuantidade() - 1);
		
		if(item.getQuantidade() <0) item.setQuantidade(0);
		
		itemRepository.save(item);
		
		return "redirect:/estoque";
		
	}
	
	@GetMapping("/remover/{id}")
	public String remover(@PathVariable("id") int itemId) {
		itemRepository.deleteById(itemId);
		
		return "redirect:/estoque";
	}
}
