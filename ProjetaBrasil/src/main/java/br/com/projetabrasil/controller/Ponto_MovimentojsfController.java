package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.projetabrasil.controller.entitiesconfig.PessoaConfig;
import br.com.projetabrasil.model.business.PessoaBusiness2;
import br.com.projetabrasil.model.business.PessoaGenericBusiness;
import br.com.projetabrasil.model.business.Pessoa_Enum_Perfil_de_PessoaBusiness;
import br.com.projetabrasil.model.business.Pessoa_VinculoBusiness;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.PontoDAO;
import br.com.projetabrasil.model.dao.Ponto_MovimentoDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Movimento_Ponto;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Ponto;
import br.com.projetabrasil.model.entities.Ponto_Movimento;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")

@ManagedBean(name = "ponto_mov")
@ViewScoped
public class Ponto_MovimentojsfController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	List<Ponto_Movimento> pontos_Mov;
	List<Ponto_Movimento> historico;
	private Ponto_Movimento ponto_movimento = new Ponto_Movimento();
	private Enum_Aux_Tipo_Identificador tipoIdentificador;
	private List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores;
	private Pessoa pessoaPontuada = new Pessoa();
	private Ponto ponto;
	private List<Ponto> listaPonto;
	private Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao;
	private PessoaConfig pessoaConfig;
	private Pessoa clienteHistorico;
	private Double pontoSoma;
	private Double pontoSomaCredito;
	private Double pontoSomaDebitoUtilizacao;
	private Double pontoSomaDebitoEstorno;

	@PostConstruct
	public void listar() {

		if ((perfilLogado == null || perfilLogado.getPaginaAtual() == null)
				|| (!perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC)
						&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD)
						&& !perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE)))
			return;

		if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC))
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.C);

		else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD))
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.D);
		else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
			setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto.E);

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		pontos_Mov = pDAO.listar(perfilLogado, null, true);
		litarPontuacoes();
		listarTipodeIdentificadores();
		setTipoIdentificador(Enum_Aux_Tipo_Identificador.CPF);
	}

	public void listarHistorico(Pessoa cliente) {
		if (cliente != null)
			clienteHistorico = cliente;
		else
			clienteHistorico = new Pessoa();

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();
		historico = pDAO.listar(perfilLogado, cliente, true);
		retornasomaPontuacao(cliente);
		Utilidades.abrirfecharDialogos("dialogoHistorico", true);
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

	public void novo() {
		configurarPessoa();
		if (listaPonto != null && listaPonto.size() > 0)
			setPonto(listaPonto.get(0));
		pessoaPontuada.setCpf_Cnpj("");
		pontoSoma = 0d;
		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		Utilidades.abrirfecharDialogos("dialogoPontuar", true);
	}

	public void configurarPessoa() {
		pessoaConfig = new PessoaConfig();
		pessoaPontuada = new Pessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(), pessoaPontuada,
				false);
		pessoaPontuada.setCpf_Cnpj("");
	}

	public void litarPontuacoes() {
		PontoDAO pDAO = new PontoDAO();
		setListaPonto(
				pDAO.retornarListaPontoConfig(perfilLogado.getAssLogado(), Enum_Aux_Tipo_Item_de_Movimento.PONTO));
	}

	public void configPonto_Movimento() {
		ponto_movimento = new Ponto_Movimento();
		if (ponto != null && ponto.getId() != null) {
			ponto_movimento.setId_ponto(ponto);
			ponto_movimento.setId_pessoa_associado(perfilLogado.getAssLogado());
			ponto_movimento.setPontuacaoMinima(ponto.getPontuacaoMinima());
			ponto_movimento.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			ponto_movimento.setUnidadeporPonto(ponto.getUnidadeporPonto());
			ponto_movimento.setValordaUnidade(ponto.getValordaUnidade());
			ponto_movimento.setValorUnidadeDevolucao(ponto.getValorUnidadeDevolucao());
			ponto_movimento.setValorUnidadeTroca(ponto.getValorUnidadeTroca());
			ponto_movimento.setDiasValidade(ponto.getDiasValidade());
			ponto_movimento.setValidade(Utilidades.retornaValidade(ponto_movimento.getDiasValidade()));
			ponto_movimento.setValoraPontuar(0);

			if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOC))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.C);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOD))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.D);
			else if (perfilLogado.getPaginaAtual().equals(Enum_Aux_Perfil_Pagina_Atual.PAGINAPONTUACAOE))
				ponto_movimento.setCreditaDebita(Enum_Aux_Tipo_Mov_Ponto.E);
		}

	}

	public void configPessoaNova(String cpf_Cnpj) {
		pessoaPontuada = new Pessoa();

		configurarPessoa();
		pessoaPontuada = pessoaConfig.ConfiguraPessoa(tipoIdentificador, perfilLogado.getUsLogado(), pessoaPontuada,
				false);
		pessoaPontuada.setCpf_Cnpj(cpf_Cnpj);
		pessoaConfig.mudarLabels(tipoIdentificador.getAux_tipo_pessoa());

		pontoSomaCredito = 0d;
		pontoSomaDebitoUtilizacao = 0d;
		pontoSomaDebitoEstorno = 0d;
		pontoSoma = 0d;

	}

	public void verificaPessoa() {
		boolean validarEmail;
		String cpf_Cnpj = pessoaPontuada.getCpf_Cnpj();
		cpf_Cnpj = Utilidades.retiraCaracteres(cpf_Cnpj);
		cpf_Cnpj = Utilidades.retiraVazios(cpf_Cnpj);
		pessoaPontuada.setCpf_Cnpj(cpf_Cnpj);
		pessoaPontuada.setEnum_Aux_Tipo_Identificador(tipoIdentificador);

		if (pessoaPontuada.getCpf_Cnpj().length() > 0) {
			Usuario us = new Usuario();
			us.setPessoa(pessoaPontuada);
			if (pessoaPontuada.getEmail() != null && pessoaPontuada.getEmail().length() > 0)
				validarEmail = true;
			else
				validarEmail = false;

			if (!PessoaBusiness2.validaDados(us, perfilLogado, validarEmail, false, true))
				return;
			PessoaDAO pessoaDAO = new PessoaDAO();
			pessoaPontuada = pessoaDAO.retornaPelaIdentificacao(pessoaPontuada.getCpf_Cnpj());
			if (pessoaPontuada == null) {
				configPessoaNova(cpf_Cnpj);
				Utilidades.abrirfecharDialogos("dialogoCadastro", true);
			} else {
				retornasomaPontuacao(pessoaPontuada);
			}
			configPonto_Movimento();
		} else {
			configPessoaNova(cpf_Cnpj);
			configPonto_Movimento();
		}
	}

	public void pessoaMerge(boolean validarEmail, boolean validarSenha) {
		Usuario usuario = new Usuario();
		usuario.setPessoa(pessoaPontuada);

		if (!PessoaBusiness2.validaDados(usuario, perfilLogado, validarEmail, validarSenha, true))
			return;
		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		pessoaPontuada = PessoaGenericBusiness.merge(pessoaPontuada, perfilLogado.getUsLogado(), perfilLogado, false);
	}

	public void vincularPessoa() {
		if (perfilLogado.getAssLogado() != null) {
			
			Pessoa_Enum_Aux_Perfil_Pessoa pessoa_Perfil = new Pessoa_Enum_Aux_Perfil_Pessoa();
			pessoa_Perfil.setId_pessoa(pessoaPontuada);		
			
			pessoa_Perfil.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);
			pessoa_Perfil.setUltimaAtualizacao(Utilidades.retornaCalendario());	
			pessoa_Perfil.setId_Empresa(1);
			pessoa_Perfil.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
			pessoa_Perfil.setUltimaAtualizacao(Utilidades.retornaCalendario());
			pessoa_Perfil = Pessoa_Enum_Perfil_de_PessoaBusiness.merge(pessoa_Perfil);
			Pessoa_Enum_Aux_Perfil_PessoasDAO pp = new  Pessoa_Enum_Aux_Perfil_PessoasDAO() ;
			Pessoa_Enum_Aux_Perfil_Pessoa ppRet = new  Pessoa_Enum_Aux_Perfil_Pessoa() ;
			ppRet = pp.isPerfilExiste(pessoa_Perfil);
			if (ppRet == null){
				pp.merge(pessoa_Perfil);
			}
			
			Pessoa_Vinculo pVincRet = new Pessoa_Vinculo();
			Pessoa_VinculoDAO pVDAO = new Pessoa_VinculoDAO();
			pVincRet = pVDAO.retornaVinculo_Mestre(pessoaPontuada, perfilLogado.getAssLogado(),
					Enum_Aux_Perfil_Pessoa.CLIENTES);

			if (pVincRet == null) {
				Pessoa_Vinculo pVinc = new Pessoa_Vinculo();

				pVinc.setAtivo(true);
				pVinc.setId_Empresa(1);
				pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
				pVinc.setId_pessoa_d(pessoaPontuada);
				pVinc.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.CLIENTES);

				pVinc.setId_pessoa_m(perfilLogado.getAssLogado());
				pVinc.setId_Pessoa_Registro(pessoaPontuada);
				pVinc.setUltimaAtualizacao(Utilidades.retornaCalendario());
				Pessoa_VinculoBusiness.merge(pVinc);
			}
		}

	}

	public void merge() {

		if (pessoaPontuada == null || pessoaPontuada.getId() == null)
			return;
		vincularPessoa();

		ponto_movimento.setId_pessoa_cliente(pessoaPontuada);
		ponto_movimento.setId_Empresa(1);

		if (ponto_movimento.getCreditaDebita().equals(Enum_Aux_Tipo_Mov_Ponto.C)) {
			double pontos = (double) (ponto_movimento.getUnidadeporPonto()
					* ((double) ponto_movimento.getValoraPontuar() / ponto_movimento.getValordaUnidade()));
			ponto_movimento.setPontosAtingidos(pontos);

		} else {
			if (pontoSoma < ponto_movimento.getPontosAtingidos()) {
				ponto_movimento.setPontosAtingidos(0);
				mensagensDisparar("Você não tem pontuações o suficiente ocorrrer DEBITO/ESTORNO!!!");

				return;

			}
			ponto_movimento
					.setValoraPontuar(ponto_movimento.getValordaUnidade() * ponto_movimento.getPontosAtingidos());
		}

		ponto_movimento.setUltimaAtualizacao(Utilidades.retornaCalendario());
		ponto_movimento.setEnum_Aux_Status_Movimento_Ponto(Enum_Aux_Status_Movimento_Ponto.CONFIRMADO);
		ponto_movimento.setDataLancamento(Utilidades.retornaValidade(0));

		Ponto_MovimentoDAO pDAO = new Ponto_MovimentoDAO();

		ponto_movimento = pDAO.merge(ponto_movimento);

		if (ponto_movimento != null && ponto_movimento.getId() != null) {
			mensagensDisparar("Pontuação para  " + ponto_movimento.getId_pessoa_cliente().getDescricao()
					+ " realizada com sucesso!!!");
		}
		listar();
		novo();

	}

	public void listarTipodeIdentificadores() {
		Enum_Aux_Tipo_Identificador[] identificadores;
		identificadores = Enum_Aux_Tipo_Identificador.values();
		listaTipodeIdentificadores = new ArrayList<Enum_Aux_Tipo_Identificador>();
		for (Enum_Aux_Tipo_Identificador identificador : identificadores) {
			if (identificador.getAux_tipo_pessoa().isSelecionar())
				listaTipodeIdentificadores.add(identificador);
		}
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Ponto_Movimento> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Ponto_Movimento> historico) {
		this.historico = historico;
	}

	public Ponto_Movimento getPonto_movimento() {
		return ponto_movimento;
	}

	public void setPonto_movimento(Ponto_Movimento ponto_movimento) {
		this.ponto_movimento = ponto_movimento;
	}

	public Enum_Aux_Tipo_Identificador getTipoIdentificador() {
		return tipoIdentificador;
	}

	public void setTipoIdentificador(Enum_Aux_Tipo_Identificador tipoIdentificador) {
		this.tipoIdentificador = tipoIdentificador;
	}

	public List<Enum_Aux_Tipo_Identificador> getListaTipodeIdentificadores() {
		return listaTipodeIdentificadores;
	}

	public void setListaTipodeIdentificadores(List<Enum_Aux_Tipo_Identificador> listaTipodeIdentificadores) {
		this.listaTipodeIdentificadores = listaTipodeIdentificadores;
	}

	public Pessoa getPessoaPontuada() {
		return pessoaPontuada;
	}

	public void setPessoaPontuada(Pessoa pessoaPontuada) {
		this.pessoaPontuada = pessoaPontuada;
	}

	public Ponto getPonto() {
		return ponto;
	}

	public void setPonto(Ponto ponto) {
		this.ponto = ponto;
	}

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public void setListaPonto(List<Ponto> listaPonto) {
		this.listaPonto = listaPonto;
	}

	public Enum_Aux_Tipo_Mov_Ponto getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public PessoaConfig getPessoaConfig() {
		return pessoaConfig;
	}

	public void setPessoaConfig(PessoaConfig pessoaConfig) {
		this.pessoaConfig = pessoaConfig;
	}

	public Pessoa getClienteHistorico() {
		return clienteHistorico;
	}

	public void setClienteHistorico(Pessoa clienteHistorico) {
		this.clienteHistorico = clienteHistorico;
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

	public List<Ponto_Movimento> getPontos_Mov() {
		return pontos_Mov;
	}

	public void setPontos_Mov(List<Ponto_Movimento> pontos_Mov) {
		this.pontos_Mov = pontos_Mov;
	}

}
