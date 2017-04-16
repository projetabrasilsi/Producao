package br.com.projetabrasil.model.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.PontoDAO;
import br.com.projetabrasil.model.dao.Ponto_MovimentoDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Movimento_Ponto;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Ponto;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;


//http://localhost:8080/LealBrasil/rest/[NomedoRepositoriodeServicos]
@Path("pessoas")
public class PessoasServices {

	@GET
	@Path("{codigo}")
	public String pesquisarCliente(@PathParam("codigo") String codigo) {

		codigo = Utilidades.removerAcentos(codigo);
		codigo = Utilidades.retiraCaracteres(codigo);
		codigo = Utilidades.retiraVazios(codigo);

		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		p = pDAO.retornaPelaIdentificacao(codigo);
		Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		String pes = g.toJson(p);
		
		return pes;
	}
	
	@GET
	@Path("{usuario}/{senha}")
	public String consultarUsuario(@PathParam("usuario") String usuario, @PathParam("senha") String senha) {
		usuario = Utilidades.removerAcentos(usuario);
		usuario = Utilidades.retiraCaracteres(usuario);
		usuario = Utilidades.retiraVazios(usuario);		
		Usuario u = new Usuario();
		UsuarioDAO uDAO = new UsuarioDAO();		
		u = uDAO.autenticar(usuario, senha);
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String us = g.toJson(u);		
		return us;
	}
	
	@GET
	@Path("{usuario}/{senha}/{perfil}")
	public String consultarUsuariocomPerfil(@PathParam("usuario") String usuario, @PathParam("senha") String senha, @PathParam("perfil") String perfil) {
		usuario = Utilidades.removerAcentos(usuario);
		usuario = Utilidades.retiraCaracteres(usuario);
		usuario = Utilidades.retiraVazios(usuario);
		Pessoa p = new Pessoa();
		PessoaDAO pDAO = new PessoaDAO();
		p = pDAO.retornaPelaIdentificacao(usuario);
		Usuario u = new Usuario();
		UsuarioDAO uDAO = new UsuarioDAO();		
		u = uDAO.autenticar(p, Enum_Aux_Perfil_Pessoa.valueOf(perfil));
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String us = g.toJson(u);		
		return us;
	}
	
	

	@POST
	@Path("{estab}/{cliente}/{valor}")

	public String pontuarCliente(@PathParam("estab") String est, @PathParam("cliente") String cli,
			@PathParam("valor") String valor) {
		Pessoa cliente = null;
		Pessoa estab = null;

		Gson gson = new Gson();
		cliente = gson.fromJson(cli, Pessoa.class);
		estab = gson.fromJson(est, Pessoa.class);

		cliente.setCpf_Cnpj(Utilidades.removerAcentos(cliente.getCpf_Cnpj()));
		cliente.setCpf_Cnpj(Utilidades.retiraCaracteres(cliente.getCpf_Cnpj()));
		cliente.setCpf_Cnpj(Utilidades.retiraVazios(cliente.getCpf_Cnpj()));
		cliente.setIdentificador(cliente.getCpf_Cnpj());

		estab.setCpf_Cnpj(Utilidades.removerAcentos(estab.getCpf_Cnpj()));
		estab.setCpf_Cnpj(Utilidades.retiraCaracteres(estab.getCpf_Cnpj()));
		estab.setCpf_Cnpj(Utilidades.retiraVazios(estab.getCpf_Cnpj()));
		estab.setIdentificador(estab.getCpf_Cnpj());
		Usuario usuario = Utilidades.validaEstabelecimento(estab);
		if (usuario == null) {
			cliente = Utilidades.criapessoa("Estabelecimento não encontrado!!!");
		} else {
			if (estab.isCpf_cnpjValido()) {
				cliente = Utilidades.avaliaPessoa(cliente, "Cliente");
				if (!cliente.isCadastrado() && cliente.isCpf_cnpjValido()) {
					cliente = Utilidades.cadastraPessoa(cliente,estab,estab, Enum_Aux_Perfil_Pessoa.CLIENTES);
				}else{
					
				}

			} else
			cliente = Utilidades.criapessoa("Estabelecimento não cadastrado!!!");

			if (cliente.getId() != null) {
				double val = Double.parseDouble(valor.replace(',', '.'));

				Ponto_Movimento ponto_movimento = configPonto_Movimento(cliente, estab);
				if (ponto_movimento == null) {
					cliente.setMensagem("pontuação ainda não foi configurada para este esbalecimento!!!");
					cliente.setPontosAnt(0d);
					cliente.setPontosdoMovimento(0d);
					cliente.setPontosAtuais(0d);
				} else {
					Double credito = 0d;
					Double debito = 0d;
					Double estorno = 0d;

					Ponto_MovimentoDAO pMovDAO = new Ponto_MovimentoDAO();
					ponto_movimento = pMovDAO.merge(ponto_movimento);

					credito = pMovDAO.somadePontos(estab, cliente, true, Enum_Aux_Tipo_Mov_Ponto.C,
							Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
					if (credito == null)
						credito = 0d;

					debito = pMovDAO.somadePontos(estab, cliente, true, Enum_Aux_Tipo_Mov_Ponto.D,
							Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
					if (debito == null)
						debito = 0d;

					estorno = pMovDAO.somadePontos(estab, cliente, true, Enum_Aux_Tipo_Mov_Ponto.E,
							Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
					if (estorno == null)
						estorno = 0d;
					cliente.setPontosdoMovimento(val);
					cliente.setPontosAtuais(credito - (debito + estorno));
					cliente.setPontosAnt(cliente.getPontosAtuais() - cliente.getPontosdoMovimento());
					cliente.setMensagem("cliente foi pontuado com sucesso para o estabelecimento");
				}

			}

		}
		Gson g = new Gson();

		return g.toJson(cliente);
	}

	public Ponto_Movimento configPonto_Movimento(Pessoa cliente, Pessoa estab) {
		Ponto_Movimento ponto_movimento = new Ponto_Movimento();
		Ponto ponto = new Ponto();
		PontoDAO p = new PontoDAO();
		ponto = p.retornaPrimeiroPonto(estab, Enum_Aux_Tipo_Item_de_Movimento.PONTO);
		if (ponto != null && ponto.getId() != null) {
			ponto_movimento.setId_ponto(ponto);
			ponto_movimento.setId_pessoa_associado(estab);
			ponto_movimento.setPontuacaoMinima(ponto.getPontuacaoMinima());
			ponto_movimento.setId_Pessoa_Registro(estab);
			ponto_movimento.setUnidadeporPonto(ponto.getUnidadeporPonto());
			ponto_movimento.setValordaUnidade(ponto.getValordaUnidade());
			ponto_movimento.setValorUnidadeDevolucao(ponto.getValorUnidadeDevolucao());
			ponto_movimento.setValorUnidadeTroca(ponto.getValorUnidadeTroca());
			ponto_movimento.setDiasValidade(ponto.getDiasValidade());
			ponto_movimento.setValidade(Utilidades.retornaValidade(ponto_movimento.getDiasValidade()));
			ponto_movimento.setValoraPontuar(0);
			ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.C);
		}
		return ponto_movimento;
	}

	

	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Pessoa pessoa = gson.fromJson(json, Pessoa.class);
		PessoaDAO pDAO = new PessoaDAO();

		pessoa = pDAO.merge(pessoa);

		String jsonSaida = gson.toJson(pessoa);
		return jsonSaida;
	}

}
