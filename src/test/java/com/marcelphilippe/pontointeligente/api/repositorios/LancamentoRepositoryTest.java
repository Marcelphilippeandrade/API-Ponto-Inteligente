package com.marcelphilippe.pontointeligente.api.repositorios;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcelphilippe.pontointeligente.api.entidades.Empresa;
import com.marcelphilippe.pontointeligente.api.entidades.Funcionario;
import com.marcelphilippe.pontointeligente.api.entidades.Lancamento;
import com.marcelphilippe.pontointeligente.api.enums.PerfilEnum;
import com.marcelphilippe.pontointeligente.api.enums.TipoEnum;
import com.marcelphilippe.pontointeligente.api.utils.SenhaUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	private Long funcionarioId;
	
	@Before
	public void setUp() throws Exception{
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		
		Funcionario funcionario = this.funcionarioRepository.save(ObterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();
		
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}
	
	@After
	public void tearDown() throws Exception{
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamento = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamento.size());
	}
	
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_ALMOCO);
		lancamento.setDescricao("Intervalo da hora do almoço - 1 hora de intervalo");
		lancamento.setFuncionario(funcionario);
		lancamento.setLocalizacao("Marcel Philippe Softwares - Sede Belo Horizonte");
		return lancamento;
	}
	
	public Funcionario ObterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException{
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Marcel Philippe Abreu Andrade");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(SenhaUtils.gerarBcrypt("ceceu2108"));
		funcionario.setCpf("06618938635");
		funcionario.setEmail("marcelpaa@hotmail.com");
		funcionario.setEmpresa(empresa);
		funcionario.setValorHora(new BigDecimal(100));
		funcionario.setQuantHorasTrabalhoDia(8F);
		funcionario.setQuantHorasAlmoco(1F);
		return funcionario;
	}
	
	public Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Marcel Phlippe Softwares");
		empresa.setCnpj("15478963540033");
		return empresa;
	}
}
