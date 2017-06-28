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

import br.com.projetabrasil.model.dao.Prontuario_de_EmergenciaDAO;
import br.com.projetabrasil.model.entities.Prontuario_de_Emergencia;

@Path("/prontuarios")
public class Prontuario_de_EmergenciaResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{id}")
	public String retornaProntuarioID(@PathParam("id") Long id){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Prontuario_de_Emergencia objeto = new Prontuario_de_EmergenciaDAO().buscar(id);
		String pes = g.toJson(objeto);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarProntuario(String json){
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Prontuario_de_Emergencia prontuario = g.fromJson(json, Prontuario_de_Emergencia.class);		
		
		String pes = null;

		pDAO.merge(prontuario);
		
		pes = g.toJson(prontuario);
		return pes;
	}
	
}
