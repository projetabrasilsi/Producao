package br.com.projetabrasil.utilteste;

import org.junit.Test;

import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.util.GeradorQRCodeOficial2;

public class TestesQr {

	
	@Test
	public void testa() throws Exception {
		GeradorQRCodeOficial2 gera = new GeradorQRCodeOficial2();
		//gera.geraNovosCodigos();
		/*int contador = 101;
		contador = gera.GerandoAsImagens(30f,25f,1.7f,1.7f,contador);
		contador++;		
		gera.GerandoAsImagens(30f,30f,2.1f,2.1f,contador);*/
		
		
		
		
		
	}
	
	@Test
	public void testaQRCODE() throws Exception {
		Objeto obj = new ObjetoDAO().buscar(32l);
		for(Objeto o : new QRCodeDAO().buscaObjetos(Enum_Aux_Status_QRCodes.VENDIDOS, obj)){
			System.out.println(o.toString());
			System.out.println(o.getDescricao());
		}
		
	}

}
