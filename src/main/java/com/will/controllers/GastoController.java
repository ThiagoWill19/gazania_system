package com.will.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.Gasto;
import com.will.entities.dtos.FiltrarGastoDto;
import com.will.entities.dtos.GastoDto;
import com.will.exceptions.ExceptionError;
import com.will.services.GastoService;

@Controller
@RequestMapping("/gastos")
public class GastoController {

	@Autowired
	private GastoService gastoService;
	
	@GetMapping
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("gastoPage");
		Gasto gasto = new Gasto();
		mv.addObject("gastoCad", gasto);
		mv.addObject("gastos",gastoService.findAll());
		return mv;
	}
	
	@PostMapping
	public String save(GastoDto gasto) {
		gastoService.verificarGastoCombustivel(gasto);
		return "redirect:/gastos";
	}
	
	@PostMapping("/filtrar")
	public ModelAndView buscaFiltrada(FiltrarGastoDto filtrarGastoDto ) {
		ModelAndView mv = new ModelAndView("gastoPage");
		Gasto gasto = new Gasto();
		mv.addObject("gastoCad", gasto);
		mv.addObject("gastos",gastoService.filtrarGastos(filtrarGastoDto));
		return mv;
	}
	
	@GetMapping("/apagar/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("errorView");

		try {
			gastoService.deleteById(id);
			mv.setViewName("redirect:/gastos");
		} catch (ExceptionError e) {
			mv.setViewName("errorVieww");
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}
	
}
