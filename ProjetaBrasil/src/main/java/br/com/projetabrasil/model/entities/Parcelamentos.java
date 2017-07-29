package br.com.projetabrasil.model.entities;

public class Parcelamentos {
	private String nParc;
	private String total;
	private String dam;
	private String nome;
	private String sacado;
	private String vencimento;
	private String Boleto;
	public String getnParc() {
		return nParc;
	}
	public void setnParc(String nParc) {
		this.nParc = nParc;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDam() {
		return dam;
	}
	public void setDam(String dam) {
		this.dam = dam;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSacado() {
		return sacado;
	}
	public void setSacado(String sacado) {
		this.sacado = sacado;
	}
	public String getVencimento() {
		return vencimento;
	}
	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}
	public String getBoleto() {
		return Boleto;
	}
	public void setBoleto(String boleto) {
		Boleto = boleto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Boleto == null) ? 0 : Boleto.hashCode());
		result = prime * result + ((dam == null) ? 0 : dam.hashCode());
		result = prime * result + ((nParc == null) ? 0 : nParc.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sacado == null) ? 0 : sacado.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
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
		Parcelamentos other = (Parcelamentos) obj;
		if (Boleto == null) {
			if (other.Boleto != null)
				return false;
		} else if (!Boleto.equals(other.Boleto))
			return false;
		if (dam == null) {
			if (other.dam != null)
				return false;
		} else if (!dam.equals(other.dam))
			return false;
		if (nParc == null) {
			if (other.nParc != null)
				return false;
		} else if (!nParc.equals(other.nParc))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sacado == null) {
			if (other.sacado != null)
				return false;
		} else if (!sacado.equals(other.sacado))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Parcelamentos [nParc=" + nParc + ", total=" + total + ", dam=" + dam + ", nome=" + nome + ", sacado="
				+ sacado + ", vencimento=" + vencimento + ", Boleto=" + Boleto + "]";
	}
		
}
