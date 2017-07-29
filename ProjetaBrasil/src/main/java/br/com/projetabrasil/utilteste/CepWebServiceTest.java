package br.com.projetabrasil.utilteste;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projetabrasil.util.CepWebService;

public class CepWebServiceTest {
	
	@Test
	@Ignore
	public void testaWebService() {
		CepWebService cep = new CepWebService("88110768");
		System.out.println(cep.toString());
	}
	
	
	
	
}
