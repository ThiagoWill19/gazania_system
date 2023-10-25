package com.will.entities.dtos;

import com.will.entities.ContratoDeServico;

public class NovoServicoDto {

	private String periodo;
	private int clienteId;
	private ContratoDeServico contrato;
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public ContratoDeServico getContrato() {
		return contrato;
	}
	public void setContrato(ContratoDeServico contrato) {
		this.contrato = contrato;
	}
	
	
	
}
