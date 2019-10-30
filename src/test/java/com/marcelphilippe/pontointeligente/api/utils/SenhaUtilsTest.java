package com.marcelphilippe.pontointeligente.api.utils;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtilsTest {
	
	private static final String senha = "123456";
	private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNula() throws Exception{
		assertNull(SenhaUtils.gerarBcrypt(null));
	}
	
	@Test
	public void testGerarHashSenha() throws Exception{
		String hash = SenhaUtils.gerarBcrypt(senha);
		assertTrue(bCryptEncoder.matches(senha, hash));
	}
}
