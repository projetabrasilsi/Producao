package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.business.PessoaGenericBusiness;
import br.com.projetabrasil.model.business.Pessoa_VinculoBusiness;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.PontoDAO;
import br.com.projetabrasil.model.dao.Ponto_MovimentoDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Movimento_Ponto;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mensagem;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Ponto;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;



@SuppressWarnings("serial")

@ManagedBean(name = "auto")
@ViewScoped
public class Auto_AtendimentojsfController implements Serializable {
	private PerfilLogado perfilLogado;

	private Ponto_Movimento ponto_movimento = new Ponto_Movimento();
	private Enum_Aux_Tipo_Identificador tipoIdentificadorCliente;
	private Enum_Aux_Tipo_Identificador tipoIdentificadorEstabelecimento;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadoresCliente;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadoresEstabelecimento;
	private Ponto ponto;
	private Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao;
	private Pessoa cliente;
	private Pessoa estabelecimento;
	private Usuario usAuto;
	private Usuario autoUs;
	private Pessoa autoPes;
	private List<Ponto> listaPonto;

	private Double pontoSoma;
	private Double pontoSomaCredito;
	private Double pontoSomaDebitoUtilizacao;
	private Double pontoSomaDebitoEstorno;
	private PessoaConfig pessoaConfig;
	private InputStream foto = null;
	private StreamedContent comprovante = null;
	private UploadedFile upLoaded;
	private final String tipoDeImagem = Utilidades.getTipoImagemSemPonto();
	private String mensagemComprovante = "Caso esteja sem o comprovante vinculado e \n digite o n. documento ERRADO,\n"
			+ "poderá invalidar sua pontuação. Deseja continuar?";
	private boolean skip;
	private boolean proximo;

	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private boolean renderizaSenha;

	@PostConstruct
	public void novo() {
		setRenderizaSenha(false);
		ponto_movimento = new Ponto_Movimento();
		ponto_movimento.setCaminhodaImagem(Utilidades.getBranco());
		cliente = configurarPessoa(Enum_Aux_Tipo_Identificador.CPF);
		autoPes = configurarPessoa(Enum_Aux_Tipo_Identificador.CPF);
		autoPes.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		autoUs = new Usuario();
		autoUs.setSenhaSemCript("");

		cliente.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		estabelecimento = configurarPessoa(Enum_Aux_Tipo_Identificador.CNPJ);
		setTipoIdentificadorCliente(Enum_Aux_Tipo_Identificador.CPF);
		setTipoIdentificadorEstabelecimento(Enum_Aux_Tipo_Identificador.CNPJ);
		listarTipodeIdentificadores();
		usAuto = new Usuario();
		setProximo(false);

		pontoSoma = 0d;
		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		Utilidades.abrirfecharDialogos("dialogoPontuar", false);
	}

	public void cancelar() {
		novo();
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			if (isProximo())
				return event.getNewStep();
			else
				return event.getOldStep();
		}
	}

	public void vincularPessoa() {
		if (estabelecimento != null) {
			Pessoa_Vinculo pVincRet = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVDAO = new Pessoa_VinculoDAO();
			pVincRet = pVDAO.retornaVinculo_Mestre(cliente, estabelecimento, Enum_Aux_Perfil_Pessoa.CLIENTES);

			if (pVincRet == null) {
				Pessoa_Vinculo pVinc = new Pessoa_Vinculo();

				pVinc.setAtivo(true);
				pVinc.setId_Empresa(1);
				pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
				pVinc.setId_pessoa_d(cliente);
				pVinc.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);

				pVinc.setId_pessoa_m(estabelecimento);
				pVinc.setId_Pessoa_Registro(cliente);
				pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
				Pessoa_VinculoBusiness.merge(pVinc);
			}
		} else {
			if (perfilLogado == null) {
				cadastroAutomatico();
			}
		}

	}

	public void upload(FileUploadEvent event) {
		try {
			try {
				comprovante = new DefaultStreamedContent(event.getFile().getInputstream());
				this.setUpLoaded(event.getFile());
			} catch (IOException e) {

			}

			UploadedFile arquivoUpload = event.getFile();
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			ponto_movimento.setCaminhoTemp(arquivoTemp.toString());

			ponto_movimento.setCaminhodaImagem(ponto_movimento.getCaminhoTemp());
			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}

	public void merge() {
		Path caminhoTemp = null;
		if (ponto_movimento.getCaminhoTemp() == null || ponto_movimento.getCaminhoTemp() == "") {
			mensagensDisparar("Sem o comprovante, caso digite n. Doc errado,  será inválidado!!!");
		} else {
			caminhoTemp = Paths.get(ponto_movimento.getCaminhoTemp());
			if (!Files.exists(caminhoTemp)) {
				mensagensDisparar("Imagem é obrigatória!!!");
				mensagensDisparar("Sem o comprovante, caso digite n. Doc errado,  será inválidado!!!");
			}
		}

		if (cliente == null || cliente.getId() == null) {
			mensagensDisparar("Informe seu CPF/CNPJ e Verifique a existência de seu cadastro!!!");
			return;
		}
		if (estabelecimento == null || estabelecimento.getId() == null) {
			mensagensDisparar(
					"Informe CPF/CNPJ do estabelecimento e Verifique a existência do cadastro do estabelecimento!!!");
			return;
		}

		vincularPessoa();

		ponto_movimento.setId_pessoa_cliente(cliente);
		ponto_movimento.setId_Empresa(1);

		if (ponto_movimento.getCreditaDebita().equals(Enum_Aux_Tipo_Mov_Ponto.C)) {
			int pontos = (int) (ponto_movimento.getUnidadeporPonto()
					* ((int) ponto_movimento.getValoraPontuar() / ponto_movimento.getValordaUnidade()));
			ponto_movimento.setPontosAtingidos(pontos);

		}

		ponto_movimento.setUltimaAtualizacao(Utilidades.retornaCalendario());
		estabelecimento.setCpf_Cnpj(Utilidades.retiraCaracteres(estabelecimento.getCpf_Cnpj()));
		estabelecimento.setCpf_Cnpj(Utilidades.retiraVazios(estabelecimento.getCpf_Cnpj()));

		pessoaaverificar(estabelecimento.getCpf_Cnpj());

		cliente.setCpf_Cnpj(Utilidades.retiraCaracteres(cliente.getCpf_Cnpj()));
		cliente.setCpf_Cnpj(Utilidades.retiraVazios(cliente.getCpf_Cnpj()));
		pessoaaverificar(cliente.getCpf_Cnpj());
		if (cliente == null || cliente.getId() == null) {
			mensagensDisparar("Cliente não foi escolhido!!!");
			return;

		}
		if (estabelecimento == null || estabelecimento.getId() == null) {
			mensagensDisparar("estabelecimento não foi encntrado!!!");
			return;

		}

		ponto_movimento.setId_pessoa_associado(estabelecimento);
		ponto_movimento.setId_pessoa_cliente(cliente);
		ponto_movimento.setId_Pessoa_Registro(cliente);
		ponto_movimento.setEnum_Aux_Status_Movimento_Ponto(Enum_Aux_Status_Movimento_Ponto.ACONFIRMAR);
		ponto_movimento.setDataLancamento(Utilidades.retornaValidade(0));
		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		List<Ponto_Movimento> pMov = pDAO.verificaSePontuacaoExiste(cliente, estabelecimento,
				Enum_Aux_Tipo_Mov_Ponto.C);

		if (pMov != null && pMov.size() > 0) {
			mensagensDisparar("Sua pontuação já ocorreu hoje para este estabelecimento!!!");
			return;
		}
		ponto_movimento = pDAO.merge(ponto_movimento);

		String cam = Utilidades.getCaminhofotocomprovante() + "" + ponto_movimento.getId() + Utilidades.getTipoimagem();
		ponto_movimento.setCaminhodaImagem(cam);
		if (ponto_movimento.getCaminhoTemp() != null && ponto_movimento.getCaminhoTemp().length() > 0) {
			Path origem = caminhoTemp;
			Path destino = Paths.get(ponto_movimento.getCaminhodaImagem());
			try {
				Utilidades.gravaDiretorio(ponto_movimento.getCaminhodaImagem());

				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException error) {
				mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");
				error.printStackTrace();
			}
		}

		if (ponto_movimento != null && ponto_movimento.getId() != null) {
			mensagensDisparar("Pontuação para  " + ponto_movimento.getId_pessoa_cliente().getDescricao()
					+ " realizada com sucesso!!!");
		}
		Utilidades.abrirfecharDialogos("dialogoPontuar", false);
		novo();

	}

	public void cadastroAutomatico() {
		perfilLogado = new PerfilLogado();
		perfilLogado.setRenderizaAssociado(false);
		perfilLogado.setIdentificadorAssinante("99999999999");
		perfilLogado.setSenhaUsuario("98765432");
		autenticar();
	}

	public void mergePessoa() {

		cliente.setId_Empresa(1);
		cliente.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		cliente.setUltimaAtualizacao(Utilidades.retornaCalendario());
		if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null
				&& (perfilLogado.getUsLogado() == null || perfilLogado.getUsLogado().getPessoa() == null
						|| perfilLogado.getUsLogado().getId() == null))
			cliente.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		else
			cliente.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());

		usAuto.setPessoa(cliente);

		if (!PessoaBusiness2.validaDados(usAuto, perfilLogado, true, true, true))
			return;

		if (cliente.getId() != null
				&& perfilLogado.getPaginaAtual().getPerfilPessoa().equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)) {
			Pessoa_Vinculo pVin = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
			pVin.setId_pessoa_d(cliente);
			pVin = pVinDAO.retornaVinculo_Mestre(cliente, Enum_Aux_Perfil_Pessoa.ATENDENTES);
			if (pVin != null && !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
				Pessoa p = pVin.getId_pessoa_m();
				mensagensDisparar(
						"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
				return;
			}
		}

		cliente = PessoaGenericBusiness.merge(cliente, usAuto, perfilLogado, true);

		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		retornasomaPontuacao(cliente, estabelecimento);
	}

	public void autoCancela() {
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
	}

	public Usuario checaUsuario(Pessoa pessoa, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.checaUsuarioCadastrado(pessoa, senha);
		return usuario;
	}

	public void checaPessoa() {
		autoPes = verificaPessoa(autoPes.getIdentificador(), autoPes.getEnum_Aux_Tipo_Identificador());
		if (autoPes == null) {
			setRenderizaSenha(false);
			autoPes = configurarPessoa(Enum_Aux_Tipo_Identificador.CPF);
			autoPes.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);

		} else {
			autoUs = checaUsuario(autoPes, autoUs.getConfSenha());
			if (autoUs == null || autoUs.getId() == null) {
				autoUs = new Usuario();
				autoUs.setConfSenha("");
				setRenderizaSenha(false);
			} else
				setRenderizaSenha(true);
		}
		

	}

	public void autenticar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (perfilLogado.getIdentificadorUsuario() == null || perfilLogado.getIdentificadorUsuario().length() <= 0)
			perfilLogado.setIdentificadorUsuario(perfilLogado.getIdentificadorAssinante());

		perfilLogado.setUsLogado(
				usuarioDAO.autenticar(perfilLogado.getIdentificadorUsuario(), perfilLogado.getSenhaUsuario()));
		if (perfilLogado.getUsLogado() == null) {
			mensagensDisparar(Enum_Aux_Tipo_Mensagem.ERRAUTENTICACAO.getMensagem());
			return;
		}

		perfilLogado.listagemPerfisdousLogado();
		if (perfilLogado.getListaPerfisdousLogado() != null & perfilLogado.getListaPerfisdousLogado().size() > 1) {
			perfilLogado.escondeDialogoAltenticacacao(true);
		} else {
			if (perfilLogado.getListaPerfisdousLogado() != null & perfilLogado.getListaPerfisdousLogado().size() == 1) {
				if (perfilLogado.getPerfilUsLogado() == null
						|| !perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
					perfilLogado.setPerfilUsLogado(perfilLogado.getListaPerfisdousLogado().get(0));
				}

				perfilLogado.setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual.PAGINAALFA);
				perfilLogado.setPerfilUsLogado(Enum_Aux_Perfil_Pessoa.OUTROS);
				perfilLogado.setRenderizapessoaeditar(false);
				perfilLogado.setRenderizapessoanovo(false);
			}
		}
	}

	public Pessoa configurarPessoa(Enum_Aux_Tipo_Identificador tipoIdentificador) {
		PessoaConfig pessoaConfig = new PessoaConfig();
		Pessoa p = new Pessoa();
		p = pessoaConfig.ConfiguraPessoa(tipoIdentificador, p, false);
		p.setCpf_Cnpj("");
		return p;
	}

	public List<Ponto> litarRelacaodePontos(Pessoa id_Pessoa_Assinante) {
		PontoDAO pDAO = new PontoDAO();
		List<Ponto> l = (List<Ponto>) pDAO.retornarListaPontoConfig(id_Pessoa_Assinante,
				Enum_Aux_Tipo_Item_de_Movimento.PONTO);
		return l;
	}

	public void listarTipodeIdentificadores() {
		Enum_Aux_Tipo_Identificador[] identificadores;
		identificadores = Enum_Aux_Tipo_Identificador.values();
		listaTipodeIdentificadoresEstabelecimento = new ArrayList<Enum_Aux_Tipo_Identificador>();
		listaTipodeIdentificadoresCliente = new ArrayList<Enum_Aux_Tipo_Identificador>();
		listaTipodeIdentificadores = new ArrayList<Enum_Aux_Tipo_Identificador>();
		for (Enum_Aux_Tipo_Identificador identificador : identificadores) {
			if (identificador.getAux_tipo_pessoa().isSelecionar()) {
				listaTipodeIdentificadoresEstabelecimento.add(identificador);
				listaTipodeIdentificadoresCliente.add(identificador);
				listaTipodeIdentificadores.add(identificador);
			}
		}
	}

	public void retornasomaPontuacao(Pessoa cliente, Pessoa assinante) {
		pontoSomaCredito = 0d;
		pontoSomaDebitoEstorno = 0d;
		pontoSomaDebitoUtilizacao = 0d;

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		if (cliente != null && cliente.getId() != null && assinante != null && assinante.getId() != null) {
			pontoSomaCredito = pDAO.somadePontos(assinante, cliente, true, Enum_Aux_Tipo_Mov_Ponto.C,
					Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
			pontoSomaDebitoEstorno = pDAO.somadePontos(assinante, cliente, true, Enum_Aux_Tipo_Mov_Ponto.E,
					Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
			pontoSomaDebitoUtilizacao = pDAO.somadePontos(assinante, cliente, true, Enum_Aux_Tipo_Mov_Ponto.D,
					Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
		}

		if (pontoSomaCredito == null)
			pontoSomaCredito = 0d;
		if (pontoSomaDebitoUtilizacao == null)
			pontoSomaDebitoUtilizacao = 0d;
		if (pontoSomaDebitoEstorno == null)
			pontoSomaDebitoEstorno = 0d;
		pontoSoma = pontoSomaCredito - (pontoSomaDebitoUtilizacao + pontoSomaDebitoEstorno);
	}

	public void pessoaaverificar(String pessoa) {
		setProximo(false);
		if (pessoa.equals("Estabelecimento")) {
			String cpf_Cnpj = estabelecimento.getIdentificador();
			cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
			cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
			if (!PessoaBusiness2.validacpfCnpj(cpf_Cnpj, tipoIdentificadorEstabelecimento)) {

				return;
			}

			estabelecimento = verificaPessoa(estabelecimento, tipoIdentificadorEstabelecimento);

			if (estabelecimento != null && estabelecimento.getId() != null) {
				setListaPonto(litarRelacaodePontos(estabelecimento));
				setProximo(true);
			}

			else {
				setProximo(false);
			}

			if (getListaPonto() != null && getListaPonto().size() > 0) {
				ponto = getListaPonto().get(0);
				configPonto_Movimento();
			}

			else
				listaPonto = new ArrayList<Ponto>();
			retornasomaPontuacao(cliente, estabelecimento);
		} else {
			String cpf_Cnpj = cliente.getIdentificador();
			cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
			cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
			if (!PessoaBusiness2.validacpfCnpj(cpf_Cnpj, tipoIdentificadorCliente)) {
				setProximo(false);
				return;
			}

			cliente = verificaPessoa(cliente, tipoIdentificadorCliente);
			if (cliente == null || cliente.getId() == null) {
				configPessoaNova(cpf_Cnpj);
				cliente.setIdentificador(cpf_Cnpj);
				cliente.setCpf_Cnpj(cpf_Cnpj);
				cliente.setEnum_Aux_Tipo_Identificador(getTipoIdentificadorCliente());
				setProximo(false);
				Utilidades.abrirfecharDialogos("dialogoCadastro", true);
				RequestContext context = RequestContext.getCurrentInstance();

				context.update("formCadastro");

			} else {
				setProximo(true);
				retornasomaPontuacao(cliente, estabelecimento);
			}
			listaPonto = new ArrayList<Ponto>();
		}

	}

	public void configPessoaNova(String cpf_Cnpj) {
		cliente = new Pessoa();
		configurarPessoa();
		cliente = pessoaConfig.ConfiguraPessoa(tipoIdentificadorCliente, perfilLogado.getUsLogado(), cliente, false);
		cliente.setCpf_Cnpj(cpf_Cnpj);
		pessoaConfig.mudarLabels(tipoIdentificadorCliente.getAux_tipo_pessoa());

		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		pontoSoma = 0d;

	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
		cliente = new Pessoa();

		if (perfilLogado == null) {
			cadastroAutomatico();
		}
		cliente = pessoaConfig.ConfiguraPessoa(tipoIdentificadorCliente, perfilLogado.getUsLogado(), cliente, false);
		cliente.setCpf_Cnpj("");
	}

	public void configPonto_Movimento() {
		ponto_movimento = new Ponto_Movimento();
		if (ponto != null && ponto.getId() != null) {
			ponto_movimento.setId_ponto(ponto);
			ponto_movimento.setId_pessoa_associado(estabelecimento);
			ponto_movimento.setPontuacaoMinima(ponto.getPontuacaoMinima());
			ponto_movimento.setId_Pessoa_Registro(cliente);
			ponto_movimento.setUnidadeporPonto(ponto.getUnidadeporPonto());
			ponto_movimento.setValordaUnidade(ponto.getValordaUnidade());
			ponto_movimento.setValorUnidadeDevolucao(ponto.getValorUnidadeDevolucao());
			ponto_movimento.setValorUnidadeTroca(ponto.getValorUnidadeTroca());
			ponto_movimento.setDiasValidade(ponto.getDiasValidade());
			ponto_movimento.setValidade(Utilidades.retornaValidade(ponto_movimento.getDiasValidade()));

			ponto_movimento.setValoraPontuar(0);
			ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.C);
			ponto_movimento.setEnum_Aux_Status_Movimento_Ponto(Enum_Aux_Status_Movimento_Ponto.ACONFIRMAR);

		}

	}

	public Pessoa verificaPessoa(Pessoa p, Enum_Aux_Tipo_Identificador tipoIdentificador) {

		String cpf_Cnpj = p.getIdentificador();
		cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
		cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);

		if (tipoIdentificador.equals(Enum_Aux_Tipo_Identificador.CPF) && cpf_Cnpj.length() != 11) {
			mensagensDisparar("número de dígitos é diferente de 11");
			p = new Pessoa();
			return p;
		}
		if (tipoIdentificador.equals(Enum_Aux_Tipo_Identificador.CNPJ) && cpf_Cnpj.length() != 14) {
			mensagensDisparar("número de dígitos é diferente de 14");
			p = new Pessoa();
			return p;
		}
		PessoaDAO pessoaDAO = new PessoaDAO();
		p = pessoaDAO.retornaPelaIdentificacao(cpf_Cnpj);

		return p;
	}

	public Pessoa verificaPessoa(String identificador, Enum_Aux_Tipo_Identificador tipoIdentificador) {

		String cpf_Cnpj = identificador;

		cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
		cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
		Pessoa p;

		if (tipoIdentificador.equals(Enum_Aux_Tipo_Identificador.CPF) && cpf_Cnpj.length() != 11) {
			mensagensDisparar("número de dígitos é diferente de 11");
			p = new Pessoa();
			return p;
		}
		if (tipoIdentificador.equals(Enum_Aux_Tipo_Identificador.CNPJ) && cpf_Cnpj.length() != 14) {
			mensagensDisparar("número de dígitos é diferente de 14");
			p = new Pessoa();
			return p;
		}
		PessoaDAO pessoaDAO = new PessoaDAO();
		p = pessoaDAO.retornaPelaIdentificacao(cpf_Cnpj);

		return p;
	}

	public void retornasomaPontuacao(Pessoa cliente) {
		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		pontoSomaCredito = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.C);
		pontoSomaDebitoEstorno = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.E);
		pontoSomaDebitoUtilizacao = pDAO.somadePontos(perfilLogado, cliente, true, Enum_Aux_Tipo_Mov_Ponto.D);

		if (pontoSomaCredito == null)
			pontoSomaCredito = 0d;
		if (pontoSomaDebitoUtilizacao == null)
			pontoSomaDebitoUtilizacao = 0d;
		if (pontoSomaDebitoEstorno == null)
			pontoSomaDebitoEstorno = 0d;
		pontoSoma = pontoSomaCredito - (pontoSomaDebitoUtilizacao + pontoSomaDebitoEstorno);
	}

	private void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public Ponto_Movimento getPonto_movimento() {
		return ponto_movimento;
	}

	public void setPonto_movimento(Ponto_Movimento ponto_movimento) {
		this.ponto_movimento = ponto_movimento;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificadorCliente() {
		return tipoIdentificadorCliente;
	}

	public void setTipoIdentificadorCliente(Enum_Aux_Tipo_Identificador tipoIdentificadorCliente) {
		this.tipoIdentificadorCliente = tipoIdentificadorCliente;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificadorEstabelecimento() {
		return tipoIdentificadorEstabelecimento;
	}

	public void setTipoIdentificadorEstabelecimento(Enum_Aux_Tipo_Identificador tipoIdentificadorEstabelecimento) {
		this.tipoIdentificadorEstabelecimento = tipoIdentificadorEstabelecimento;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadoresCliente() {
		return listaTipodeIdentificadoresCliente;
	}

	public void setListaTipodeIdentificadoresCliente(
			List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadoresCliente) {
		this.listaTipodeIdentificadoresCliente = listaTipodeIdentificadoresCliente;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadoresEstabelecimento() {
		return listaTipodeIdentificadoresEstabelecimento;
	}

	public void setListaTipodeIdentificadoresEstabelecimento(
			List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadoresEstabelecimento) {
		this.listaTipodeIdentificadoresEstabelecimento = listaTipodeIdentificadoresEstabelecimento;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public Enum_Aux_Tipo_Mov_Ponto getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Pessoa getCliente() {
		return cliente;
	}

	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}

	public Pessoa getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Pessoa estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Double getPontoSoma() {
		return pontoSoma;
	}

	public void setPontoSoma(Double pontoSoma) {
		this.pontoSoma = pontoSoma;
	}

	public Double getPontoSomaCredito() {
		return pontoSomaCredito;
	}

	public void setPontoSomaCredito(Double pontoSomaCredito) {
		this.pontoSomaCredito = pontoSomaCredito;
	}

	public Double getPontoSomaDebitoUtilizacao() {
		return pontoSomaDebitoUtilizacao;
	}

	public void setPontoSomaDebitoUtilizacao(Double pontoSomaDebitoUtilizacao) {
		this.pontoSomaDebitoUtilizacao = pontoSomaDebitoUtilizacao;
	}

	public Double getPontoSomaDebitoEstorno() {
		return pontoSomaDebitoEstorno;
	}

	public void setPontoSomaDebitoEstorno(Double pontoSomaDebitoEstorno) {
		this.pontoSomaDebitoEstorno = pontoSomaDebitoEstorno;
	}

	public Usuario getUsAuto() {
		return usAuto;
	}

	public void setUsAuto(Usuario usAuto) {
		this.usAuto = usAuto;
	}

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public void setListaPonto(List<Ponto> listaPonto) {
		this.listaPonto = listaPonto;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}

	public StreamedContent getComprovante() {
		return comprovante;
	}

	public void setComprovante(StreamedContent comprovante) {
		this.comprovante = comprovante;
	}

	public UploadedFile getUpLoaded() {
		return upLoaded;
	}

	public void setUpLoaded(UploadedFile upLoaded) {
		this.upLoaded = upLoaded;
	}

	public String getTipoDeImagem() {
		return tipoDeImagem;
	}

	public String getMensagemComprovante() {
		return mensagemComprovante;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public boolean isProximo() {
		return proximo;
	}

	public void setProximo(boolean proximo) {
		this.proximo = proximo;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadores() {
		return listaTipodeIdentificadores;
	}

	public void setListaTipodeIdentificadores(List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores) {
		this.listaTipodeIdentificadores = listaTipodeIdentificadores;
	}

	public Usuario getAutoUs() {
		return autoUs;
	}

	public void setAutoUs(Usuario autoUs) {
		this.autoUs = autoUs;
	}

	public Pessoa getAutoPes() {
		return autoPes;
	}

	public void setAutoPes(Pessoa autoPes) {
		this.autoPes = autoPes;
	}

	public boolean isRenderizaSenha() {
		return renderizaSenha;
	}

	public void setRenderizaSenha(boolean renderizaSenha) {
		this.renderizaSenha = renderizaSenha;
	}

}
