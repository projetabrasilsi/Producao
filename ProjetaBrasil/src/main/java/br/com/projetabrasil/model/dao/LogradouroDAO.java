package br.com.projetabrasil.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.util.HibernateUtil;


public class LogradouroDAO extends GenericDAO<Logradouro>{

	public Logradouro buscaLogradouroPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Logradouro.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Logradouro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	public Logradouro buscaLogradouroPeloNomeEPelaCidade(String descricao,Cidade c) {
		if(c!=null &&c.getId()==null)
			c = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Logradouro.class);
			consulta.add(Restrictions.eq("cidade", c));
			consulta.add(Restrictions.like("descricao", descricao));
			consulta.addOrder(Order.asc("descricao"));
			consulta.setMaxResults(1);
			return (Logradouro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Logradouro> listaLogradouroPelaCidade(Cidade c) {
		if(c!=null &&c.getId()==null)
			c = null;
		
		List<Logradouro> logradouros = new ArrayList<>();
		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Logradouro.class);
			consulta.add(Restrictions.eq("cidade", c));			
			consulta.addOrder(Order.asc("descricao"));
			
			logradouros = consulta.list();
			return logradouros;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	

}
