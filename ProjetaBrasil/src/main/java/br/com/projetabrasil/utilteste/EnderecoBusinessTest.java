package br.com.projetabrasil.utilteste;

import org.junit.Test;

import br.com.projetabrasil.model.business.EnderecoBusiness;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Pessoa;

public class EnderecoBusinessTest {
	
	@Test
	public void testaBuscaPorEndereco(){
		Pessoa pessoa = new Pessoa();
		pessoa.setId(17l);
		Endereco endereco = EnderecoBusiness.buscaEnderecoPorPessoa(pessoa);
		System.out.println(endereco.toString());
	}
}
