package br.com.projetabrasil.model.entities;

public class DAM {
	String chave;
	String valor;
	InscricaoMunicipal im;
	String nDams;
	public String getnDams() {
		return nDams;
	}
	public void setnDams(String nDams) {
		this.nDams = nDams;
	}
	
	
	public InscricaoMunicipal getIm() {
		return im;
	}
	public void setIm(InscricaoMunicipal im) {
		this.im = im;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((im == null) ? 0 : im.hashCode());
		result = prime * result + ((nDams == null) ? 0 : nDams.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		DAM other = (DAM) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (im == null) {
			if (other.im != null)
				return false;
		} else if (!im.equals(other.im))
			return false;
		if (nDams == null) {
			if (other.nDams != null)
				return false;
		} else if (!nDams.equals(other.nDams))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DAM [chave=" + chave + ", valor=" + valor + ", im=" + im + ", nDams=" + nDams + "]";
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

}
