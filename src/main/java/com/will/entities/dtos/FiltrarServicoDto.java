package com.will.entities.dtos;

public class FiltrarServicoDto {
	
	private String periodo;
	private String StatusDoServico;
	
	public FiltrarServicoDto() {
		
	}
	
	public FiltrarServicoDto(String periodo) {
		this.periodo = periodo;
		this.StatusDoServico = "TODOS";
	}
	
	
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getStatusDoServico() {
		return StatusDoServico;
	}
	public void setStatusDoServico(String statusDoServico) {
		StatusDoServico = statusDoServico;
	}
	
	

}
