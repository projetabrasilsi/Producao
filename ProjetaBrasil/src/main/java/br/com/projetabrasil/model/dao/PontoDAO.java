package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Ponto;
import br.com.projetabrasil.util.HibernateUtil;


public class PontoDAO extends GenericDAO<Ponto>{
	List<Ponto> lista;
	@SuppressWarnings("unchecked")
	public List<Ponto> retornarListaPontoConfig(Pessoa usuarioLogado,Enum_Aux_Tipo_Item_de_Movimento tipo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			        crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", tipo));
			lista =  (List<Ponto>)  crit.list();
			return lista;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	public Ponto retornaPrimeiroPonto(Pessoa estab,Enum_Aux_Tipo_Item_de_Movimento tipo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Assinante", estab));
			        crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", tipo));
			        crit.addOrder(Order.desc("id"));
			        crit.setMaxResults(1);
			Ponto p = new Ponto();
			p = (Ponto) crit.uniqueResult();
			
			return p;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	
	
	
	public List<Ponto> retornarListaPontos(Pessoa usuarioLogado, Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			        crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			        crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", enum_Aux_Tipo_Item_de_Movimento));
			lista =  (List<Ponto>)  crit.list();
			return lista;
		}catch(RuntimeException erro){
			
			erro.printStackTrace();
			
			throw erro;
		}
		
	}
	
	public Ponto retornarPonto(Pessoa usuarioLogado){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class)
					.setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id",  usuarioLogado.getId()));
			Criteria crit = sessao.createCriteria(Ponto.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id_Pessoa_Asssinante", subQuery1)));
			crit.add(Restrictions.eq("id_Pessoa_Assinante", usuarioLogado));
			return (Ponto) crit.uniqueResult();
		}catch(RuntimeException erro){
			erro.printStackTrace();
			throw erro;
		}
		
	}
	
}
