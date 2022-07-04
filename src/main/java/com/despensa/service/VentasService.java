package com.despensa.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.despensa.dto.VentasByDayDTO;
import com.despensa.model.Cliente;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;
import com.despensa.repository.VentasRepository;

@Service
@Validated
public class VentasService implements IVentasService{

	@Autowired
	private VentasRepository ventasRepository;
	
	@Autowired
	private ProductoService productoService;
	
	@Transactional
	@Override
	public boolean create(Ventas v) {
		Producto p = v.getProducto();
		int cant = v.getCantidad()/2 >= 1? v.getCantidad()/2 : 1;
		if((cant > 0 && cant < 4) && p.getStock() >= cant) {
				p.setStock(p.getStock()-cant);
				productoService.update(p);
				return ventasRepository.save(v) != null? true : false;
		}
		return false;
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

	@Override
	public List<VentasByDayDTO> ventasByDay() {
		return ventasRepository.ventasByDay();
	}

	@Override
	public Producto bestProduct() {
		List<Producto> p = ventasRepository.bestProduct();
		if(p.size() > 0) {
			return p.get(0);
		}else {
			return null;
		}
	}
	
}