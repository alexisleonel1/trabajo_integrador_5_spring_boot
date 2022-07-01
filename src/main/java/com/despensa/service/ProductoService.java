package com.despensa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.despensa.model.Producto;
import com.despensa.repository.ProductoRepository;

@Service
@Validated
public class ProductoService implements BaseService<Producto>{
	
	@Autowired
	private ProductoRepository productoRepository;

	@Transactional
	@Override
	public boolean create(Producto p) {
		Producto pro = productoRepository.save(p);
		return pro != null? true : false;
	}

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> find(int id) {
		return productoRepository.findById(id);
	}

	@Override
	public boolean delete(int id) {
		if (!this.productoRepository.existsById(id)) 
			return false;
		productoRepository.deleteById(id);;
		return true;
	}

	@Override
	public boolean update(Producto t) {
		if (!this.productoRepository.existsById(t.getId())) 
			return false;
		this.productoRepository.save(t);
		return true;
	}
}
