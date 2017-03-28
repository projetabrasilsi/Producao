package br.com.projetabrasil.model.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.util.HibernateUtil;



public class GenericDAO<Entity> {	
	private Class<Entity> classe;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(){
		
		this.classe = (Class<Entity>)	((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	public void abreSessao(){
		
	}
	  
	
	public void salvar(Entity e){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.save(e);
			 transacao.commit();		
			 
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public void excluir(Entity e){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.delete(e);
			 transacao.commit();	
			 
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	
	public void alterar(Entity e){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try{
			 transacao = sessao.beginTransaction();
			 sessao.update(e);
			 transacao.commit();	
			
		}catch(RuntimeException erro){			
			if (transacao!=null)
			transacao.rollback();
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	@SuppressWarnings("unchecked")
	public List<Entity> listar(){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(classe);
			
			List<Entity> resultado = consulta.list();
			return resultado;
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}		
	}
	@SuppressWarnings("unchecked")
	public Entity buscar(Long id){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();		
		try{	
			Criteria consulta = sessao.createCriteria(classe);
			
			consulta.add(Restrictions.idEq(id));
					
			Entity resultado = (Entity) consulta.uniqueResult();
			return resultado;
			
		}catch(RuntimeException erro){		
		
			throw erro;
		}
		finally{
			sessao.close();
		}		
		
	}
	
	public Entity  merge(Entity e) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;
		try {
			transacao = sessao.beginTransaction();
			@SuppressWarnings("unchecked")
			Entity ent = (Entity) sessao.merge(e);
			transacao.commit();
			return(ent);
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
