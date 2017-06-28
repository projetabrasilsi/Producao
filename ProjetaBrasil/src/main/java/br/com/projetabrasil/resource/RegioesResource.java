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

import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.dao.LogradouroDAO;
import br.com.projetabrasil.model.dao.BairroDAO;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.Bairro;

@Path("/regioes")
public class RegioesResource {
	
	//CIDADE ------------------------------------
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/cidade/{id}")
	public String retornaCidadeID(@PathParam("id") Long id){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Cidade cidade = new CidadeDAO().buscar(id);
		String pes = g.toJson(cidade);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cidade/cadastrar")
	public String cadastrarCidade(String json){
		CidadeDAO oDAO = new CidadeDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Cidade cidade = g.fromJson(json, Cidade.class);		
		
		String pes = null;

		oDAO.merge(cidade);
		
		pes = g.toJson(cidade);
		return pes;
	}
	
	//LOGRADOURO ------------------------------------
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/logradouro/{id}")
	public String retornaLogradouroID(@PathParam("id") Long id){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Logradouro logradouro = new LogradouroDAO().buscar(id);
		String pes = g.toJson(logradouro);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar/logradouro")
	public String cadastrarLogradouro(String json){
		LogradouroDAO oDAO = new LogradouroDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Logradouro logradouro = g.fromJson(json, Logradouro.class);		
		
		String pes = null;

		oDAO.merge(logradouro);
		
		pes = g.toJson(logradouro);
		return pes;
	}
	
	//BAIRRO ------------------------------------
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/bairro/{id}")
	public String retornaBairroID(@PathParam("id") Long id){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Bairro objeto = new BairroDAO().buscar(id);
		String pes = g.toJson(objeto);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar/bairro")
	public String cadastrarBairro(String json){
		BairroDAO oDAO = new BairroDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Bairro bairro = g.fromJson(json, Bairro.class);		
		
		String pes = null;

		oDAO.merge(bairro);
		
		pes = g.toJson(bairro);
		return pes;
	}
	
}	
