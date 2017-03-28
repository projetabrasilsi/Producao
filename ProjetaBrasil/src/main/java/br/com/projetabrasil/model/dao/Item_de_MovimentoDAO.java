package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Item_de_Movimento;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class Item_de_MovimentoDAO extends GenericDAO<Item_de_Movimento> {
	
	@SuppressWarnings("unchecked")
	public List<Item_de_Movimento> listar(Pessoa id_Pessoa_Assinante, Enum_Aux_Tipo_Item_de_Movimento tipodeItem,
			List<Enum_Aux_Tipo_Item_de_Movimento> foraTipodeItem){
		List<Item_de_Movimento> lista = new ArrayList<Item_de_Movimento>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Item_de_Movimento.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));
			        if(tipodeItem!=null)
					crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento",tipodeItem));
			        if(foraTipodeItem!=null){
			        	 crit.add(Restrictions.not(
			        			    Restrictions.in("enum_Aux_Tipo_Item_de_Movimento",foraTipodeItem)));
			        	
			        	}
			        	
			
			
			                              
			lista = crit.list();
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}
	public Item_de_Movimento retornaItemPelaReferencia(
			Pessoa id_Pessoa_Registro, Pessoa id_Pessoa_Assinante,String referencia,
			Enum_Aux_Tipo_Item_de_Movimento tipo ){
	 
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		Item_de_Movimento item = new Item_de_Movimento(id_Pessoa_Registro, id_Pessoa_Assinante, 
				Enum_Aux_Sim_ou_Nao.SIM,
			tipo);
		try{
			crit = sessao.createCriteria(Item_de_Movimento.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante))
					.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento",tipo))					
					.add(Restrictions.eq("referencia",referencia));
			item = (Item_de_Movimento) crit.uniqueResult();
			
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		
		return item;
	}
	public int retornaUltimaReferencia(Pessoa id_Pessoa_Assinante,
			Enum_Aux_Tipo_Item_de_Movimento tipo ){
	 
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		Integer ultimaReferencia = 0 ; 
			
		try{
			crit = sessao.createCriteria(Item_de_Movimento.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante))
					.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento",tipo));
			crit.setProjection(Projections.max("ultimaReferencia"));
			ultimaReferencia = (Integer) crit.uniqueResult();
			
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		if(ultimaReferencia==null)
			ultimaReferencia = 0;
		++ultimaReferencia;
		        
		
		return  ultimaReferencia; 
		
	}
	@SuppressWarnings("unchecked")
	public List<Item_de_Movimento> listar(Pessoa id_Pessoa_Assinante){
		List<Item_de_Movimento> lista = new ArrayList<Item_de_Movimento>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Item_de_Movimento.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));
			lista = crit.list();
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}

}
