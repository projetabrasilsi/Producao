package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import br.com.projetabrasil.controller.AutenticacaojsfController;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.PontoDAO;
import br.com.projetabrasil.util.Utilidades;


@SuppressWarnings("serial")
public class PerfilLogado implements Serializable {
	private Usuario usLogado;
	private Pessoa assLogado;
	Enum_Aux_Perfil_Pagina_Atual paginaAtual;
	Enum_Aux_Perfil_Pessoa perfilUsLogado;
	private List<Enum_Aux_Perfil_Pessoa> listaPerfisdousLogado;
	private String foco;
	private String identificadorAssinante;
	private String identificadorUsuario;
	private String senhaUsuario;
	private boolean renderizaAssociado;
	private boolean renderizaPontuacao;
	private boolean renderizapessoanovo;
	private boolean renderizapessoaeditar;
	@ManagedProperty(value = "#{pontuacao.listaPontuacoesConfig}")
	private List<Ponto> pontos;
	@ManagedProperty(value = "#{AutenticacaojsfController}")
	private AutenticacaojsfController autenticacao;

	public boolean isRenderizapessoanovo() {
		return renderizapessoanovo;
	}

	public void setRenderizapessoanovo(boolean renderizapessoanovo) {
		this.renderizapessoanovo = renderizapessoanovo;
	}

	public boolean isRenderizapessoaeditar() {
		return renderizapessoaeditar;
	}

	public void setRenderizapessoaeditar(boolean renderizapessoaeditar) {
		this.renderizapessoaeditar = renderizapessoaeditar;
	}

	public PerfilLogado() {
		if (!renderizaAssociado)
			foco = "usuario";
		else
			foco = "ass";

		identificadorAssinante = "";
		identificadorUsuario = "";
		senhaUsuario = "";
		usLogado = new Usuario()	;
		assLogado = new Pessoa();
		setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual.PAGINAAUTENTICACAO);
		setRenderizapessoaeditar(true);
		setRenderizapessoanovo(true);
	}

	public void verificaAssinante() {

		if (perfilUsLogado != null				&& ( perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ASSINANTES) 						
				                                    || perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)
				                                    || perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.VENDAS))
						&& perfilUsLogado.isTemPerfilMestre() && assLogado.getId() != null) {
			
			setRenderizaAssociado(true);
			if (assLogado != null && assLogado.getIdentificador() != null && identificadorAssinante != null
					&& identificadorAssinante.length() > 0
					&& !assLogado.getIdentificador().equals(identificadorAssinante)) {
				autenticacao.redirecionaPaginas("alfapage.xhtml",
						"erro no redirecionamento para página alfapage!!!",true);
			}

		} else {
			setRenderizaAssociado(false);
		}

	}

	public void escondeDialogoAltenticacacao(boolean escolherPerfil) {				
		Utilidades.abrirfecharDialogos("dialogoAutenticacao",false);
		if (escolherPerfil)			
		Utilidades.abrirfecharDialogos("dialogoPerfil",true);
	}

	public boolean temPermissoes(List<String> permissoes) {
		if (usLogado != null && permissoes != null) {
			if (perfilUsLogado != null) {
				String perf = perfilUsLogado.getDescricao().toUpperCase();
				for (String permissao : permissoes) {
					if (perf.equals(permissao.toUpperCase()))
						return true;
				}
			}
		}

		return false;
	}

	public boolean temPermissoes2(List<String> permissoes) {
		boolean retorno;
		retorno = false;
		if (usLogado == null || permissoes == null || perfilUsLogado == null) {
			return retorno;
		}
		String perf = perfilUsLogado.getDescricao().toUpperCase();
		for (String permissao : permissoes) {
			if (perf.equals(permissao.toUpperCase()) && retorno == false)
				retorno = true;
		}

		PontoDAO pDAO = new PontoDAO();
		List<Ponto> pontos;
		if (assLogado != null && assLogado.getId() != null)
			pontos = pDAO.retornarListaPontoConfig(assLogado, Enum_Aux_Tipo_Item_de_Movimento.PONTO );
		else
			pontos = pDAO.retornarListaPontoConfig(usLogado.getPessoa(), Enum_Aux_Tipo_Item_de_Movimento.PONTO);

		setPontos(pontos);
		if (retorno)
			if (pontos == null || pontos.isEmpty())
				retorno = false;

		return retorno;

	}
	
	public boolean renderizaPelaPagina(String permissao, Pessoa pessoa) {
		String pAtual = this.getPaginaAtual().toString();
		if (pAtual.equals(permissao) && pessoa.getDescricao() != null) {
			return false;
		}else{
			return true;
		}
	}

	public void listagemPerfisdousLogado() {
		Pessoa_Enum_Aux_Perfil_PessoasDAO pf = new Pessoa_Enum_Aux_Perfil_PessoasDAO();
		List<Pessoa_Enum_Aux_Perfil_Pessoa> pessoaPerfis = pf.retornaListaPerfil(usLogado.getPessoa());
		List<Enum_Aux_Perfil_Pessoa> perfis = new ArrayList<Enum_Aux_Perfil_Pessoa>();
		listaPerfisdousLogado = new ArrayList<Enum_Aux_Perfil_Pessoa>(pessoaPerfis.size());

		for (Pessoa_Enum_Aux_Perfil_Pessoa pessoaPerfil : pessoaPerfis) {
			perfis.add(pessoaPerfil.getEnum_Aux_Perfil_Pessoa());
		}

		setListaPerfisdousLogado(perfis);
	}

	

	public Usuario getUsLogado() {
		return usLogado;
	}

	public void setUsLogado(Usuario usLogado) {
		this.usLogado = usLogado;
	}

	public Enum_Aux_Perfil_Pagina_Atual getPaginaAtual() {
		return paginaAtual;
	}

	public void setPaginaAtual(Enum_Aux_Perfil_Pagina_Atual paginaAtual) {
		this.paginaAtual = paginaAtual;
	}

	@Override
	public String toString() {
		return "PerfilLogado [usLogado=" + usLogado + ", assLogado=" + assLogado + ", paginaAtual=" + paginaAtual
				+ ", perfilUsLogado=" + perfilUsLogado + ", listaPerfisdousLogado=" + listaPerfisdousLogado + ", foco="
				+ foco + ", identificadorAssinante=" + identificadorAssinante + ", identificadorUsuario="
				+ identificadorUsuario + ", senhaUsuario=" + senhaUsuario + ", renderizaAssociado=" + renderizaAssociado
				+ ", renderizaPontuacao=" + renderizaPontuacao + ", renderizapessoanovo=" + renderizapessoanovo
				+ ", renderizapessoaeditar=" + renderizapessoaeditar + ", pontos=" + pontos + ", autenticacao="
				+ autenticacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assLogado == null) ? 0 : assLogado.hashCode());
		result = prime * result + ((paginaAtual == null) ? 0 : paginaAtual.hashCode());
		result = prime * result + ((usLogado == null) ? 0 : usLogado.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilLogado other = (PerfilLogado) obj;
		if (assLogado == null) {
			if (other.assLogado != null)
				return false;
		} else if (!assLogado.equals(other.assLogado))
			return false;
		if (paginaAtual != other.paginaAtual)
			return false;
		if (usLogado == null) {
			if (other.usLogado != null)
				return false;
		} else if (!usLogado.equals(other.usLogado))
			return false;
		return true;
	}

	public List<Enum_Aux_Perfil_Pessoa> getListaPerfisdousLogado() {
		return listaPerfisdousLogado;
	}

	public void setListaPerfisdousLogado(List<Enum_Aux_Perfil_Pessoa> listaPerfisdousLogado) {
		this.listaPerfisdousLogado = listaPerfisdousLogado;
	}

	public String getFoco() {
		return foco;
	}

	public void setFoco(String foco) {
		this.foco = foco;
	}



	public String getIdentificadorUsuario() {
		return identificadorUsuario;
	}

	public void setIdentificadorUsuario(String identificadorUsuario) {
		this.identificadorUsuario = identificadorUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Enum_Aux_Perfil_Pessoa getPerfilUsLogado() {
		return perfilUsLogado;
	}

	public void setPerfilUsLogado(Enum_Aux_Perfil_Pessoa perfilUsLogado) {
		this.perfilUsLogado = perfilUsLogado;
		if ( this.perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ASSINANTES)
				|| perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.VENDAS))
			setAssLogado(usLogado.getPessoa());
		else 
			if (perfilUsLogado.equals(Enum_Aux_Perfil_Pessoa.ATENDENTES)) {
			Pessoa_VinculoDAO pVincDAO = new Pessoa_VinculoDAO();
			Pessoa_Vinculo pessoa_Vinculo = new Pessoa_Vinculo();
			pessoa_Vinculo.setId_pessoa_d(usLogado.getPessoa());
			pessoa_Vinculo = pVincDAO.retornaVinculo_Mestre(getUsLogado().getPessoa(),Enum_Aux_Perfil_Pessoa.ATENDENTES);
			if (pessoa_Vinculo != null && pessoa_Vinculo.getId_pessoa_m() != null) {
				setAssLogado(pessoa_Vinculo.getId_pessoa_m());
				setIdentificadorAssinante(pessoa_Vinculo.getId_pessoa_m().getCpf_Cnpj());
				setRenderizaAssociado(true);
			}
			else{
				setAssLogado(null);
				setIdentificadorAssinante("---");
				setRenderizaAssociado(false);
			}
		}
	}

	public boolean isRenderizaAssociado() {
		return renderizaAssociado;
	}

	public void setRenderizaAssociado(boolean renderizaAssociado) {
		this.renderizaAssociado = renderizaAssociado;
	}

	public void setAssLogado(Pessoa assLogado) {
		this.assLogado = assLogado;
	}

	public Pessoa getAssLogado() {
		return assLogado;
	}

	/**
	 * @return the renderizaPontuacao
	 */
	public boolean isRenderizaPontuacao() {
		return renderizaPontuacao;
	}

	/**
	 * @param renderizaPontuacao
	 *            the renderizaPontuacao to set
	 */
	public void setRenderizaPontuacao(boolean renderizaPontuacao) {
		this.renderizaPontuacao = renderizaPontuacao;
	}

	

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public String getIdentificadorAssinante() {
		return identificadorAssinante;
	}

	public void setIdentificadorAssinante(String identificadorAssinante) {
		this.identificadorAssinante = identificadorAssinante;
	}

}
