package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Comentarioprivado;
import com.claudiomendonca.calcprojapi.repository.ComentarioprivadoRepository;

@Service
public class ComentarioprivadoService {
	
	@Autowired
	private ComentarioprivadoRepository comentarioprivadoRepository;

	public Comentarioprivado atualizar(Long id, Comentarioprivado comentarioprivado) {
		Comentarioprivado comentarioprivadoSalva = buscarComentarioprivadoPeloid(id);
		
		BeanUtils.copyProperties(comentarioprivado, comentarioprivadoSalva, "id");
		return comentarioprivadoRepository.save(comentarioprivadoSalva);
	}   

	public Comentarioprivado buscarComentarioprivadoPeloid(Long id) {
		Comentarioprivado comentarioprivadoSalva =  comentarioprivadoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return comentarioprivadoSalva;
	}
    
}
