package br.com.projetabrasil.model.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.util.HibernateUtil;


public class PaisDAO extends GenericDAO<Pais> {
	
	public Pais buscaPaisPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pais.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Pais) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<Pais> retornaPaisesEmOrdemAlfabetica() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pais.class);
			consulta.addOrder(Order.asc("descricao"));
			return consulta.list();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
}