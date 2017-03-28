package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Combo_Detalhe;
import br.com.projetabrasil.model.entities.Combo_Mestre;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class Combo_DetalheDAO extends GenericDAO<Combo_Detalhe> {
	@SuppressWarnings("unchecked")
	public List<Combo_Detalhe> listar(Pessoa id_Pessoa_Assinante, Combo_Mestre iCM){
		List<Combo_Detalhe> lista = new ArrayList<Combo_Detalhe>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		
		
		
		try{
			crit = sessao.createCriteria(Combo_Detalhe.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante))
					.add(Restrictions.eq("id_Combo_Mestre",iCM));
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
