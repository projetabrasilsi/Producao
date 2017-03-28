import java.text.ParseException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.junit.Ignore;
import org.junit.Test;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Sexo;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.HibernateUtil;
import br.com.projetabrasil.util.Utilidades;

public class HibernateUtilTest {

	private Pessoa p;
	private Usuario us;
	private Pessoa_Enum_Aux_Perfil_Pessoa pp;

	@SuppressWarnings("unused")
	@Ignore
	@Test
	public void calendario() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		//Criteria crit = null;
		//DetachedCriteria subQuery1 = null;

		DetachedCriteria subQuery1 = DetachedCriteria.forClass(Pessoa_Vinculo.class).setProjection(Property.forName("id_pessoa_d"));
		subQuery1.add(Restrictions.eq("id_pessoa_m", 1l));

		Criteria crit = sessao.createCriteria(Pessoa.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.and(Subqueries.propertyIn("id", subQuery1)));

	}

	@Test
	public void cadastraPessoa() throws ParseException {
		p = new Pessoa();
		p.setCpf_Cnpj("10554498928");
		p.setCpf_Cnpj(Utilidades.retiraCaracteres(p.getCpf_Cnpj()));

		boolean vf = false;
		if (p.getCpf_Cnpj().length() == 11)
			vf = Utilidades.isValidCPF(p.getCpf_Cnpj());
		else if (p.getCpf_Cnpj().length() == 14)
			vf = Utilidades.isValidCNPJ(p.getCpf_Cnpj());

		if (vf) {

			p.setIdentificador(p.getCpf_Cnpj());
			PessoaDAO pDAO = new PessoaDAO();
			p = pDAO.retornaPelaIdentificacao(p.getIdentificador());
			
			System.out.println("Aqui é uma pessoa: "+p);

			if (p == null) {
				p = new Pessoa();
				p.setCpf_Cnpj("10554498928");
				p.setIdentificador(p.getCpf_Cnpj());
				p.setRg_Insc("6155857");
				p.setSexo(Enum_Aux_Sexo.MASCULINO);
				p.setFone_1("48911111111");
				p.setFone_2("");
				p.setFone_3("");
				p.setEmail("admin@gmail.com");
				p.setId_Pessoa_Registro(p);
				p.setAutoPontuacao(Enum_Aux_Sim_ou_Nao.NAO);
				p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
				p.setDescricao("Admin");
				p.setFantasia_Apelido("");
				p.setDataNascimento(Utilidades.retornaData("20/04/1889"));
			}
			p = pDAO.merge(p);
			us = new Usuario();
			us.setPessoa(p);

			UsuarioDAO usDAO = new UsuarioDAO();
			us = usDAO.retornaUsuarioPelaPessoa(p);

			if (us == null) {
				try {
					us = new Usuario();
					us.setPessoa(p);
					us.setAtivo(true);
					us.setSenhaSemCript("11111111");
					us.setConfSenha("11111111");
					//us.setId(null);
					us.setId_Empresa(1);
					us.setId_Pessoa_Registro(p);
					us.setPessoa(p);
					
				} catch (RuntimeException error) {
					error.printStackTrace();
					throw error;
				}
			}
			
			us = usDAO.merge(us);

			pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
			Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();

			pp.setId_pessoa(p);
			pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);

			pp = ppDAO.retornaPerfildaPessoaPelaPessoa(pp);

			if (pp == null) {
				pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
				pp.setId_pessoa(p);
				pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);
				pp.setId_Empresa(1);

				pp.setId_Pessoa_Registro(p);
				pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
			}
			pp = ppDAO.merge(pp);
		}
	}
		
	
		
}	
