package br.com.projetabrasil.resource;

import java.util.ArrayList;
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
import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.model.json.QRCodeJson;

@Path("/pessoas")

public class PessoaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consulta/{id}/{cpf}")
	public String retornaPessoaCPF(@PathParam("id") Long id, @PathParam("cpf") String cpf) {
		

		QRCode q = new QRCodeDAO().buscaQRCodePorCpfEId(cpf, id);
		if (q == null)
			return null;

		Pessoa p = (Pessoa) new PessoaDAO().retornaPelaIdentificacao(cpf);

		QRCodeJson qc = new QRCodeJson();
		Endereco e = new Endereco();
		Usuario u = new Usuario();
		List<Contato> c = new ArrayList<Contato>();
		if (p != null) {
			e = new EnderecoDAO().buscaEnderecoPorPessoa(p);			
			c = new ContatoDAO().buscarContatoPelaPessoa(p);
			u = new UsuarioDAO().retornaUsuarioPelaPessoa(p);
		}
		qc.setFoto("");
		qc.setToken("");
		qc.setQr(q);
		qc.setEnd(e);
		qc.setUsur(u);		
		qc.setCont(c);
		Gson g = new Gson();   
		String pes = g.toJson(qc);
		return pes;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public String cadastrarPessoa(String json) {
		PessoaDAO pDAO = new PessoaDAO();
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();

		Pessoa pessoa = g.fromJson(json, Pessoa.class);
		Pessoa p = pDAO.retornaPelaIdentificacao(pessoa.getCpf_Cnpj());

		String pes = null;

		if (p != null) {
			pDAO.merge(pessoa);
		} else {
			pessoa = null;
		}

		pes = g.toJson(pessoa);
		return pes;
	}

}
