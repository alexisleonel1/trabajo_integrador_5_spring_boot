package com.despensa.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Ventas {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		@ManyToOne
		private Cliente cliente;
		
		@ManyToOne
		private Producto producto;
		

		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Calendar fecha;
		
		@Column
		private int cantidad;
		
		@Column
		private double total;
		
		public Ventas() {
			super();
		}
		
		public Ventas(Cliente cliente, Producto producto, Calendar fecha, int cantidad) {
			super();
			this.cliente=cliente;
			this.producto=producto;
			this.fecha=fecha;
			this.cantidad=cantidad;
			this.total=cantidad*producto.getPrecio();
		}
		
		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int c) {
			if((this.producto.getStock()>=c)&&(c<=3)) {
				if(this.cantidad<c){
					this.producto.setStock(this.producto.getStock()-(c-this.cantidad));
				}else if (this.cantidad>c){
					this.producto.setStock(this.producto.getStock()+(this.cantidad-c));
				}
				this.cantidad = c;
			}
		}

		public int getId() {
			return id;
		}

		public Cliente getCliente() {
			return cliente;
		}

		public Producto getProducto() {
			return producto;
		}

		public Calendar getFecha() {
			return fecha;
		}

		public double getTotal() {
			return total;
		}

}
