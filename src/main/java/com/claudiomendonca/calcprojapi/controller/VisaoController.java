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

import com.claudiomendonca.calcprojapi.Service.VisaoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Visao;
import com.claudiomendonca.calcprojapi.repository.VisaoRepository;

@RestController
@RequestMapping("/api/visoes")
public class VisaoController {

    @Autowired
	private VisaoRepository visaoRepository;

	@Autowired
	private VisaoService visaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Visao> listar() {
        return visaoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Visao> criar(@Valid @RequestBody Visao visao, HttpServletResponse response) {
		Visao visaoSalva = visaoRepository.save(visao);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, visaoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(visaoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visao> buscarPeloid(@PathVariable Long id) {
		Optional<Visao> visao = visaoRepository.findById(id);
		return visao.isPresent() ? ResponseEntity.ok(visao.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.visaoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Visao> atualizar(@PathVariable Long id, @Valid @RequestBody Visao visao) {
		Visao visaoSalva = visaoService.atualizar(id, visao);
		return ResponseEntity.ok(visaoSalva);
	}
    
}
