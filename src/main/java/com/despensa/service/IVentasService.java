package com.despensa.service;


import java.util.Date;
import java.util.List;

import com.despensa.model.Ventas;

public interface IVentasService extends BaseService<Ventas> {

	public List<Ventas> ventasByDay(Date f);
	
	
}
