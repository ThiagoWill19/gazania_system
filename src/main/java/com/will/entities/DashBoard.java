package com.will.entities;

import java.util.List;

import com.will.entities.enums.StatusDoPagamento;
import com.will.entities.enums.StatusDoServico;

public class DashBoard {
	
	private String periodo;
	
	private List<Gasto> gastos;
	private double totalGastos;
	
	private List<Servico>serviços;
	private double totalServicos;
	private int quantidadeServicosAbertos;
	private int quantidadeServicosFinalizados;
	private int quantidadeServicosFinalizadosAguardandoPagamento;
	
	private int quantidadeClientes;
	
	
	
	private double statusAtual;
	
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public List<Gasto> getGastos() {
		return gastos;
	}

	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}

	public double getTotalGastos() {
		
		for(Gasto g : gastos) {
			totalGastos += g.getValor();
		}
		
		return totalGastos;
	}
	
	public String getTotalGastosToString() {
		return "R$ " + String.format("%.2f", getTotalGastos());
	}

	public void setTotalGastos(double totalGastos) {
		this.totalGastos = totalGastos;
	}

	public List<Servico> getServiços() {
		return serviços;
	}

	public void setServiços(List<Servico> serviços) {
		this.serviços = serviços;
	}

	public double getTotalServicos() {
		
		for(Servico s : serviços) {
			totalServicos += s.getValor();
		}
		
		
		return totalServicos;
	}
	
	public String getTotalServicosToString() {
		return "R$ " + String.format("%.2f", getTotalServicos());
	}

	public void setTotalServicos(double totalServicos) {
		this.totalServicos = totalServicos;
	}

	public double getStatusAtual() {

		statusAtual = totalServicos - totalGastos;
		
		return statusAtual;
	}
	
	public String getStatusAtualToString() {
		return "R$ " + String.format("%.2f", getStatusAtual());
	}

	public void setStatusAtual(double statusAtual) {
		this.statusAtual = statusAtual;
	}

	public int getQuantidadeServicosAbertos() {
		
		for(Servico s : getServiços()) {
			if(s.getStatusDoServico() == StatusDoServico.EM_ABERTO) {
				this.quantidadeServicosAbertos ++;
			}
			
		}
		
		return this.quantidadeServicosAbertos;
	}

	public void setQuantidadeServicosAbertos(int quantidadeServicosAbertos) {
		this.quantidadeServicosAbertos = quantidadeServicosAbertos;
	}

	public int getQuantidadeServicosFinalizados() {
		
		for(Servico s : getServiços()) {
	
			if(s.getStatusDoServico() == StatusDoServico.FINALIZADO) {
				this.quantidadeServicosFinalizados ++;
			}
		}
		
		return this.quantidadeServicosFinalizados;
	}

	public void setQuantidadeServicosFinalizados(int quantidadeServicosFinalizados) {
		this.quantidadeServicosFinalizados = quantidadeServicosFinalizados;
	}

	public int getQuantidadeServicosTotais() {
		
		int total = serviços.size();
		
		return total;
	}

	public int getQuantidadeServicosFinalizadosAguardandoPagamento() {
		
		for(Servico s : getServiços()) {
			
			if(s.getStatusDoServico() == StatusDoServico.FINALIZADO && s.getStatusDoPagamento() == StatusDoPagamento.PENDENTE) {
				this.quantidadeServicosFinalizadosAguardandoPagamento  ++;
			}
		}
		
		return quantidadeServicosFinalizadosAguardandoPagamento;
	}

	public void setQuantidadeServicosFinalizadosAguardandoPagamento(int quantidadeServicosFinalizadosAguardandoPagamento) {
		this.quantidadeServicosFinalizadosAguardandoPagamento = quantidadeServicosFinalizadosAguardandoPagamento;
	}

	public int getQuantidadeClientes() {
		return quantidadeClientes;
	}

	public void setQuantidadeClientes(int quantidadeClientes) {
		this.quantidadeClientes = quantidadeClientes;
	}
	
	
	
	

}
