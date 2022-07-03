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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private ProductoService productoService;

	@GetMapping("/")
	@Operation(
            summary = "Returns a list of type Producto",
            description = "Method in charge of returning a list of products",
            tags = { "Producto controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<List<Producto>> finAll() {
	List<Producto>productos= productoService.findAll();      	
	if (productos.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(
            summary = "Returns an object of type Producto",
            description = "Method in charge of returning a product",
            tags = { "Producto controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<Producto> getProducto(@PathVariable(value="id")int id) {
		Optional<Producto> producto = this.productoService.find(id);
		if (producto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(producto.get(), HttpStatus.OK);
	}
	
	@PostMapping("")
	@Operation(
            summary = "Save an object of type Producto",
            description = "Method in charge of persisting a product",
            tags = { "Producto controller" },
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> postProducto(@RequestBody Producto p) {
		if(this.productoService.create(p))
			return new ResponseEntity<>("Producto guardado", HttpStatus.OK);
		return new ResponseEntity<>("No se pudo guardar el nuevo producto, por favor revise su imformacion", HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("")
	@Operation(
            summary = "Update an object of type Producto",
            description = "Method in charge of update a product",
            tags = { "Producto controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> updateProducto(@RequestBody Producto p) {
	    if(this.productoService.update(p))
			return new ResponseEntity<>("Producto actualizado", HttpStatus.OK);
		return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_ACCEPTABLE);
	}

	@DeleteMapping("/{id}")
	@Operation(
            summary = "Delete an object of type Producto",
            description = "Method in charge of delete a product",
            tags = { "Producto controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> deleteProducto(@PathVariable(value = "id")int id) { 
	    if(this.productoService.delete(id))
			return new ResponseEntity<>("Producto no eliminado", HttpStatus.OK);
		return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_ACCEPTABLE);
	}
}
