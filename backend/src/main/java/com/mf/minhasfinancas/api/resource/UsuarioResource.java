package com.mf.minhasfinancas.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mf.minhasfinancas.api.dto.UsuarioDTO;
import com.mf.minhasfinancas.exception.RegraNegocioException;
import com.mf.minhasfinancas.model.entity.Usuario;
import com.mf.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
	
	@Autowired
	private UsuarioService service;
	
	
	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}
	

	@PostMapping(value = "/salvar")
	public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDto) {
		
		Usuario usuario = Usuario.builder()
							.nome(usuarioDto.getNome())
							.email(usuarioDto.getEmail())
							.senha(usuarioDto.getSenha())
							.build();
		try {
			
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
			
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
