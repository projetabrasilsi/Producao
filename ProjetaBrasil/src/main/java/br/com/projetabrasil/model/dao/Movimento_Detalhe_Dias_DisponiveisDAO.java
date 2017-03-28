package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Movimento_Detalhe_A;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_Dias_Disponiveis;
import br.com.projetabrasil.util.HibernateUtil;


public class Movimento_Detalhe_Dias_DisponiveisDAO extends GenericDAO<Movimento_Detalhe_Dias_Disponiveis> {
	@SuppressWarnings("unchecked")
	public List<Movimento_Detalhe_Dias_Disponiveis> retornaDiasDisponiveis(Movimento_Detalhe_A id_MDA){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		List<Movimento_Detalhe_Dias_Disponiveis> diasDisponiveis = null;
		Criteria crit = sessao.createCriteria(Movimento_Detalhe_Dias_Disponiveis.class);
		try{
			crit.add(Restrictions.eq("id_Movimento_Detalhe_A",id_MDA));
			diasDisponiveis = crit.list(); 
			
		}catch(RuntimeException error){
			error.printStackTrace();
			
		}
		finally {
			sessao.close();
		}
		return diasDisponiveis;
	}

}
