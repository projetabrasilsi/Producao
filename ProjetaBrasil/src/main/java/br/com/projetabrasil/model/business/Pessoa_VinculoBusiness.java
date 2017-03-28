package br.com.projetabrasil.model.business;

import java.io.Serializable;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;

@SuppressWarnings("serial")
public class Pessoa_VinculoBusiness implements Serializable {
	
	public static Pessoa_Vinculo merge(Pessoa_Vinculo pV){
		Pessoa_VinculoDAO pessoa_Vinculo= new Pessoa_VinculoDAO();
		pV = pessoa_Vinculo.merge(pV);			 
		return pV;
	}
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

}
