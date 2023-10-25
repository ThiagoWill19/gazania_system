package com.will.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.will.entities.Endereco;

public class ClienteDto {

	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;
	@NotBlank(message = "Campo email é obrigatório")
	private String email;
	@NotBlank(message = "Campo whatsapp é obrigatório")
	private String whatsapp;
	@NotNull(message = "Campo endereço é obrigatório")
	private Endereco endereco;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	
}
