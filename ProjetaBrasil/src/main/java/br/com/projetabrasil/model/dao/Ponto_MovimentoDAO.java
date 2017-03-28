package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Status_Movimento_Ponto;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.util.HibernateUtil;
import br.com.projetabrasil.util.Utilidades;


public class Ponto_MovimentoDAO extends GenericDAO<Ponto_Movimento> {

	@SuppressWarnings("unchecked")
	public List<Ponto_Movimento> listar(PerfilLogado perfilLogado, Pessoa cliente, boolean pesqAss) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Ponto_Movimento> pontuacoes = new ArrayList<Ponto_Movimento>();
		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null) {
				subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));

				crit = sessao.createCriteria(Ponto_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)						
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_associado", subQuery1)));
				crit.add(Restrictions.eq("id_pessoa_associado", perfilLogado.getAssLogado()));
				
				
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				
				crit.add(Restrictions.ge("validade", dt ));
				
				
				
				crit.addOrder(Order.desc("ultimaAtualizacao"));
				crit.addOrder(Order.asc("id_pessoa_cliente"));

				if (cliente != null) {
					crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				}

			} else {
				crit = sessao.createCriteria(Ponto_Movimento.class);
				crit.add(Restrictions.eq("id", 0));
			}

			pontuacoes = new ArrayList<Ponto_Movimento>(crit.list());      

			return pontuacoes;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<Ponto_Movimento> verificaSePontuacaoExiste( Pessoa cliente, 
			Pessoa estabelecimento, Enum_Aux_Tipo_Mov_Ponto enum_Aux_Tipo_Mov_Ponto ) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Ponto_Movimento> pontuacoes = new ArrayList<Ponto_Movimento>();		
		Criteria crit;

		try {
			

				crit = sessao.createCriteria(Ponto_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);				
				crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				
				crit.add(Restrictions.eq("id_pessoa_associado", estabelecimento));				
				crit.add(Restrictions.eq("creditaDebita", enum_Aux_Tipo_Mov_Ponto));
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				
				crit.add(Restrictions.eq("dataLancamento", dt ));				
 			pontuacoes =  crit.list();      

			return pontuacoes;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Double somadePontos(PerfilLogado perfilLogado, Pessoa cliente, boolean pesqAss,
			Enum_Aux_Tipo_Mov_Ponto tipoSoma) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Double valorRetorno;

		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null) {
				subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));

				crit = sessao.createCriteria(Ponto_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_associado", subQuery1)));
				crit.add(Restrictions.eq("id_pessoa_associado", perfilLogado.getAssLogado()));
				if (tipoSoma != null) {
					crit.add(Restrictions.eq("creditaDebita", tipoSoma));

				}
				
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				
				crit.add(Restrictions.ge("validade", dt ));
				
				

				crit.add(Restrictions.ge("validade", dt));
				if (cliente != null && cliente.getId() != null) {
					crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				}

			} else {
				crit = sessao.createCriteria(Ponto_Movimento.class);
				crit.add(Restrictions.eq("id", 0));
			}
			crit.setProjection(Projections.sum("pontosAtingidos"));
            
			if (crit.uniqueResult() != null) {				
				valorRetorno =  new Double((double) crit.uniqueResult());

			} else				
				valorRetorno =  new Double(0l);
			
			
			return valorRetorno.doubleValue();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Double somadePontos(Pessoa associado, Pessoa cliente, boolean pesqAss,
			Enum_Aux_Tipo_Mov_Ponto tipoSoma, Enum_Aux_Status_Movimento_Ponto enum_Aux_Status_Movimento_Ponto) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Double valorRetorno;

		DetachedCriteria subQuery1 = null;
		Criteria crit;

		try {
			if (associado != null &&associado.getId() != null) {
				subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));

				crit = sessao.createCriteria(Ponto_Movimento.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_associado", subQuery1)));
				crit.add(Restrictions.eq("id_pessoa_associado", associado));
				crit.add(Restrictions.eq("enum_Aux_Status_Movimento_Ponto", enum_Aux_Status_Movimento_Ponto));
				if (tipoSoma != null) {
					crit.add(Restrictions.eq("creditaDebita", tipoSoma));

				}
				
				java.util.Date d = Utilidades.retornaCalendario().getTime();
				java.sql.Date dt = new java.sql.Date (d.getTime());
				
				crit.add(Restrictions.ge("validade", dt ));
				
				

				crit.add(Restrictions.ge("validade", dt));
				if (cliente != null && cliente.getId() != null) {
					crit.add(Restrictions.eq("id_pessoa_cliente", cliente));
				}

			} else {
				crit = sessao.createCriteria(Ponto_Movimento.class);
				crit.add(Restrictions.eq("id", 0));
				crit.add(Restrictions.eq("enum_Aux_Status_Movimento_Ponto", enum_Aux_Status_Movimento_Ponto));
			}
			crit.setProjection(Projections.sum("pontosAtingidos"));
            
			if (crit.uniqueResult() != null) {				
				valorRetorno =  new Double((double) crit.uniqueResult());

			} else				
				valorRetorno =  new Double(0l);
			
			
			return valorRetorno.doubleValue();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
