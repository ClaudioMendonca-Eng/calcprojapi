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

import com.claudiomendonca.calcprojapi.Service.ProjetoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Projeto;
import com.claudiomendonca.calcprojapi.repository.ProjetoRepository;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private ProjetoService projetoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Projeto> criar(@Valid @RequestBody Projeto projeto, HttpServletResponse response) {
		Projeto projetoSalva = projetoRepository.save(projeto);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, projetoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Projeto> buscarPeloid(@PathVariable Long id) {
		Optional<Projeto> projeto = projetoRepository.findById(id);
		return projeto.isPresent() ? ResponseEntity.ok(projeto.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.projetoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Projeto> atualizar(@PathVariable Long id, @Valid @RequestBody Projeto projeto) {
		Projeto projetoSalva = projetoService.atualizar(id, projeto);
		return ResponseEntity.ok(projetoSalva);
	}
    
}
