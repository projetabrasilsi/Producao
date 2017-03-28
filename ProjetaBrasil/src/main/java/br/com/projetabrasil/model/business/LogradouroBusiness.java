package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.LogradouroDAO;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
public class LogradouroBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Logradouro> listar() {
		List<Logradouro> logradouro = null;
		
		try {
			LogradouroDAO logradouroDAO = new LogradouroDAO();			
			logradouro = logradouroDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return logradouro;
		
	}

	public static Logradouro buscaLogradouroPeloNome(String descricao) {
		LogradouroDAO lDAO = new LogradouroDAO();
		descricao = Utilidades.formataString(descricao);
		Logradouro logradouro = lDAO.buscaLogradouroPeloNome(descricao);
		return logradouro;
	}
	
	public static Logradouro buscaLogradouroPeloNomeECidade(String descricao,Cidade c) {
		LogradouroDAO lDAO = new LogradouroDAO();
		descricao = Utilidades.formataString(descricao);
		Logradouro logradouro = lDAO.buscaLogradouroPeloNomeEPelaCidade(descricao, c);
		return logradouro;
	}
	
	public static List<Logradouro> listaLogradouroPelaCidade(Cidade c) {
		List<Logradouro> logradouros = new ArrayList<>();
		LogradouroDAO lDAO = new LogradouroDAO();
		
		logradouros = lDAO.listaLogradouroPelaCidade(c);
		return logradouros;
	}
	
	public static List<Logradouro> listaLogradouros() {
		List<Logradouro> logradouros = new ArrayList<>();
		LogradouroDAO lDAO = new LogradouroDAO();
		
		logradouros = lDAO.listar();
		return logradouros;
	}

	public static Logradouro merge(Logradouro logradouro) {
		LogradouroDAO lDAO = new LogradouroDAO();
		logradouro.setDescricao(Utilidades.formataString(logradouro.getDescricao()));
		Logradouro l = lDAO.merge(logradouro);
		return l;
	}

	
}
