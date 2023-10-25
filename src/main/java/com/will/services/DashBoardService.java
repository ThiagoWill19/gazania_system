package com.will.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.entities.DashBoard;
import com.will.entities.Gasto;
import com.will.entities.Servico;
import com.will.entities.dtos.FiltrarServicoDto;

@Service
public class DashBoardService {

	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public DashBoard filtrarPorPeriodo(String periodo) {
		
		List<Servico> servicosDoPeriodo = servicoService.filtrarServicos(new FiltrarServicoDto(periodo));
		List<Gasto> gastosDoPeriodo = gastoService.findByPeriodo(periodo);
		
		DashBoard dashBoard = new DashBoard();
		dashBoard.setGastos(gastosDoPeriodo);
		dashBoard.setServiços(servicosDoPeriodo);
		dashBoard.setPeriodo(periodo);
		dashBoard.setQuantidadeClientes(clienteService.findAll().size());
		
		return dashBoard;

	}
	
}
