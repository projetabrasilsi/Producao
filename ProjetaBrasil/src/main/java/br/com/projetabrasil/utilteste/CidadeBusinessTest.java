package br.com.projetabrasil.utilteste;

import java.util.List;

import org.junit.Test;

import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Pais;

public class CidadeBusinessTest {
	
	@Test
	public void testaAssociacaoDeEstados(){
		Pais p = new Pais();
		p.setDescricao("Brasil");
		
		EstadoDAO eDAO = new EstadoDAO();
		List<Estado> estados = eDAO.buscaEstadoPorPais(p);
				
		
		System.out.println("");
		for(Estado estado : estados) {
			System.out.println(estado.toString());
		}
		
	}
	
}
