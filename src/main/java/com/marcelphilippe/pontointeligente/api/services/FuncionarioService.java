package com.marcelphilippe.pontointeligente.api.services;

import java.util.Optional;

import com.marcelphilippe.pontointeligente.api.entidades.Funcionario;

public interface FuncionarioService {
	
	/**
	 * Persiste um funcionario na base de dados
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario persistirFuncionario(Funcionario funcionario);
	
	/**
	 * Busca e retorna um funcionario dado um CPF
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	
	/**
	 * Busca e retorna um funcionario dado um email
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Busca e retorna um funcionario dado um id
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);

}
