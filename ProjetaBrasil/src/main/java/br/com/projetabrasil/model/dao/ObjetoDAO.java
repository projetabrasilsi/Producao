package br.com.projetabrasil.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.util.HibernateUtil;

public class ObjetoDAO extends GenericDAO<Objeto> {

	public Objeto verifica_Objeto(Objeto obj) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Objeto objeto= new Objeto();
		try {
			Criteria crit = sessao.createCriteria(Objeto.class);
			crit.add(Restrictions.eq("id_Pessoa_Vinculo", obj.getId_Pessoa_Vinculo()));
			crit.add(Restrictions.eq("enum_Aux_Tipos_Objeto", obj.getEnum_Aux_Tipos_Objeto()));

			if (obj.getId_Marca_e_Raca() != null)
				crit.add(Restrictions.eq("id_Marca_e_Raca", obj.getId_Marca_e_Raca()));

			if (obj.getId_Modelo_de_Marca_e_Raca() != null)
				crit.add(Restrictions.eq("id_Modelo_de_Marca_e_Raca", obj.getId_Modelo_de_Marca_e_Raca()));

			if (obj.getDescricao() != null && obj.getDescricao().length() > 0)
				crit.add(Restrictions.eq("descricao", obj.getDescricao()));
			crit.setMaxResults(1);
			objeto = (Objeto) crit.uniqueResult();
			
		} catch (RuntimeException e) {
			e.printStackTrace();

		} finally {
			sessao.close();
		}
		return objeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Objeto> lista_Objetos(Pessoa p){
		List<Objeto> objs = new ArrayList<>();
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try{
		Criteria crit = sessao.createCriteria(Objeto.class);
		crit.add(Restrictions.eq("id_Pessoa_Vinculo",p));
		crit.addOrder(Order.desc("id"));
		objs = crit.list();
		}catch(RuntimeException e){
			e.printStackTrace();			
		}
		finally {
			sessao.close();
		}
		return objs;
	}

}
