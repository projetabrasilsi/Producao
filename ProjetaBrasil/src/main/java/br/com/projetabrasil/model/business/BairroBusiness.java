package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.BairroDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
public class BairroBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Bairro> listar() {
		List<Bairro> bairro = null;
		
		try {
			BairroDAO bairroDAO = new BairroDAO();			
			bairro = bairroDAO.listar();
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return bairro;
		
	}
	
	public static List<Bairro> listarPelaCidade(Cidade c) {
		BairroDAO bDAO = new BairroDAO();
		return bDAO.listarBairroPelaCidade(c);
	}

	public static Bairro buscaBairroPeloNome(String descricao) {
		BairroDAO bDAO = new BairroDAO();
		descricao = Utilidades.formataString(descricao);
		Bairro bairro = bDAO.buscaBairroPeloNome(descricao);
		return bairro;
	}
	public static Bairro buscaBairroPeloNomeECidade(String descricao, Cidade c) {
		BairroDAO bDAO = new BairroDAO();
		descricao = Utilidades.formataString(descricao);
		Bairro bairro = bDAO.buscaBairroPeloNomeECidade(descricao,c);
		
		return bairro;
	}

	public static Bairro merge(Bairro bairro) {
		BairroDAO bDAO = new BairroDAO();
		bairro.setDescricao(Utilidades.formataString(bairro.getDescricao()));
		Bairro b = bDAO.merge(bairro);
		return b;
	}

	
}
