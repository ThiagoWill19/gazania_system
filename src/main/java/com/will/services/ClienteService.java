package com.will.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.will.entities.Cliente;
import com.will.entities.dtos.ClienteDto;
import com.will.exceptions.NotFoundException;
import com.will.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente save(ClienteDto clienteDto) {
		Cliente c = new Cliente();
		c.setNome(clienteDto.getNome());
		c.setEmail(clienteDto.getEmail());
		c.setWhatsapp(clienteDto.getWhatsapp());
		c.setEndereco(clienteDto.getEndereco());
		
		return clienteRepository.save(c);
		
	}
	
	public Cliente update(ClienteDto clienteDto, int id) {
		Cliente c = clienteRepository.findById(id).get();
		c.setNome(clienteDto.getNome());
		c.setEmail(clienteDto.getEmail());
		c.setWhatsapp(clienteDto.getWhatsapp());
		clienteDto.getEndereco().setId(c.getEndereco().getId());
		c.setEndereco(clienteDto.getEndereco());
		
		return clienteRepository.save(c);
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Cliente findById(int id) throws NotFoundException {
		
		if(clienteRepository.existsById(id)) {
			return clienteRepository.findById(id).get();
		}else {
			throw new NotFoundException("Nenhum cliente cadastrado com ID: " + id);
		}
		
	}
	
	public List<Cliente> findByName(String nome) {
		
		return clienteRepository.findByNomeContainingIgnoreCase(nome);
		
	}
	
	public void delete(int id) throws NotFoundException {
		
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		}else {
			throw new NotFoundException("Nada encontrado com ID: " + id);
		}
		
	}
	
	
}
