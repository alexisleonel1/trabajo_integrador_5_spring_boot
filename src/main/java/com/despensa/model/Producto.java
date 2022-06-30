package com.despensa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {
	
	@Id
	private int id;
	
	public Producto() {
		super();
	}

	public int getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getStock() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void restarStock(int cantidad) {
		
	}

	public void sumarStock(int i) {
		// TODO Auto-generated method stub
		
	}

}
