package com.mf.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mf.minhasfinancas.model.entity.Lancamento;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
	
	

}
