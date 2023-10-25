 package com.will.controllers;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.Demonstrativo;
import com.will.exceptions.ExceptionError;
import com.will.services.DemonstrativoService;

@Controller
@RequestMapping("/dre")
public class DemonstrativoController {
	
	@Autowired
	private DemonstrativoService demonstrativoService;
	
	@PostMapping()
	public ModelAndView getDemonstrativo(String periodo) {
		
		ModelAndView mv = new ModelAndView("drePage");
		mv.addObject("demonstrativo", demonstrativoService.montarDemosntrativo(periodo));
		return mv;
		
	}
	
	@PostMapping("/salvar")
	public ModelAndView save (Demonstrativo demonstrativo) {
		ModelAndView mv = new ModelAndView();

		System.out.println(demonstrativo.getPeriodo());
		try {
			demonstrativoService.save(demonstrativo);
			mv.setViewName("redirect:/dre");
		} catch (ExceptionError e) {
			mv.setViewName("errorView");
			mv.addObject("message",e.getMessage());
		}
		return mv;
	}
	
	@GetMapping()
	public ModelAndView getDre() {
		
		Date date = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		SimpleDateFormat sdf =  new SimpleDateFormat("YYYY-MM");
		
		ModelAndView mv = new ModelAndView("drePage");
		mv.addObject("demonstrativo", demonstrativoService.montarDemosntrativo(sdf.format(cal.getTime())));
		return mv;
	}
	
	@GetMapping("/{periodo}")
	public ModelAndView findPeriodo(@PathVariable("periodo") String periodo) {
		ModelAndView mv = new ModelAndView("drePage");
		mv.addObject("demonstrativo", demonstrativoService.montarDemosntrativo(periodo));
		return mv;
	}
	
	@PostMapping("/salvartxt")
	public ModelAndView salvarTxt( String periodo) {
		ModelAndView mv = new ModelAndView("drePage");
		mv.addObject("demonstrativo",demonstrativoService.montarDemosntrativo(periodo));
		mv.addObject("message" , "PDF do período " + periodo + " gerado com sucesso!");
		System.out.println(periodo);
		demonstrativoService.gerarArquivo(demonstrativoService.montarDemosntrativo(periodo));
		return mv;
	}
}
