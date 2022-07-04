package com.despensa.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.despensa.model.Cliente;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;
import com.despensa.service.ClienteService;
import com.despensa.service.ProductoService;
import com.despensa.service.VentasService;

import java.sql.Date;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class Data {

    long ID =1;
    
    @Bean
    public CommandLineRunner cargaDB(ClienteService clientes, ProductoService productos, VentasService ventas){
        return args-> {

            IntStream.range(0, 10).forEach(i -> {
                Producto p = new Producto("Producto " + i, 10 + i * 2, 100 + i * 5);
                try {
                    productos.create(p);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            IntStream.range(0, 10).forEach(i -> {
                Cliente c = new Cliente("Cliente " + i, "Apellido " + i);
                try {
                    clientes.create(c);
                    ID ++;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            
            List<Cliente> cs = clientes.findAll();
            List<Producto> ps = productos.findAll();
            
            Producto p1 = ps.get((int) (Math.random()*ps.size()-1));
            Producto p2 = ps.get((int) (Math.random()*ps.size()-1));
            Producto p3 = ps.get((int) (Math.random()*ps.size()-1));
            
            
            Cliente c1 = cs.get((int) (Math.random()*cs.size()-1));
            Cliente c2 = cs.get((int) (Math.random()*cs.size()-1));
            Cliente c3 = cs.get((int) (Math.random()*cs.size()-1));
            Ventas v1 = new Ventas(c1,p1,new Date(422-07-1),3);
            Ventas v2 = new Ventas(c2,p2,new Date(2022-07-2),2);
            Ventas v3 = new Ventas(c3,p3,new Date(2022-07-3),1);
            
            ventas.create(v1);
            ventas.create(v2);
            ventas.create(v3);
        };
    }
}

