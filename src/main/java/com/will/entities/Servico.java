	package com.will.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.entities.enums.ServicoContratado;
import com.will.entities.enums.StatusDoPagamento;
import com.will.entities.enums.StatusDoServico;

@Entity
@Table(name = "servicos")
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Cliente cliente;
	
	private String periodo;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "servico")
	private ContratoDeServico contrato;
	
	@Enumerated(EnumType.STRING)
	private StatusDoServico statusDoServico;
	
	@Enumerated(EnumType.STRING)
	private StatusDoPagamento statusDoPagamento;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
	private List<ProdutoVendido> produtosVendidos;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
	private List<ServicoAdicional> servicosAdicionais;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
	private List<Visita> visitasFeitas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "servico")
	private List<VisitaPiscina> visitasFeitasPiscina;
	
	
	private double valor;
	
	private boolean fechamentoEnviado = false;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	public List<ProdutoVendido> getProdutosVendidos() {
		return produtosVendidos;
	}

	public void setProdutosVendidos(List<ProdutoVendido> produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}
	

	public List<ServicoAdicional> getServicosAdicionais() {
		return servicosAdicionais;
	}

	public void setServicosAdicionais(List<ServicoAdicional> servicosAdicionais) {
		this.servicosAdicionais = servicosAdicionais;
	}

	public double getValor() {
		start();
		return valor;
	}
	
	public String getValorToString() {
		return "R$ " + String.format("%.2f", getValor());
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public ContratoDeServico getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDeServico contrato) {
		this.contrato = contrato;
	}

	public List<Visita> getVisitasFeitas() {
		return visitasFeitas;
	}

	public void setVisitasFeitas(List<Visita> visitasFeitas) {
		this.visitasFeitas = visitasFeitas;
	}

	public List<VisitaPiscina> getVisitasFeitasPiscina() {
		return visitasFeitasPiscina;
	}

	public void setVisitasFeitasPiscina(List<VisitaPiscina> visitasFeitasPiscina) {
		this.visitasFeitasPiscina = visitasFeitasPiscina;
	}

	public boolean isFechamentoEnviado() {
		return fechamentoEnviado;
	}

	public void setFechamentoEnviado(boolean fechamentoEnviado) {
		this.fechamentoEnviado = fechamentoEnviado;
	}

	private void start() {
		
		valor = 0;
		
		if(produtosVendidos != null) {
			for(ProdutoVendido p : produtosVendidos) {
				valor += p.getValor();
			}
		}
		
		if(servicosAdicionais != null) {
			for(ServicoAdicional s : servicosAdicionais) {
				valor += s.getValor();
			}
		}
		
		if(contrato.getServicoContratado() == ServicoContratado.JARDINAGEM && visitasFeitas != null) {
			
			double valorDoServicoContratado = contrato.getValorDaVisita() * visitasFeitas.size();
			 valor += valorDoServicoContratado;
			
		}
		
		if(contrato.getServicoContratado() == ServicoContratado.PISCINA) {
			
			 valor += contrato.getValorDaVisita();
		}
		
		if(contrato.getServicoContratado() == ServicoContratado.JARDINAGEM_E_PISCINA) {
			
			 valor += contrato.getValorDaVisitaPiscina();
			 
			 double valorDoServicoContratado = contrato.getValorDaVisita() * visitasFeitas.size();
			 valor += valorDoServicoContratado;
		}
		
	}
	
	
}
