package br.com.projetabrasil.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;


public class EnderecoDAO extends GenericDAO<Endereco> {
	@SuppressWarnings("unchecked")
	public List<Endereco> listar(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(Endereco.class);			
			List<Endereco> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
			erro.printStackTrace();
			throw erro;
		}
		finally{
			sessao.close();
		}
	}

	public Endereco buscaEnderecoPorPessoa(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Endereco.class);
			consulta.add(Restrictions.eq("pessoa", pessoa));
			return (Endereco) consulta.setMaxResults(1).uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Endereco verificaEndereco(Endereco endereco) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria crit = sessao.createCriteria(Endereco.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);					
					crit.add(Restrictions.eq("bairro", endereco.getBairro()));			        
					crit.add(Restrictions.eq("logradouro", endereco.getLogradouro()));
					crit.add(Restrictions.eq("pessoa", endereco.getPessoa()));
					crit.add(Restrictions.eq("numero", endereco.getNumero()));;
			return (Endereco) crit.setMaxResults(1).uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
