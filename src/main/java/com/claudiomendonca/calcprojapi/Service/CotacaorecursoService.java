package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Cotacaorecurso;
import com.claudiomendonca.calcprojapi.repository.CotacaorecursoRepository;

@Service
public class CotacaorecursoService {
	
	@Autowired
	private CotacaorecursoRepository cotacaorecursoRepository;

	public Cotacaorecurso atualizar(Long id, Cotacaorecurso cotacaorecurso) {
		Cotacaorecurso cotacaorecursoSalva = buscarCotacaorecursoPeloid(id);
		
		BeanUtils.copyProperties(cotacaorecurso, cotacaorecursoSalva, "id");
		return cotacaorecursoRepository.save(cotacaorecursoSalva);
	}   

	public Cotacaorecurso buscarCotacaorecursoPeloid(Long id) {
		Cotacaorecurso cotacaorecursoSalva =  cotacaorecursoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return cotacaorecursoSalva;
	}
    
}
