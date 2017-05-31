package br.com.projetabrasil.model.entities;

import java.io.Serializable;

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
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="objeto")
public class Objeto extends GenericDomain implements Serializable{
	@Id
	@SequenceGenerator(name="pk_objeto", sequenceName="messsounds_objeto",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_objeto")
	private Long id;
	
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_aux_Tipos_Objeto", nullable=false)
	Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objeto;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Status_Pet", nullable=true)	
	private Enum_Aux_Status_Pet enum_Aux_Status_Pet;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Sexo", nullable=true)	
	private Enum_Aux_Sexo sexo;
	
	@ManyToOne
	@JoinColumn(name="id_Marca_e_Raca")
	private Marca_e_Raca id_Marca_e_Raca;
	
	@ManyToOne
	@JoinColumn(name="id_Modelo_de_Marca_e_Raca")
	private Modelo_de_Marca_e_Raca id_Modelo_de_Marca_e_Raca;
	
	@ManyToOne
	@JoinColumn (name="id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="perfil_do_Momento_do_Registro", nullable=false)
	Enum_Aux_Perfil_Pessoa perfil_do_Momento_do_Registro;
	
	@ManyToOne
	@JoinColumn(name="id_Pessoa_Vinculo", nullable=false)
	private Pessoa id_Pessoa_Vinculo;
	
	@ManyToOne
	@JoinColumn(name="cor", nullable=true)
	private Cor cor;	
	private String descricao;
	
	@Transient
	@Column(name = "caminhodaImagem")	
	private String caminhodaImagem;
	@Transient
	@Column(name = "caminhoTemp")	
	private String caminhoTemp;
	
	@Transient
	@Column(name = "tipodeImagem")	
	private String tipodeImagem;
	
	public String getTipodeImagem() {
		return tipodeImagem;
	}
	public void setTipodeImagem(String tipodeImagem) {
		this.tipodeImagem = tipodeImagem;
	}
	int ano_Objeto;
	int mod_Veiculo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Enum_Aux_Tipos_Objetos getEnum_Aux_Tipos_Objeto() {
		return enum_Aux_Tipos_Objeto;
	}
	public void setEnum_Aux_Tipos_Objeto(Enum_Aux_Tipos_Objetos enum_Aux_Tipos_Objeto) {
		this.enum_Aux_Tipos_Objeto = enum_Aux_Tipos_Objeto;
	}
	public Marca_e_Raca getId_Marca_e_Raca() {
		return id_Marca_e_Raca;
	}
	public void setId_Marca_e_Raca(Marca_e_Raca id_Marca_e_Raca) {
		this.id_Marca_e_Raca = id_Marca_e_Raca;
	}
	public Modelo_de_Marca_e_Raca getId_Modelo_de_Marca_e_Raca() {
		return id_Modelo_de_Marca_e_Raca;
	}
	public void setId_Modelo_de_Marca_e_Raca(Modelo_de_Marca_e_Raca id_Modelo_de_Marca_e_Raca) {
		this.id_Modelo_de_Marca_e_Raca = id_Modelo_de_Marca_e_Raca;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public Enum_Aux_Perfil_Pessoa getPerfil_do_Momento_do_Registro() {
		return perfil_do_Momento_do_Registro;
	}
	public void setPerfil_do_Momento_do_Registro(Enum_Aux_Perfil_Pessoa perfil_do_Momento_do_Registro) {
		this.perfil_do_Momento_do_Registro = perfil_do_Momento_do_Registro;
	}
	public Pessoa getId_Pessoa_Vinculo() {
		return id_Pessoa_Vinculo;
	}
	public void setId_Pessoa_Vinculo(Pessoa id_Pessoa_Vinculo) {
		this.id_Pessoa_Vinculo = id_Pessoa_Vinculo;
	}
	public Cor getCor() {
		return cor;
	}
	public void setCor(Cor cor) {
		this.cor = cor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getAno_Objeto() {
		return ano_Objeto;
	}
	public void setAno_Objeto(int ano_Objeto) {
		this.ano_Objeto = ano_Objeto;
	}
	public int getMod_Veiculo() {
		return mod_Veiculo;
	}
	public void setMod_Veiculo(int mod_Veiculo) {
		this.mod_Veiculo = mod_Veiculo;
	}
	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}
	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}
	public String getCaminhoTemp() {
		return caminhoTemp;
	}
	public void setCaminhoTemp(String caminhoTemp) {
		this.caminhoTemp = caminhoTemp;
	}
	public Enum_Aux_Status_Pet getEnum_Aux_Status_Pet() {
		return enum_Aux_Status_Pet;
	}
	public void setEnum_Aux_Status_Pet(Enum_Aux_Status_Pet enum_Aux_Status_Pet) {
		this.enum_Aux_Status_Pet = enum_Aux_Status_Pet;
	}	
	public Enum_Aux_Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Enum_Aux_Sexo sexo) {
		this.sexo = sexo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ano_Objeto;
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((enum_Aux_Tipos_Objeto == null) ? 0 : enum_Aux_Tipos_Objeto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((id_Marca_e_Raca == null) ? 0 : id_Marca_e_Raca.hashCode());
		result = prime * result + ((id_Modelo_de_Marca_e_Raca == null) ? 0 : id_Modelo_de_Marca_e_Raca.hashCode());
		result = prime * result + ((id_Pessoa_Registro == null) ? 0 : id_Pessoa_Registro.hashCode());
		result = prime * result + ((id_Pessoa_Vinculo == null) ? 0 : id_Pessoa_Vinculo.hashCode());
		result = prime * result + mod_Veiculo;
		result = prime * result
				+ ((perfil_do_Momento_do_Registro == null) ? 0 : perfil_do_Momento_do_Registro.hashCode());
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
		Objeto other = (Objeto) obj;
		if (ano_Objeto != other.ano_Objeto)
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
		if (enum_Aux_Tipos_Objeto != other.enum_Aux_Tipos_Objeto)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (id_Marca_e_Raca == null) {
			if (other.id_Marca_e_Raca != null)
				return false;
		} else if (!id_Marca_e_Raca.equals(other.id_Marca_e_Raca))
			return false;
		if (id_Modelo_de_Marca_e_Raca == null) {
			if (other.id_Modelo_de_Marca_e_Raca != null)
				return false;
		} else if (!id_Modelo_de_Marca_e_Raca.equals(other.id_Modelo_de_Marca_e_Raca))
			return false;
		if (id_Pessoa_Registro == null) {
			if (other.id_Pessoa_Registro != null)
				return false;
		} else if (!id_Pessoa_Registro.equals(other.id_Pessoa_Registro))
			return false;
		if (id_Pessoa_Vinculo == null) {
			if (other.id_Pessoa_Vinculo != null)
				return false;
		} else if (!id_Pessoa_Vinculo.equals(other.id_Pessoa_Vinculo))
			return false;
		if (mod_Veiculo != other.mod_Veiculo)
			return false;
		if (perfil_do_Momento_do_Registro != other.perfil_do_Momento_do_Registro)
			return false;
		return true;
	}
	
		
	}