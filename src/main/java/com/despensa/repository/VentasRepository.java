package com.despensa.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.despensa.dto.VentasByDayDTO;
import com.despensa.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{
	
	@Query(value="SELECT CASE WHEN (v != null) THEN true ELSE false END "
			+ "FROM Ventas v "
			+ "GROUP BY v.fecha HAVING v.fecha = :fecha") 
	public boolean fullQuota(Calendar fecha/*,int idCliente*/);

	
	@Modifying
    @Query(value="SELECT new com.despensa.dto.VentasByDayDTO( v.fecha, COUNT(v.id) ) "
            + "FROM Ventas v "
            + "GROUP BY v.fecha")
	public List<VentasByDayDTO> ventasByDay();

}

// (v.cliente = :idCliente AND v.producto = :id )AND (SUM(v.cantidad) > 3 OR (SUM(v.cantidad) + :cantidad) > 3)
//, Producto id, int cantidad, 