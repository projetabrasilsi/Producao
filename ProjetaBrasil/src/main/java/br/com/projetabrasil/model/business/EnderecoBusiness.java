package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.List;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;


@SuppressWarnings("serial")
public class EnderecoBusiness implements Serializable {
	
	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	
	public static List<Endereco> listar(PerfilLogado perfilLogado) {
		List<Endereco> endereco = null;
		
		try {
			EnderecoDAO enderecoDAO = new EnderecoDAO();			
			endereco = enderecoDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return endereco;
		
	}

	public static void merge(Endereco endereco) {
		EnderecoDAO eDAO = new EnderecoDAO();
		try {
			Endereco e = verificaEndereco(endereco);
			if(e == null){
				eDAO.merge(endereco);
			}else{
				mensagensDisparar("Usuário já possui este endereço");
			}			
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		}
		
	}

	public static Endereco buscaEnderecoPorPessoa(Pessoa pessoa) {
		Endereco endereco = new EnderecoDAO().buscaEnderecoPorPessoa(pessoa);
		return endereco;
	}
	
	public static Endereco verificaEndereco(Endereco endereco){
		Endereco e = new EnderecoDAO().verificaEndereco(endereco);
		return e;
	}
}
