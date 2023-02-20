package com.mf.minhasfinancas.service;

import com.mf.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha); 
	
	Usuario salvarUsuario(Usuario novoUsuario);
	
	void validarEmail(String email);
}
