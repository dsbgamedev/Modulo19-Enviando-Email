package br.enviandoemail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	private String userName = "dsbocatto@gmail.com";
	private String senha = "Fantasma@01";

	
	@org.junit.Test
	public void testeEmail() {
		
		try {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");/*Autorização*/
		properties.put("mail.smtp.starttls", "true");/*Autenticação*/
		properties.put("mail.smtp.host","smtp.gmail.com");/*Servidor gmail Google*/
		properties.put("mail.smtp.port", "465");/*Porta do servidor*/
		properties.put("mail.smtp.socketFactory.port", "465");/*Especifica a porta a ser conectada pelo socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe socket de conexao ao smtp*/
		
		//@SuppressWarnings("unused")
		Session session = Session.getInstance(properties, new Authenticator() {
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, senha);
			}
		});
		
		Address[] toUser = InternetAddress.parse("douglas.bocatto@gmail.com, dsbocatto@gmail.com");
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));/*Quem está enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*E-mail de destino*/
		message.setSubject("Chegou email enviado pelo java");/*Assunto do e-mail*/
		message.setText("Ola programador voce acaba de receber e-mail enviado com java");
		
		Transport.send(message);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
