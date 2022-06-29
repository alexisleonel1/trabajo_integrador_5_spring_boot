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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.despensa.model.Cliente;
import com.despensa.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("")
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = clienteService.findAll();
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	};
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getBook(@PathVariable(value="id")int id) {
		Optional<Cliente> cliente = this.clienteService.find(id);
		if (cliente.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postCliente(@RequestBody Cliente c) {
		if(this.clienteService.create(c))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable(value="id")int id) {
		this.clienteService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}