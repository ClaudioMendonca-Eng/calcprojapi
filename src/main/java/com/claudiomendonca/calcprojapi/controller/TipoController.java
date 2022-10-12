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

import com.claudiomendonca.calcprojapi.Service.TipoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Tipo;
import com.claudiomendonca.calcprojapi.repository.TipoRepository;

@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    @Autowired
	private TipoRepository tipoRepository;

	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Tipo> listar() {
        return tipoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Tipo> criar(@Valid @RequestBody Tipo tipo, HttpServletResponse response) {
		Tipo tipoSalva = tipoRepository.save(tipo);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, tipoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tipo> buscarPeloid(@PathVariable Long id) {
		Optional<Tipo> tipo = tipoRepository.findById(id);
		return tipo.isPresent() ? ResponseEntity.ok(tipo.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.tipoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tipo> atualizar(@PathVariable Long id, @Valid @RequestBody Tipo tipo) {
		Tipo tipoSalva = tipoService.atualizar(id, tipo);
		return ResponseEntity.ok(tipoSalva);
	}
    
}
