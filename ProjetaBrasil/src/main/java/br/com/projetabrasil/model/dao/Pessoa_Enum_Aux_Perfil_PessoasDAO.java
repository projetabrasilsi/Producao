package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class Pessoa_Enum_Aux_Perfil_PessoasDAO extends GenericDAO<Pessoa_Enum_Aux_Perfil_Pessoa> {

	public Pessoa_Enum_Aux_Perfil_Pessoa isPerfilExiste(Pessoa_Enum_Aux_Perfil_Pessoa pPerfil) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Pessoa_Enum_Aux_Perfil_Pessoa pessoaPerfil = pPerfil;
		try {
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class)
			.setProjection(Property.forName("id"));
			
			subQuery1.add(Restrictions.eq("id",pPerfil.getId_pessoa().getId().longValue()));
				    
			
				Criteria crit = sessao.createCriteria(Pessoa_Enum_Aux_Perfil_Pessoa.class)
				    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				    .add(Restrictions.and(
				        Subqueries.propertyIn("id_pessoa", subQuery1)));
				  crit.add(Restrictions.eq("enum_Aux_Perfil_Pessoa",pPerfil.getEnum_Aux_Perfil_Pessoa() ));  
		 
				  return (Pessoa_Enum_Aux_Perfil_Pessoa) crit.uniqueResult();
			
			
			
			
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
		finally {
			sessao.close();
		}
		return pessoaPerfil;
		
		
	}

	public Enum_Aux_Perfil_Pessoa retornaPerfil(Pessoa_Enum_Aux_Perfil_Pessoa pPerfil) {
		Enum_Aux_Perfil_Pessoa perf;
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Pessoa_Enum_Aux_Perfil_Pessoa.class);
			crit.createAlias("pessoa", "p");
			crit.add(Restrictions.eq("p.id", pPerfil.getId_pessoa().getId()));
			crit.add(Restrictions.like("enum_Aux_Perfil_Pessoa", pPerfil.getEnum_Aux_Perfil_Pessoa()));
			Pessoa_Enum_Aux_Perfil_Pessoa pf = (Pessoa_Enum_Aux_Perfil_Pessoa) crit.uniqueResult();
			perf = pf.getEnum_Aux_Perfil_Pessoa();
			return perf;
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa_Enum_Aux_Perfil_Pessoa> retornaListaPerfil(Pessoa pessoa) {
		List<Pessoa_Enum_Aux_Perfil_Pessoa> pf = null;
		

		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class)
				    .setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id",pessoa.getId()));
				    
				
				Criteria crit = sessao.createCriteria(Pessoa_Enum_Aux_Perfil_Pessoa.class)
				    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				    .add(Restrictions.and(Subqueries.propertyIn("id_pessoa", subQuery1)));
			
			pf = crit.list();
			
			
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
		return pf;
	}
	
	

	public Pessoa_Enum_Aux_Perfil_Pessoa retornaPerfildaPessoaPelaPessoa(Pessoa_Enum_Aux_Perfil_Pessoa pp) {
		Pessoa_Enum_Aux_Perfil_Pessoa pf = null;		
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Pessoa_Enum_Aux_Perfil_Pessoa.class);
			crit.createAlias("id_pessoa", "p");
			crit.add(Restrictions.eq("p.id", pp.getId_pessoa().getId()));
			crit.add(Restrictions.like("enum_Aux_Perfil_Pessoa", pp.getEnum_Aux_Perfil_Pessoa()));
			crit.setMaxResults(1);
			pf = (Pessoa_Enum_Aux_Perfil_Pessoa) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
		return pf;
	}

}
