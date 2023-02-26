package com.mf.minhasfinancas.service;

import java.util.List;

import com.mf.minhasfinancas.model.entity.Lancamento;
import com.mf.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	Lancamento salvar (Lancamento lancamento);
	Lancamento atualizar(Lancamento lancamento);
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizerStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lancamento);
	
}
