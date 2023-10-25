package com.will.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.entities.enums.EscalaDeServico;
import com.will.entities.enums.ServicoContratado;

@Entity
public class ContratoDeServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JsonIgnore
	private Servico servico;
	
	@Enumerated(EnumType.STRING)
	private ServicoContratado servicoContratado;
	
	@Enumerated(EnumType.STRING)
	private EscalaDeServico escala;
	
	@Enumerated(EnumType.STRING)
	private EscalaDeServico escalaPiscina;
	
	private double valorDaVisita;
	
	private double valorDaVisitaPiscina;

	
	public EscalaDeServico getEscalaPiscina() {
		return escalaPiscina;
	}

	public void setEscalaPiscina(EscalaDeServico escalaPiscina) {
		this.escalaPiscina = escalaPiscina;
	}

	public double getValorDaVisitaPiscina() {
		return valorDaVisitaPiscina;
	}

	public void setValorDaVisitaPiscina(double valorDaVisitaPiscina) {
		this.valorDaVisitaPiscina = valorDaVisitaPiscina;
	}
	
	public String getValorVisitaPiscinaToString() {
		return "R$ " + String.format("%.2f",valorDaVisitaPiscina);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public ServicoContratado getServicoContratado() {
		return servicoContratado;
	}

	public void setServicoContratado(ServicoContratado servicoContratado) {
		this.servicoContratado = servicoContratado;
	}

	public EscalaDeServico getEscala() {
		return escala;
	}

	public void setEscala(EscalaDeServico escala) {
		this.escala = escala;
	}

	public double getValorDaVisita() {
		return valorDaVisita;
	}
	
	public String getValorVisitaToString() {
		return "R$ " + String.format("%.2f",valorDaVisita);
	}

	public void setValorDaVisita(double valorDaVisita) {
		this.valorDaVisita = valorDaVisita;
	}
	
	
}
