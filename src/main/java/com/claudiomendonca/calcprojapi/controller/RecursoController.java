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

import com.claudiomendonca.calcprojapi.Service.RecursoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Recurso;
import com.claudiomendonca.calcprojapi.repository.RecursoRepository;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
	private RecursoRepository recursoRepository;

	@Autowired
	private RecursoService recursoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Recurso> listar() {
        return recursoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Recurso> criar(@Valid @RequestBody Recurso recurso, HttpServletResponse response) {
		Recurso recursoSalva = recursoRepository.save(recurso);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, recursoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(recursoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Recurso> buscarPeloid(@PathVariable Long id) {
		Optional<Recurso> recurso = recursoRepository.findById(id);
		return recurso.isPresent() ? ResponseEntity.ok(recurso.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.recursoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Recurso> atualizar(@PathVariable Long id, @Valid @RequestBody Recurso recurso) {
		Recurso recursoSalva = recursoService.atualizar(id, recurso);
		return ResponseEntity.ok(recursoSalva);
	}
    
}
