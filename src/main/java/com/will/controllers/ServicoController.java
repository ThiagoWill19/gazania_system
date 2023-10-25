package com.will.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.will.entities.ProdutoVendido;
import com.will.entities.Servico;
import com.will.entities.ServicoAdicional;
import com.will.entities.Visita;
import com.will.entities.VisitaPiscina;
import com.will.entities.dtos.FiltrarServicoDto;
import com.will.entities.dtos.NovoServicoDto;
import com.will.entities.dtos.ServicoUpdateDto;
import com.will.exceptions.ExceptionError;
import com.will.services.ClienteService;
import com.will.services.ServicoService;

@Controller
@RequestMapping("/servicos")
public class ServicoController {
	
	@Autowired
	private ServicoService servicoService;
	@Autowired
	private ClienteService clienteService;

	private final String ERROR_VIEW = "errorView";

	
	@PostMapping
	public ModelAndView save(NovoServicoDto novoServicoDto){
		ModelAndView mv = new ModelAndView();
		
		try {
			Servico servico = servicoService.save(novoServicoDto);
			mv.setViewName("redirect:/servicos/" + servico.getId());
		} catch (ExceptionError e ) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
			return mv;
		}

		return mv;
	}
	
	@PostMapping("/alterar/{id}")
	public ModelAndView update(@PathVariable("id") int id, ServicoUpdateDto servico){
		ModelAndView mv = new ModelAndView();
		
		try {
			servicoService.update(id, servico);
			mv.setViewName("redirect:/servicos/" + id); 
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}

	
	@GetMapping
	public ModelAndView findAll(){
		ModelAndView mv = new ModelAndView("servicoPage");

		mv.addObject("servicos",servicoService.findAll());
		mv.addObject("clientes",clienteService.findAll());
		return mv;
	}
	
	@PostMapping("/filtrar")
	public ModelAndView filtrarServicos(FiltrarServicoDto filtrar) {
		ModelAndView mv = new ModelAndView("servicoPage");

		mv.addObject("servicos", servicoService.filtrarServicos(filtrar));
		mv.addObject("clientes",clienteService.findAll());
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView findById(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("servicoDetail");

		try {
			mv.addObject("servico", servicoService.findById(id));
			return mv;
		} catch (ExceptionError e) {
			mv = new ModelAndView(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
			return mv;
		}
	}
	
	
	@GetMapping("/remover/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView();

		try {
			servicoService.deleteById(id);
			mv.setViewName("redirect:/servicos");
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}
	
	@PostMapping("/adicionarProduto/{servicoId}")
	public ModelAndView adicionarProduto(@PathVariable("servicoId") int id, ProdutoVendido produto) {
		ModelAndView mv = new ModelAndView();
		try {
			servicoService.addProduto(id, produto);
			mv.setViewName("redirect:/servicos/" + id);
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}

	@PostMapping("/adicionarServico/{servicoId}")
	public ModelAndView adicionarServico(@PathVariable("servicoId") int id, ServicoAdicional servicoAdicional){
		ModelAndView mv = new ModelAndView();
		
		try {
			servicoService.addServicoAdc(id, servicoAdicional);
			mv.setViewName("redirect:/servicos/"+ id);
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}
	
	@PostMapping("/adicionarVisita/{idServico}")
	public ModelAndView adicionarVisita(@PathVariable("idServico") int id, Visita visita) {
		ModelAndView mv = new ModelAndView();
		
		try {
			servicoService.addVisita(id, visita);
			mv.setViewName("redirect:/servicos/"+ id);
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}
	
	@PostMapping("/adicionarVisitaPiscina/{idServico}")
	public ModelAndView adicionarVisitaPiscina(@PathVariable("idServico") int id, VisitaPiscina visita) {
		ModelAndView mv = new ModelAndView();
		
		try {
			servicoService.addVisitaPiscina(id, visita);
			mv.setViewName("redirect:/servicos/"+ id);
		} catch (ExceptionError e) {
			mv.setViewName(ERROR_VIEW);
			mv.addObject("message", e.getMessage());
		}
		return mv;
	}
	
	@GetMapping("/removerProduto")
	public String removerProduto(@RequestParam(name = "idServico") int idServico,@RequestParam(name = "idProduto") int idProduto) {
		servicoService.removerProduto(idProduto);
		return "redirect:/servicos/"+ idServico;
		
	}
	
	@GetMapping("/removerServicoAdicional")
	public String removerServicoAdicional(@RequestParam(name = "idServico") int idServico,@RequestParam(name = "idServicoAdc") int idServicoAdc) {
		servicoService.removerServicoAdc(idServicoAdc);
		return "redirect:/servicos/"+ idServico;
		
	}
	
	@GetMapping("/removerVisita")
	public String removerVisita(@RequestParam(name = "idServico") int idServico, @RequestParam(name = "idVisita") int idVisita) {
		servicoService.removerVisita(idVisita);
		return "redirect:/servicos/" + idServico; 
	}
	
	@GetMapping("/removerVisitaPiscina")
	public String removerVisitaPiscina(@RequestParam(name = "idServico") int idServico, @RequestParam(name = "idVisita") int idVisita) {
		servicoService.removerVisitaPiscina(idVisita);
		return "redirect:/servicos/" + idServico; 
	}
	
	@GetMapping("/fechamento/{servicoId}")
	public String enviarFechamento(@PathVariable("servicoId") int id) {
		servicoService.enviarFechamentoPorEmail(id);
		return "redirect:/servicos/"+ id;
	}
	
	@GetMapping("/fechamentoPdf/{servicoId}")
	public String gerarFechamentoPdf (@PathVariable("servicoId") int id) {
		servicoService.gerarPdfDoFechamento(id);
		return "redirect:/servicos/"+id;
	}
}
