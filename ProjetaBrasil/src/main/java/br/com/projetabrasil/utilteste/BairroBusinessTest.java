package br.com.projetabrasil.utilteste;

import org.junit.Test;

import br.com.projetabrasil.model.business.BairroBusiness;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Estado;

public class BairroBusinessTest {
	
	@Test
	public void testaAssociacaoDeCidadeComEstado(){
		Estado estado = new Estado();
//		estado.setCidades(BairroBusiness.associaCidadesAoEstado(11l));
		System.out.println(estado.toString());
	}
	
	@Test
	public void testaBuscaBairro(){
		Bairro b = BairroBusiness.buscaBairroPeloNome("Roçado");
		System.out.println(b.toString());
	}
}
