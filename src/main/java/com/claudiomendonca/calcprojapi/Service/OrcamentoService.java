package com.claudiomendonca.calcprojapi.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Orcamento;
import com.claudiomendonca.calcprojapi.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

    
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public Orcamento atualizar(Long id, Orcamento orcamento) {
		Orcamento orcamentoSalva = buscarOrcamentoPeloid(id);
		
		BeanUtils.copyProperties(orcamento, orcamentoSalva, "id");
		return orcamentoRepository.save(orcamentoSalva);
	}  

	public Orcamento buscarOrcamentoPeloid(Long id) {
		Orcamento orcamentoSalva =  orcamentoRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return orcamentoSalva;
	}
    
}
