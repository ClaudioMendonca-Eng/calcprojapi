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

import com.claudiomendonca.calcprojapi.Service.CompartilharService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Compartilhar;
import com.claudiomendonca.calcprojapi.repository.CompartilharRepository;

@RestController
@RequestMapping("/api/compartilhados")
public class CompartilharController {

    @Autowired
	private CompartilharRepository compartilharRepository;

	@Autowired
	private CompartilharService compartilharService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Compartilhar> listar() {
        return compartilharRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Compartilhar> criar(@Valid @RequestBody Compartilhar compartilhar, HttpServletResponse response) {
		Compartilhar compartilharSalva = compartilharRepository.save(compartilhar);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, compartilharSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(compartilharSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Compartilhar> buscarPeloid(@PathVariable Long id) {
		Optional<Compartilhar> compartilhar = compartilharRepository.findById(id);
		return compartilhar.isPresent() ? ResponseEntity.ok(compartilhar.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.compartilharRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Compartilhar> atualizar(@PathVariable Long id, @Valid @RequestBody Compartilhar compartilhar) {
		Compartilhar compartilharSalva = compartilharService.atualizar(id, compartilhar);
		return ResponseEntity.ok(compartilharSalva);
	}
    
}
