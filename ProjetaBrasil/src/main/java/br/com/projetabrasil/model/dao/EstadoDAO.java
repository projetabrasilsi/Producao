package br.com.projetabrasil.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.util.HibernateUtil;


public class EstadoDAO extends GenericDAO<Estado> {

	public Estado buscaEstado(Estado estado) {
		if(estado!=null && estado.getId()==null)
			estado = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Estado.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("pais",estado.getPais()));			        
					crit.add(Restrictions.eq("descricao",estado.getDescricao()));
			return (Estado) crit.setMaxResults(1).uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Estado buscaEstadoPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	public Estado buscaEstadoPeloNome(String descricao, Pais p) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("descricao", descricao));
			consulta.add(Restrictions.like("pais", p));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Estado> buscaEstadoPorPais(Pais p) {
		if(p!=null && p.getId()==null)
			p = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.eq("pais", p));
			consulta.addOrder(Order.asc("descricao"));
			
			List<Estado> estados = new ArrayList();
			
					estados = consulta.list();
			return estados;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Estado buscaEstadoPelaSigla(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("sigla", descricao));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	public Estado buscaEstadoPelaSigla(String descricao, Pais p) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Estado.class);
			consulta.add(Restrictions.like("sigla", descricao));
			consulta.add(Restrictions.like("pais", p));
			return (Estado) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
