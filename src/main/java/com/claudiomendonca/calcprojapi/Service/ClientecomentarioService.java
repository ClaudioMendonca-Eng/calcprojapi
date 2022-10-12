package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Clientecomentario;
import com.claudiomendonca.calcprojapi.repository.ClientecomentarioRepository;

@Service
public class ClientecomentarioService  {
	
	@Autowired
	private ClientecomentarioRepository clientecomentarioRepository;

	public Clientecomentario atualizar(Long id, Clientecomentario clientecomentario) {
		Clientecomentario clientecomentarioSalva = buscarClientecomentarioPeloid(id);
		
		BeanUtils.copyProperties(clientecomentario, clientecomentarioSalva, "id");
		return clientecomentarioRepository.save(clientecomentarioSalva);
	}   

	public Clientecomentario buscarClientecomentarioPeloid(Long id) {
		Clientecomentario clientecomentarioSalva =  clientecomentarioRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return clientecomentarioSalva;
	}
	
}
