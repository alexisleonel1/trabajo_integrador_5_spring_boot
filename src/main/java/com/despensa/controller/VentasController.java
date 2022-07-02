package com.despensa.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.despensa.model.Ventas;
import com.despensa.service.VentasService;


@RestController
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	private VentasService ventasService;
	
	@GetMapping("")
	public ResponseEntity<List<Ventas>> findAll(){
		List<Ventas> ventas = ventasService.findAll();
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	};
	
	@GetMapping("/{id}")
	public ResponseEntity<Ventas> getVenta(@PathVariable(value="id")int id) {
		Optional<Ventas> ventas = this.ventasService.find(id);
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas.get(), HttpStatus.OK);
	}
	
	@GetMapping("/ventas-por-dia/{f}")
	public ResponseEntity<List<Ventas>> getVentaByDay(@PathVariable(value="f")String f) throws ParseException{
		Date fecha=transformDate(f);
		List<Ventas> ventas = this.ventasService.ventasByDay(fecha);
		if (ventas.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ventas, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<?> postVentas(@RequestBody Ventas c) {
		if(this.ventasService.create(c))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PutMapping("")
	public ResponseEntity<?> updateProducto(@RequestBody Ventas v) {
	    if(this.ventasService.update(v))
			return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVentas(@PathVariable(value="id")int id) {
		  if(this.ventasService.delete(id))
				return new ResponseEntity<>(HttpStatus.OK);
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	private Date transformDate(String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
		Date d = dateFormat.parse(date);
		/*long time = d.getTime();
		Timestamp dateTransform = new Timestamp(time);*/
		return d;
	}
}
