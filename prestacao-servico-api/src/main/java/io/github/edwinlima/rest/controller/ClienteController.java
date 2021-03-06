package io.github.edwinlima.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import io.github.edwinlima.domain.service.ClienteService;
import io.github.edwinlima.rest.model.ClienteInput;
import io.github.edwinlima.rest.model.ClienteOutput;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

	private final ClienteService service;
	
	@Autowired
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<ClienteOutput> salvar(@Valid @RequestBody ClienteInput cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(cliente));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id,@Valid @RequestBody ClienteInput cliente){
		service.atualizar(id, cliente);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteOutput> buscarClientePeloId(@PathVariable Integer id){
		return ResponseEntity.ok(service.clienteEncontradoPeloId(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id){
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteOutput>> listar(){
		return ResponseEntity.ok(service.listar());
	}
}
