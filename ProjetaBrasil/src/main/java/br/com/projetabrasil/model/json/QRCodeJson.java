package br.com.projetabrasil.model.json;

import java.io.Serializable;
import java.util.List;

import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;


public class QRCodeJson implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String foto;
	private String token;	
	private QRCode qr;
	private Endereco end;
	private List<Contato> cont;	
	private Usuario usur;
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public QRCode getQr() {
		return qr;
	}
	public void setQr(QRCode qr) {
		this.qr = qr;
	}
	public Endereco getEnd() {
		return end;
	}
	public void setEnd(Endereco end) {
		this.end = end;
	}
	public List<Contato> getCont() {
		return cont;
	}
	public void setCont(List<Contato> cont) {
		this.cont = cont;
	}
	public Usuario getUsur() {
		return usur;
	}
	public void setUsur(Usuario usur) {
		this.usur = usur;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "QRCodeJson [foto=" + foto + ", token=" + token + ", qr=" + qr + ", end=" + end + ", cont=" + cont
				+ ", usur=" + usur + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cont == null) ? 0 : cont.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((qr == null) ? 0 : qr.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((usur == null) ? 0 : usur.hashCode());
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
		QRCodeJson other = (QRCodeJson) obj;
		if (cont == null) {
			if (other.cont != null)
				return false;
		} else if (!cont.equals(other.cont))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (foto == null) {
			if (other.foto != null)
				return false;
		} else if (!foto.equals(other.foto))
			return false;
		if (qr == null) {
			if (other.qr != null)
				return false;
		} else if (!qr.equals(other.qr))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (usur == null) {
			if (other.usur != null)
				return false;
		} else if (!usur.equals(other.usur))
			return false;
		return true;
	}
					
}
