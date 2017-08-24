package br.com.projetabrasil.utilteste;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.util.GeradorQRCodeOficial;
import br.com.projetabrasil.util.Utilidades;

public class TestesQr {
	
	
	@Test
	public void testando(){
		String data = ""+Utilidades.retornaDataDoDiaString();
		System.out.println(data);
		System.out.println(""+Utilidades.retornaCalendario());
	}

	@Ignore
	@Test
	public void testa() throws Exception {
		GeradorQRCodeOficial gera = new GeradorQRCodeOficial();
		gera.geraNovosCodigos();
		/*int contador = 101;
		contador = gera.GerandoAsImagens(30f,25f,1.7f,1.7f,contador);
		contador++;		
		gera.GerandoAsImagens(30f,30f,2.1f,2.1f,contador);*/
		
		
		
		
		
	}
	@Ignore
	@Test
	public void alteraQrCode(){
		QRCode qr = new QRCode();
		QRCode qr2 = new QRCode();
		QRCodeDAO qrDAO = new QRCodeDAO();
		
		
		List<Long> idLista = new ArrayList<>();
		idLista.add(102l);
		idLista.add(106l);
		idLista.add(118l);
		idLista.add(112l);
		idLista.add(122l);
		idLista.add(128l);
		idLista.add(124l);
		idLista.add(113l);
		idLista.add(120l);
		idLista.add(114l);
		idLista.add(129l);

		for (Long s : idLista) {			
			
			System.out.println("QR Code "+s);			
			qr = qrDAO.buscaCodersId(s);
			System.out.println("status inicial: "+qr.getId()+" - "+qr.getStatus() );
			if(qr !=null && qr.getId()!=null){
				
				qr.setStatus(Enum_Aux_Status_QRCodes.LIVRES);
				qr.setUltimaAtualizacao(Utilidades.retornaCalendario());
				
				
								
				
				qr = qrDAO.merge(qr);
				System.out.println("status final: "+qr.getId()+" - "+qr.getStatus() );
				System.out.println("atualizado");
				
			}
			
		}
		
		
		
		
	}
	
	/*QRCode qr = new QRCode();*/

}
