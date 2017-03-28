package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;

public class ContatoDAO extends GenericDAO<Contato> {
	
	@SuppressWarnings("unchecked")
	public List<Contato> listardeContatosporPessoa(Pessoa p) {
		List<Contato> contatos = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Contato.class);
			consulta.add(Restrictions.eq("id_pessoa",p));
			contatos = consulta.list();
			return contatos;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Contato> listardeContatosporContato(String contato) {
		List<Contato> contatos = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Contato.class);
			consulta.add(Restrictions.like("contato", contato));
			contatos = consulta.list();
			return contatos;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Contato buscarContatoPelaPessoaEContato(Pessoa p, String contato) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Contato.class);
			consulta.add(Restrictions.eq("id_pessoa",p));
			consulta.add(Restrictions.like("contato", contato));
			consulta.setMaxResults(1);
			return (Contato) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Contato buscaContatoExistenteeQueSejaDeOutraPessoa(Pessoa p, String contato) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Contato.class);
			//ne --> busca um contato existente e que seja diferente de id_pessoa;
			consulta.add(Restrictions.ne("id_pessoa",p));
			consulta.add(Restrictions.like("contato", contato));
			consulta.setMaxResults(1);
			return (Contato) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
