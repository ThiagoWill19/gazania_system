package com.will.entities.dtos;

import com.will.entities.Gasto;

public class GastoDto extends Gasto{

	private double valorLitro = 0;
	private double litragemAbastecida;
	
	public GastoDto(double valorLitro, double litragemAbastecida) {
		super();
		this.valorLitro = valorLitro;
		this.litragemAbastecida = litragemAbastecida;
	}

	public double getValorLitro() {
		return valorLitro;
	}

	public void setValorLitro(double valorLitro) {
		this.valorLitro = valorLitro;
	}

	public double getLitragemAbastecida() {
		return litragemAbastecida;
	}

	public void setLitragemAbastecida(double litragemAbastecida) {
		this.litragemAbastecida = litragemAbastecida;
	}
	
	
	
}
