package br.com.projetabrasil.model.business;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


@SuppressWarnings("serial")
public class PessoaBusiness implements Serializable {
	
	
	private static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}
	public static Pessoa identificadorExiste(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pessoa.class);
			consulta.add(Restrictions.like("identificador", pessoa.getIdentificador()));
			Pessoa pessoaExistente = (Pessoa) consulta.uniqueResult();
			return pessoaExistente;

		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public static Pessoa merge(Pessoa pessoa) {
		
		if (pessoa.getId() != null) {
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.EXISTENTE.getMensagem() + "\n"
					+ pessoa.getEnum_Aux_Tipo_Identificador().getDescricao() + ": " + pessoa.getIdentificador() + " - "
					+ pessoa.getDescricao());
		}
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoa = (Pessoa) pessoaDAO.merge(pessoa);
			if (pessoa.getId() != null)
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ALTERACAO.getMensagem());
			else
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.INCLUSAO.getMensagem());
		} catch (RuntimeException erro) {
			if (pessoa.getId() != null)
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRALTERACAO.getMensagem());
			else
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRINCLUSAO.getMensagem());

			erro.printStackTrace();
		}
		return pessoa;
	}

	public static Pessoa registroAtualdaLista(ActionEvent evento) {
		return (Pessoa) evento.getComponent().getAttributes().get("registroAtual");
	}

	public static void excluir(Pessoa pessoa) {
		if (pessoa != null) {
			try {
				PessoaDAO pessoaDAO = new PessoaDAO();
				pessoaDAO.excluir(pessoa);
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.EXCLUSAO.getMensagem());
			} catch (RuntimeException erro) {
				mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERREXCLUSAO.getMensagem());
			}
		}
	}
	public static List<Pessoa> listar(PerfilLogado perfilLogado ) {
		
		List<Pessoa> pessoas = null;
		
		try {
			PessoaDAO pessoaDAO = new PessoaDAO();			
			pessoas = pessoaDAO.listar(perfilLogado);
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		}
		return pessoas;
	}
	public static Pessoa buscaPessoa(Pessoa pessoa) {
		pessoa = PessoaBusiness.identificadorExiste(pessoa);
		if (pessoa==null)
			pessoa = new Pessoa();
		if (pessoa.getEnum_Aux_Tipo_Identificador() == null)
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		return pessoa;
	}
	public static List<Estado> associaEstadosAoPais(Long id) {
		PessoaDAO pDAO = new PessoaDAO();
		List<Estado> estados = pDAO.associaEstadosAoPais(id);
		return estados;
	}
	public static List<Cidade> associaCidadesAoEstado(Long id) {
		PessoaDAO pDAO = new PessoaDAO();
		List<Cidade> cidades = pDAO.associaCidadesAoEstado(id);
		return cidades;
	}
	public static List<Bairro> associaBairrosACidade(Long id) {
		PessoaDAO pDAO = new PessoaDAO();
		List<Bairro> bairros = pDAO.associaBairrosACidade(id);
		return bairros;
	}
	public static List<Logradouro> associaLogradourosACidade(Long id) {
		PessoaDAO pDAO = new PessoaDAO();
		List<Logradouro> logradouros = pDAO.associaLogradourosACidade(id);
		return logradouros;
	}
	
	

}
