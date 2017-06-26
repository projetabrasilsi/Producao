package br.com.projetabrasil.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.entities.Contato;

@Path("/contatos")
public class ContatoResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{cpf}")
	public String retornaPessoaCPF(@PathParam("cpf") String cpf){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		List<Contato> contatos = new ContatoDAO().buscarContatoPelaPessoa(new PessoaDAO().retornaPelaIdentificacao(cpf));
		String pes = g.toJson(contatos);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarContato(String json){
		ContatoDAO cDAO = new ContatoDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Contato contato = g.fromJson(json, Contato.class);		
		
		String pes = null;

		cDAO.merge(contato);
		
		pes = g.toJson(contato);
		return pes;
	}
}
