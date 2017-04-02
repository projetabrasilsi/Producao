package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Objetos_Acesso;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;

public class Objetos_AcessoDAO extends GenericDAO<Objetos_Acesso> {
	public Objetos_Acesso verificaObjetoAcessoPorPessoa(Pessoa pessoa, Objetos_Acesso objeto) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Objetos_Acesso.class);
			consulta.add(Restrictions.eq("pessoa", pessoa));
			consulta.add(Restrictions.eq("objetos_acesso", objeto));
			return (Objetos_Acesso) consulta.setMaxResults(1).uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public List<Objetos_Acesso> listarObjetoAcessoPorPessoa(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Objetos_Acesso.class);
			consulta.add(Restrictions.eq("pessoa", pessoa));
			List<Objetos_Acesso> lista = consulta.list();
			return lista;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
