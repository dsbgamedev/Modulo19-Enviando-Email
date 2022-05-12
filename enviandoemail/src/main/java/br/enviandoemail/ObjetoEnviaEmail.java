package br.enviandoemail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
		
	}
	
	
	public void enviarEmailAnexo(boolean envioHtml) throws Exception{
		
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
		
		
		
		
		/*Parte 1 do email que é texto e a descrição do e-mail*/
		MimeBodyPart corpoEmail = new MimeBodyPart();
		
		if(envioHtml) {
			corpoEmail.setContent(textoEmail, "text/html; charset=utf-8");
		}else {
			corpoEmail.setText(textoEmail);

		}
		
		List<FileInputStream> arquivos = new ArrayList<FileInputStream>();
		arquivos.add(simuladorDePDF());
		arquivos.add(simuladorDePDF());
		arquivos.add(simuladorDePDF());
		arquivos.add(simuladorDePDF());
		
		Multipart multipart = new MimeMultipart();//Esta juntando as duas partes 1 e 2 
		multipart.addBodyPart(corpoEmail);
		
		int index = 0;
		for (FileInputStream fileInputStream : arquivos) {
			
		/*Parte 2 do email que são os anexos do PDF*/
		MimeBodyPart anexoEmail = new MimeBodyPart();
		
		//Onde é passado o simulador de PDF voce passa o arquivo gravado no banco de dados
		anexoEmail.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, 
				"application/pdf")));
		anexoEmail.setFileName("anexoemail" + index +".pdf");
		
		multipart.addBodyPart(anexoEmail);
		
		index++;
		
		}		
		message.setContent(multipart);
				
		Transport.send(message);
}
	
	/**
	 * 
	 *
	 * Esse metodo simulador o PDF ou qualquer arquivo que possa ser enviado por anexo no email.
	 * Voce pode pegar o arquivo no seu banco de dados base64, byte[], Stream de Arquivos.
	 * Pode estar em um banco de dados, ou pasta.
	 * Retornar um PDF em Branco com o texto do Pragrafo de exemplo
	 */
	private FileInputStream simuladorDePDF() throws Exception{
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto e do PDF"));
		document.close();
		
		return new FileInputStream(file);
	}
}
