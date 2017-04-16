package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.ObjetoBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.dao.CoresDAO;
import br.com.projetabrasil.model.dao.Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.Modelo_de_Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.entities.Cor;
import br.com.projetabrasil.model.entities.Enum_Aux_Classificacao_Objetos;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.model.entities.Modelo_de_Marca_e_Raca;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ObjetojsfController extends GenericController implements Serializable {

	private Objeto objeto;
	private List<Objeto> objetos;
	private Pessoa pessoa;
	private PessoaConfig pessoaConfig;
	private Usuario usuario;

	private List<Enum_Aux_Classificacao_Objetos> listaClassificacaoObjeto;
	
	

	private List<Marca_e_Raca> listaRacaMarca;
	

	private List<Modelo_de_Marca_e_Raca> listaModelo;
	

	private List<Cor> listaCor;
	private Cor cor;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;

	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;

	// ATRIBUTOS PARA FOTO
	private final String tipoDeImagem = Utilidades.getTipoImagemSemPonto();
	private StreamedContent foto = null;
	private UploadedFile upLoaded;

	@PostConstruct
	public void listar() {
		pessoa = new Pessoa();
		usuario = new Usuario();

		objetos = new ArrayList<>();
		objeto = new Objeto();

		
		listaRacaMarca = new ArrayList<>();
		listarRacaMarca();

		
		listaModelo = new ArrayList<>();

		cor = new Cor();
		listaCor = new ArrayList<>();

		configurarPessoa();

		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);

		
		listarCor();

		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);

	}

	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		objeto = new Objeto();
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);
	}

	public void merge() {

		this.objeto.setUltimaAtualizacao(Utilidades.retornaCalendario());

		// Inseri no banco o usuário que registrou o país, SE usuário NÃO
		// existir, o id_registro é feito com o associado
		if (perfilLogado.getUsLogado().getPessoa() != null) {
			this.objeto.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		} else {
			this.objeto.setId_Pessoa_Registro(perfilLogado.getAssLogado());
		}

		objeto.setId_Empresa(0);
		objeto.setId(null);
		objeto.setPerfil_do_Momento_do_Registro(perfilLogado.getPerfilUsLogado());
		
		objeto.setEnum_Aux_Tipos_Objeto(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos());
		objeto.setId_Pessoa_Vinculo(pessoa);

		// VALIDACAO PARA FOTO
		Path caminhoTemp;
		if (objeto.getCaminhoTemp() == null || objeto.getCaminhoTemp() == "") {
			mensagensDisparar("Imagem é obrigatória!!!");
			return;
		} else {
			caminhoTemp = Paths.get(objeto.getCaminhoTemp());
			if (!Files.exists(caminhoTemp)) {
				mensagensDisparar("Imagem é obrigatória!!!");
				return;
			}
		}
       
		objeto = ObjetoBusiness.merge(this.objeto);

		objeto.setCaminhodaImagem(
				Utilidades.getCaminhofotoobjetos() + "" + objeto.getId() + Utilidades.getTipoimagem());

		Path origem = caminhoTemp;
		Path destino = Paths.get(objeto.getCaminhodaImagem());
		try {
			Utilidades.gravaDiretorio(objeto.getCaminhodaImagem());
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");

			error.printStackTrace();
		}

		listaObjetosecaminhosdeimagem(pessoa);
		
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);

	}

	public void editar(ActionEvent event) {
		objeto = (Objeto) event.getComponent().getAttributes().get("registroAtual");
		objeto.setCaminhodaImagem(Utilidades.getCaminhofotoobjetos() + "" + objeto.getId() + Utilidades.getTipoimagem());

		objeto.setCaminhoTemp(objeto.getCaminhodaImagem());

		Utilidades.abrirfecharDialogos("dialogoCadastro", true);
	}

	public void cancela() {
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage", true);
		}
	}

	public void cancelaValidacao() {
		Utilidades.abrirfecharDialogos("dialogoIdentidade", false);

		if (perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.OUTROS)) {
			perfilLogado = new PerfilLogado();
			autenticacao.redirecionaPaginas("alfapage.xhtml", "Erro ao tentar chamar a pagina alfapage", true);
		}

		autenticacao.redirecionaPaginas("index.xhtml", null, true);
	}

	

	public void listaObjetosecaminhosdeimagem(Pessoa p) {
		objetos = new ArrayList<Objeto>();
		List<Objeto> objetosEImagens = new ArrayList<Objeto>();

		ObjetoDAO objDAO = new ObjetoDAO();

		objetos = objDAO.lista_Objetos(p);
		int x = 0;
		for (Objeto i : objetos) {
			i.setCaminhodaImagem(Utilidades.getCaminhofotoobjetos() + "" + i.getId() + Utilidades.getTipoimagem());
			i.setTipodeImagem(Utilidades.tipodeImagem());
			objetosEImagens.add(x, i);
			x++;
		}
		objetos = objetosEImagens;
	}

	public void buscaPessoa() {
		/*
		 * o padrão aqui é física ou jurídica mas quando ele chama
		 * PessoasBusiness.buscaPessoa ele pode buscar perfil OUTROS
		 */
		pessoa.setCpf_Cnpj(Utilidades.retiraCaracteres(pessoa.getCpf_Cnpj()));
		pessoa.setIdentificador(pessoa.getCpf_Cnpj());
		Enum_Aux_Tipo_Identificador tipoIdent = pessoa.getEnum_Aux_Tipo_Identificador();
		usuario.setPessoa(pessoa);
		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, true, true, true))
			return;

		String identificador;
		identificador = pessoa.getIdentificador();
		pessoa = PessoaBusiness.buscaPessoa(pessoa);

		if (pessoa.getId() == null) {
			mensagensDisparar("Este CPF não existe na base de dados");
			return;
		}

		if (pessoa.getIdentificador() == null || pessoa.getIdentificador().length() <= 0) {
			pessoa.setIdentificador(identificador);
			pessoa.setCpf_Cnpj(identificador);
		} else {
			if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAATENDENTES)) {

				Pessoa_Vinculo pVin = new Pessoa_Vinculo();
				Pessoa_VinculoDAO pVinDAO = new Pessoa_VinculoDAO();
				pVin = pVinDAO.retornaVinculo_Mestre(pessoa, Enum_Aux_Perfil_Pessoa.ATENDENTES);
				if (pVin != null
						&& !perfilLogado.getUsLogado().getPessoa().getId().equals(pVin.getId_pessoa_m().getId())) {
					Pessoa p = pVin.getId_pessoa_m();
					mensagensDisparar(
							"Este atendente já tem um Vinculo com um Outro Associado: " + p.getFantasia_Apelido());
					return;
				}

			}
			// SOMENTE LISTA SE PESSOA EXISTIR
		}
		if (pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa().equals(Enum_Aux_Tipo_Pessoa.OUTROS))
			pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
		if (!pessoa.getEnum_Aux_Tipo_Identificador().equals(tipoIdent))
			pessoa.setEnum_Aux_Tipo_Identificador(tipoIdent);

		listaObjetosecaminhosdeimagem(pessoa);

		

		mudaLabel();
		Utilidades.abrirfecharDialogos("dialogoIdentidade", false);
	}

	public void listarRacaMarca() {
		setListaRacaMarca(new Marca_e_RacaDAO().listar_Marca_e_Raca(perfilLogado.getPaginaAtual().getClassificacaoObjeto()));		
		listaModelo = new ArrayList<>();
	}

	public void listarModelo() {
		setListaModelo(new Modelo_de_Marca_e_RacaDAO().listar_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca(objeto.getId_Marca_e_Raca()));
	}

	public void listarCor() {
		setListaCor(new CoresDAO().listar());
	}

	public void mudaLabel() {
		pessoaConfig.mudarLabels(pessoa.getEnum_Aux_Tipo_Identificador().getAux_tipo_pessoa());
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void novaCor() {
		this.cor = new Cor();
		Utilidades.abrirfecharDialogos("dialogoCadastroCor", true);
	}

	public void adicionarCor(ActionEvent event) {
		if (new CoresDAO().verifica_Cor(cor.getDescricao()) == null) {
			new CoresDAO().merge(cor);
		}

		listarCor();
		Utilidades.abrirfecharDialogos("dialogoCadastroCor", false);
	}

	public void upload(FileUploadEvent event) {
		try {
			try {
				foto = new DefaultStreamedContent(event.getFile().getInputstream());
				this.setUpLoaded(event.getFile());
			} catch (IOException e) {

			}

			UploadedFile arquivoUpload = event.getFile();

			// Messages.addGlobalInfo(arquivoUpload.getContentType()+"-"+arquivoUpload.getSize()+"-"+arquivoUpload.getFileName()+"-");
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			objeto.setCaminhoTemp(arquivoTemp.toString());
			objeto.setCaminhodaImagem(objeto.getCaminhoTemp());
			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public PerfilLogado getPerfilLogadoTemp() {
		return perfilLogadoTemp;
	}

	public void setPerfilLogadoTemp(PerfilLogado perfilLogadoTemp) {
		this.perfilLogadoTemp = perfilLogadoTemp;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public List<Enum_Aux_Classificacao_Objetos> getListaClassificacaoObjeto() {
		return listaClassificacaoObjeto;
	}

	public void setListaClassificacaoObjeto(List<Enum_Aux_Classificacao_Objetos> listaClassificacaoObjeto) {
		this.listaClassificacaoObjeto = listaClassificacaoObjeto;
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Marca_e_Raca> getListaRacaMarca() {
		return listaRacaMarca;
	}

	public void setListaRacaMarca(List<Marca_e_Raca> listaRacaMarca) {
		this.listaRacaMarca = listaRacaMarca;
	}

	

	public List<Modelo_de_Marca_e_Raca> getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(List<Modelo_de_Marca_e_Raca> listaModelo) {
		this.listaModelo = listaModelo;
	}

	

	public List<Cor> getListaCor() {
		return listaCor;
	}

	public void setListaCor(List<Cor> listaCor) {
		this.listaCor = listaCor;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
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

}