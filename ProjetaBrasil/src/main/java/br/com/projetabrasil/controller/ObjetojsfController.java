package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.projetabrasil.model.business.Pessoa_VinculoBusiness;
import br.com.projetabrasil.model.dao.CoresDAO;
import br.com.projetabrasil.model.dao.Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.Modelo_de_Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.ObjetoDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.Prontuario_de_EmergenciaDAO;
import br.com.projetabrasil.model.entities.Cor;
import br.com.projetabrasil.model.entities.Enum_Aux_Classificacao_Objetos;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Sexo;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Pet;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Prontuario_de_Emergencia;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Vacina;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipos_Objetos;
import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.model.entities.Modelo_de_Marca_e_Raca;
import br.com.projetabrasil.model.entities.Objeto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Prontuario_de_Emergencia;
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
	
	private List<Pessoa> listaVeterinariosDaClinica;
	private Pessoa id_veterinario_responsavel;
	
	private List<Enum_Aux_Classificacao_Objetos> listaClassificacaoObjeto;	
	
	private List<Enum_Aux_Sexo> listaSexo;
	
	private List<Prontuario_de_Emergencia> listaProntuarioEmergencia;
	private List<Prontuario_de_Emergencia> listaVacina;

	private List<Marca_e_Raca> listaRacaMarca;
	private Marca_e_Raca raca;

	private List<Modelo_de_Marca_e_Raca> listaModelo;

	private List<Cor> listaCor;
	private Cor cor;
	
	private Date dataVacina;
	
	private Prontuario_de_Emergencia prontuarioEmergencia;

	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private PerfilLogado perfilLogadoTemp;

	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	
	private Enum_Aux_Tipo_Prontuario_de_Emergencia tipoProntuarioEmergencia;
	private List<Enum_Aux_Tipo_Prontuario_de_Emergencia> listaTiposProntuarioEmergencia;
	private String descricaoProntuario;
	
	private Enum_Aux_Tipo_Vacina tipoVacina;
	private List<Enum_Aux_Tipo_Vacina> listaTiposVacina;

	// ATRIBUTOS PARA FOTO
	private final String tipoDeImagem = Utilidades.getTipoImagemSemPonto();
	private final String tipoDeAudio = Utilidades.getTipoAudioSemPonto();
	private StreamedContent audio = null;
	private StreamedContent foto = null;
	private UploadedFile upLoaded;

	//REVER ATRIBUTOS A BAIXO
	private String labelAno;
	
	@PostConstruct
	public void listar() {
		pessoa = new Pessoa();
		usuario = new Usuario();

		objetos = new ArrayList<>();
		objeto = new Objeto();
		
		this.raca = new Marca_e_Raca();
		
		listaRacaMarca = new ArrayList<>();
		listarRacaMarca();
		
		listaProntuarioEmergencia = new ArrayList<>();
		listaVacina = new ArrayList<>();
		
		id_veterinario_responsavel = new Pessoa();
		listaVeterinariosDaClinica = new ArrayList<>();
		
		listaModelo = new ArrayList<>();

		cor = new Cor();
		listaCor = new ArrayList<>();

		configurarPessoa();

		pessoa.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);

		
		listarCor();
		listarTiposdeProntuario();
		listarTiposdeVacina();
		listarVeterinariosDaClinica();
		listarTiposdeSexo();

		Utilidades.abrirfecharDialogos("dialogoIdentidade", true);

	}

	public void novo(ActionEvent event) {
		perfilLogadoTemp = perfilLogado;
		objeto = new Objeto();
		listaProntuarioEmergencia = new ArrayList<>();
		listaVacina = new ArrayList<>();
		mudaLabelAno();
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
		
		objeto.setPerfil_do_Momento_do_Registro(perfilLogado.getPerfilUsLogado());
		
		objeto.setEnum_Aux_Tipos_Objeto(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos());
		objeto.setId_Pessoa_Vinculo(pessoa);

		// VALIDACAO PARA FOTO
		Path caminhoTemp;
		Path caminhoTempAudio = null;
		if (objeto.getCaminhoTempAudio() != null && objeto.getCaminhoTempAudio() != "") {
			caminhoTempAudio = Paths.get(objeto.getCaminhoTempAudio());
		}		
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
		
		objeto = ObjetoBusiness.merge(objeto);

		objeto.setCaminhodaImagem(
				Utilidades.getCaminhofotoobjetos() + "" + objeto.getId() + Utilidades.getTipoimagem());
		
		objeto.setCaminhodoAudio(
				Utilidades.getCaminhoaudioobjetos() + "" + objeto.getId() + Utilidades.getTipoAudio());
		
		Path origem = caminhoTemp;
		Path destino = Paths.get(objeto.getCaminhodaImagem());
		try {
			Utilidades.gravaDiretorio(objeto.getCaminhodaImagem());
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");

			error.printStackTrace();
		}				
		
		if (caminhoTempAudio != null) {
			origem = caminhoTempAudio;
			destino = Paths.get(objeto.getCaminhodoAudio());
			try {
				Utilidades.gravaDiretorio(objeto.getCaminhodoAudio());
				Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException error) {
				mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");
				error.printStackTrace();
			}
		}					

		listaObjetosecaminhosdeimagem(pessoa); 
				
		objeto.setEnum_Aux_Status_Pet(Enum_Aux_Status_Pet.OK);
		objeto = ObjetoBusiness.merge(this.objeto);
		objetos.add(objeto);
		mergeListaProntuarioEmergencia();
		mergeListaVacina();
		
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);

	}
	
	public void mergeListaProntuarioEmergencia() {
		for (Prontuario_de_Emergencia c : listaProntuarioEmergencia) {
			if(c.getId_Objeto()==null || c.getId_Objeto().getId()==null)
				c.setId_Objeto(objeto);
			Prontuario_de_EmergenciaDAO cDAO = new Prontuario_de_EmergenciaDAO();
			cDAO.merge(c);
		}

	}
	
	public void mergeListaVacina() {
		for (Prontuario_de_Emergencia c : listaVacina) {
			if(c.getId_Objeto()==null || c.getId_Objeto().getId()==null)
				c.setId_Objeto(objeto);
			Prontuario_de_EmergenciaDAO cDAO = new Prontuario_de_EmergenciaDAO();
			cDAO.merge(c);
		}

	}

	public void editar(ActionEvent event) {
		objeto = (Objeto) event.getComponent().getAttributes().get("registroAtual");
		objeto.setCaminhodaImagem(Utilidades.getCaminhofotoobjetos() + "" + objeto.getId() + Utilidades.getTipoimagem());
		objeto.setCaminhodoAudio(Utilidades.getCaminhoaudioobjetos() + "" + objeto.getId() + Utilidades.getTipoAudio());
		
		objeto.setCaminhoTemp(objeto.getCaminhodaImagem());
		
		if(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos() == Enum_Aux_Tipos_Objetos.PETS){
			listarProntuarioEmergenciadoObjeto();
			listarVacinadoObjeto();
		}
			
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
	
	public void mudaLabelAno() {
		if(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos().equals(Enum_Aux_Tipos_Objetos.PETS)){
			setLabelAno("Ano de Nascimento:");
		}else{
			setLabelAno("Ano:");
		}
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
	}

	public void novaCor() {
		this.cor = new Cor();
		Utilidades.abrirfecharDialogos("dialogoCadastroCor", true);
	}
	
	public void novaRaca() {		
		this.raca = new Marca_e_Raca();
		Utilidades.abrirfecharDialogos("dialogoCadastroRaca", true);
	}

	public void adicionarCor(ActionEvent event) {
		if (new CoresDAO().verifica_Cor(cor.getDescricao()) == null) {
			new CoresDAO().merge(cor);
		}

		listarCor();
		Utilidades.abrirfecharDialogos("dialogoCadastroCor", false);
	}
	
	public void adicionarRaca(ActionEvent event) {
		this.raca.setEnum_Aux_Classificacao_Objetos(perfilLogado.getPaginaAtual().getClassificacaoObjeto());
		this.raca.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		this.raca.setId_Empresa(1);
		this.raca.setUltimaAtualizacao(Utilidades.retornaCalendario());
		this.raca.setCodigo("0");
		if (new Marca_e_RacaDAO().verifica_Marca_e_Raca(this.raca.getDescricao()) == null) {
			new Marca_e_RacaDAO().merge(this.raca);
		}

		listarRacaMarca();
		Utilidades.abrirfecharDialogos("dialogoCadastroRaca", false);
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
	
	public void uploadAudio(FileUploadEvent event) {
		try {
			try {
				audio = new DefaultStreamedContent(event.getFile().getInputstream());
				this.setUpLoaded(event.getFile());
			} catch (IOException e) {

			}

			UploadedFile arquivoUpload = event.getFile();

			// Messages.addGlobalInfo(arquivoUpload.getContentType()+"-"+arquivoUpload.getSize()+"-"+arquivoUpload.getFileName()+"-");
			Path arquivoTemp = Files.createTempFile(null, null);

			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);

			objeto.setCaminhoTempAudio(arquivoTemp.toString());
			objeto.setCaminhodoAudio(objeto.getCaminhoTempAudio());
			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}
	
	public boolean renderizaPorClassificacao(){
		if(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos().equals(Enum_Aux_Tipos_Objetos.PETS)){
			return false;
		}
		return true;
	}
	
	public boolean renderizaSeForPet() {
		if(perfilLogado.getPaginaAtual().getClassificacaoObjeto().getEnum_Aux_Tipos_Objetos().equals(Enum_Aux_Tipos_Objetos.PETS)){
			return true;
		}
		return false;
	}
	
	public boolean renderizaVacina() {
		if(perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.VETERINARIOS) || perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.AGROEVETERINARIA)
				|| perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.PETSHOPECLINICAVETERINARIA) || perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.AGROPECUARIA)   
					|| perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.CLINICAVETERINARIA) || perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.PETSHOP)
						|| perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES)){
			return true;
		}
		return false;
	}
	
	public void listarVeterinariosDaClinica(){		
		listaVeterinariosDaClinica = Pessoa_VinculoBusiness.listarVeterinariosDaClinica(perfilLogado.getUsLogado().getPessoa());
	}
	
	public void listarProntuarioEmergenciadoObjeto() {
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		listaProntuarioEmergencia = pDAO.listarProntuarioporObjeto(objeto);

	}
	
	public void excluirProntuario(ActionEvent evento) {
		setProntuarioEmergencia((Prontuario_de_Emergencia) evento.getComponent().getAttributes().get("registroAtualProntuario"));
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		pDAO.excluir(getProntuarioEmergencia());
		listaProntuarioEmergencia.remove(prontuarioEmergencia);
	}
	
	public void incluirProntuarioEmergenciaNaLista() {
		if(this.tipoProntuarioEmergencia == null){
			mensagensDisparar("É necessário selecionar um tipo de Prontuario");
			return;
		}else{
			prontuarioEmergencia = new Prontuario_de_Emergencia();
			prontuarioEmergencia.setId_Pessoa(null);
			prontuarioEmergencia.setId_Objeto(objeto);
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			prontuarioEmergencia.setId_Empresa(1);
			prontuarioEmergencia.setUltimaAtualizacao(Utilidades.retornaCalendario());
			
			prontuarioEmergencia.setTipo_Prontuario_Emergencia(this.tipoProntuarioEmergencia);
			prontuarioEmergencia.setDescricao(this.descricaoProntuario);		
					
			listaProntuarioEmergencia.add(prontuarioEmergencia);
		}		
		
	}
	
	public void listarVacinadoObjeto() {
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		listaVacina = pDAO.listarVacinaporObjeto(objeto);

	}
	
	public void excluirVacina(ActionEvent evento){
		setProntuarioEmergencia((Prontuario_de_Emergencia) evento.getComponent().getAttributes().get("registroAtualVacina"));
		Prontuario_de_EmergenciaDAO pDAO = new Prontuario_de_EmergenciaDAO();
		pDAO.excluir(getProntuarioEmergencia());
		listaVacina.remove(prontuarioEmergencia);
	}
	
	public void incluirVacinaNaLista(){
		if(this.tipoVacina == null){
			mensagensDisparar("É necessário selecionar um tipo de Vacina");
			return;
		}else{
			prontuarioEmergencia = new Prontuario_de_Emergencia();
			prontuarioEmergencia.setId_Pessoa(null);
			prontuarioEmergencia.setId_Objeto(objeto);
			if (perfilLogado.getAssLogado() != null && perfilLogado.getAssLogado().getId() != null)
				prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getAssLogado());
			else
				prontuarioEmergencia.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			prontuarioEmergencia.setId_Empresa(1);
			prontuarioEmergencia.setUltimaAtualizacao(Utilidades.retornaCalendario());
			
			prontuarioEmergencia.setDescricao(this.tipoVacina.getDescricao());
			prontuarioEmergencia.setDataVacinacao(dataVacina);
			prontuarioEmergencia.setTipo_Vacina(tipoVacina);

			prontuarioEmergencia.setId_clinica_responsavel(perfilLogado.getUsLogado().getPessoa());
			prontuarioEmergencia.setId_veterinario_responsavel(id_veterinario_responsavel);
			
			listaVacina.add(prontuarioEmergencia);
		}
		
	}
	
	public void listarTiposdeProntuario() {
		Enum_Aux_Tipo_Prontuario_de_Emergencia[] listagem;
		listagem = Enum_Aux_Tipo_Prontuario_de_Emergencia.values();
		listaTiposProntuarioEmergencia = new ArrayList<Enum_Aux_Tipo_Prontuario_de_Emergencia>();
		for (Enum_Aux_Tipo_Prontuario_de_Emergencia i : listagem) {
			listaTiposProntuarioEmergencia.add(i);
		}
	}
	
	public void listarTiposdeVacina() {
		Enum_Aux_Tipo_Vacina[] listagem;
		listagem = Enum_Aux_Tipo_Vacina.values();
		listaTiposVacina = new ArrayList<Enum_Aux_Tipo_Vacina>();
		for (Enum_Aux_Tipo_Vacina i : listagem) {
			listaTiposVacina.add(i);
		}
	}
	
	public void listarTiposdeSexo() {
		Enum_Aux_Sexo[] listagem;
		listagem = Enum_Aux_Sexo.values();
		listaSexo = new ArrayList<Enum_Aux_Sexo>();
		for (Enum_Aux_Sexo i : listagem) {
			listaSexo.add(i);
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

	public String getLabelAno() {
		return labelAno;
	}

	public void setLabelAno(String labelAno) {
		this.labelAno = labelAno;
	}

	public List<Prontuario_de_Emergencia> getListaProntuarioEmergencia() {
		return listaProntuarioEmergencia;
	}

	public void setListaProntuarioEmergencia(List<Prontuario_de_Emergencia> listaProntuarioEmergencia) {
		this.listaProntuarioEmergencia = listaProntuarioEmergencia;
	}

	public Prontuario_de_Emergencia getProntuarioEmergencia() {
		return prontuarioEmergencia;
	}

	public void setProntuarioEmergencia(Prontuario_de_Emergencia prontuarioEmergencia) {
		this.prontuarioEmergencia = prontuarioEmergencia;
	}

	public Enum_Aux_Tipo_Prontuario_de_Emergencia getTipoProntuarioEmergencia() {
		return tipoProntuarioEmergencia;
	}

	public void setTipoProntuarioEmergencia(Enum_Aux_Tipo_Prontuario_de_Emergencia tipoProntuarioEmergencia) {
		this.tipoProntuarioEmergencia = tipoProntuarioEmergencia;
	}

	public List<Enum_Aux_Tipo_Prontuario_de_Emergencia> getListaTiposProntuarioEmergencia() {
		return listaTiposProntuarioEmergencia;
	}

	public void setListaTiposProntuarioEmergencia(
			List<Enum_Aux_Tipo_Prontuario_de_Emergencia> listaTiposProntuarioEmergencia) {
		this.listaTiposProntuarioEmergencia = listaTiposProntuarioEmergencia;
	}

	public String getDescricaoProntuario() {
		return descricaoProntuario;
	}

	public void setDescricaoProntuario(String descricaoProntuario) {
		this.descricaoProntuario = descricaoProntuario;
	}

	public Date getDataVacina() {
		return dataVacina;
	}

	public void setDataVacina(Date dataVacina) {
		this.dataVacina = dataVacina;
	}

	public List<Prontuario_de_Emergencia> getListaVacina() {
		return listaVacina;
	}

	public void setListaVacina(List<Prontuario_de_Emergencia> listaVacina) {
		this.listaVacina = listaVacina;
	}

	public List<Enum_Aux_Tipo_Vacina> getListaTiposVacina() {
		return listaTiposVacina;
	}

	public void setListaTiposVacina(List<Enum_Aux_Tipo_Vacina> listaTiposVacina) {
		this.listaTiposVacina = listaTiposVacina;
	}

	public Enum_Aux_Tipo_Vacina getTipoVacina() {
		return tipoVacina;
	}

	public void setTipoVacina(Enum_Aux_Tipo_Vacina tipoVacina) {
		this.tipoVacina = tipoVacina;
	}

	public List<Pessoa> getListaVeterinariosDaClinica() {
		return listaVeterinariosDaClinica;
	}

	public void setListaVeterinariosDaClinica(List<Pessoa> listaVeterinariosDaClinica) {
		this.listaVeterinariosDaClinica = listaVeterinariosDaClinica;
	}

	public Pessoa getId_veterinario_responsavel() {
		return id_veterinario_responsavel;
	}

	public void setId_veterinario_responsavel(Pessoa id_veterinario_responsavel) {
		this.id_veterinario_responsavel = id_veterinario_responsavel;
	}

	public List<Enum_Aux_Sexo> getListaSexo() {
		return listaSexo;
	}

	public void setListaSexo(List<Enum_Aux_Sexo> listaSexo) {
		this.listaSexo = listaSexo;
	}

	public Marca_e_Raca getRaca() {
		return raca;
	}

	public void setRaca(Marca_e_Raca raca) {
		this.raca = raca;
	}

	public StreamedContent getAudio() {
		return audio;
	}

	public void setAudio(StreamedContent audio) {
		this.audio = audio;
	}

	public String getTipoDeAudio() {
		return tipoDeAudio;
	}	

	
}