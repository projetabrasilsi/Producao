package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.model.entities.Modelo_de_Marca_e_Raca;
import br.com.projetabrasil.util.HibernateUtil;

public class Modelo_de_Marca_e_RacaDAO extends GenericDAO<Modelo_de_Marca_e_Raca> {
	
	
	

	public Modelo_de_Marca_e_Raca verifica_Modelo_de_Marca_e_Por_Marca_e_Raca_e_Descricao_e_Tipo_de_Objeto(Modelo_de_Marca_e_Raca m){
		Modelo_de_Marca_e_Raca mod = new Modelo_de_Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			DetachedCriteria subQuery1 = null;
			subQuery1 = DetachedCriteria.forClass(Marca_e_Raca.class)
				    .setProjection(Property.forName("id"));		
			subQuery1.add(Restrictions.eq("enum_Aux_Classificacao_Objetos",m.getMarca_e_Raca().getEnum_Aux_Classificacao_Objetos()));
			
			
			Criteria crit = sessao.createCriteria(Modelo_de_Marca_e_Raca.class); ;
		    crit.add(Restrictions.eq("marca_e_Raca",m.getMarca_e_Raca()));
		    crit.add(Restrictions.and(Subqueries.propertyIn("marca_e_Raca", subQuery1)));
		    crit.add(Restrictions.eq("descricao",m.getDescricao()));
		    crit.setMaxResults(1);
		    mod = (Modelo_de_Marca_e_Raca) crit.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}finally {
			sessao.close();
		}
		return mod;
	}
	
	public Modelo_de_Marca_e_Raca verifica_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca_e_Descricao(String descricao){
		Modelo_de_Marca_e_Raca mod = new Modelo_de_Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{		
			Criteria crit = sessao.createCriteria(Modelo_de_Marca_e_Raca.class); ;
		    crit.add(Restrictions.eq("descricao",descricao));
		    crit.setMaxResults(1);
		    mod = (Modelo_de_Marca_e_Raca) crit.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();			
		}finally {
			sessao.close();
		}
		return mod;
	}
	
	public Modelo_de_Marca_e_Raca verifica_Modelo_de_Marca_e_Raca_Por_Codigo_e_Marca_e_Raca(Modelo_de_Marca_e_Raca m){
		Modelo_de_Marca_e_Raca mod = new Modelo_de_Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Modelo_de_Marca_e_Raca.class); ;
		    crit.add(Restrictions.eq("marca_e_Raca",m.getMarca_e_Raca()));
		    crit.add(Restrictions.eq("codigo",m.getCodigo()));
		    crit.setMaxResults(1);
		    mod = (Modelo_de_Marca_e_Raca) crit.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}finally {
			sessao.close();
		}
		return mod;
	}
	
	@SuppressWarnings("unchecked")
	public List<Modelo_de_Marca_e_Raca>  listar_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca_e_Descricao(Modelo_de_Marca_e_Raca m){		
		List<Modelo_de_Marca_e_Raca> lista = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			DetachedCriteria subQuery1 = null;
			subQuery1 = DetachedCriteria.forClass(Marca_e_Raca.class)
				    .setProjection(Property.forName("id"));		
			subQuery1.add(Restrictions.eq("enum_Aux_Classificacao_Objetos",m.getMarca_e_Raca().getEnum_Aux_Classificacao_Objetos()));
			
			Criteria crit = sessao.createCriteria(Modelo_de_Marca_e_Raca.class); ;
		    crit.add(Restrictions.eq("marca_e_Raca",m.getMarca_e_Raca()));
		    crit.add(Restrictions.and(Subqueries.propertyIn("marca_e_Raca", subQuery1)));
		    crit.addOrder(Order.asc("descricao"));
		    lista = crit.list();
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}finally {
			sessao.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Modelo_de_Marca_e_Raca>  listar_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca(Marca_e_Raca m){		
		List<Modelo_de_Marca_e_Raca> lista = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Modelo_de_Marca_e_Raca.class); ;
		    crit.add(Restrictions.eq("marca_e_Raca",m));
		    crit.addOrder(Order.asc("descricao"));
		    lista = crit.list();
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}finally {
			sessao.close();
		}
		return lista;
	}

}
