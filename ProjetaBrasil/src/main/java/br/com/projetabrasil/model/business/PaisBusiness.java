package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.Utilidades;



@SuppressWarnings("serial")
public class PaisBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	public static Pais VerificaPaisPadrao(String descricao,String sigla, PerfilLogado perfil){
		Pessoa pes;
		Pais p = buscaPaisPeloNome(descricao);
		if(p == null){
			p = new Pais();
			p.setDescricao(descricao);
			p.setId_Empresa(1);
			p.setSigla(sigla);
			p.setUltimaAtualizacao(Utilidades.retornaCalendario());
			if (perfil.getAssLogado()!=null && perfil.getAssLogado().getId()!=null)
				pes = perfil.getAssLogado();
				else
				pes = perfil.getUsLogado().getPessoa();
			p.setId_Pessoa_Registro(pes);
			p = merge(p);			
		}
		return p;
			
		
	}

	public static List<Pais> listar() {
		List<Pais> paises = null;
		
		try {
			PaisDAO paisDAO = new PaisDAO();			
			paises = paisDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return paises;
		
	}

	public static Pais buscaPaisPeloNome(String descricao) {
		PaisDAO pDAO = new PaisDAO();
		Pais pais = pDAO.buscaPaisPeloNome(descricao);
		return pais;
	}
	

	public static Pais merge(Pais pais) {
		PaisDAO pDAO = new PaisDAO();
		pais = pDAO.merge(pais);
		return pais;
	}
	
	
}
