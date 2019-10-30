package com.marcelphilippe.pontointeligente.api.services;

import java.util.Optional;

import com.marcelphilippe.pontointeligente.api.entidades.Empresa;

public interface EmpresaService {
	
	/**Retorna uma empresa dado um Cnpj
	* 
	*@param cnpj 
	*@return Optional<Empresa>
	*/
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
}
