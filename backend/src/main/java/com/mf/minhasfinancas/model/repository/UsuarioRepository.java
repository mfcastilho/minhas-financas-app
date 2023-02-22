package com.mf.minhasfinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mf.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{


	//ao usarmos a convenção exists o spring faz automaticamente  a consulta sql
	boolean existsByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);
}


