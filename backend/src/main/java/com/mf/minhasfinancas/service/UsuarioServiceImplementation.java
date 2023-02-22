package com.mf.minhasfinancas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mf.minhasfinancas.exception.ErroAutenticacao;
import com.mf.minhasfinancas.exception.RegraNegocioException;
import com.mf.minhasfinancas.model.entity.Usuario;
import com.mf.minhasfinancas.model.repository.UsuarioRepository;


@Service
public class UsuarioServiceImplementation implements UsuarioService{

	
	
	private UsuarioRepository repository;
	
	                        
	@Autowired
	public UsuarioServiceImplementation(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		
		Optional<Usuario> usuario =  repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("O usuário não encontrado");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}
		
		
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario novoUsuario) {
		
		validarEmail(novoUsuario.getEmail());
		
		return repository.save(novoUsuario);
	}

	@Override
	public void validarEmail(String email) {
		
		boolean existe =  repository.existsByEmail(email);
		
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email");
		}
	}

}
