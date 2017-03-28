package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
public class CidadeBusiness implements Serializable {
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Cidade> listar() {
		List<Cidade> cidade  = new ArrayList<>();
		
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();			
			cidade = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return cidade;
		
	}
	
	public static List<Cidade> listarPeloEstado(Estado e) {
		List<Cidade> cidades = new ArrayList<>();
		
		try {
			CidadeDAO cidadeDAO = new CidadeDAO();			
			cidades = cidadeDAO.buscaCidadePeloEstado(e);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return cidades;
		
	}

	public static Cidade buscaCidade(Cidade cidade) {
		CidadeDAO cDAO = new CidadeDAO();
		Cidade c = cDAO.buscaCidadeporDescricaoEEstado(cidade);
		return c;
	}
	
	public static Cidade buscaCidadePeloNome(String descricao) {
		CidadeDAO cDAO = new CidadeDAO();
		descricao = Utilidades.formataString(descricao);
		Cidade c = cDAO.buscaCidadePeloNome(descricao);
		return c;
	}
	public static Cidade buscaCidadePeloNomeEEstado(Estado e, String cid) {
		CidadeDAO cDAO = new CidadeDAO();
		
		Cidade c = cDAO.buscaCidadePeloNomeEEstado(e,cid);
		return c;
	}

	public static Cidade merge(Cidade cidade) {
		CidadeDAO cDAO = new CidadeDAO();
		cidade.setDescricao(Utilidades.formataString(cidade.getDescricao()));
		Cidade c = cDAO.merge(cidade);
		return c;
	}
}
