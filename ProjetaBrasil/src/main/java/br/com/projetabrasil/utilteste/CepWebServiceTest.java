package br.com.projetabrasil.utilteste;

import org.junit.Test;

import br.com.projetabrasil.util.CepWebService;

public class CepWebServiceTest {
	
	@Test
	public void testaWebService() {
		CepWebService cep = new CepWebService("88110768");
		System.out.println(cep.toString());
		
	}
}
