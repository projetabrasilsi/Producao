package br.com.projetabrasil.model.json;

import br.com.projetabrasil.model.entities.Objeto;

public class Objeto_Json {
	private String ano;
	private String caminhodaImagem;
	private String caminhodoAudio;
	private String cor;
	private String descricao;
	private String status_do_Pet;
	private String fcm_Tokem;
	public String getFcm_Tokem() {
		return fcm_Tokem;
	}
	public void setFcm_Tokem(String fcm_Tokem) {
		this.fcm_Tokem = fcm_Tokem;
	}
	@Override
	public String toString() {
		return "Objeto_Json [ano=" + ano + ", caminhodaImagem=" + caminhodaImagem + ", caminhodoAudio=" + caminhodoAudio
				+ ", cor=" + cor + ", descricao=" + descricao + ", status_do_Pet=" + status_do_Pet + ", fcm_Tokem="
				+ fcm_Tokem + ", tipo_Objeto=" + tipo_Objeto + ", id=" + id + ", marca_e_raca=" + marca_e_raca
				+ ", id_pessoa_vinculo=" + id_pessoa_vinculo + ", Sexo=" + Sexo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Sexo == null) ? 0 : Sexo.hashCode());
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((caminhodaImagem == null) ? 0 : caminhodaImagem.hashCode());
		result = prime * result + ((caminhodoAudio == null) ? 0 : caminhodoAudio.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fcm_Tokem == null) ? 0 : fcm_Tokem.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_pessoa_vinculo == null) ? 0 : id_pessoa_vinculo.hashCode());
		result = prime * result + ((marca_e_raca == null) ? 0 : marca_e_raca.hashCode());
		result = prime * result + ((status_do_Pet == null) ? 0 : status_do_Pet.hashCode());
		result = prime * result + ((tipo_Objeto == null) ? 0 : tipo_Objeto.hashCode());
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
		Objeto_Json other = (Objeto_Json) obj;
		if (Sexo == null) {
			if (other.Sexo != null)
				return false;
		} else if (!Sexo.equals(other.Sexo))
			return false;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (caminhodaImagem == null) {
			if (other.caminhodaImagem != null)
				return false;
		} else if (!caminhodaImagem.equals(other.caminhodaImagem))
			return false;
		if (caminhodoAudio == null) {
			if (other.caminhodoAudio != null)
				return false;
		} else if (!caminhodoAudio.equals(other.caminhodoAudio))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fcm_Tokem == null) {
			if (other.fcm_Tokem != null)
				return false;
		} else if (!fcm_Tokem.equals(other.fcm_Tokem))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_pessoa_vinculo == null) {
			if (other.id_pessoa_vinculo != null)
				return false;
		} else if (!id_pessoa_vinculo.equals(other.id_pessoa_vinculo))
			return false;
		if (marca_e_raca == null) {
			if (other.marca_e_raca != null)
				return false;
		} else if (!marca_e_raca.equals(other.marca_e_raca))
			return false;
		if (status_do_Pet == null) {
			if (other.status_do_Pet != null)
				return false;
		} else if (!status_do_Pet.equals(other.status_do_Pet))
			return false;
		if (tipo_Objeto == null) {
			if (other.tipo_Objeto != null)
				return false;
		} else if (!tipo_Objeto.equals(other.tipo_Objeto))
			return false;
		return true;
	}
	private String tipo_Objeto;
	private String id;
	private String marca_e_raca;
	private String id_pessoa_vinculo;
	private String Sexo;
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}
	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}
	public String getCaminhodoAudio() {
		return caminhodoAudio;
	}
	public void setCaminhodoAudio(String caminhodoAudio) {
		this.caminhodoAudio = caminhodoAudio;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus_do_Pet() {
		return status_do_Pet;
	}
	public void setStatus_do_Pet(String status_do_Pet) {
		this.status_do_Pet = status_do_Pet;
	}
	public String getTipo_Objeto() {
		return tipo_Objeto;
	}
	public void setTipo_Objeto(String tipo_Objeto) {
		this.tipo_Objeto = tipo_Objeto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarca_e_raca() {
		return marca_e_raca;
	}
	public void setMarca_e_raca(String marca_e_raca) {
		this.marca_e_raca = marca_e_raca;
	}
	public String getId_pessoa_vinculo() {
		return id_pessoa_vinculo;
	}
	public void setId_pessoa_vinculo(String id_pessoa_vinculo) {
		this.id_pessoa_vinculo = id_pessoa_vinculo;
	}
	public String getSexo() {
		return Sexo;
	}
	public void setSexo(String sexo) {
		Sexo = sexo;
	}
	
	
	
	
	

}
