package com.will.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.will.entities.enums.CategoriaDosGastos;
import com.will.entities.enums.FormaDePagamento;

@Entity
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String data;
	private double valor;
	private String descricaoGasto;
	@Enumerated(EnumType.STRING)
	private CategoriaDosGastos categoria;
	@Enumerated(EnumType.STRING)
	private FormaDePagamento pagamento;
	private String descricaoPagamento;
	
	
	
	public int getId() {
		return id;
	} 	
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	
	public String getValorToString() {
		return "R$ " + String.format("%.2f", this.valor);
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricaoGasto() {
		return descricaoGasto;
	}
	public void setDescricaoGasto(String descricaoGasto) {
		this.descricaoGasto = descricaoGasto;
	}
	public CategoriaDosGastos getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaDosGastos categoria) {
		this.categoria = categoria;
	}
	public FormaDePagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(FormaDePagamento pagamento) {
		this.pagamento = pagamento;
	}
	public String getDescricaoPagamento() {
		return descricaoPagamento;
	}
	public void setDescricaoPagamento(String descricaoPagamento) {
		this.descricaoPagamento = descricaoPagamento;
	}
	
	
	
	
}
