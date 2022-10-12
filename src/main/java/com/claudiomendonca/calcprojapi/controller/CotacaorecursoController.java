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

import com.claudiomendonca.calcprojapi.Service.CotacaorecursoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Cotacaorecurso;
import com.claudiomendonca.calcprojapi.repository.CotacaorecursoRepository;

@RestController
@RequestMapping("/api/cotacaorecursos")
public class CotacaorecursoController {

    @Autowired
	private CotacaorecursoRepository cotacaorecursoRepository;

	@Autowired
	private CotacaorecursoService cotacaorecursoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Cotacaorecurso> listar() {
        return cotacaorecursoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Cotacaorecurso> criar(@Valid @RequestBody Cotacaorecurso cotacaorecurso, HttpServletResponse response) {
		Cotacaorecurso cotacaorecursoSalva = cotacaorecursoRepository.save(cotacaorecurso);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cotacaorecursoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cotacaorecursoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cotacaorecurso> buscarPeloid(@PathVariable Long id) {
		Optional<Cotacaorecurso> cotacaorecurso = cotacaorecursoRepository.findById(id);
		return cotacaorecurso.isPresent() ? ResponseEntity.ok(cotacaorecurso.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.cotacaorecursoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cotacaorecurso> atualizar(@PathVariable Long id, @Valid @RequestBody Cotacaorecurso cotacaorecurso) {
		Cotacaorecurso cotacaorecursoSalva = cotacaorecursoService.atualizar(id, cotacaorecurso);
		return ResponseEntity.ok(cotacaorecursoSalva);
	}
    
}
