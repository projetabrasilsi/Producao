package br.com.projetabrasil.model.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.InscricaoMunicipal;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;

public class InscricaoMunicipalDAO extends GenericDAO<InscricaoMunicipal> {
	
	public InscricaoMunicipal consultaPelaiMCidade(String iM,Cidade id_Cidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InscricaoMunicipal.class);
			consulta.add(Restrictions.eq("nInsc", iM));
			consulta.add(Restrictions.eq("id_Cidade", id_Cidade));
			consulta.setMaxResults(1);
			return (InscricaoMunicipal) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	
	public InscricaoMunicipal consultaPelaiMCidadeEmpresa(String iM,Cidade id_Cidade, Pessoa id_Pessoa_Empresa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InscricaoMunicipal.class);
			consulta.add(Restrictions.eq("nInsc", iM));
			consulta.add(Restrictions.eq("id_Cidade", id_Cidade));
			consulta.add(Restrictions.eq("id_Pessoa_Empresa", id_Pessoa_Empresa));
			consulta.setMaxResults(1);
			return (InscricaoMunicipal) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}
	
	public InscricaoMunicipal consultaPelaiMCidadeEmpresaCliente(String iM,Cidade id_Cidade, Pessoa id_Pessoa_Empresa,Pessoa id_Pessoa_Cliente ) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(InscricaoMunicipal.class);
			consulta.add(Restrictions.eq("nInsc", iM));
			consulta.add(Restrictions.eq("id_Cidade", id_Cidade));
			consulta.add(Restrictions.eq("id_Pessoa_Empresa", id_Pessoa_Empresa));
			consulta.add(Restrictions.eq("id_Pessoa_Cliente", id_Pessoa_Cliente));
			consulta.setMaxResults(1);
			return (InscricaoMunicipal) consulta.uniqueResult();
		} catch (RuntimeException error) {
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

}
