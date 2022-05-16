package br.com.emailprojet.dto;

import br.com.emailprojet.model.Email;

public class EmailLayout {
	
	private static final String QUEBRA_DE_LINHA_DUPLA = "<br><br>";
	private static final String QUEBRA_DE_LINHA_UNICA = "<br>";
	
	public Email montarEmailAdministrador(String destinatario, String assunto) {
		
		StringBuilder texto = new StringBuilder();
		
		texto
			.append("A/C Administrador")
			.append(QUEBRA_DE_LINHA_DUPLA);
	
		texto	
			.append("Solicito aleração de senha do sistema")
			.append(QUEBRA_DE_LINHA_DUPLA);
		
		gerarAssinatura(texto);
		
		gerarRodape(texto);
			
		return new Email(destinatario, assunto, texto.toString());		
	}

	private String gerarRodape(StringBuilder texto) {
		return texto.append("Email automatico, favor nao responder esse email").toString();
		
	}
	
	private String gerarAssinatura(StringBuilder texto) {
		return texto
					.append("Att.")
					.append(QUEBRA_DE_LINHA_DUPLA)
					.append("Operador de caixa")
					.append(QUEBRA_DE_LINHA_UNICA)
					.toString();
		
	}


}
