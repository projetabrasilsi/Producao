package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.util.HibernateUtil;


public class CidadeDAO extends GenericDAO<Cidade> {
	
	
	
	public Cidade buscaCidadeporDescricaoEEstado(Cidade cidade) {
		if(cidade!=null && cidade.getId()==null)
			cidade = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Cidade.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("estado",cidade.getEstado()));
					crit.add(Restrictions.like("descricao",cidade.getDescricao()));
					crit.setMaxResults(1);
			return (Cidade) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> buscaCidadePeloEstado(Estado e) {
		
		if(e!=null && e.getId()==null)
			e = null;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Cidade> cidades = new ArrayList<>();
		try{	
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado",e));
			consulta.addOrder(Order.asc("descricao"));
			cidades = consulta.list();
			return cidades;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}

	public Cidade buscaCidadePeloNome(String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.like("descricao", descricao));
			return (Cidade) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	public Cidade buscaCidadePeloNomeEEstado(Estado e,  String descricao) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado", e));
			consulta.add(Restrictions.like("descricao", descricao));
			return (Cidade) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}


}
