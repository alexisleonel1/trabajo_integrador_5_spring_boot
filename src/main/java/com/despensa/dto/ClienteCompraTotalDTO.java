package com.despensa.dto;

public class ClienteCompraTotalDTO {

	private int id;
	private String nombre;
	private String apellido;
	private double total;
	
	public ClienteCompraTotalDTO(String nombre,String apellido,int id, double total) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.total = total;
	}

	public String getNombre() {
		return nombre;
	}
	
	public int getId() {
		return id;
	}

	public String getApellido() {
		return apellido;
	}

	public double getTotal() {
		return total;
	}
}
