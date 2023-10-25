package com.will.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "demonstrativos")
public class Demonstrativo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String periodo;
	
	private double pagamentoPrestadorServico;
	private double prolabore;
	private double rgps;
	
	private double abastecimentoCarro;
	private double abastecimentoEquipamento;
	private double compraProduto;
	private double compraEquipamento;
	private double compraAutomovel;
	private double manutencaCarro;
	private double manutencaoEquipameto;
	private double retirada;
	
	private double servicosContrarado;
	private double servicosAdicionais;
	private double vendas;
	
	private double totalGastos;
	private double totalReceita;
	private double lucro;
	
	private boolean statusDosServicos = false;
	private boolean salvo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public double getPagamentoPrestadorServico() {
		return pagamentoPrestadorServico;
	}
	public void setPagamentoPrestadorServico(double pagamentoPrestadorServico) {
		this.pagamentoPrestadorServico = pagamentoPrestadorServico;
	}
	public double getProlabore() {
		return prolabore;
	}
	public void setProlabore(double prolabore) {
		this.prolabore = prolabore;
	}
	public double getRgps() {
		return rgps;
	}
	public void setRgps(double rgps) {
		this.rgps = rgps;
	}
	public double getAbastecimentoCarro() {
		return abastecimentoCarro;
	}
	public void setAbastecimentoCarro(double abastecimentoCarro) {
		this.abastecimentoCarro = abastecimentoCarro;
	}
	public double getAbastecimentoEquipamento() {
		return abastecimentoEquipamento;
	}
	public void setAbastecimentoEquipamento(double abastecimentoEquipamento) {
		this.abastecimentoEquipamento = abastecimentoEquipamento;
	}
	public double getCompraProduto() {
		return compraProduto;
	}
	public void setCompraProduto(double compraProduto) {
		this.compraProduto = compraProduto;
	}
	public double getCompraEquipamento() {
		return compraEquipamento;
	}
	public void setCompraEquipamento(double compraEquipamento) {
		this.compraEquipamento = compraEquipamento;
	}
	public double getCompraAutomovel() {
		return compraAutomovel;
	}
	public void setCompraAutomovel(double compraAutomovel) {
		this.compraAutomovel = compraAutomovel;
	}
	public double getManutencaCarro() {
		return manutencaCarro;
	}
	public void setManutencaCarro(double manutencaCarro) {
		this.manutencaCarro = manutencaCarro;
	}
	public double getManutencaoEquipameto() {
		return manutencaoEquipameto;
	}
	public void setManutencaoEquipameto(double manutencaoEquipameto) {
		this.manutencaoEquipameto = manutencaoEquipameto;
	}
	public double getRetirada() {
		return retirada;
	}
	public void setRetirada(double retirada) {
		this.retirada = retirada;
	}
	public double getServicosContrarado() {
		return servicosContrarado;
	}
	public void setServicosContrarado(double servicosContrarado) {
		this.servicosContrarado = servicosContrarado;
	}
	public double getServicosAdicionais() {
		return servicosAdicionais;
	}
	public void setServicosAdicionais(double servicosAdicionais) {
		this.servicosAdicionais = servicosAdicionais;
	}
	public double getVendas() {
		return vendas;
	}
	public void setVendas(double vendas) {
		this.vendas = vendas;
	}
	public double getTotalGastos() {
		return totalGastos;
	}
	public void setTotalGastos(double totalGastos) {
		this.totalGastos = totalGastos;
	}
	public String getGastoToString() {
		return "R$ " + String.format("%.2f", totalGastos);
	}
	public double getTotalReceita() {
		return totalReceita;
	}
	public void setTotalReceita(double totalReceita) {
		this.totalReceita = totalReceita;
	}
	public String getReceitaToString() {
		return "R$ " + String.format("%.2f", totalReceita);
	}
	public double getLucro() {
		return lucro;
	}
	public void setLucro(double lucro) {
		this.lucro = lucro;
	}
	
	public String getLucroToString() {
		return "R$ " + String.format("%.2f", lucro);
	}
	public boolean isStatusDosServicos() {
		return statusDosServicos;
	}
	public void setStatusDosServicos(boolean statusDosServiços) {
		this.statusDosServicos = statusDosServiços;
	}
	public boolean isSalvo() {
		return salvo;
	}
	public void setSalvo(boolean salvo) {
		this.salvo = salvo;
	}
	
	
	
	
	
	
	

}
