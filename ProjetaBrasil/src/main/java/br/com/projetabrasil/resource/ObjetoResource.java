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

import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.entities.Objeto;

@Path("/objetos")
public class ObjetoResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{id}")
	public String retornaObjetoID(@PathParam("id") Long id){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Objeto objeto = new ObjetoDAO().buscar(id);
		String pes = g.toJson(objeto);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarObjeto(String json){
		ObjetoDAO oDAO = new ObjetoDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Objeto objeto = g.fromJson(json, Objeto.class);		
		
		String pes = null;

		oDAO.merge(objeto);
		
		pes = g.toJson(objeto);
		return pes;
	}
	
}
