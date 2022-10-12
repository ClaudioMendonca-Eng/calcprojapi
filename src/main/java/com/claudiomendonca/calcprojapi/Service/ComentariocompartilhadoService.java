package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Comentariocompartilhado;
import com.claudiomendonca.calcprojapi.repository.ComentariocompartilhadoRepository;

@Service
public class ComentariocompartilhadoService {
	
	@Autowired
	private ComentariocompartilhadoRepository comentariocompartilhadoRepository;

	public Comentariocompartilhado atualizar(Long id, Comentariocompartilhado comentariocompartilhado) {
		Comentariocompartilhado comentariocompartilhadoSalva = buscarComentariocompartilhadoPeloid(id);
		
		BeanUtils.copyProperties(comentariocompartilhado, comentariocompartilhadoSalva, "id");
		return comentariocompartilhadoRepository.save(comentariocompartilhadoSalva);
	}   

	public Comentariocompartilhado buscarComentariocompartilhadoPeloid(Long id) {
		Comentariocompartilhado comentariocompartilhadoSalva =  comentariocompartilhadoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return comentariocompartilhadoSalva;
	}
    
}
