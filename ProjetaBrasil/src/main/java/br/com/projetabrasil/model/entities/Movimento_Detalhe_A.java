package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.primefaces.model.StreamedContent;

@SuppressWarnings("serial")
@Entity
public class Movimento_Detalhe_A extends GenericDomain implements Serializable {
   @Id
   @SequenceGenerator(name="pk_movimento_detalhe_a", sequenceName="mess_sounds_movimento_detalhe_a", allocationSize=1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pk_movimento_detalhe_a")
    private Long id;

	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	@ManyToOne
	@JoinColumn(name = "id_Pessoa_Assinante")
	private Pessoa id_Pessoa_Assinante;

	
	@ManyToOne	
	@JoinColumn(name="id_Movimento_Detalhe" )
	private Movimento_Detalhe id_Movimento_Detalhe;
	 
	@Column(name="disponibilizado",precision=18, scale=4, nullable=false)
	private double disponibilizado;
	@Column(name="agendado", precision=18, scale=4, nullable=false)
	private double agendado;
	@Column(name="livre", precision=18, scale=4, nullable=false )
	private double livre;
	@Column(name="saldo", precision=18,scale=4, nullable=false)
	private double saldo;	
	@Column(name="valor", precision=18,scale=4, nullable=false)
	private double valor;
	@Column(name="inicio")
	@Temporal(TemporalType.DATE)
	private Date inicio;
	@Column(name="fim")
	@Temporal(TemporalType.DATE)
	private Date fim;
	@Enumerated(EnumType.STRING)
	Enum_Aux_Sim_ou_Nao isAgendado;
	@Temporal(TemporalType.TIME)
	private Date horarioMaximoAgendamentodoDia;
	private String caminhoDaImagem;
	@Transient
	@Column(name="caminhoTemp")
	private String caminhoTemp;
	@Transient
	@Column(name = "foto")	
	 private StreamedContent foto;
	@Column(name="regulamento", columnDefinition="TEXT")
	private String regulamento;
	@Column(name="Enum_Aux_Tipo_Item_de_Movimento")
	@Enumerated(EnumType.STRING)
	private Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento;
	@Transient
	@Column(name="disponivel")
	private boolean disponivel;
	@Transient
	@Column(name="descricaoDisponivel")
	private String descricaoDisponivel;
	@Transient
	@Column(name="codigo")
	private String codigo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public Movimento_Detalhe getId_Movimento_Detalhe() {
		return id_Movimento_Detalhe;
	}
	public void setId_Movimento_Detalhe(Movimento_Detalhe id_Movimento_Detalhe) {
		this.id_Movimento_Detalhe = id_Movimento_Detalhe;
	}
	public double getDisponibilizado() {
		return disponibilizado;
	}
	public void setDisponibilizado(double disponibilizado) {
		this.disponibilizado = disponibilizado;
	}
	public double getAgendado() {
		return agendado;
	}
	public void setAgendado(double agendado) {
		this.agendado = agendado;
	}
	public double getLivre() {
		return livre;
	}
	public void setLivre(double livre) {
		this.livre = livre;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Enum_Aux_Sim_ou_Nao getIsAgendado() {
		return isAgendado;
	}
	public void setIsAgendado(Enum_Aux_Sim_ou_Nao isAgendado) {
		this.isAgendado = isAgendado;
	}
	public Date getHorarioMaximoAgendamentodoDia() {
		return horarioMaximoAgendamentodoDia;
	}
	public void setHorarioMaximoAgendamentodoDia(Date horarioMaximoAgendamentodoDia) {
		this.horarioMaximoAgendamentodoDia = horarioMaximoAgendamentodoDia;
	}
	public String getCaminhoDaImagem() {
		return caminhoDaImagem;
	}
	public void setCaminhoDaImagem(String caminhoDaImagem) {
		this.caminhoDaImagem = caminhoDaImagem;
	}
	@Override
	public String toString() {
		return "Movimento_Detalhe_A [id=" + id + ", id_Pessoa_Registro=" + id_Pessoa_Registro + ", id_Pessoa_Assinante="
				+ id_Pessoa_Assinante + ", id_Movimento_Detalhe=" + id_Movimento_Detalhe + ", disponibilizado="
				+ disponibilizado + ", agendado=" + agendado + ", livre=" + livre + ", saldo=" + saldo + ", valor="
				+ valor + ", inicio=" + inicio + ", fim=" + fim + ", isAgendado=" + isAgendado
				+ ", horarioMaximoAgendamentodoDia=" + horarioMaximoAgendamentodoDia + ", caminhoDaImagem="
				+ caminhoDaImagem + ", regulamento=" + regulamento + ", enum_Aux_Tipo_Item_de_Movimento="
				+ enum_Aux_Tipo_Item_de_Movimento + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(agendado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((caminhoDaImagem == null) ? 0 : caminhoDaImagem.hashCode());
		temp = Double.doubleToLongBits(disponibilizado);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((enum_Aux_Tipo_Item_de_Movimento == null) ? 0 : enum_Aux_Tipo_Item_de_Movimento.hashCode());
		result = prime * result + ((fim == null) ? 0 : fim.hashCode());
		result = prime * result
				+ ((horarioMaximoAgendamentodoDia == null) ? 0 : horarioMaximoAgendamentodoDia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Movimento_Detalhe == null) ? 0 : id_Movimento_Detalhe.hashCode());
		result = prime * result + ((id_Pessoa_Assinante == null) ? 0 : id_Pessoa_Assinante.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((isAgendado == null) ? 0 : isAgendado.hashCode());
		temp = Double.doubleToLongBits(livre);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((regulamento == null) ? 0 : regulamento.hashCode());
		temp = Double.doubleToLongBits(saldo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimento_Detalhe_A other = (Movimento_Detalhe_A) obj;
		if (Double.doubleToLongBits(agendado) != Double.doubleToLongBits(other.agendado))
			return false;
		if (caminhoDaImagem == null) {
			if (other.caminhoDaImagem != null)
				return false;
		} else if (!caminhoDaImagem.equals(other.caminhoDaImagem))
			return false;
		if (Double.doubleToLongBits(disponibilizado) != Double.doubleToLongBits(other.disponibilizado))
			return false;
		if (enum_Aux_Tipo_Item_de_Movimento != other.enum_Aux_Tipo_Item_de_Movimento)
			return false;
		if (fim == null) {
			if (other.fim != null)
				return false;
		} else if (!fim.equals(other.fim))
			return false;
		if (horarioMaximoAgendamentodoDia == null) {
			if (other.horarioMaximoAgendamentodoDia != null)
				return false;
		} else if (!horarioMaximoAgendamentodoDia.equals(other.horarioMaximoAgendamentodoDia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Movimento_Detalhe == null) {
			if (other.id_Movimento_Detalhe != null)
				return false;
		} else if (!id_Movimento_Detalhe.equals(other.id_Movimento_Detalhe))
			return false;
		if (id_Pessoa_Assinante == null) {
			if (other.id_Pessoa_Assinante != null)
				return false;
		} else if (!id_Pessoa_Assinante.equals(other.id_Pessoa_Assinante))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (isAgendado != other.isAgendado)
			return false;
		if (Double.doubleToLongBits(livre) != Double.doubleToLongBits(other.livre))
			return false;
		if (regulamento == null) {
			if (other.regulamento != null)
				return false;
		} else if (!regulamento.equals(other.regulamento))
			return false;
		if (Double.doubleToLongBits(saldo) != Double.doubleToLongBits(other.saldo))
			return false;
		if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
			return false;
		return true;
	}
	public String getCaminhoTemp() {
		return caminhoTemp;
	}
	public void setCaminhoTemp(String caminhoTemp) {
		this.caminhoTemp = caminhoTemp;
	}
	public StreamedContent getFoto() {
		return foto;
	}
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	public String getRegulamento() {
		return regulamento;
	}
	public void setRegulamento(String regulamento) {
		this.regulamento = regulamento;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Pessoa getId_Pessoa_Assinante() {
		return id_Pessoa_Assinante;
	}
	public void setId_Pessoa_Assinante(Pessoa id_Pessoa_Assinante) {
		this.id_Pessoa_Assinante = id_Pessoa_Assinante;
	}
	public Enum_Aux_Tipo_Item_de_Movimento getEnum_Aux_Tipo_Item_de_Movimento() {
		return enum_Aux_Tipo_Item_de_Movimento;
	}
	public void setEnum_Aux_Tipo_Item_de_Movimento(Enum_Aux_Tipo_Item_de_Movimento enum_Aux_Tipo_Item_de_Movimento) {
		this.enum_Aux_Tipo_Item_de_Movimento = enum_Aux_Tipo_Item_de_Movimento;
	}
	public boolean isDisponivel() {
		return disponivel;
	}
	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	public String getDescricaoDisponivel() {
		return descricaoDisponivel;
	}
	public void setDescricaoDisponivel(String descricaoDisponivel) {
		this.descricaoDisponivel = descricaoDisponivel;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
