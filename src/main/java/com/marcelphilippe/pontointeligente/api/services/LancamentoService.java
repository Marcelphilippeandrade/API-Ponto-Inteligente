package com.marcelphilippe.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.marcelphilippe.pontointeligente.api.entidades.Lancamento;

public interface LancamentoService {
	
	/**
	 * Retorna uma lista paginada de lancamento de um determinado funcionario
	 * 
	 * @param funcionarioId
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> buscarFuncionarioPorId(Long funcionarioId, PageRequest pageRequest);
	
	/**
	 * Retorna um lancamento por ID
	 * 
	 * @param id
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * Persiste um lancamento na base de dados
	 * 
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Remove um lancamento da base de dados
	 * 
	 * @param id
	 */
	void remover(Long id);

}
