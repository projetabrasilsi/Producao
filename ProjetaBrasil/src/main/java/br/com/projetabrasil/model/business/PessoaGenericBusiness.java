package br.com.projetabrasil.model.business;

import java.io.Serializable;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
public class PessoaGenericBusiness implements Serializable {
	
	
	
	public static Pessoa merge(Pessoa pessoaCadastro, Usuario usuarioCadastro, PerfilLogado perfilLogado,boolean origemCadastro) {
		Pessoa pessoa =    pessoaCadastro;
		Usuario usuario = usuarioCadastro;
		
		Pessoa_Enum_Aux_Perfil_Pessoa pessoa_Perfil = null;
		pessoa = PessoaBusiness2.retiradadosembranco(pessoa);		
		pessoa.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		
		
		// merge da pessoa;
		pessoa = PessoaBusiness.merge(pessoa);		
		
		// merge usuario;
		if (usuario != null && (usuario.getSenha()!=null && usuario.getConfSenha()!=null )) {
			usuario.setPessoa(pessoa);
			usuario.setUltimaAtualizacao(Utilidades.retornaCalendario());
			usuario.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			usuario = UsuarioBusiness.merge(usuario);
			
		}
		// merge pessoa_perfil;
		pessoa_Perfil = new Pessoa_Enum_Aux_Perfil_Pessoa();
		pessoa_Perfil.setId_pessoa(pessoa);		
		if(perfilLogado.getPaginaAtual().getPerfilPessoa()!=null){
			if(!perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.OUTROS))
		pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(perfilLogado.getPaginaAtual().getPerfilPessoa());
			else
				pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
		}
		else
			pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
			
		
		pessoa_Perfil.setUltimaAtualizacao(Utilidades.retornaCalendario());	
		pessoa_Perfil.setId_Empresa(1);
		pessoa_Perfil.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		pessoa_Perfil.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pessoa_Perfil = Pessoa_Enum_Perfil_de_PessoaBusiness.merge(pessoa_Perfil);
		
		Pessoa_Enum_Aux_Perfil_PessoasDAO pp = new  Pessoa_Enum_Aux_Perfil_PessoasDAO() ;
		Pessoa_Enum_Aux_Perfil_Pessoa ppRet = new  Pessoa_Enum_Aux_Perfil_Pessoa() ;
		ppRet = pp.isPerfilExiste(pessoa_Perfil);
		if (ppRet == null){
			pp.merge(pessoa_Perfil);
		}
		
		
		
		
		if (pessoa.getId() != null 	&& (perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES) || 
				perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.CLIENTES)) || 
					perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES) ||
						perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES)
						||
						perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES)) 		
		vincularPessoa(pessoa, perfilLogado);
		
		
		
		
		return pessoa;
	}
	
	public static void vincularPessoa(Pessoa quem, PerfilLogado perfilLogado) {
		Pessoa_Vinculo pVinc = new Pessoa_Vinculo();
		pVinc.setAtivo(true);
		pVinc.setId_Empresa(1);
		pVinc.setEnum_Aux_Perfil_Pessoa(perfilLogado.getPaginaAtual().getPerfilPessoa());
		pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pVinc.setId_pessoa_d(quem);
				
		//DEPENDENDO DO PERFIL É NECESSÁRIO VINCULAR AO ASSOCIADO LOGADO E NÃO À EMPRESA
		if(
				(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES) &&
				 perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAREPRESENTANTES)
				 )
				|| 
				(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.DISTRIBUIDORES) && 
						perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAREVENDEDORES))
				||
				(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REPRESENTANTES) &&
						 perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINADISTRIBUIDORES)
						 )
						|| 
						(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.REVENDEDORES) && 
								perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAFUNCIONARIOS))
				){
			pVinc.setId_pessoa_m(perfilLogado.getAssLogado());
			
		}else{
			pVinc.setId_pessoa_m(perfilLogado.getUsLogado().getPessoa());
		}
		
		pVinc.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
		Pessoa_VinculoBusiness.merge(pVinc);
	}
	
	public static Pessoa  buscaPessoa(Pessoa pessoaCadastro){
		 Pessoa pessoa = pessoaCadastro;
		 pessoa.setIdentificador(pessoa.getIdentificador());
		 PessoaDAO pDAO = new PessoaDAO();
		 pessoa = pDAO.retornaPelaIdentificacao(pessoa.getIdentificador());
		 
		 return pessoa;
	}
	public static void chamaDialogoCastro(){		
		Utilidades.abrirfecharDialogos("dialogoIdentidade",false);		
		Utilidades.abrirfecharDialogos("dialogoCadastro",true);
	}
	
}
