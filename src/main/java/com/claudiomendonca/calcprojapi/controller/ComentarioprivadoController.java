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

import com.claudiomendonca.calcprojapi.Service.ComentarioprivadoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Comentarioprivado;
import com.claudiomendonca.calcprojapi.repository.ComentarioprivadoRepository;

@RestController
@RequestMapping("/api/comentarioprivados")
public class ComentarioprivadoController {

    @Autowired
	private ComentarioprivadoRepository comentarioprivadoRepository;

	@Autowired
	private ComentarioprivadoService comentarioprivadoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Comentarioprivado> listar() {
        return comentarioprivadoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Comentarioprivado> criar(@Valid @RequestBody Comentarioprivado comentarioprivado, HttpServletResponse response) {
		Comentarioprivado comentarioprivadoSalva = comentarioprivadoRepository.save(comentarioprivado);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, comentarioprivadoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(comentarioprivadoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comentarioprivado> buscarPeloid(@PathVariable Long id) {
		Optional<Comentarioprivado> comentarioprivado = comentarioprivadoRepository.findById(id);
		return comentarioprivado.isPresent() ? ResponseEntity.ok(comentarioprivado.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.comentarioprivadoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Comentarioprivado> atualizar(@PathVariable Long id, @Valid @RequestBody Comentarioprivado comentarioprivado) {
		Comentarioprivado comentarioprivadoSalva = comentarioprivadoService.atualizar(id, comentarioprivado);
		return ResponseEntity.ok(comentarioprivadoSalva);
	}
    
}
