package com.mf.minhasfinancas.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mf.minhasfinancas.exception.RegraNegocioException;
import com.mf.minhasfinancas.model.entity.Usuario;
import com.mf.minhasfinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Profile("test")
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	
	//@Autowired
	UsuarioService service;
	
	//@Autowired
	UsuarioRepository repository;
	
	
	@BeforeEach
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImplementation(repository);
	}
	
	
	@Test
	public void deveValidarEmail() {
		
		//Avisando que esperamos que o método NÃO lance uma excessão 
		assertDoesNotThrow(()->{
			
			//cenario
			//UsuarioRepository usuarioRepositoryMock =  Mockito.mock(UsuarioRepository.class); 
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
			
			
			//repository.deleteAll();
			
			//ação
			service.validarEmail("email@email.com");
			
		});
	}

	
	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		
		//Avisando que esperamos que o método lance uma excessão do tipo RegraNegocioException
		assertThrows(RegraNegocioException.class, ()->{
			
			//cenario
			//Usuario usuario = Usuario.builder().nome("usuario").email("email@email.com").build();
			//repository.save(usuario);
			
			Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
			
			//ação
			service.validarEmail("email@email.com");
		});
	}

}










