package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Tipo;
import com.claudiomendonca.calcprojapi.repository.TipoRepository;

@Service
public class TipoService {

    
	
	@Autowired
	private TipoRepository tipoRepository;

	public Tipo atualizar(Long id, Tipo tipo) {
		Tipo tipoSalva = buscartipoPeloid(id);
		
		BeanUtils.copyProperties(tipo, tipoSalva, "id");
		return tipoRepository.save(tipoSalva);
	}  

	public Tipo buscartipoPeloid(Long id) {
		Tipo tipoSalva =  tipoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return tipoSalva;
	}
    
}
