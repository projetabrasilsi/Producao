package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Combo_Mestre;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class Combo_MestreDAO extends GenericDAO<Combo_Mestre> {
	@SuppressWarnings("unchecked")
	public List<Combo_Mestre> listar(Pessoa id_Pessoa_Assinante){
		List<Combo_Mestre> lista = new ArrayList<Combo_Mestre>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		try{
			crit = sessao.createCriteria(Combo_Mestre.class)
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
