package com.claudiomendonca.calcprojapi.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.claudiomendonca.calcprojapi.event.RecursoCriadoEvent;
import com.claudiomendonca.calcprojapi.model.Empresasistema;
import com.claudiomendonca.calcprojapi.repository.EmpresasistemaRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/empresasistema")
@AllArgsConstructor
public class EmpresasistemaController {

    @Autowired
    private EmpresasistemaRepository empresasistemaRepository;

    @Autowired
	private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Empresasistema> lista() {
        return empresasistemaRepository.findAll();
    }
    
    @PostMapping
	public ResponseEntity<Empresasistema> criar(@Valid @RequestBody Empresasistema empresasistema, HttpServletResponse response) {
		Empresasistema empresasistemaSalva = empresasistemaRepository.save(empresasistema);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, empresasistemaSalva.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(empresasistemaSalva);
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Empresasistema> findById(@PathVariable Long id) {
        return empresasistemaRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
    this.empresasistemaRepository.deleteById(id);
    }
    
}
