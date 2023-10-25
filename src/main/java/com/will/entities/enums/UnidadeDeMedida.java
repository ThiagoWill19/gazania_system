package com.will.entities.enums;

public enum UnidadeDeMedida {

	GRAMA("g"),KILO("Kg"), ML("mL"),LITRO("L"), UNIDADE("unidade(s)");
	
	private final String name;
	
	private UnidadeDeMedida(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
