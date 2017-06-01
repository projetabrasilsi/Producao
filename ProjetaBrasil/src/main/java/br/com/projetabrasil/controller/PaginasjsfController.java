package br.com.projetabrasil.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pagina_Atual;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Mov_Ponto;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.model.entities.Ponto_Movimento;


@SuppressWarnings("serial")
@ManagedBean(name = "paginas")
@ViewScoped
public class PaginasjsfController implements Serializable {
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	@ManagedProperty(value = "#{pontuacao.tipoMovimentacao}")
	private Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao;
	
	@ManagedProperty(value = "#{pontuacao.pontuacao}")
	private Ponto_Movimento pontuacao;

	

	public void mudaPaginaAtual(ActionEvent event) {
		// String paginaAtual
		String paginaAtual = (String) event.getComponent().getAttributes().get("paginaAtual");
		String descricao = Enum_Aux_Perfil_Pagina_Atual.PAGINATRANSFERENCIASQRCODE.getDescricao();
		String descricao2 = Enum_Aux_Perfil_Pagina_Atual.PAGINAVINCULARQRCODE.getDescricao();
		String descricao3 = Enum_Aux_Perfil_Pagina_Atual.PAGINAVINCULARQRCODEPESSOASESINDICATOS.getDescricao();
		if(paginaAtual.equals(descricao)||paginaAtual.equals(descricao2) ||paginaAtual.equals(descricao3)){
			String perfilTransferencia = (String) event.getComponent().getAttributes().get("perfildeTransferencia");
			perfilLogado.setPerfildeTransferencia(Enum_Aux_Perfil_Pessoa.valueOf(perfilTransferencia) );
		}
		
		if (Enum_Aux_Perfil_Pagina_Atual.valueOf(paginaAtual) != null)
			perfilLogado.setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual.valueOf(paginaAtual));
		paginaAtual = perfilLogado.getPaginaAtual().getUrl();
		autenticacao.redirecionaPaginas(paginaAtual, "",true);		
		return;

	}
	
	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	
	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	/**
	 * @return the tipoMovimentacao
	 */
	public Enum_Aux_Tipo_Mov_Ponto getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	/**
	 * @param tipoMovimentacao the tipoMovimentacao to set
	 */
	public void setTipoMovimentacao(Enum_Aux_Tipo_Mov_Ponto tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	/**
	 * @return the pontuacao
	 */
	public Ponto_Movimento getPontuacao() {
		return pontuacao;
	}

	/**
	 * @param pontuacao the pontuacao to set
	 */
	public void setPontuacao(Ponto_Movimento pontuacao) {
		this.pontuacao = pontuacao;
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

}
