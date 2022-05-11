package br.enviandoemail;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	
	@org.junit.Test
	public void testeEmail() throws Exception{
		
		ObjetoEnviaEmail enviaEmail = new ObjetoEnviaEmail("douglas.bocatto@gmail.com", 
															"Douglas JDev",
														    "Testando email",
														    "texo descrição email");
		
		enviaEmail.enviarEmail();
	}

}
