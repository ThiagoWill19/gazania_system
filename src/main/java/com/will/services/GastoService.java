package com.will.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.entities.Gasto;
import com.will.entities.dtos.FiltrarGastoDto;
import com.will.entities.dtos.GastoDto;
import com.will.entities.enums.CategoriaDosGastos;
import com.will.exceptions.ExceptionError;
import com.will.exceptions.NotFoundException;
import com.will.repositories.GastoRepository;

@Service
public class GastoService {

	@Autowired
	private GastoRepository gastoRepository;
	
	public void save(Gasto gasto) {
		gastoRepository.save(gasto);
	}
	
	public void verificarGastoCombustivel(GastoDto gasto) {
		Gasto g = new Gasto();
		
		if(gasto.getCategoria() == CategoriaDosGastos.ABASTECIMENTO_CARRO
				|| gasto.getCategoria() == CategoriaDosGastos.ABASTECIMENTO_EQUIPAMENTO) {
			
			gasto.setValor(gasto.getLitragemAbastecida() * gasto.getValorLitro());
			gasto.setDescricaoGasto(gasto.getDescricaoGasto() + " - V.L: " +
			String.format("%.2f", gasto.getValorLitro()) + " / Litros: " +
					String.format("%.2f", gasto.getLitragemAbastecida()));
				
		}
		
		g.setData(gasto.getData());
		g.setValor(gasto.getValor());
		g.setDescricaoGasto(gasto.getDescricaoGasto());
		g.setCategoria(gasto.getCategoria());
		g.setPagamento(gasto.getPagamento());
		g.setDescricaoPagamento(gasto.getDescricaoPagamento());
		save(g);
	}
	
	public void deleteById(int id) throws ExceptionError {
		if(gastoRepository.existsById(id)) {
			gastoRepository.deleteById(id);
		}else throw new NotFoundException("Não há gasto cadastrado no sistema com o Id informado!");
	}
	
	public Gasto findById(int id) throws ExceptionError{
		if(!gastoRepository.existsById(id)) {
			return gastoRepository.findById(id).get();
		}else throw new NotFoundException("Não há gasto cadastrado no sistema com o Id informado!");
	}
	
	public List<Gasto> findByPeriodo(String periodo){
		
		if(periodo.equals("") || periodo == null) return findAll();
		
		List<Gasto> gastosFiltradosPorPeriodo = new ArrayList<>();
		
		if(periodo.contains("-")) {
			for(Gasto g : gastoRepository.findAll()) {
				
				String[] data2 = g.getData().split("-");
				
				if(periodo.equals((data2[0]+"-"+data2[1]))) {
					gastosFiltradosPorPeriodo.add(g);
				}
			}	
		}else {
			for(Gasto g : gastoRepository.findAll()) {
				
				String[] data2 = g.getData().split("-");
				
				if(periodo.equals(data2[0])) {
					gastosFiltradosPorPeriodo.add(g);
			}
		}

			
		}
		
		return gastosFiltradosPorPeriodo;
	}
	
	public List<Gasto> filtrarGastos(FiltrarGastoDto filtrarGastoDto){
		
		List<Gasto> gastosFiltrados = new ArrayList<>();
		List<Gasto> gastos = findByPeriodo(filtrarGastoDto.getPeriodo());
		
		if(!filtrarGastoDto.getCategoria().equals("TODOS")) {
			for(Gasto g : gastos) {
				if(g.getCategoria().toString().equals(filtrarGastoDto.getCategoria())) {
					gastosFiltrados.add(g);
				}
			}
			
			return gastosFiltrados;
		}else return gastos;
		
		
	}
	
	
	public List<Gasto> findAll(){
		return gastoRepository.findAll();
	}
	
	public List<Gasto> findByCategoria(String categoria){
		
		return gastoRepository.findByCategoria(CategoriaDosGastos.valueOf(categoria));
	}
	
	
}
