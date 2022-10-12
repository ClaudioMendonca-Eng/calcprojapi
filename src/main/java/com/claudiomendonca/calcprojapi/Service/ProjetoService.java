package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Projeto;
import com.claudiomendonca.calcprojapi.repository.ProjetoRepository;

@Service
public class ProjetoService {

    
	
	@Autowired
	private ProjetoRepository projetoRepository;

	public Projeto atualizar(Long id, Projeto projeto) {
		Projeto projetoSalva = buscarprojetoPeloid(id);
		
		BeanUtils.copyProperties(projeto, projetoSalva, "id");
		return projetoRepository.save(projetoSalva);
	}  

	public Projeto buscarprojetoPeloid(Long id) {
		Projeto projetoSalva =  projetoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return projetoSalva;
	}
    
}
