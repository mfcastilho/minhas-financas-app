package com.mf.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mf.minhasfinancas.exception.RegraNegocioException;
import com.mf.minhasfinancas.model.entity.Lancamento;
import com.mf.minhasfinancas.model.enums.StatusLancamento;
import com.mf.minhasfinancas.model.enums.TipoLancamento;
import com.mf.minhasfinancas.model.repository.LancamentoRepository;

@Service
public class LancamentoServiceImplementation implements LancamentoService{
	
	
	private LancamentoRepository repository;
	
	public LancamentoServiceImplementation(LancamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
		
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		
		java.util.Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		
		java.util.Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
		
		
	}

	@Override
	@Transactional
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		
		Example exemple = Example.of(lancamentoFiltro, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(exemple);
	}

	@Override
	@Transactional
	public void atualizerStatus(Lancamento lancamento, StatusLancamento status) {
		
		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {
		
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			
			throw new RegraNegocioException("Informe uma descrição válida");
		}
		
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido");
		}
		
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um ano válido");
		}
		
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Informe um Usuário válido");
		}
		
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um valor válido");
		}
		
		if(lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um tipo de Lançamento");
		}
	}

	@Override
	public Optional<Lancamento> obterPorId(Long id) {
		
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public BigDecimal obterSaldoPorUsuario(Long id) {
		
		BigDecimal receitas = repository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.RECEITA.name());
		BigDecimal despesas =  repository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.DESPESA.name());
		
		if(receitas == null) {
			receitas = BigDecimal.ZERO;
		}
		
		if(despesas == null) {
			despesas = BigDecimal.ZERO;
		}
		
		
		
		return receitas.subtract(despesas);
	}

}
