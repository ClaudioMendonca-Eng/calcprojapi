package com.claudiomendonca.calcprojapi.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.claudiomendonca.calcprojapi.model.Usuario;
import com.claudiomendonca.calcprojapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

    
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalva = buscarusuarioPeloid(id);
		
		BeanUtils.copyProperties(usuario, usuarioSalva, "id");
		return usuarioRepository.save(usuarioSalva);
	}  

	public Usuario buscarusuarioPeloid(Long id) {
		Usuario usuarioSalva =  usuarioRepository.findById(id)
								.orElseThrow(() -> new EmptyResultDataAccessException(1));

		return usuarioSalva;
	}
    
}
