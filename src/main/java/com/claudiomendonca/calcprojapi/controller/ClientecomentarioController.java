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

import com.claudiomendonca.calcprojapi.Service.ClientecomentarioService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Clientecomentario;
import com.claudiomendonca.calcprojapi.repository.ClientecomentarioRepository;

@RestController
@RequestMapping("/api/clientecomentarios")
public class ClientecomentarioController {

    @Autowired
	private ClientecomentarioRepository clientecomentarioRepository;

	@Autowired
	private ClientecomentarioService clientecomentarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Clientecomentario> listar() {
        return clientecomentarioRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Clientecomentario> criar(@Valid @RequestBody Clientecomentario clientecomentario, HttpServletResponse response) {
		Clientecomentario clientecomentarioSalva = clientecomentarioRepository.save(clientecomentario);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clientecomentarioSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clientecomentarioSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Clientecomentario> buscarPeloid(@PathVariable Long id) {
		Optional<Clientecomentario> clientecomentario = clientecomentarioRepository.findById(id);
		return clientecomentario.isPresent() ? ResponseEntity.ok(clientecomentario.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.clientecomentarioRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Clientecomentario> atualizar(@PathVariable Long id, @Valid @RequestBody Clientecomentario clientecomentario) {
		Clientecomentario clientecomentarioSalva = clientecomentarioService.atualizar(id, clientecomentario);
		return ResponseEntity.ok(clientecomentarioSalva);
	}

    
}
