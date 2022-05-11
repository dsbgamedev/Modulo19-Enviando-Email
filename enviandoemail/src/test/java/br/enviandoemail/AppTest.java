package br.enviandoemail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("ola apenas teste <br/><br/>");
		stringBuilderTextoEmail.append("Estamos fazendo varios testes<br/><br/>");
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("douglas.bocatto@gmail.com", 
															"Douglas JDev",
														    "Testando email",
														    stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmail(true);
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
		
		return new FileInputStream(file);
	}

}
