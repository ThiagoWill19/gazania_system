package com.will.entities;

import java.text.DecimalFormat;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.entities.enums.UnidadeDeMedida;

@Entity
@Table(name = "produtos_vendidos")
public class ProdutoVendido implements Comparable<ProdutoVendido> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String produto;
	
	private double quantidade;
	
	@Enumerated(EnumType.STRING)
	private UnidadeDeMedida unidadeDeMedida;
	
	private double valor;
	
	private String data;
	
	@ManyToOne
	@JsonIgnore
	private Servico servico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(produto);
	}
	
	public String formatarDouble() {
	    if (quantidade == (long) quantidade) {
	        return String.format("%d", (long) quantidade);
	    } else {
	        DecimalFormat df = new DecimalFormat("#.##");
	        return df.format(quantidade);
	    }
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoVendido other = (ProdutoVendido) obj;
		return Objects.equals(produto, other.produto);
	}
	
	@Override
    public int compareTo(ProdutoVendido outro) {
        return this.produto.compareTo(outro.produto);
    }
	
	
}
