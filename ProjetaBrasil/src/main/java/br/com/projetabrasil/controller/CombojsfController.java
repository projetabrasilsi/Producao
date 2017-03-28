package br.com.projetabrasil.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.com.projetabrasil.model.dao.Combo_DetalheDAO;
import br.com.projetabrasil.model.dao.Combo_MestreDAO;
import br.com.projetabrasil.model.dao.Item_de_MovimentoDAO;
import br.com.projetabrasil.model.entities.Combo_Detalhe;
import br.com.projetabrasil.model.entities.Combo_Mestre;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Item_de_Movimento;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
@ManagedBean(name = "combo")
@ViewScoped
public class CombojsfController extends GenericController implements Serializable {
	private List<Combo_Mestre> combosM;
	private Combo_Mestre comboM;
	private List<Combo_Detalhe> combosD;
	private List<Combo_Detalhe> combosDTemp;
	private Combo_Detalhe comboD;
	private Combo_Detalhe comboDTemp;
	private List<Item_de_Movimento> itens_Mov;
	private Item_de_Movimento item_Mov;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private double qtdeAnt;
	private double difQtde;
	private boolean alteracao;
	private List<Enum_Aux_Tipo_Item_de_Movimento> foraListaItemMovimento;

	@PostConstruct
	public void listar() {
		Combo_MestreDAO cMDAO = new Combo_MestreDAO();
		combosM = cMDAO.listar(perfilLogado.getAssLogado());
		listaItens();

	}

	public void novoM() {
		comboM = new Combo_Mestre();
		configuraComboM();
		novoD();
		combosDTemp = new ArrayList<Combo_Detalhe>();
		combosD = new ArrayList<Combo_Detalhe>();

		Utilidades.abrirfecharDialogos("dialogoCombos", true);
	}

	public void listaItens() {
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		
		foraListaItemMovimento = new ArrayList<Enum_Aux_Tipo_Item_de_Movimento>();
		foraListaItemMovimento.add(Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
		foraListaItemMovimento.add(Enum_Aux_Tipo_Item_de_Movimento.FORMAPAGAMENTO);
		foraListaItemMovimento.add(Enum_Aux_Tipo_Item_de_Movimento.PONTO);
		
		
		itens_Mov = iMDAO.listar(perfilLogado.getAssLogado(),null,foraListaItemMovimento );
	}

	public void configuraComboM() {
		comboM.setDesconto(0d);
		comboM.setDescricao("");
		comboM.setId_Empresa(1);
		comboM.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		comboM.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		comboM.setPercDesc(0d);
		comboM.setReferencia("");
		comboM.setTotal(0d);
		comboM.setTotalLiquido(0d);
		comboM.setUltimaAtualizacao(Utilidades.retornaCalendario());
	}

	public void novoD() {
		comboD = new Combo_Detalhe();
		configuraComboD();
		setAlteracao(false);
	}

	public void configuraComboD() {
		comboD.setDescricao("");
		comboD.setId_Combo_Mestre(comboM);
		comboD.setId_Empresa(1);

		comboD.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
		comboD.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		comboD.setQtde(0d);
		comboD.setPercDesc(0d);
		comboD.setDesconto(0d);
		comboD.setReferencia("");
		comboD.setTotal(0d);
		comboD.setTotalLiquido(0d);
		comboD.setnMeses((short) 12);
		comboDTemp = comboD;
		setQtdeAnt(-1d);
		setDifQtde(0d);

	}

	public void adicionalistaComboDTemp() {
		int i;
		if (comboM.getDescricao().length() <= 0) {
			mensagensDisparar("Informe a descrição da combo");
			return;
		}
		if (comboM.getReferencia().length() <= 0) {
			mensagensDisparar("Informe a referencia da combo");
			return;
		}
		if (comboM.getnMeses() <= 0) {
			mensagensDisparar("Informe o número de meses");
			return;
		}
		if (item_Mov == null || item_Mov.getId() == null || item_Mov.getId() <= 0) {
			mensagensDisparar("Escolha o item que irá compor a combo");
			return;
		}
		if (qtdeAnt < 0)
			if (comboD.getQtde() <= 0) {
				mensagensDisparar("Informe a quantidade");
				return;
			}
		comboDTemp.setId_Itens_Movimento(item_Mov);
		i = -1;
		i = combosDTemp.indexOf(comboDTemp);
		boolean precoUnico = item_Mov.getIsPrecoUnico().equals(Enum_Aux_Sim_ou_Nao.SIM);
		if (i >= 0 && precoUnico) {
			mensagensDisparar("Item com quantidade única não poderá ser incluído novamente!!!");
			return;
		} else {
			if (precoUnico) {
				comboDTemp.setQtde(1);
				comboD.setQtde(1);
			}
		}
		comboD = comboDTemp;
		setDadosComboDImultaveis();
		setDadosComboDMultaveis();
		double total;
		if (item_Mov.getIsPrecoUnico().equals(Enum_Aux_Sim_ou_Nao.SIM))
			total = comboD.getQtde() * comboD.getValorUnidade();
		else {
			if (item_Mov.getIsAnual().equals(Enum_Aux_Sim_ou_Nao.SIM) )
				total = comboD.getQtde() * comboD.getValorUnidade() * comboM.getnMeses();
			else
				total = comboD.getQtde() * comboD.getValorUnidade();
		}

		comboD.setTotal(total);
		comboD.setPercDesc(comboM.getPercDesc());
		comboD.setDesconto(comboD.getTotal() * comboD.getPercDesc() / 100);
		comboD.setTotalLiquido(comboD.getTotal() - comboD.getDesconto());

		if (i >= 0)
			combosDTemp.set(i, comboD);
		else
			combosDTemp.add(comboD);

		total = 0;

		for (Combo_Detalhe cD : combosDTemp) {
			total += cD.getTotal();

		}

		realizaTotalizacaoFinal(total);
		if (i == -1)
			novoD();
	}

	public void realizaTotalizacaoFinal(double total) {

		comboM.setTotal(total);
		comboM.setDesconto(comboM.getTotal() * comboM.getPercDesc() / 100);
		comboM.setTotalLiquido(comboM.getTotal() - comboM.getDesconto());

	}

	public void setDadosComboDImultaveis() {
		comboD.setId_Combo_Mestre(comboM);
		comboD.setId_Empresa(1);
		comboD.setId_Itens_Movimento(item_Mov);
		comboD.setId_Pessoa_Assinante(perfilLogado.getAssLogado());
	}

	public void setDadosComboDMultaveis() {
		comboD.setId_Pessoa_Registro(perfilLogado.getUsLogado().getPessoa());
		comboD.setUltimaAtualizacao(Utilidades.retornaCalendario());
		comboD.setPercDesc(comboM.getPercDesc());
		if (!alteracao) {
			comboD.setDescricao(item_Mov.getDescricao());
			comboD.setReferencia(item_Mov.getReferencia());
			comboD.setValorUnidade(item_Mov.getValordaUnidade());
		}

		comboD.setPercDesc(comboM.getPercDesc());
		comboD.setDesconto(comboD.getTotal() * comboD.getPercDesc() / 100);
		comboD.setTotalLiquido(comboD.getTotal() - comboD.getDesconto());
	}

	public void editarM(ActionEvent event) {
		setComboM((Combo_Mestre) event.getComponent().getAttributes().get("registroAtual"));

		Combo_DetalheDAO cDDAO = new Combo_DetalheDAO();
		combosDTemp = cDDAO.listar(perfilLogado.getAssLogado(), getComboM());

		Utilidades.abrirfecharDialogos("dialogoCombos", true);

	}

	public void editarD(ActionEvent event) {
		setComboD((Combo_Detalhe) event.getComponent().getAttributes().get("registroAtual2"));
		comboDTemp = comboD;

		setQtdeAnt(comboD.getQtde());
		setAlteracao(true);
		item_Mov = comboDTemp.getId_Itens_Movimento();
		Utilidades.abrirfecharDialogos("dialogoQtde", true);
	}

	public void excluirD(ActionEvent event) {
		setComboD((Combo_Detalhe) event.getComponent().getAttributes().get("registroAtual2"));
		int i = combosDTemp.indexOf(comboD);
		if (comboD != null && comboD.getId() != null) {
			Combo_DetalheDAO cDDAO = new Combo_DetalheDAO();
			cDDAO.excluir(comboD);
		}
		combosDTemp.remove(i);
		realizaTotalizacaoFinal(comboM.getTotal() - comboD.getTotal());
	}

	public void merge() {
		Combo_MestreDAO cMDAO = new Combo_MestreDAO();
		comboM = cMDAO.merge(comboM);
		Combo_DetalheDAO cDDAO = new Combo_DetalheDAO();
		int i = 0;
		for (Combo_Detalhe cD : combosDTemp) {
			cD.setId_Combo_Mestre(comboM);
			comboD = cD;
			comboD = cDDAO.merge(comboD);

			combosDTemp.set(i, comboD);
		}
		listar();
		Utilidades.abrirfecharDialogos("dialogoCombos", false);
	}

	public void executarJS() {

		RequestContext.getCurrentInstance().execute("funcao(10)");
	}

	public List<Combo_Mestre> getCombosM() {
		return combosM;
	}

	public void setCombosM(List<Combo_Mestre> combosM) {
		this.combosM = combosM;
	}

	public Combo_Mestre getComboM() {
		return comboM;
	}

	public void setComboM(Combo_Mestre comboM) {
		this.comboM = comboM;
	}

	public List<Combo_Detalhe> getCombosD() {
		return combosD;
	}

	public void setCombosD(List<Combo_Detalhe> combosD) {
		this.combosD = combosD;
	}

	public Combo_Detalhe getComboD() {
		return comboD;
	}

	public void setComboD(Combo_Detalhe comboD) {
		this.comboD = comboD;
	}

	public List<Item_de_Movimento> getItens_Mov() {
		return itens_Mov;
	}

	public void setItens_Mov(List<Item_de_Movimento> itens_Mov) {
		this.itens_Mov = itens_Mov;
	}

	public Item_de_Movimento getItem_Mov() {
		return item_Mov;
	}

	public void setItem_Mov(Item_de_Movimento item_Mov) {
		this.item_Mov = item_Mov;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Combo_Detalhe> getCombosDTemp() {
		return combosDTemp;
	}

	public void setCombosDTemp(List<Combo_Detalhe> combosDTemp) {
		this.combosDTemp = combosDTemp;
	}

	public double getQtdeAnt() {
		return qtdeAnt;
	}

	public void setQtdeAnt(double qtdeAnt) {
		this.qtdeAnt = qtdeAnt;
	}

	public double getDifQtde() {
		return difQtde;
	}

	public void setDifQtde(double difQtde) {
		this.difQtde = difQtde;
	}

	public Combo_Detalhe getComboDTemp() {
		return comboDTemp;
	}

	public void setComboDTemp(Combo_Detalhe comboDTemp) {
		this.comboDTemp = comboDTemp;
	}

	public boolean isAlteracao() {
		return alteracao;
	}

	public void setAlteracao(boolean alteracao) {
		this.alteracao = alteracao;
	}

}
