package com.mf.minhasfinancas.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
}
