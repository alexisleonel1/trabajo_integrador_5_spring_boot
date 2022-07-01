package com.despensa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.despensa.model.Ventas;
import com.despensa.repository.VentasRepository;

@Service
@Validated
public class VentasService implements BaseService<Ventas>{

	@Autowired
	private VentasRepository ventasRepository;
	
	@Transactional
	@Override
	public boolean create(Ventas v) {
		Ventas c = ventasRepository.save(v);
		return c != null? true : false;
	}

	@Override
	public List<Ventas> findAll() {
		return ventasRepository.findAll();
	}

	@Override
	public Optional<Ventas> find(int id) {
		return ventasRepository.findById(id);
	}

	@Override
	public boolean update(Ventas v) {
		if (!this.ventasRepository.existsById(v.getId())) 
			return false;
		this.ventasRepository.save(v);
		return true;
	}

	@Override
	public boolean delete(int id) {
		if (!this.ventasRepository.existsById(id)) 
			return false;
		ventasRepository.deleteById(id);
		return true;
	}
	
}