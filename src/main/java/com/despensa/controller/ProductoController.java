package com.despensa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.despensa.model.Cliente;
import com.despensa.model.Producto;
import com.despensa.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
    	public ResponseEntity<List<Producto>> finAll() {
		List<Producto>productos= productoService.findAll();      	
		if (productos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(productos, HttpStatus.OK);
    	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable(value="id")int id) {
		Optional<Producto> producto = this.productoService.find(id);
		if (producto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(producto.get(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postProducto(@RequestBody Producto p) {
		if(this.productoService.create(p))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProducto(@RequestBody Producto p) {
	    if(this.productoService.update(p))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable(value = "id")int id) { 
	    if(this.productoService.delete(id))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
