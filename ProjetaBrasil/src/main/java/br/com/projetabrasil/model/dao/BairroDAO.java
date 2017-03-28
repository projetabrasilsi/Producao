package br.com.projetabrasil.model.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.util.HibernateUtil;


public class BairroDAO extends GenericDAO<Bairro> {

	public Bairro buscaBairroPeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Bairro.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Bairro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Bairro buscaBairroPeloNomeECidade(String descricao, Cidade c) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		if(c!=null &&c.getId()==null)
			c = null;
		
		try {
			Criteria consulta = sessao.createCriteria(Bairro.class);
			consulta.add(Restrictions.like("descricao", descricao));
			consulta.add(Restrictions.eq("cidade", c));
			consulta.setMaxResults(1);
			return (Bairro) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Bairro> listarBairroPelaCidade(Cidade c) {
		if(c!=null &&c.getId()==null)
			c = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Bairro> bairros = new ArrayList<>();
		try {
			Criteria consulta = sessao.createCriteria(Bairro.class);
			consulta.add(Restrictions.eq("cidade", c));
			consulta.addOrder(Order.asc("descricao"));
			bairros = consulta.list();
			
			return bairros;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	
	
	

}


