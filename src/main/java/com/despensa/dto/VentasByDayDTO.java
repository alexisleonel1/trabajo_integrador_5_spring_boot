package com.despensa.dto;

import java.util.Calendar;

public class VentasByDayDTO {

	private Calendar fecha;
	private long cantidad;
	
	public VentasByDayDTO(Calendar fecha,long cantidad) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public long getCantidad() {
		return cantidad;
	}
	
}
