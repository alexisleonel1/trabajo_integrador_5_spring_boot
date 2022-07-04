package com.despensa.service;

import java.util.List;

import com.despensa.dto.VentasByDayDTO;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;

public interface IVentasService extends BaseService<Ventas> {

	public List<VentasByDayDTO> ventasByDay();
	
	public Producto bestProduct();

}
