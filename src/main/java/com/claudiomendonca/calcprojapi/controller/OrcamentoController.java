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

import com.claudiomendonca.calcprojapi.Service.OrcamentoService;
import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Orcamento;
import com.claudiomendonca.calcprojapi.repository.OrcamentoRepository;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    @Autowired
	private OrcamentoRepository orcamentoRepository;

	@Autowired
	private OrcamentoService orcamentoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
    public List<Orcamento> listar() {
        return orcamentoRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Orcamento> criar(@Valid @RequestBody Orcamento orcamento, HttpServletResponse response) {
		Orcamento orcamentoSalva = orcamentoRepository.save(orcamento);		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, orcamentoSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orcamentoSalva);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Orcamento> buscarPeloid(@PathVariable Long id) {
		Optional<Orcamento> orcamento = orcamentoRepository.findById(id);
		return orcamento.isPresent() ? ResponseEntity.ok(orcamento.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.orcamentoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Orcamento> atualizar(@PathVariable Long id, @Valid @RequestBody Orcamento orcamento) {
		Orcamento orcamentoSalva = orcamentoService.atualizar(id, orcamento);
		return ResponseEntity.ok(orcamentoSalva);
	}
    
}
