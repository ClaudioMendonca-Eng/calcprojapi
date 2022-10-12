package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Cliente;
import com.claudiomendonca.calcprojapi.repository.ClienteRepository;

@Service
public class ClienteService  {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente atualizar(Long id, Cliente cliente) {
		Cliente clienteSalva = buscarClientePeloid(id);
		
		BeanUtils.copyProperties(cliente, clienteSalva, "id");
		return clienteRepository.save(clienteSalva);
	}   

	
	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Cliente clienteSalva = buscarClientePeloid(id);
		clienteSalva.setAtivo(ativo);
		clienteRepository.save(clienteSalva);
	}

	public Cliente buscarClientePeloid(Long id) {
		Cliente clienteSalva =  clienteRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return clienteSalva;
	}
	
}
