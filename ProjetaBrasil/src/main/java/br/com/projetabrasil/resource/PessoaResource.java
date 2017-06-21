package br.com.projetabrasil.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Pessoa;

import javax.ws.rs.GET;

@Path("/pessoas")
public class PessoaResource {
	
	@GET	
	@Path("/consulta/{id}/{cpf}")
	public String retornaPessoaCPF(@PathParam("id") Long id, @PathParam("cpf") String cpf){		
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Pessoa p = (Pessoa) new QRCodeDAO().buscaPessoa(cpf, id);
		String pes = g.toJson(p);
		return pes;
	}
	
	@GET	
	@Path("/cadastra/{cpf}/{nome}")
	public String cadastrarPessoa(@PathParam("cpf") String cpf, @PathParam("nome") String nome){
		PessoaDAO pDAO = new PessoaDAO();
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		
		Pessoa p = pDAO.retornaPelaIdentificacao(cpf);
		String pes = null;
		
		if(p == null){
			pes = g.toJson(true);
		}
		
		p.setId(null);
		p.setDescricao(nome);
		
		pDAO.merge(p);
		
		pes = g.toJson(true);
		return pes;
	}
}
