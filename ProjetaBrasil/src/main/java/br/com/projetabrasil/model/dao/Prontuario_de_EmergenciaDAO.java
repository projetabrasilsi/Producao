package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Prontuario_de_Emergencia;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Prontuario_de_Emergencia;
import br.com.projetabrasil.util.HibernateUtil;

public class Prontuario_de_EmergenciaDAO extends GenericDAO<Prontuario_de_Emergencia> {
	
	@SuppressWarnings("unchecked")
	public List<Prontuario_de_Emergencia> listarProntuarioporPessoa(Pessoa p) {
		List<Prontuario_de_Emergencia> prontuarios = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Prontuario_de_Emergencia.class);
			consulta.add(Restrictions.eq("id_pessoa",p));
			prontuarios = consulta.list();
			return prontuarios;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	public Prontuario_de_Emergencia buscarProntuarioporPessoa(Pessoa p, Enum_Aux_Tipo_Prontuario_de_Emergencia prontuario) {
		Prontuario_de_Emergencia pr = new Prontuario_de_Emergencia();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Prontuario_de_Emergencia.class);
			consulta.add(Restrictions.eq("id_pessoa",p));
			consulta.add(Restrictions.eq("tipo_Prontuario_Emergencia",prontuario));
			consulta.setMaxResults(1);
			pr = (Prontuario_de_Emergencia) consulta.uniqueResult();
			return (Prontuario_de_Emergencia) pr;
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
