package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Recurso;
import com.claudiomendonca.calcprojapi.repository.RecursoRepository;

@Service
public class RecursoService {

    
	
	@Autowired
	private RecursoRepository recursoRepository;

	public Recurso atualizar(Long id, Recurso recurso) {
		Recurso recursoSalva = buscarrecursoPeloid(id);
		
		BeanUtils.copyProperties(recurso, recursoSalva, "id");
		return recursoRepository.save(recursoSalva);
	}  

	public Recurso buscarrecursoPeloid(Long id) {
		Recurso recursoSalva =  recursoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return recursoSalva;
	}
    
}
