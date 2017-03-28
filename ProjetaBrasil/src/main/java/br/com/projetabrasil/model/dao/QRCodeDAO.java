package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.util.HibernateUtil;
import br.com.projetabrasil.util.Utilidades;



public class QRCodeDAO extends GenericDAO<QRCode> {
	

	
	//
	
	public QRCode buscaCodersId(String id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		

		QRCode resultado;
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			crit.add(Restrictions.eq("id", Long.parseLong(id)));
			crit.setMaxResults(1);
			resultado = (QRCode)  crit.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public QRCode buscaCoders(String coders) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		

		QRCode resultado;
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			crit.add(Restrictions.eq("coders", coders));
			crit.setMaxResults(1);
			resultado = (QRCode)  crit.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<QRCode> listarQRCodersInicioEFim(Long inicio, Long fim) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		

		List<QRCode> resultado = new ArrayList();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.addOrder(Order.asc("id"));								
				crit.add(Restrictions.ge("id",  inicio));
				crit.add(Restrictions.le("id",  fim));
				crit.addOrder(Order.asc("id"));
				resultado = crit.list();
			
				
			
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public List<QRCode> listarQRCodersPorAdministradorEDiretores(Enum_Aux_Status_QRCodes aux_Status_QRCodes) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		

		List<QRCode> resultado;
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if (aux_Status_QRCodes != null) {
				crit.add(Restrictions.eq("status", aux_Status_QRCodes));
				resultado = crit.list();
			} else {

				resultado = listar();
			}
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCodersPorSupervisor(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			crit.add(Restrictions.eq("id_Pessoa_Supervisores", perfilLogado.getUsLogado().getPessoa()));

			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.SUPERVISIONADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REPRESENTADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.ASSINADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REGISTRADOS));
			crit.add(or);

			@SuppressWarnings("unchecked")
			List<QRCode> resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCodersPorRepresentante(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.add(Restrictions.eq("id_Pessoa_Representantes", perfilLogado.getUsLogado().getPessoa()));
			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REPRESENTADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.ASSINADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REGISTRADOS));
			crit.add(or);

			@SuppressWarnings("unchecked")
			List<QRCode> resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCodersPorAssinante(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			crit.add(Restrictions.eq("id_Pessoa_Assinantes", perfilLogado.getAssLogado()));

			Disjunction or = Restrictions.disjunction();
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.ASSINADOS));
			or.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REGISTRADOS));
			crit.add(or);

			@SuppressWarnings("unchecked")
			List<QRCode> resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCodersPorCliente(PerfilLogado perfilLogado) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			crit.add(Restrictions.eq("id_Pessoa_Clientes", perfilLogado.getUsLogado().getPessoa()));
			crit.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.ASSINADOS));
			@SuppressWarnings("unchecked")
			List<QRCode> resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> transfereQRCoders(String destino, Pessoa id_Pessoa, List<QRCode> listCoders,
			Enum_Aux_Tipos_Objetos pf) {

		List<QRCode> lc = new ArrayList<QRCode>();
		for (Iterator<QRCode> iter = listCoders.iterator(); iter.hasNext();) {
			QRCode e = iter.next();
			if (destino.toUpperCase().equals("SUPERVISORES")) {
				e.setId_Pessoa_Assinantes(id_Pessoa);
				e.setData_Supervisao(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.SUPERVISIONADOS);
			} else if (destino.toUpperCase().equals("REPRESENTANTES")) {
				e.setId_Pessoa_Representantes(id_Pessoa);

				e.setData_Representacao(Utilidades.retornaCalendario());
				e.setTipo_Objeto(pf);
				e.setStatus(Enum_Aux_Status_QRCodes.REPRESENTADOS);
			} else if (destino.toUpperCase().equals("ASSINANTES")) {
				e.setId_Pessoa_Assinantes(id_Pessoa);
				e.setData_Assinatura(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.ASSINADOS);
			} else if (destino.toUpperCase().equals("CLIENTES")) {
				e.setId_Pessoa_Registro(id_Pessoa);
				e.setData_Registro(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.REGISTRADOS);
			}
			lc.add(e);
		}
		return lc;
	}

	public List<QRCode> confirmaTransferenciadeQRCoders(List<QRCode> listCoders) {

		List<QRCode> lc = new ArrayList<QRCode>();
		for (Iterator<QRCode> iter = listCoders.iterator(); iter.hasNext();) {
			QRCode e = iter.next();
			QRCodeDAO q = new QRCodeDAO();
			q.merge(e);
		}
		return lc;
	}

	@SuppressWarnings("unchecked")
	public void geraNovosCoders(int qtde, PerfilLogado pf) {
		List<QRCode> lqr;
		for (int i = 0; i < qtde; i++) {
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			Criteria crit = sessao.createCriteria(QRCode.class).addOrder(Order.desc("id")).setMaxResults(1);

			lqr = crit.list();

			String cript;
			Long ctg;
			
			if (lqr.size() > 0)
				ctg = Long.parseLong("" + lqr.get(0).getId());
			else
				ctg = 0l;
			ctg++;
			cript = "" + ctg;

			cript = StringUtils.leftPad(String.valueOf(cript), 8, "0");
			String cp = cript;

			SimpleHash hash = new SimpleHash("md5", cript);
			cript = hash.toHex();
			QRCode e = new QRCode(pf.getUsLogado().getPessoa(), Enum_Aux_Status_QRCodes.LIVRES, cript);
			QRCodeDAO q = new QRCodeDAO();
			q.merge(e);
			System.out.println("gerou...: " + cp + " - :" + cript);

		}

	}

}
