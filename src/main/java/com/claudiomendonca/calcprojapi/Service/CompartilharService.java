package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Compartilhar;
import com.claudiomendonca.calcprojapi.repository.CompartilharRepository;

@Service
public class CompartilharService {
	
	@Autowired
	private CompartilharRepository compartilharRepository;

	public Compartilhar atualizar(Long id, Compartilhar compartilhar) {
		Compartilhar compartilharSalva = buscarCompartilharPeloid(id);
		
		BeanUtils.copyProperties(compartilhar, compartilharSalva, "id");
		return compartilharRepository.save(compartilharSalva);
	}   

	public Compartilhar buscarCompartilharPeloid(Long id) {
		Compartilhar compartilharSalva =  compartilharRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return compartilharSalva;
	}
    
}
