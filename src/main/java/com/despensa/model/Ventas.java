package com.despensa.model;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Ventas {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		
		@ManyToOne
		private Cliente cliente;
		
		@ManyToOne
		private Producto producto;
		
		@Column
		private Timestamp fecha;
		
		@Column
		private int cantidad;
		
		@Column
		private int total;
		
		public Ventas() {
			super();
		}
		
		public Ventas(Cliente cliente, Producto producto, Timestamp fecha, int cantidad) {
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
					this.producto.restarStock(c-this.cantidad);
				}else if (this.cantidad>c){
					this.producto.sumarStock(this.cantidad-c);
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

		public Timestamp getFecha() {
			return fecha;
		}

		public int getTotal() {
			return total;
		}

}