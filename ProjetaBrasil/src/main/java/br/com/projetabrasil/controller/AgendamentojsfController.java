package br.com.projetabrasil.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.projetabrasil.model.dao.AgendamentoDAO;
import br.com.projetabrasil.model.entities.Agendamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Agendamento;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_A;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;


@ManagedBean(name = "agendamento")
@ViewScoped
public class AgendamentojsfController {
	private List<Agendamento> agendamentos;
	private Agendamento agendamento;
	private boolean disponivel;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private String descricaoDisponivel;
	private Enum_Aux_Status_Agendamento status_Agendamento;
	private String codigo;
	private Agendamento agendamentoPesquisado;
	private Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador;
	private boolean renderiza;
	private boolean podebaixar;

	@PostConstruct
	public void listar() {
		setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.TELEFONE);

		if (!perfilLogado.getPerfilUsLogado().equals(Enum_Aux_Perfil_Pessoa.ASSINANTES))
			return;
		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamentos = agDAO.checaAgendamentodoCliente(null, null, null,
				perfilLogado.getAssLogado());
	}

	public void defineStatus(String status_Agendamento) {
		this.status_Agendamento = Enum_Aux_Status_Agendamento.valueOf(status_Agendamento);
		codigo = "";
		setRenderiza(false);
		setPodebaixar(podebaixar);
		Utilidades.abrirfecharDialogos("dialogoAgendamento", true);
	}

	public void acao() {
        agendamentoPesquisado.setEnum_Aux_Status_Agendamento(status_Agendamento );
        agendamentoPesquisado.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
        agendamentoPesquisado.setDataBaixa(Utilidades.retornaCalendario2());
        agendamentoPesquisado.setUltimaAtualizacao(Utilidades.retornaCalendario());       
		Utilidades.abrirfecharDialogos("dialogoAgendamento", false);
		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamentoPesquisado = agDAO.merge(agendamentoPesquisado);
		listar();
		
	}

	public void cancelar() {
		Utilidades.abrirfecharDialogos("dialogoAgendamento", false);
	}

	public void novo(Movimento_Detalhe_A idMov) {
		agendamento = new Agendamento();
		agendamento.setDataAgendamento(Utilidades.retornaCalendario());
		agendamento.setDataBaixa(null);
		agendamento.setEnum_Aux_Status_Agendamento(Enum_Aux_Status_Agendamento.AGENDADO);
		agendamento.setId_Empresa(1);
		agendamento.setId_Movimento_Detalhe_A(idMov);
		agendamento.setId_Pessoa_Assinante(idMov.getId_Pessoa_Assinante());
		agendamento.setId_Pessoa_Baixa(null);
		agendamento.setId_Pessoa_Cliente(perfilLogado.getUsLogado().getPessoa());
		agendamento.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		agendamento.setUltimaAtualizacao(Utilidades.retornaCalendario());
		agendamento.setValor(idMov.getValor());
		Utilidades.abrirfecharDialogos("dialogoCalendario", true);
	}

	public void pesquidaAgendamento() {
		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamentoPesquisado = agDAO.checaAgendamentoAtivodoCliente(perfilLogado.getAssLogado(), codigo,
				Enum_Aux_Status_Agendamento.AGENDADO);
		setRenderiza(agendamentoPesquisado != null);
		setPodebaixar(isRenderiza());
		if (isRenderiza()) {
			if (agendamentoPesquisado != null) {
				
			setPodebaixar(!agendamentoPesquisado.getDataAgendamento().getTime().after(Utilidades.retornaCalendario2()));
			} else
				setPodebaixar(false);
		}
		

	}

	public void merge() {
		AgendamentoDAO AgDAO = new AgendamentoDAO();
		agendamento.setCodigo(Utilidades.randon(perfilLogado.getUsLogado().getPessoa().getCpf_Cnpj()));
		AgDAO.merge(agendamento);
		Utilidades.abrirfecharDialogos("dialogoCalendario", false);
	}

	public void editar(ActionEvent event) {

	}

	public void excluir(ActionEvent event) {

	}

	public void checar(ActionEvent event) {
		Movimento_Detalhe_A movDet = (Movimento_Detalhe_A) event.getComponent().getAttributes().get("registroAtual");

		AgendamentoDAO agDAO = new AgendamentoDAO();
		agendamento = agDAO.checaAgendamentoAtivodoCliente(perfilLogado.getUsLogado().getPessoa(), movDet,
				Enum_Aux_Status_Agendamento.AGENDADO);
		if (agendamentos != null) {

			setDescricaoDisponivel("Imprimir");
		} else {

			setDescricaoDisponivel("Agendar");
		}
	}

	public void somar() {

	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public String getDescricaoDisponivel() {
		return descricaoDisponivel;
	}

	public void setDescricaoDisponivel(String descricaoDisponivel) {
		this.descricaoDisponivel = descricaoDisponivel;
	}

	public Enum_Aux_Status_Agendamento getStatus_Agendamento() {
		return status_Agendamento;
	}

	public void setStatus_Agendamento(Enum_Aux_Status_Agendamento status_Agendamento) {
		this.status_Agendamento = status_Agendamento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Agendamento getAgendamentoPesquisado() {
		return agendamentoPesquisado;
	}

	public void setAgendamentoPesquisado(Agendamento agendamentoPesquisado) {
		this.agendamentoPesquisado = agendamentoPesquisado;
	}

	public Enum_Aux_Tipo_Identificador getEnum_Aux_Tipo_Identificador() {
		return enum_Aux_Tipo_Identificador;
	}

	public void setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador) {
		this.enum_Aux_Tipo_Identificador = enum_Aux_Tipo_Identificador;
	}

	public boolean isRenderiza() {
		return renderiza;
	}

	public void setRenderiza(boolean renderiza) {
		this.renderiza = renderiza;
	}

	public boolean isPodebaixar() {
		return podebaixar;
	}

	public void setPodebaixar(boolean podebaixar) {
		this.podebaixar = podebaixar;
	}

}
