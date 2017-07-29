package br.com.projetabrasil.resource;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.projetabrasil.model.business.BairroBusiness;
import br.com.projetabrasil.model.business.CidadeBusiness;
import br.com.projetabrasil.model.business.EstadoBusiness;
import br.com.projetabrasil.model.business.LogradouroBusiness;
import br.com.projetabrasil.model.business.PaisBusiness;
import br.com.projetabrasil.model.dao.BairroDAO;
import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.dao.LogradouroDAO;
import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Enum_Aux_Estados;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Sexo;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Relacionamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_de_Contato;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.json.Contato_Json;
import br.com.projetabrasil.model.json.QRCodeJson;
import br.com.projetabrasil.util.Utilidades;
import br.com.projetabrasil.util.viacep.CEP;
import br.com.projetabrasil.util.viacep.ViaCEPClient;

@Path("/qrcode")
public class QRCodeResource {

	private Pais pais;
	private Estado estado;
	private Cidade cidade;
	private Bairro bairro;
	private Logradouro logradouro;
	private Endereco endereco;

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Enum_Aux_Tipo_Logradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(Enum_Aux_Tipo_Logradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	private Enum_Aux_Tipo_Logradouro tipoLogradouro;

	@POST
	@Path("/cadastrar")

	public String cadastrarPessoa(String json) {
		System.out.println("" + json);
		/*PessoaDAO pDAO = new PessoaDAO();
		final Gson g = new GsonBuilder().registerTypeAdapter(QRCodeJson.class, new MyTypeAdapter<QRCodeJson>())
				.create();
		QRCodeJson qjs = g.fromJson(json, QRCodeJson.class);
		String imagem = qjs.getFoto();
		System.out.println("" + imagem);
		String caminho = "";

		// Busca o QRCode
		QRCode q = new QRCodeDAO().buscaQRCodePorCpfEId(qjs.getCliente_cpf(), Long.parseLong(qjs.getqRCode_Id()));
		Pessoa p = pDAO.retornaPessoaPelocpf_Cnpj(qjs.getCliente_cpf());
		if (p == null) {
			// cadastra a pessoa
			p = new Pessoa();
			// cadastra o endereço
			p.setCpf_Cnpj(qjs.getCliente_cpf());
			p.setDataNascimento(Date.valueOf(qjs.getCliente_dataNascimento()));
			p.setDescricao(qjs.getCliente_nome_Razao());
			p.setEmail("");
			p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
			p.setFantasia_Apelido(qjs.getCliente_nome_Razao());
			p.setFcm_Tokem(qjs.getCliente_Fcm_Tokem());
			p.setId_Empresa(1);
			p.setIdentificador(qjs.getCliente_cpf());
			p.setRg_Insc(qjs.getCliente_identidade());
			p.setSenha(qjs.getUsuario_Senha());
			p.setSexo(Enum_Aux_Sexo.valueOf(qjs.getCliente_sexo()));
			p.setUltimaAtualizacao(Utilidades.retornaData());
			p.setFcm_Tokem(qjs.getCliente_Fcm_Tokem());
			Pessoa pRegis = new Pessoa();
			if (q.getId_Pessoa_Revenda() != null)
				pRegis = q.getId_Pessoa_Revenda();
			else

			if (q.getId_Pessoa_Representacao() != null)
				pRegis = q.getId_Pessoa_Representacao();
			else

			if (q.getId_Pessoa_Revenda() != null)
				pRegis = q.getId_Pessoa_Revenda();
			else

			if (q.getId_Pessoa_Distribuicao() != null)
				pRegis = q.getId_Pessoa_Distribuicao();
			else if (q.getId_Pessoa_Logistica() != null)
				pRegis = q.getId_Pessoa_Logistica();

			p.setId_Pessoa_Registro(pRegis);
			p = pDAO.merge(p);

			Pessoa_Vinculo pVin = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
			pVin.setId_pessoa_d(p);
			pVin = pVinDAO.retornaVinculo_Mestre(p, pRegis, Enum_Aux_Perfil_Pessoa.CLIENTES);
			if (pVin == null) {
				pVin = new Pessoa_Vinculo();
				pVin.setId_pessoa_d(p);
				pVin.setId_pessoa_m(pRegis);
				pVin.setAtivo(true);
				pVin.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
				pVin.setId_Empresa(1);
				pVin.setId_Pessoa_Registro(pRegis);
				pVin.setUltimaAtualizacao(Utilidades.retornaCalendario());
				pVin = pVinDAO.merge(pVin);
			}

			Pessoa_Enum_Aux_Perfil_Pessoa pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
			Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
			pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
			pp.setId_pessoa(p);
			pp = ppDAO.isPerfilExiste(pp);
			if (pp == null) {
				pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
				pp.setId_pessoa(p);
				pp.setId_Empresa(1);
				pp.setId_Pessoa_Registro(pRegis);
				pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
				pp = ppDAO.merge(pp);
			}
			endereco = new Endereco();
			CEP cp = new CEP();

			if (qjs.getEndereco_cep() != null && qjs.getEndereco_cep() != "") {
				String cep;
				cep = qjs.getEndereco_cep();

				ViaCEPClient client = new ViaCEPClient();

				try {
					cp = client.getEndereco(cep);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				if (cp.getBairro() == null && qjs.getEndereco_bairro() != null)
					cp.setBairro(qjs.getEndereco_bairro());

				if (cp.getLogradouro() == null && qjs.getEndereco_logradouro() != null)
					cp.setLogradouro(qjs.getEndereco_logradouro());

				if (cp.getLocalidade() == null && qjs.getEndereco_cidade() != null)
					cp.setLocalidade(qjs.getEndereco_cidade());

				if (cp.getComplemento() == null && qjs.getEndereco_complemento() != null)
					cp.setComplemento(qjs.getEndereco_complemento());

				if (cp.getTipo_Logradouro() == null && qjs.getEndereco_TipoLogradouro() != null)
					cp.setTipo_Logradouro(qjs.getEndereco_TipoLogradouro());
			}
			/////////////////////////////

			endereco = fazChecagemDoCEPnaBasedeDados(cp, pRegis);
			if (qjs.getEndereco_numero() != null && qjs.getEndereco_numero() != "")
				endereco.setNumero(Integer.parseInt(qjs.getEndereco_numero()));
			endereco.setComplemento(qjs.getEndereco_complemento());

			// cadastra o contato
			List<Contato> c = new ArrayList<>();
			if (qjs.getContato() != null) {
				for (Contato_Json ctjs : qjs.getContato()) {
					Contato ct = new Contato();
					ct.setContato(Utilidades.retornaSomenteNumeros(ctjs.getContato()));
					ct.setId_Empresa(1);
					ct.setId_Pessoa(p);
					ct.setId_Pessoa_Registro(pRegis);
					ct.setTipoContato(Enum_Aux_Tipo_de_Contato.valueOf(ctjs.getTipoContato()));
					ct.setTipoRelacionamento(Enum_Aux_Tipo_Relacionamento.valueOf(ctjs.getRelacionamento()));
					ct.setUltimaAtualizacao(Utilidades.retornaCalendario());
					Contato ctConf = new ContatoDAO().buscarContatoPelaPessoaEContato(p, ct.getContato());
					if (ctConf == null) {
						ct = new ContatoDAO().merge(ct);
						c.add(ct);
					}

				}
			}

		}else{
			if(p.getFcm_Tokem()==null || p.getFcm_Tokem()==""){
				p.setFcm_Tokem(qjs.getCliente_Fcm_Tokem());
				p = new PessoaDAO().merge(p);
			}
		}
		//foto
		
		if(qjs.getFoto()!=null && qjs.getFoto().length()>0 ){
			Utilidades.converterTextoemImagem(qjs.getFoto(), Utilidades.getCaminhofotopessoas() + "" + p.getId() 
			+ Utilidades.getTipoimagem());
			
		}*/
		
		return "Ok";
	}

	

	public Endereco fazChecagemDoCEPnaBasedeDados(CEP cep, Pessoa p) {
		// ao setar um endereço novo -- já verificamos se o pais padrão BRASIL
		// está cadastrdo se não estiver cadastrado,
		// ocorre o merge na base de dados e o atributo geral pais é setado - e
		// o atributo geral endereço também é
		// criado e setado com pais e um tipo de logradouro padrão;
		cep.setBairro(Utilidades.removerAcentos(cep.getBairro().toUpperCase()));
		cep.setCep(Utilidades.retiraCaracteres(cep.getCep()));
		cep.setComplemento(Utilidades.removerAcentos(cep.getComplemento()));
		cep.setLocalidade(Utilidades.removerAcentos(cep.getLocalidade().toUpperCase()));
		cep.setLogradouro(Utilidades.removerAcentos(cep.getLogradouro()));
		if (cep.getTipo_Logradouro() != null && cep.getTipo_Logradouro().length() > 0)
			cep.setTipo_Logradouro(Utilidades.removerAcentos(cep.getTipo_Logradouro().toUpperCase()));
		else
			cep.setTipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA.getDescricao());

		setarEnderecoNovo(p);
		Estado e = new Estado();
		e = EstadoBusiness.buscaEstadoPelaSigla(cep.getUf());
		if (e == null) {
			e = new Estado();
			e.setSigla(cep.getUf());
			e.setDescricao(Enum_Aux_Estados.valueOf(cep.getUf()).getDescricao());
			e.setId_Empresa(1);
			e.setPais(pais);
			e.setSigla(cep.getUf());
			e.setId_Pessoa_Registro(p);
			e.setUltimaAtualizacao(Utilidades.retornaCalendario());
			e = EstadoBusiness.merge(e);
		}

		Cidade c = new Cidade();
		c = CidadeBusiness.buscaCidadePeloNomeEEstado(e, cep.getLocalidade());
		if (c == null && cep.getLocalidade() != null && cep.getLocalidade().length() > 0) {
			c = new Cidade();
			ViaCEPClient client = new ViaCEPClient();
			CEP cepRetorno;
			try {
				cepRetorno = client.getEndereco(cep.getCep());
				if (cepRetorno != null)
					c.setCep(Utilidades.retiraCaracteres(cepRetorno.getCep()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			c.setDescricao(cep.getLocalidade());
			c.setEstado(e);
			c.setId_Empresa(1);
			c.setId_Pessoa_Registro(p);
			c.setUltimaAtualizacao(Utilidades.retornaCalendario());
			c = CidadeBusiness.merge(c);
		}

		Logradouro l = new Logradouro();
		String ll = cep.getLogradouro();
		if (ll == null)
			ll = "";
		l = LogradouroBusiness.buscaLogradouroPeloNomeECidade(ll, c);
		if (l == null) {
			l = new Logradouro(new Cidade(new Estado(pais)));
			l.setCidade(c);
			l.setDescricao(ll);
			l.setId_Empresa(1);
			l.setId_Pessoa_Registro(p);
			l.setUltimaAtualizacao(Utilidades.retornaCalendario());
			l = LogradouroBusiness.merge(l);
		}

		Bairro b = new Bairro();
		String bb = cep.getBairro();
		if (bb == null)
			bb = "";

		b = BairroBusiness.buscaBairroPeloNomeECidade(bb, c);
		if (b == null) {
			b = new Bairro();
			b.setCidade(c);
			b.setDescricao(cep.getBairro());
			b.setId_Empresa(1);
			b.setId_Pessoa_Registro(p);
			b.setUltimaAtualizacao(Utilidades.retornaCalendario());
			b = BairroBusiness.merge(b);
		}
		String tpl = cep.getTipo_Logradouro();
		if (tpl.length() > 0) {
			endereco.getLogradouro()
					.setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.valueOf(cep.getTipo_Logradouro()));
		} else
			endereco.getLogradouro().setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RUA);

		endereco.setBairro(b);
		endereco.setLogradouro(l);
		endereco.setComplemento("");
		endereco.setId_Empresa(1);
		endereco.setCep(cep.getCep());
		endereco.setPessoa(p);
		endereco.setUltimaAtualizacao(Utilidades.retornaCalendario());

		return endereco;
	}

	public Pais checaPais(String par, Pessoa pes) {
		Pais p = new PaisDAO().buscaPaisPeloNome(par);

		return p;
	}

	public Estado checaEstado(String par, Pessoa pes) {
		Estado e = new EstadoDAO().buscaEstadoPelaSigla(par);

		return e;
	}

	public Cidade checaCidade(String par, Estado e, Pessoa pes) {
		Cidade c = new CidadeDAO().buscaCidadePeloNomeEEstado(e, par);

		return c;
	}

	public Logradouro checaLogradouro(String par, Cidade c) {
		Logradouro l = new LogradouroDAO().buscaLogradouroPeloNomeEPelaCidade(par, c);
		return l;
	}

	public Bairro checaBairro(String par, Cidade c) {
		Bairro b = new BairroDAO().buscaBairroPeloNomeECidade(par, c);
		return b;
	}

	public void setarEnderecoNovo(Pessoa p) {
		pais = new Pais();
		setPais(PaisBusiness.VerificaPaisPadrao("BRASIL", "BRL", p));
		tipoLogradouro = Enum_Aux_Tipo_Logradouro.RUA;
		endereco = new Endereco(new Logradouro(new Cidade(new Estado(pais))), new Bairro(new Cidade(new Estado(pais))));

	}

}
