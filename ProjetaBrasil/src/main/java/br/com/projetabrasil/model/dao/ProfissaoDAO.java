package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Profissao;
import br.com.projetabrasil.util.HibernateUtil;

public class ProfissaoDAO extends GenericDAO<Profissao> {
	@SuppressWarnings("unchecked")
	public List<Profissao> listardeProfissoes(String descricao ) {
		List<Profissao> profissoes = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Profissao.class);
			consulta.add(Restrictions.like("descricao","%"+descricao+"%").ignoreCase());
			consulta.addOrder(Order.asc("descricao"));
			profissoes = consulta.list();
			return profissoes;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	public Profissao PesquisaProfissaoPorDescricao(String descricao) {
		Profissao prof = new Profissao();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Profissao.class);
			
			consulta.add(Restrictions.like("descricao",descricao).ignoreCase());	
			consulta.setMaxResults(1);
			prof = (Profissao) consulta.uniqueResult();
			return prof;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
}
