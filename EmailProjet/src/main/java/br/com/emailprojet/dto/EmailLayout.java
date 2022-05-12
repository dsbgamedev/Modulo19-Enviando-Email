package br.com.emailprojet.dto;

import br.com.emailprojet.model.Email;

public class EmailLayout {
	
	public Email montarEmailAdministrador(String destinatario, String assunto) {
		
		StringBuilder texto = new StringBuilder();
		
		
		
		return new Email(destinatario, assunto, texto.toString());
		
	}

}
