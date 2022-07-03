package com.despensa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.despensa.dto.ClienteCompraTotalDTO;
import com.despensa.model.Cliente;
import com.despensa.repository.ClienteRepository;

@Service
@Validated
public class ClienteService implements IClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	@Override
	public boolean create(Cliente cliente) {
		Cliente c = clienteRepository.save(cliente);
		return c != null? true : false;
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> find(int id) {
		return clienteRepository.findById(id);
	}

	@Override
	public boolean update(Cliente cliente) {
		if (!this.clienteRepository.existsById(cliente.getID())) 
			return false;
		this.clienteRepository.save(cliente);
		return true;
	}

	@Override
	public boolean delete(int id) {
		if (!this.clienteRepository.existsById(id)) 
			return false;
		clienteRepository.deleteById(id);
		return true;
	}

	@Override
	public List<ClienteCompraTotalDTO> getClientesByVentas() {
		return clienteRepository.getClientesByVentas();
	}
	
}