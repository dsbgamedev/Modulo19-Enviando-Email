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

public class ObjetoEnviaEmail {

	private String userName = "dsbocatto@gmail.com";
	private String senha = "Fantasma@01";
	
	private String listaDestinario= "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String textoEmail = "";
	
	public ObjetoEnviaEmail(String listaDestinario, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinario = listaDestinario;
		this.nomeRemetente =  nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;		
	}
	

	public void enviarEmail(boolean envioHtml) throws Exception{
		try {
			Properties properties = new Properties();

			properties.put("mail.smtp.ssl.trust", "*");
			properties.put("mail.smtp.auth", "true");/* Autorização */
			properties.put("mail.smtp.starttls", "true");/* Autenticação */
			properties.put("mail.smtp.host", "smtp.gmail.com");/* Servidor gmail Google */
			properties.put("mail.smtp.port", "465");/* Porta do servidor */
			properties.put("mail.smtp.socketFactory.port", "465");/* Especifica a porta a ser conectada pelo socket */
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");/* Classe socket de conexao ao smtp */

			// @SuppressWarnings("unused")
			Session session = Session.getInstance(properties, new Authenticator() {

				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, senha);
				}
			});

			Address[] toUser = InternetAddress.parse(listaDestinario);

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userName, nomeRemetente));/* Quem está enviando */
			message.setRecipients(Message.RecipientType.TO, toUser);/* E-mail de destino */
			message.setSubject(assuntoEmail);/* Assunto do e-mail */
			
			if(envioHtml) {
				message.setContent(textoEmail, "text/html; charset=utf-8");
			}else {
				message.setText(textoEmail);

			}
			
			
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
