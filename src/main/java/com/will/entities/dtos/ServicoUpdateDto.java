package com.will.entities.dtos;

import com.will.entities.ContratoDeServico;
import com.will.entities.enums.StatusDoPagamento;
import com.will.entities.enums.StatusDoServico;

public class ServicoUpdateDto {
	
	private ContratoDeServico contrato;
	
	private StatusDoServico statusDoServico;
	
	private StatusDoPagamento statusDoPagamento;

	

	public ContratoDeServico getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDeServico contrato) {
		this.contrato = contrato;
	}

	public StatusDoServico getStatusDoServico() {
		return statusDoServico;
	}

	public void setStatusDoServico(StatusDoServico statusDoServico) {
		this.statusDoServico = statusDoServico;
	}

	public StatusDoPagamento getStatusDoPagamento() {
		return statusDoPagamento;
	}

	public void setStatusDoPagamento(StatusDoPagamento statusDoPagamento) {
		this.statusDoPagamento = statusDoPagamento;
	}
	

}
