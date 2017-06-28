package br.com.projetabrasil.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.entities.Endereco;

@Path("/enderecos")
public class EnderecoResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{cep}")
	public String retornaEnderecoCEP(@PathParam("cep") String cep){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Endereco endereco = new EnderecoDAO().buscaEnderecoPorCEP(cep);
		String pes = g.toJson(endereco);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarEndereco(String json){
		EnderecoDAO eDAO = new EnderecoDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Endereco endereco = g.fromJson(json, Endereco.class);		
		
		String pes = null;

		eDAO.merge(endereco);
		
		pes = g.toJson(endereco);
		return pes;
	}
	
}
