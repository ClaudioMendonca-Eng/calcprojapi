package com.claudiomendonca.calcprojapi.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudiomendonca.calcprojapi.Service.ClienteService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Cliente;
import com.claudiomendonca.calcprojapi.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalva = clienteRepository.save(cliente);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPeloid(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.clienteRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		Cliente clienteSalva = clienteService.atualizar(id, cliente);
		return ResponseEntity.ok(clienteSalva);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
		clienteService.atualizarPropriedadeAtivo(id, ativo);
	}

    
}
