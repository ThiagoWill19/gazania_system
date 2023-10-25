package com.will.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.services.DashBoardService;

@Controller
@RequestMapping("/home")
public class DashBoardController {

	@Autowired
	private DashBoardService boardService;
	
	@GetMapping()
	public ModelAndView getAll() {
		Date d = new Date(System.currentTimeMillis());
		Calendar cal = new GregorianCalendar();
		cal.setTime(d);
		SimpleDateFormat sdf =  new SimpleDateFormat("YYYY-MM");
		
		ModelAndView mv =  new ModelAndView("homePage");
		mv.addObject(boardService.filtrarPorPeriodo(sdf.format(cal.getTime())));
		
		return mv;
	}
	
	@PostMapping()
	public ModelAndView findByPeriodo( String periodo) {
	
		ModelAndView mv =  new ModelAndView("homePage");
		mv.addObject(boardService.filtrarPorPeriodo(periodo));
		
		return mv;
	}
	
}
