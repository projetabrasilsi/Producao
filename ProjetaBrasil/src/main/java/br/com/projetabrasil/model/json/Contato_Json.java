package br.com.projetabrasil.model.json;

public class Contato_Json {
	
	private String id;
	private String cpf;
	private String TipoContato;
	private String Relacionamento;
	private String Contato;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipoContato() {
		return TipoContato;
	}
	public void setTipoContato(String tipoContato) {
		TipoContato = tipoContato;
	}
	public String getRelacionamento() {
		return Relacionamento;
	}
	public void setRelacionamento(String relacionamento) {
		Relacionamento = relacionamento;
	}
	public String getContato() {
		return Contato;
	}
	public void setContato(String contato) {
		Contato = contato;
	}
	@Override
	public String toString() {
		return "Contato_Json [id=" + id + ", cpf=" + cpf + ", TipoContato=" + TipoContato + ", Relacionamento="
				+ Relacionamento + ", Contato=" + Contato + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Contato == null) ? 0 : Contato.hashCode());
		result = prime * result + ((Relacionamento == null) ? 0 : Relacionamento.hashCode());
		result = prime * result + ((TipoContato == null) ? 0 : TipoContato.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Contato_Json other = (Contato_Json) obj;
		if (Contato == null) {
			if (other.Contato != null)
				return false;
		} else if (!Contato.equals(other.Contato))
			return false;
		if (Relacionamento == null) {
			if (other.Relacionamento != null)
				return false;
		} else if (!Relacionamento.equals(other.Relacionamento))
			return false;
		if (TipoContato == null) {
			if (other.TipoContato != null)
				return false;
		} else if (!TipoContato.equals(other.TipoContato))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
