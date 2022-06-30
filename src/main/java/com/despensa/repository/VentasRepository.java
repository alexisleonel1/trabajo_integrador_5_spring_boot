package com.despensa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.despensa.model.Ventas;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Integer>{

}

