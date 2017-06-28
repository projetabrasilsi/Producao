package br.com.projetabrasil.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Usuario;

@Path("/usuarios")
public class UsuarioResource {
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarUsuario(String json){
		UsuarioDAO uDAO = new UsuarioDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Usuario usuario = g.fromJson(json, Usuario.class);
		Pessoa p = new PessoaDAO().retornaPelaIdentificacao(usuario.getPessoa().getCpf_Cnpj());
		Usuario u = uDAO.retornaUsuarioPelaPessoa(p);
		
		String pes = null;
		
		if(u != null){
			uDAO.merge(usuario);
		} else {
			usuario = null;
		}	
		
		pes = g.toJson(usuario);
		return pes;
	}
	
}
