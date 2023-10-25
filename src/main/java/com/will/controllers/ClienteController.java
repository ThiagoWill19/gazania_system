package com.will.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.Cliente;
import com.will.entities.dtos.ClienteDto;
import com.will.exceptions.ExceptionError;
import com.will.services.ClienteService;
import com.will.services.ServicoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping
	public ModelAndView findAll() {
		ClienteDto clienteDto = new ClienteDto();
		ModelAndView mv =  new ModelAndView("clientePage");
		mv.addObject("clientes", clienteService.findAll());
		mv.addObject("clienteDto",clienteDto);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("clienteDetail");
	
		try {
			mv.addObject("cliente", clienteService.findById(id));
			mv.addObject("servicosEmAberto",servicoService.procurarServicoAbertoPorCliente(id));
			return mv;
		} catch (ExceptionError e) {
			mv = new ModelAndView("errorView");
			mv.addObject("message", e.getMessage());
			return mv;
		}
	}
	
	@PostMapping("/buscar")
	public ModelAndView findByNome(String nome) {
		ClienteDto clienteDto = new ClienteDto();
		ModelAndView mv = new ModelAndView("clientePage");
		mv.addObject("clienteDto",clienteDto);
		mv.addObject("clientes", clienteService.findByName(nome));
		return mv;
		
	}
	
	
	@PostMapping
	public String save (@Valid ClienteDto clienteDto){

		clienteService.save(clienteDto);
		
		return "redirect:/clientes";
	}
	
	@PostMapping("/update")
	public String update(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto();
		clienteDto.setNome(cliente.getNome());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setWhatsapp(cliente.getWhatsapp());
		clienteDto.setEndereco(cliente.getEndereco());
		
		clienteService.update(clienteDto, cliente.getId());
		
		return "redirect:/clientes/" + cliente.getId();
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id){
		
		try {
			clienteService.delete(id);
			return "redirect:/clientes";
			
		} catch (ExceptionError e) {
			return "redirect:/clientes";
		}
		
	}

}
