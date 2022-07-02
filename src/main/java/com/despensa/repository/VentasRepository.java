package com.despensa.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.despensa.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{
	
	@Query(value="SELECT CASE WHEN (v != null) THEN true ELSE false END "
			+ "FROM Ventas v "
			+ "GROUP BY v.fecha HAVING v.fecha = :fecha") 
	public boolean fullQuota(Date fecha/*,int idCliente*/);
	
	@Query(value="SELECT v FROM Ventas v "
			+ "WHERE v.fecha = :f")
	public List<Ventas> ventasByDay(Date f);

}

// (v.cliente = :idCliente AND v.producto = :id )AND (SUM(v.cantidad) > 3 OR (SUM(v.cantidad) + :cantidad) > 3)
//, Producto id, int cantidad, 