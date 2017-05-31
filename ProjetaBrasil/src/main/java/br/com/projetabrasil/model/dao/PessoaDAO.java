package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.model.entities.Profissao;
import br.com.projetabrasil.util.HibernateUtil;


public class PessoaDAO extends GenericDAO<Pessoa> {

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		DetachedCriteria subQuery1 = null;
		DetachedCriteria subQuery2 = null;
		Criteria crit = null;
		try {

			if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.SUPERVISORES)
					&& perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAASSINANTES)) {

				subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class)
						.setProjection(Property.forName("id_pessoa"));
				subQuery1.add(Restrictions.eq("id_pessoa_m", perfilLogado.getUsLogado().getPessoa()));
				crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));
				List<Pessoa> pessoas = (List<Pessoa>) crit.list();

				perfilLogado.setPaginaAtual(perfilLogado.getPaginaAtual());

				subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class)
						.setProjection(Property.forName("id_pessoa_d"));
				subQuery1.add(Restrictions.in("id_pessoa_m", pessoas));
				crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
						.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));

			} else {
				subQuery1 = DetachedCriteria.forClass(Pessoa_Enum_Aux_Perfil_Pessoa.class)
						.setProjection(Property.forName("id_pessoa"));

				subQuery1.add(
						Restrictions.eq("enum_Aux_Perfil_Pessoa", perfilLogado.getPaginaAtual().getPerfilPessoa()));
				if (perfilLogado.getPaginaAtual().getPerfilPessoa().isPossuiVinculo()
						&& !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES)) {
					subQuery2 = DetachedCriteria.forClass(Pessoa_Vinculo.class)
							.setProjection(Property.forName("id_pessoa_d"));
					if (perfilLogado.getAssLogado() == null || perfilLogado.getAssLogado().getId() == null)
						subQuery2.add(Restrictions.eq("id_pessoa_m", perfilLogado.getUsLogado().getPessoa()));
					else
						subQuery2.add(Restrictions.eq("id_pessoa_m", perfilLogado.getAssLogado()));

				}

				if (perfilLogado.getPaginaAtual().getPerfilPessoa().isPossuiVinculo()
						&& !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES)) {
					crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
							.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1),
									Subqueries.propertyIn("id", subQuery2)));
					if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
						crit.add(Restrictions.eq("id", 0l));
					}
				} else {
					crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
							.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));
				}
			}

			List<Pessoa> resultado = crit.list();
			
			
			
			
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listaEstabelecimentosPontuados(Pessoa id_Pessoa_Cliente) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			DetachedCriteria subQuery1 = null;
			Criteria crit = null;

			subQuery1 = DetachedCriteria.forClass(Ponto_Movimento.class)
					.setProjection(Property.forName("id_pessoa_associado"));
			subQuery1.add(Restrictions.eq("id_pessoa_cliente", id_Pessoa_Cliente));

			crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));
			List<Pessoa> l = crit.list();
			return l;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;

		} finally {
			sessao.close();
		}

	}

	public Pessoa retornaPelaIdentificacao(String identificador) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.eq("identificador", identificador));
			or.add(Restrictions.eq("cpf_Cnpj", identificador));
			or.add(Restrictions.eq("fone_1", identificador));
			Criteria crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.add(or);
			return (Pessoa) crit.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> associaEstadosAoPais(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Estado.class).createAlias("pais", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Estado> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<Cidade> associaCidadesAoEstado(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Cidade.class).createAlias("estado", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Cidade> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<Bairro> associaBairrosACidade(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Bairro.class).createAlias("cidade", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Bairro> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<Logradouro> associaLogradourosACidade(Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Logradouro.class).createAlias("cidade", "a");
			consulta.add(Restrictions.eq("a.id", id));
			List<Logradouro> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}	
	}

	public List<Pessoa> listardeIndicacoes(String indicacaoBusca) {
		List<Pessoa> pessoas = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Pessoa.class);
			consulta.add(Restrictions.like("descricao","%"+indicacaoBusca+"%").ignoreCase());
			consulta.addOrder(Order.asc("descricao"));
			pessoas = consulta.list();
			return pessoas;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
