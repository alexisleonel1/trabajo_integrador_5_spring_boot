package com.despensa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.despensa.dto.ClienteCompraTotalDTO;
import com.despensa.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Query(value="SELECT new com.despensa.dto.ClienteCompraTotalDTO(c.nombre,c.apellido,c.id,COALESCE(SUM(v.total), 0.0)) "
			+ "FROM Cliente c LEFT JOIN Ventas v ON (c.id=v.cliente) "
			+ "GROUP BY v.cliente")
	public List<ClienteCompraTotalDTO> getClientesByVentas();
}