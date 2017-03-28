package br.com.projetabrasil.model.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.HibernateUtil;


public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String identificadorUsuario, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa", "p");
			consulta.add(Restrictions.eq("p.identificador", identificadorUsuario));
			SimpleHash hash = new SimpleHash("md5", senha);
			
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException error) {			
			error.printStackTrace();
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Usuario checaUsuarioCadastrado(Pessoa pessoa, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);

			consulta.add(Restrictions.eq("pessoa", pessoa));

			if (senha != null && senha.length() > 0) {
				SimpleHash hash = new SimpleHash("md5", senha);
				consulta.add(Restrictions.eq("senha", hash.toHex()));
				consulta.setMaxResults(1);
			}

			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}

	public Usuario autenticar(Pessoa estab, Enum_Aux_Perfil_Pessoa perfil) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria pessoaPerfilCrit = sessao.createCriteria(Pessoa_Enum_Aux_Perfil_Pessoa.class);
			pessoaPerfilCrit.add(Restrictions.eq("enum_Aux_Perfil_Pessoa", perfil));
			pessoaPerfilCrit.add(Restrictions.eq("id_pessoa", estab));
			pessoaPerfilCrit.setMaxResults(1);
			Pessoa_Enum_Aux_Perfil_Pessoa pessoaPerfil = (Pessoa_Enum_Aux_Perfil_Pessoa) pessoaPerfilCrit
					.uniqueResult();
			String ident = "";
			if (pessoaPerfil != null)
				ident = estab.getIdentificador();
			Criteria crit = sessao.createCriteria(Usuario.class);
			crit.createAlias("pessoa", "p");
			crit.add(Restrictions.eq("p.identificador", ident));
			SimpleHash hash = new SimpleHash("md5", estab.getSenha());
			crit.add(Restrictions.eq("senha", hash.toHex()));
			crit.setMaxResults(1);
			Usuario resultado = (Usuario) crit.uniqueResult();
			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}
	}

	public String criptografaSenha(String senha) {
		SimpleHash hash = new SimpleHash("md5", senha);
		return hash.toHex();
	}

	public Usuario retornaUsuarioPelaPessoa(Pessoa pessoa) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("pessoa", pessoa));
			Usuario resultado = (Usuario) consulta.uniqueResult();

			return resultado;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			sessao.close();
		}

	}

}
