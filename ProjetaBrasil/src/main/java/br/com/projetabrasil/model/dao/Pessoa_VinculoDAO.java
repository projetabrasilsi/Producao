package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.util.HibernateUtil;


public class Pessoa_VinculoDAO extends GenericDAO<Pessoa_Vinculo> {
	

	public Pessoa_Vinculo pessoaEstaVinculada(Pessoa_Vinculo pessoa_Vinculo, PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));
		DetachedCriteria subQuery2 = DetachedCriteria.forClass(Pessoa_Enum_Aux_Perfil_Pessoa.class)
				.setProjection(Property.forName("id_pessoa"));
		Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class);
		try {
			if (pessoa_Vinculo.getId_pessoa_m() != null) {
				subQuery1.add(Restrictions.eq("id", pessoa_Vinculo.getId_pessoa_m().getId()));
			}
			subQuery2.add(Restrictions.eq("enum_Aux_Perfil_Pessoa",  perfilLogado.getPaginaAtual().getPerfilPessoa() ));
			if (pessoa_Vinculo.getId_pessoa_m() != null) {
				crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_m", subQuery1),
								Subqueries.propertyIn("id_pessoa_d", subQuery2)));
				crit.add(Restrictions.eq("id_pessoa_d", pessoa_Vinculo.getId_pessoa_d()));
			} else {

				crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_d", subQuery2)));
				crit.add(Restrictions.eq("id_pessoa_d", pessoa_Vinculo.getId_pessoa_d()));
			}

			return (Pessoa_Vinculo) crit.uniqueResult();
		} catch (RuntimeException error) {			
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	
	public Pessoa_Vinculo retornaVinculo_Mestre(Pessoa_Vinculo pessoa_Vinculo, Enum_Aux_Perfil_Pessoa  perfilUsLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id", pessoa_Vinculo.getId_pessoa_d().getId()));

			DetachedCriteria subQuery2 = DetachedCriteria.forClass(Pessoa_Enum_Aux_Perfil_Pessoa.class)
					.setProjection(Property.forName("id_pessoa"));
			subQuery2.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilUsLogado ));

			Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_d", subQuery1),
							Subqueries.propertyIn("id_pessoa_d", subQuery2)));
			crit.add(Restrictions.eq("id_pessoa_d", pessoa_Vinculo.getId_pessoa_d()));
			crit.setMaxResults(1);
			return (Pessoa_Vinculo) crit.uniqueResult();
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Pessoa_Vinculo retornaVinculo_Mestre(Pessoa pessoaDetalhe,Pessoa pessoaMestre, Enum_Aux_Perfil_Pessoa perfilPessoaCadastro) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
						Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("id_pessoa_d",pessoaDetalhe));			        
					crit.add(Restrictions.eq("id_pessoa_m",pessoaMestre));
			        if(perfilPessoaCadastro!=null && perfilPessoaCadastro.getId()>0)
				        crit.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilPessoaCadastro));
			        crit.addOrder(Order.desc("id"));
			        crit.setMaxResults(1);
					
			return (Pessoa_Vinculo) crit.uniqueResult();
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Pessoa_Vinculo retornaVinculo_emOutroMestre(Pessoa pessoaDetalhe,Pessoa pessoaMestre, Enum_Aux_Perfil_Pessoa perfilPessoaCadastro) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
						Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("id_pessoa_d",pessoaDetalhe));			        
					crit.add(Restrictions.ne("id_pessoa_m",pessoaMestre));
			        if(perfilPessoaCadastro!=null && perfilPessoaCadastro.getId()>0)
				        crit.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilPessoaCadastro));
			        crit.addOrder(Order.desc("id"));
			        crit.setMaxResults(1);
					
			return (Pessoa_Vinculo) crit.uniqueResult();
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public Pessoa_Vinculo retornaVinculo_Mestre(Pessoa pessoaDetalhe, Enum_Aux_Perfil_Pessoa perfilPessoaCadastro) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			
           
				

			Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("id_pessoa_d",pessoaDetalhe));			        
			        if(perfilPessoaCadastro!=null && perfilPessoaCadastro.getId()>0)
				        crit.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilPessoaCadastro));
			        crit.addOrder(Order.desc("id"));
			        crit.setMaxResults(1);
					
			return (Pessoa_Vinculo) crit.uniqueResult();
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}
	

	public List<Pessoa_Vinculo> retornaPessoasVinculadas(Pessoa_Vinculo pessoa_Vinculo, PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {

			DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa.class).setProjection(Property.forName("id"));
			subQuery1.add(Restrictions.eq("id", pessoa_Vinculo.getId_pessoa_m()));

			DetachedCriteria subQuery2 = DetachedCriteria.forClass(Pessoa_Enum_Aux_Perfil_Pessoa.class)
					.setProjection(Property.forName("id_pessoa"));
			subQuery2.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilLogado.getPaginaAtual()));

			Criteria crit = sessao.createCriteria(Pessoa_Vinculo.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id_pessoa_m", subQuery1),
							Subqueries.propertyIn("id_pessoa_d", subQuery2)));

			@SuppressWarnings("unchecked")
			List<Pessoa_Vinculo> pessoas_vinculos = crit.list();

			return pessoas_vinculos;
		} catch (RuntimeException error) {
			throw error;

		} finally {
			sessao.close();
		}
	}

}
