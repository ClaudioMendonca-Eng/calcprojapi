package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Visao;
import com.claudiomendonca.calcprojapi.repository.VisaoRepository;

@Service
public class VisaoService {

    
	
	@Autowired
	private VisaoRepository visaoRepository;

	public Visao atualizar(Long id, Visao visao) {
		Visao visaoSalva = buscarvisaoPeloid(id);
		
		BeanUtils.copyProperties(visao, visaoSalva, "id");
		return visaoRepository.save(visaoSalva);
	}  

	public Visao buscarvisaoPeloid(Long id) {
		Visao visaoSalva =  visaoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return visaoSalva;
	}
    
}
