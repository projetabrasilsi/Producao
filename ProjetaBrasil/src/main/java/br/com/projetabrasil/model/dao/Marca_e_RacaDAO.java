package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Classificacao_Objetos;
import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.util.HibernateUtil;

public class Marca_e_RacaDAO extends GenericDAO<Marca_e_Raca> {
	
	public Marca_e_Raca verifica_Marca_e_Raca(Marca_e_Raca m){
		Marca_e_Raca mR = new Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ;
		   	crit.add(Restrictions.eq("enum_Aux_Classificacao_Objetos", m.getEnum_Aux_Classificacao_Objetos()));
		   	crit.add(Restrictions.eq("descricao", m.getDescricao()));
		   	crit.setMaxResults(1);
		   	mR = (Marca_e_Raca) crit.uniqueResult();
		   	return mR;
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return mR;
	}
	
	public Marca_e_Raca verifica_Marca_e_Raca(String descricao){
		Marca_e_Raca mR = new Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try{	
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ;
		   	crit.add(Restrictions.eq("descricao", descricao).ignoreCase());
		   	crit.setMaxResults(1);
		   	
		   	mR = (Marca_e_Raca) crit.uniqueResult();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return mR;
	}
	
	
	public Marca_e_Raca verifica_Marca_e_Raca_Pelo_Codigo(Marca_e_Raca m){
		Marca_e_Raca mR = new Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ; 
		   	crit.add(Restrictions.eq("enum_Aux_Classificacao_Objetos", m.getEnum_Aux_Classificacao_Objetos()));
		   	crit.add(Restrictions.eq("codigo", m.getCodigo()));
		   	crit.setMaxResults(1);
		   	mR = (Marca_e_Raca) crit.uniqueResult();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return mR;
	}
	
	public Marca_e_Raca verifica_Marca_e_Raca_Pelo_Codigo(String codigo){
		Marca_e_Raca mR = new Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{		
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ; 
		   	crit.add(Restrictions.eq("codigo", codigo));
		   	crit.setMaxResults(1);
		   	mR = (Marca_e_Raca) crit.uniqueResult();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return mR;
	}
	
	public Marca_e_Raca verifica_Marca_e_Raca_Pelo_Codigo_E_Pela_Classificacao(String codigo, Enum_Aux_Classificacao_Objetos enum_Aux_Classificacao_Objetos){
		Marca_e_Raca mR = new Marca_e_Raca();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{		
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ; 
		   	crit.add(Restrictions.eq("codigo", codigo));
		   	crit.add(Restrictions.eq("enum_Aux_Classificacao_Objetos", enum_Aux_Classificacao_Objetos));
		   	crit.setMaxResults(1);
		   	mR = (Marca_e_Raca) crit.uniqueResult();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return mR;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Marca_e_Raca> listar_Marca_e_Raca(Marca_e_Raca m){
		
		List<Marca_e_Raca> lista = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{		
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ; 
			crit.add(Restrictions.eq("enum_Aux_Classificacao_Objetos", m.getEnum_Aux_Classificacao_Objetos()));
		   	crit.add(Restrictions.eq("descricao", "%"+m.getDescricao()+"%").ignoreCase());
		   	crit.addOrder(Order.asc("descricao"));
		   	lista = crit.list();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Marca_e_Raca> listar_Marca_e_Raca(String descricao){
		
		List<Marca_e_Raca> lista = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{	   	
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ;
		   	crit.add(Restrictions.eq("descricao", "%"+descricao+"%").ignoreCase());
		   	crit.addOrder(Order.asc("descricao"));
		   	lista = crit.list();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Marca_e_Raca> listar_Marca_e_Raca(Enum_Aux_Classificacao_Objetos classificacao){
		
		List<Marca_e_Raca> lista = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{	   	
			Criteria crit = sessao.createCriteria(Marca_e_Raca.class); ;
		   	crit.add(Restrictions.eq("enum_Aux_Classificacao_Objetos", classificacao));
		   	crit.addOrder(Order.asc("descricao"));
		   	lista = crit.list();		   	
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		finally{
			sessao.close();
		}
		return lista;
	}

}
