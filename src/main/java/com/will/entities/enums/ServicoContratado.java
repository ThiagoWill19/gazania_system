package com.will.entities.enums;

public enum ServicoContratado {

	JARDINAGEM("Manutenção de Jardim"),
	PISCINA("Manutenção de Piscina"),
	JARDINAGEM_E_PISCINA("Manutenção de Jardim e Piscina");
	
	private final String name;
	
	private ServicoContratado(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
}
