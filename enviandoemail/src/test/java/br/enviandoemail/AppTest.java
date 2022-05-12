package br.enviandoemail;



/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("ola apenas teste <br/><br/>");
		stringBuilderTextoEmail.append("Fazendo varios testes<br/><br/>");
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("douglas.bocatto@gmail.com, moraes@cbadiesel.com.br", 
															"Douglas JDev",
														    "Testando email",
														    stringBuilderTextoEmail.toString());
		
		enviaEmail.enviarEmailAnexo(true);
	}
	

}
