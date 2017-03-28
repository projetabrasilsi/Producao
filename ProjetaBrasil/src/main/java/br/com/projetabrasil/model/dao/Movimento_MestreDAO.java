package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Movimento_Mestre;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.util.HibernateUtil;


public class Movimento_MestreDAO extends GenericDAO<Movimento_Mestre> {
	@SuppressWarnings("unchecked")
	public List<Movimento_Mestre> listarMovimento(Pessoa id_Pessoa_Assinante, Pessoa id_Pessoa_Registro,
			Pessoa id_Pessoa_Supervisor){
		List<Movimento_Mestre> lista = new ArrayList<Movimento_Mestre>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Criteria crit;
		DetachedCriteria subQuery1 = null;
		try{
			crit = sessao.createCriteria(Movimento_Mestre.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (id_Pessoa_Assinante!=null)
			crit.add(Restrictions.eq("id_Pessoa_Assinante",id_Pessoa_Assinante));
			else
			if (id_Pessoa_Registro!=null)
				crit.add(Restrictions.eq("id_Pessoa_Registro",id_Pessoa_Registro));
			else
			if(id_Pessoa_Supervisor!=null){
				subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class)
					    .setProjection(Property.forName("id_pessoa_d"));			    
					subQuery1.add(Restrictions.eq("id_pessoa_m",id_Pessoa_Supervisor));			
					crit = sessao.createCriteria(Movimento_Mestre.class)
							    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
							    .add(Restrictions.and(Subqueries.propertyIn("id_Pessoa_Registro", subQuery1)));
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
