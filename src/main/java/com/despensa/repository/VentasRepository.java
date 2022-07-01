package com.despensa.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.despensa.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{
	
	@Query(value="SELECT CASE WHEN (v != null) THEN true ELSE false END "
			+ "FROM Ventas v "
			+ "GROUP BY v.fecha HAVING v.fecha = :fecha") 
	public boolean fullQuota(Timestamp fecha/*,int idCliente*/);

}

// v.cliente = :idCliente AND v.producto = :id AND (SUM(v.cantidad) > 3 OR (SUM(v.cantidad) + :cantidad) > 3)
//, Producto id, int cantidad, 