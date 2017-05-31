package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.entities.Pessoa;
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
	
	public static List<Pessoa> listarVeterinariosDaClinica(Pessoa p){
		List<Pessoa_Vinculo> vinculos = new Pessoa_VinculoDAO().listarVeterinariosDaClinica(p);
		List<Pessoa> veterinariosDaClinica = new ArrayList<Pessoa>();
		for(Pessoa_Vinculo pV : vinculos){
			veterinariosDaClinica.add(pV.getId_pessoa_d());
		}
		return veterinariosDaClinica;
	}

}
