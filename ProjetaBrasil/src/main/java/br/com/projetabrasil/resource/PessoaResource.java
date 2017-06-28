package br.com.projetabrasil.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Pessoa;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

@Path("/pessoas")
public class PessoaResource {
	
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{id}/{cpf}")
	public String retornaPessoaCPF(@PathParam("id") Long id, @PathParam("cpf") String cpf){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Pessoa p = (Pessoa) new QRCodeDAO().buscaPessoa(cpf, id);
		String pes = g.toJson(p);
		return pes;
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarPessoa(String json){
		PessoaDAO pDAO = new PessoaDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Pessoa pessoa = g.fromJson(json, Pessoa.class);
		Pessoa p = pDAO.retornaPelaIdentificacao(pessoa.getCpf_Cnpj());
		
		String pes = null;
		
		if(p != null){
			pDAO.merge(pessoa);
		} else {
			pessoa = null;
		}
		
		pes = g.toJson(pessoa);
		return pes;
	}
	
}
