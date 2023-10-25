package com.will.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.DadosFixos;
import com.will.repositories.DadosFixosRepository;

@Controller
@RequestMapping("/config")
public class DadosFixosController {
	
	@Autowired
	private DadosFixosRepository dadosFixosRepository;
	
	@GetMapping()
	public ModelAndView find() {
		ModelAndView mv = new ModelAndView("configPage");
		mv.addObject("dados",dadosFixosRepository.findAll());
		return mv;
	}
	
	@PostMapping()
	public String save(DadosFixos dados) {
		dadosFixosRepository.save(dados);
		return "redirect:/config";
	}
}
