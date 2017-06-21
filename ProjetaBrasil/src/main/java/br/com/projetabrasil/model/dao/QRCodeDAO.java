package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_QRCodes;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.Objeto;
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
			resultado = (QRCode) crit.uniqueResult();
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
			resultado = (QRCode) crit.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public int retornaOUltimoCoders() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.setProjection(Projections.max("id"));
			Integer i = (Integer) crit.uniqueResult();
			if (i == null)
				i = 0;

			return i;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
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
			crit.add(Restrictions.ge("id", inicio));
			crit.add(Restrictions.le("id", fim));
			crit.addOrder(Order.asc("id"));
			resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<QRCode> listarQRCoders() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		List<QRCode> resultado = new ArrayList();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			crit.addOrder(Order.asc("id"));
			resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCodersPorPerfil(PerfilLogado perfilLogado, boolean disponivel) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Pessoa pes = new Pessoa();
		pes = perfilLogado.getUsLogado().getPessoa();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			Disjunction or = Restrictions.disjunction();
			if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES)
					|| perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.LOGISTICA)) {
				crit.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.LIVRES));
				
			} else

			if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES)) {
 			    crit.add(Restrictions.eq("id_Pessoa_Distribuicao", pes));
 			   crit.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.DISTRIBUIDOS));

				

			} else if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES)) {
				crit.add(Restrictions.eq("id_Pessoa_Representacao", pes));
				or.add(Restrictions.eq("status",Enum_Aux_Status_QRCodes.REPRESENTADOS));
				or.add(Restrictions.eq("status",Enum_Aux_Status_QRCodes.VENDIDOS));
				crit.add(or);
			} else if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES) ||
					perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.FUNCIONARIOS)) {
				
				if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.FUNCIONARIOS))
				pes = perfilLogado.getAssLogado();
				
				crit.add(Restrictions.eq("id_Pessoa_Revenda", pes));
				
				or.add(Restrictions.eq("status",Enum_Aux_Status_QRCodes.REVENDIDOS));
				or.add(Restrictions.eq("status",Enum_Aux_Status_QRCodes.VENDIDOS));
				crit.add(or);
			}

			
			

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
			e.setTipo_Objeto(pf);
			if (destino.toUpperCase().equals("LOGISTICA")) {
				e.setId_Pessoa_Logistica(id_Pessoa);
				e.setData_Logistica(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.LOGISTICA);
			} else if (destino.toUpperCase().equals("DISTRIBUIDORES")) {
				e.setId_Pessoa_Distribuicao(id_Pessoa);
				e.setData_Distribuicao(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.DISTRIBUIDOS);
			} else if (destino.toUpperCase().equals("REPRESENTANTES")) {
				e.setId_Pessoa_Representacao(id_Pessoa);
				e.setData_Representacao(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.REPRESENTADOS);
			} else if (destino.toUpperCase().equals("REVENDAS")) {
				e.setId_Pessoa_Revenda(id_Pessoa);
				e.setData_Revenda(Utilidades.retornaCalendario());
				e.setStatus(Enum_Aux_Status_QRCodes.REVENDIDOS);
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
	
	public List<Objeto> buscaObjetosVendidos() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		List<Objeto> resultado = new ArrayList();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
			crit.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.VENDIDOS));
			crit.add(Restrictions.eq("tipo_Objeto", Enum_Aux_Tipos_Objetos.PETS));
			crit.setProjection(Projections.property("id_Objeto"));		
			resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<QRCode> listarQRCoders(Enum_Aux_Status_QRCodes status, Objeto idObjeto) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		List<QRCode> resultado = new ArrayList();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			if(status != null){
				crit.add(Restrictions.eq("status", status));				
			}
			if(idObjeto != null){
				crit.add(Restrictions.eq("id_Objeto", idObjeto));
			}		
			resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {

			throw erro;
		} finally {
			sessao.close();
		}
	}

	public List<Objeto> buscaObjetos(Enum_Aux_Status_QRCodes status, Objeto o) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		List<Objeto> resultado = new ArrayList();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
			if(status != null){
				crit.add(Restrictions.eq("status", status));				
			}
			if(o != null){
				crit.add(Restrictions.eq("id_Objeto", o));
			}	
			crit.add(Restrictions.eq("tipo_Objeto", Enum_Aux_Tipos_Objetos.PETS));
			crit.setProjection(Projections.property("id_Objeto"));		
			resultado = crit.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public Pessoa buscaPessoa(String cpf, Long id) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		Pessoa resultado = new Pessoa();
		try {
			Criteria crit = sessao.createCriteria(QRCode.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);		
			
			crit.add(Restrictions.eq("id", id));	
			crit.add(Restrictions.eq("cpf_Cnpj", cpf));							
			crit.add(Restrictions.eq("tipo_Objeto", Enum_Aux_Tipos_Objetos.PETS));
			crit.add(Restrictions.eq("status", Enum_Aux_Status_QRCodes.REVENDIDOS));
			crit.setProjection(Projections.property("id_Pessoa_Cliente"));		
			resultado = (Pessoa) crit.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
