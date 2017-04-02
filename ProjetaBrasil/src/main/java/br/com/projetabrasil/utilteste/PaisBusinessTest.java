package br.com.projetabrasil.utilteste;

import org.junit.Test;

import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.Pessoa;

public class PaisBusinessTest {
	
	@Test
	public void testaBuscaDePais(){
		Pais pais = new Pais();
		pais = PaisBusiness.buscaPaisPeloNome("bRaSil");
		System.out.println(pais.toString());
	}
	
	public void testaInsertDePais() {
		Pais pais = new Pais();
		pais.setDescricao("Brasil");
		pais.setId(null);
		pais.setId_Empresa(0);
		pais.setMaskTel("Teste2");
		pais.setMaskZip("Teste");
		pais.setSigla("BR");
		Pessoa p = new PessoaDAO().retornaPelaIdentificacao("10554498928");
		pais.setId_Pessoa_Registro(p);
		PaisBusiness.merge(pais);
	}
}
