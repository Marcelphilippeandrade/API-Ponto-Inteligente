package com.marcelphilippe.pontointeligente.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelphilippe.pontointeligente.api.dtos.FuncionarioDto;
import com.marcelphilippe.pontointeligente.api.entidades.Funcionario;
import com.marcelphilippe.pontointeligente.api.response.Response;
import com.marcelphilippe.pontointeligente.api.services.FuncionarioService;
import com.marcelphilippe.pontointeligente.api.utils.SenhaUtils;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin("*")
public class FuncionarioController {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioController.class);

	@Autowired
	private FuncionarioService funcionarioService;

	public FuncionarioController() {

	}

	/**
	 * Atualiza os dados de um funcionário
	 * 
	 * @param id
	 * @param funcionarioDto
	 * @param result
	 * @return ResponseEntity<Response<FuncionarioDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<FuncionarioDto>> atualizar(@PathVariable("id") Long id,
			@Valid @RequestBody FuncionarioDto funcionarioDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando funcionario: {}", funcionarioDto.toString());
		Response<FuncionarioDto> response = new Response<FuncionarioDto>();

		Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(id);

		if (!funcionario.isPresent()) {
			result.addError(new ObjectError("Funcionario", "Funcionario não encontrado."));
		}

		this.atualizarDadosFuncionario(funcionario.get(), funcionarioDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando funcionario: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.funcionarioService.persistirFuncionario(funcionario.get());
		response.setData(this.conveterFuncionarioDto(funcionario.get()));

		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna um DTO com os dados do funcionario
	 * 
	 * @param funcionario
	 * @return
	 */
	private FuncionarioDto conveterFuncionarioDto(Funcionario funcionario) {
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		funcionarioDto.setId(funcionario.getId());
		funcionarioDto.setEmail(funcionario.getEmail());
		funcionarioDto.setNome(funcionario.getNome());
		funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> funcionarioDto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasTrabalhoDia -> funcionarioDto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasTrabalhoDia))));
		funcionario.getValorHoraOpt().ifPresent(valorHora -> funcionarioDto.setValorHora(Optional.of(valorHora.toString())));
		
		return funcionarioDto;
	}

	/**
	 * 
	 * @param funcionario
	 * @param funcionarioDto
	 * @param result
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	private void atualizarDadosFuncionario(Funcionario funcionario, @Valid FuncionarioDto funcionarioDto,
			BindingResult result) throws NoSuchAlgorithmException {

		funcionario.setNome(funcionarioDto.getNome());

		if (!funcionario.getEmail().equals(funcionarioDto.getEmail())) {
			this.funcionarioService.buscarPorEmail(funcionarioDto.getEmail())
					.ifPresent(func -> result.addError(new ObjectError("Email", "Emai já existente.")));
			funcionario.setEmail(funcionarioDto.getEmail());
		}

		funcionario.setQuantHorasAlmoco(null);
		funcionarioDto.getQtdHorasAlmoco()
				.ifPresent(qtdHorasAlmoco -> funcionario.setQuantHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));

		funcionario.setQuantHorasTrabalhoDia(null);
		funcionarioDto.getQtdHorasTrabalhoDia().ifPresent(
				qtdHorasTrabalhoDia -> funcionario.setQuantHorasTrabalhoDia(Float.valueOf(qtdHorasTrabalhoDia)));
		
		funcionario.setValorHora(null);
		funcionarioDto.getValorHora().ifPresent(qtdValorHora -> funcionario.setValorHora(new BigDecimal(qtdValorHora)));
		
		if(funcionarioDto.getSenha().isPresent()) {
			funcionario.setSenha(SenhaUtils.gerarBcrypt(funcionarioDto.getSenha().get()));
		}
	}
}
