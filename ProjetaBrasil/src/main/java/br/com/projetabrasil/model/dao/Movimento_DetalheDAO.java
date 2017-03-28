package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Item_de_Movimento;
import br.com.projetabrasil.model.entities.Movimento_Detalhe;
import br.com.projetabrasil.model.entities.Movimento_Mestre;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class Movimento_DetalheDAO extends GenericDAO<Movimento_Detalhe> {
	@SuppressWarnings("unchecked")
	public List<Movimento_Detalhe> listarPeloResponsavel(Pessoa id_Pessoa_Resposnavel, Movimento_Mestre id_Movimento_Mestre){
		List<Movimento_Detalhe> lista = new ArrayList<Movimento_Detalhe>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Movimento_Detalhe.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Resposnavel",id_Pessoa_Resposnavel));
					crit.add(Restrictions.eq("id_Movimento_Mestre",id_Movimento_Mestre));
			lista = crit.list();
		}catch(RuntimeException error){
	      error.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<Movimento_Detalhe> listarPeloMestre(Movimento_Mestre id_Movimento_Mestre, 
			Pessoa id_Pessoa_Assinante, Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento){
		List<Movimento_Detalhe> lista = new ArrayList<Movimento_Detalhe>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		DetachedCriteria subQuery1 = null;
		DetachedCriteria subQuery2 = null;
		Criteria crit;
		try{
			crit = sessao.createCriteria(Movimento_Detalhe.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if(id_Movimento_Mestre!=null)
					crit.add(Restrictions.eq("id_Movimento_Mestre",id_Movimento_Mestre));
			
			if(id_Pessoa_Assinante!=null){
				
				subQuery1 = DetachedCriteria.forClass(Movimento_Mestre.class)
					    .setProjection(Property.forName("id"));			    
					subQuery1.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));		
					if(enum_Aux_Tipo_Item_de_Movimento!=null){
						subQuery2 = DetachedCriteria.forClass(Item_de_Movimento.class)
							    .setProjection(Property.forName("id"));			    
							subQuery2.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento",enum_Aux_Tipo_Item_de_Movimento));
														 	
							crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
							crit.add(Restrictions.and(Subqueries.propertyIn("id_Movimento_Mestre", subQuery1),
									Subqueries.propertyIn("id_Item_de_Movimento", subQuery2)));
							
					}
					else{
						crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
						crit.add(Restrictions.and(Subqueries.propertyIn("id_Movimento_Mestre", subQuery1)));
					}
					
			}else{
				if(enum_Aux_Tipo_Item_de_Movimento!=null){
					subQuery2 = DetachedCriteria.forClass(Item_de_Movimento.class)
						    .setProjection(Property.forName("id"));			    
						subQuery2.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento",enum_Aux_Tipo_Item_de_Movimento));
													 	
						crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
						crit.add(Restrictions.and(Subqueries.propertyIn("id_Item_de_Movimento", subQuery2)));
				}
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
}
