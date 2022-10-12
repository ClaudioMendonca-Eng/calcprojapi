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

import com.claudiomendonca.calcprojapi.Service.ComentariocompartilhadoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Comentariocompartilhado;
import com.claudiomendonca.calcprojapi.repository.ComentariocompartilhadoRepository;

@RestController
@RequestMapping("/api/comentariocompartilhados")
public class ComentariocompartilhadoController {

    @Autowired
	private ComentariocompartilhadoRepository comentariocompartilhadoRepository;

	@Autowired
	private ComentariocompartilhadoService comentariocompartilhadoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Comentariocompartilhado> listar() {
        return comentariocompartilhadoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Comentariocompartilhado> criar(@Valid @RequestBody Comentariocompartilhado comentariocompartilhado, HttpServletResponse response) {
		Comentariocompartilhado comentariocompartilhadoSalva = comentariocompartilhadoRepository.save(comentariocompartilhado);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, comentariocompartilhadoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(comentariocompartilhadoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comentariocompartilhado> buscarPeloid(@PathVariable Long id) {
		Optional<Comentariocompartilhado> comentariocompartilhado = comentariocompartilhadoRepository.findById(id);
		return comentariocompartilhado.isPresent() ? ResponseEntity.ok(comentariocompartilhado.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.comentariocompartilhadoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comentariocompartilhado> atualizar(@PathVariable Long id, @Valid @RequestBody Comentariocompartilhado comentariocompartilhado) {
		Comentariocompartilhado comentariocompartilhadoSalva = comentariocompartilhadoService.atualizar(id, comentariocompartilhado);
		return ResponseEntity.ok(comentariocompartilhadoSalva);
	}

    
}
