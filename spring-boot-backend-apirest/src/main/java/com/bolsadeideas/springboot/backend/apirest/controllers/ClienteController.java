package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.models.services.IClienteService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
	
	private final IClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> index() {
		List<Cliente> clientes = this.clienteService.findAll();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> show(@PathVariable Long id) {
		Cliente cliente = this.clienteService.findById(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
		Cliente clienteCreado = this.clienteService.save(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = this.clienteService.save(cliente);
		clienteActual.setNombre(cliente.getNombre());
		clienteActual.setApellido(cliente.getApellido());
		clienteActual.setEmail(cliente.getEmail());		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteActual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long id) {
		this.clienteService.delete(id);
	}
}