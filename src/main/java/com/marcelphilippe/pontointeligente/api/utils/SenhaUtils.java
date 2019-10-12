package com.marcelphilippe.pontointeligente.api.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {
	
	private static final Logger log = LoggerFactory.getLogger(SenhaUtils.class);
	
	public SenhaUtils() {
		
	}

	/**
	 * ​Gera​ ​um​ ​hash​ ​utilizando​ ​o​ ​BCrypt.
	 * 
	 * @param senha
	 * @return String
	 */
	public static String gerarBcrypt(String senha){
		
		if(senha == null) {
			return senha;
		}
		
		log.info("Gerando Hash com BCrypt.");
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.encode(senha);
	}
	
	/**
	 * Verifica se a senha e valida
	 * 
	 * @param senha
	 * @param senhaEncoded
	 * @return boolean
	 */
	public static boolean senhaValida (String senha, String senhaEncoded) {
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		return bCryptEncoder.matches(senha, senhaEncoded);
	}
}
