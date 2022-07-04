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

import com.despensa.dto.VentasByDayDTO;
import com.despensa.model.Cliente;
import com.despensa.model.Producto;
import com.despensa.model.Ventas;
import com.despensa.service.VentasService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	private VentasService ventasService;
	
	@GetMapping("")
	@Operation(
            summary = "Returns a list of type Ventas",
            description = "Method in charge of returning a list of sales",
            tags = { "Ventas controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ventas.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<List<Ventas>> findAll(){
		List<Ventas> ventas = ventasService.findAll();
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	};
	
	@GetMapping("/{id}")
	@Operation(
            summary = "Returns an object of type Ventas",
            description = "Method in charge of returning a sales",
            tags = { "Ventas controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ventas.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<Ventas> getVenta(@PathVariable(value="id")int id) {
		Optional<Ventas> ventas = this.ventasService.find(id);
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas.get(), HttpStatus.OK);
	}
	
	@GetMapping("/ventas-por-dia")
	@Operation(
            summary = "Returns an object a list of type VentasByDayDTO",
            description = "Method responsible for returning a list of sales by day",
            tags = { "Ventas controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VentasByDayDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<List<VentasByDayDTO>> getVentaByDay() {
		List<VentasByDayDTO> ventas = this.ventasService.ventasByDay();
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@GetMapping("/producto-mas-vendido")
	@Operation(
            summary = "Returns an object a list of type Producto",
            description = "Responsible method for the return of the best-selling product",
            tags = { "Ventas controller" },
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
	public ResponseEntity<Producto> getProductoMasVendido() {
		Producto ventas = this.ventasService.bestProduct();
		if (ventas == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@PostMapping("")
	@Operation(
            summary = "Save an object of type Ventas",
            description = "Method in charge of persisting a sale",
            tags = { "Ventas controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> postVentas(@RequestBody Ventas c) {
		if(this.ventasService.create(c))
			return new ResponseEntity<>("Venta guardada",HttpStatus.OK);
		return new ResponseEntity<>("No se pudo guardar la nueva venta, por favor revise su imformacion",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("")
	@Operation(
            summary = "Update an object of type Ventas",
            description = "Method in charge of update a sale",
            tags = { "Ventas controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> updateProducto(@RequestBody Ventas v) {
	    if(this.ventasService.update(v))
			return new ResponseEntity<>("Venta no actualizada",HttpStatus.OK);
		return new ResponseEntity<>("Venta no encontrada",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	@Operation(
            summary = "Delete an object of type Ventas",
            description = "Method in charge of delete a sale",
            tags = { "Ventas controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> deleteVentas(@PathVariable(value="id")int id) {
		  if(this.ventasService.delete(id))
				return new ResponseEntity<>("Venta eliminada", HttpStatus.OK);
			return new ResponseEntity<>("Venta no encontrada", HttpStatus.NOT_ACCEPTABLE);
	}
}
