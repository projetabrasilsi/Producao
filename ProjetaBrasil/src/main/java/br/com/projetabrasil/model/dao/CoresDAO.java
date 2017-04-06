package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Cor;
import br.com.projetabrasil.util.HibernateUtil;

public class CoresDAO extends GenericDAO<Cor> {
	public Cor verifica_Cor(String descricao){
		Cor cor = new Cor();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Cor.class);
		try{
		crit.add(Restrictions.eq("descricao",descricao).ignoreCase());
		crit.setMaxResults(1);
		cor = (Cor) crit.uniqueResult();
		}catch(RuntimeException e){
			e.printStackTrace();			
		}
		finally {
			sessao.close();
		}
		return cor;
	}
	@SuppressWarnings("unchecked")
	public List<Cor> retornaListaDeCores(){
		List<Cor> cores = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit = sessao.createCriteria(Cor.class);
		try{
			crit.addOrder(Order.asc("descricao"));
			cores = crit.list();
			
		}catch(RuntimeException e){
			e.printStackTrace();
			
		}
		finally {
			sessao.close();
		}
		
		return cores;
		
		
	}

}
