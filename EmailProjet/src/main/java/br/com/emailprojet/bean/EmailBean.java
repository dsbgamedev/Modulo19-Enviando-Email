package br.com.emailprojet.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emailprojet.dto.EmailLayout;
import br.com.emailprojet.model.Email;
import br.com.emailprojet.service.EmailService;


@Named //Named referencia a classe bean
@RequestScoped //Utilizando pacote CDI
public class EmailBean implements Serializable{
	
	private static final String DESTINATARIO = "douglas.bocatto@gmail.com";
	
	private static final String ASSUNTO = "mudança de senha";
	
	private static final long serialVersionUID = -7450949716383915231L;
	
	@Inject
	private EmailService emailService;
	
	public String enviarEmail() {
		emailService.enviar(montarEmail());
		return null;
	}

	private Email montarEmail() {
		EmailLayout layout = new EmailLayout();
		return layout.montarEmailAdministrador(DESTINATARIO, ASSUNTO);
	}
}
