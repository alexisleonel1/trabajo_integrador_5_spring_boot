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

import com.despensa.dto.ClienteCompraTotalDTO;
import com.despensa.model.Cliente;
import com.despensa.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("")
	@Operation(
            summary = "Returns a list of type Cliente",
            description = "Method in charge of returning a list of clients",
            tags = { "Cliente controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@Operation(
            summary = "Returns an object of type Cliente",
            description = "Method in charge of returning a client",
            tags = { "Cliente controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<Cliente> getCliente(@PathVariable(value="id")int id) {
		Optional<Cliente> cliente = this.clienteService.find(id);
		if (cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
	}
	
	@GetMapping("/compras")
	@Operation(
            summary = "Returns an object a list of type ClienteCompraTotalDTO",
            description = "Method responsible for returning a list of customers with the total of their purchases",
            tags = { "Cliente controller" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteCompraTotalDTO.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<List<ClienteCompraTotalDTO>> getCompras() {
		List<ClienteCompraTotalDTO> clientes = this.clienteService.getClientesByVentas();
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}
	
	@PostMapping("")
	@Operation(
            summary = "Save an object of type Cliente",
            description = "Method in charge of persisting a client",
            tags = { "Cliente controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> postCliente(@RequestBody Cliente c) {
		if(this.clienteService.create(c))
			return new ResponseEntity<>("Cliente guardado",HttpStatus.OK);
		return new ResponseEntity<>("No se pudo guardar el nuevo cliente, por favor revise su imformacion",HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("")
	@Operation(
            summary = "Update an object of type Cliente",
            description = "Method in charge of update a client",
            tags = { "Cliente controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> updateCliente(@RequestBody Cliente c) {
		if(this.clienteService.update(c))
			return new ResponseEntity<>("Cliente actualizado",HttpStatus.OK);
		return new ResponseEntity<>("Cliente no encontrado",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	@Operation(
            summary = "Delete an object of type Cliente",
            description = "Method in charge of delete a client",
            tags = { "Cliente controller" },
            responses = {
            		@ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Not acceptable", responseCode = "406", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
	public ResponseEntity<?> deleteCliente(@PathVariable(value="id")int id) {
		if(this.clienteService.delete(id))
			return new ResponseEntity<>("Cliente eliminado",HttpStatus.OK);
		return new ResponseEntity<>("Cliente no encontrado",HttpStatus.NOT_ACCEPTABLE);
	}

}