package com.despensa.dto;

import java.util.Date;

public class VentasByDayDTO {

	private String fecha;
	private long cantidad;
	
	public VentasByDayDTO(Date fecha,long cantidad) {
		super();
		this.fecha = fecha.toString();
		this.cantidad = cantidad;
	}

	public String getFecha() {
		return fecha;
	}

	public long getCantidad() {
		return cantidad;
	}
	
}
