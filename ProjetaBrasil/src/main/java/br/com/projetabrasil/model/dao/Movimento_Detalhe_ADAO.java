package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Item_de_Movimento;
import br.com.projetabrasil.model.entities.Movimento_Detalhe;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_A;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.util.HibernateUtil;


public class Movimento_Detalhe_ADAO extends GenericDAO<Movimento_Detalhe_A> {
	Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

	@SuppressWarnings("unchecked")
	public List<Movimento_Detalhe_A> listar(Pessoa id_Pessoa_Assinante) {
		List<Movimento_Detalhe_A> l = new ArrayList<Movimento_Detalhe_A>();
		Criteria crit = sessao.createCriteria(Movimento_Detalhe_A.class);

		try {
			crit.add(Restrictions.eq("id_Pessoa_Assinante", id_Pessoa_Assinante));

			l = crit.list();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}

	public Movimento_Detalhe_A retornaMovimento_Detalhe_A(Long id) {
		Movimento_Detalhe_A l = new Movimento_Detalhe_A();
		Criteria crit = sessao.createCriteria(Movimento_Detalhe_A.class);
		try {
			crit.add(Restrictions.eq("id", id));
			l = (Movimento_Detalhe_A) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
		} finally {
			sessao.close();
		}
		return l;
	}

	public Double somapeloTipodeMovimento(Pessoa assinante, Enum_Aux_Tipo_Item_de_Movimento tipoMov) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		double valorRetorno;
		double valorRetorno2;

		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (tipoMov != null) {
				subQuery1 = DetachedCriteria.forClass(Item_de_Movimento.class).setProjection(Property.forName("id"));
				subQuery1.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", tipoMov));
			}

			crit = sessao.createCriteria(Movimento_Detalhe.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.add(Restrictions.eq("id_Pessoa_Assinante", assinante));
			if (tipoMov != null)
				crit.add(Restrictions.and(Subqueries.propertyIn("id_Item_de_Movimento", subQuery1)));

			crit.setProjection(Projections.sum("qtde"));

			if (crit.uniqueResult() != null) {
				valorRetorno = (double) crit.uniqueResult() ;

			} else
				valorRetorno = 0d;
			
			

			crit = sessao.createCriteria(Movimento_Detalhe_A.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.add(Restrictions.eq("id_Pessoa_Assinante", assinante));
			if (tipoMov != null)
				crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", tipoMov));
				

			crit.setProjection(Projections.sum("disponibilizado"));

			if (crit.uniqueResult() != null) {
				valorRetorno2 = (double) crit.uniqueResult();

			} else
				valorRetorno2 = 0d;
			valorRetorno +=valorRetorno2;
			return valorRetorno;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimento_Detalhe_A> vouchersParaoCliente(Pessoa id_Pessoa_Cliente, Enum_Aux_Tipo_Item_de_Movimento tipoMov) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Movimento_Detalhe_A> l;

		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			subQuery1 = DetachedCriteria.forClass(Ponto_Movimento.class).setProjection(Property.forName("id_pessoa_associado"));		    
			subQuery1.add(Restrictions.eq("id_pessoa_cliente",id_Pessoa_Cliente));
			crit = sessao.createCriteria(Movimento_Detalhe_A.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.add(Restrictions.and(Subqueries.propertyIn("id_Pessoa_Assinante", subQuery1)));
			crit.add(Restrictions.eq("enum_Aux_Tipo_Item_de_Movimento", tipoMov));
			l = crit.list();
			
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
		return l;
	}

}
