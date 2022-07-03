package com.despensa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.despensa.dto.VentasByDayDTO;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{
	
    @Query(value="SELECT new com.despensa.dto.VentasByDayDTO( v.fecha, COUNT(v.id) ) "
            + "FROM Ventas v "
            + "GROUP BY v.fecha")
	public List<VentasByDayDTO> ventasByDay();
    
    @Query(value="SELECT p "
            + "FROM Ventas v JOIN Producto p ON (p.id=v.producto) "
            + "GROUP BY v.producto "
            + "ORDER BY SUM(v.cantidad) DESC")
	public List<Producto> bestProduct();
}