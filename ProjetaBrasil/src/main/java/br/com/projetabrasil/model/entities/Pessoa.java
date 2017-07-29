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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import br.com.projetabrasil.model.services.PessoaAdapter;
@SuppressWarnings("serial")
@Entity
@Table(name="Pessoa")
@JsonAdapter(PessoaAdapter.class)
public class Pessoa extends GenericDomain implements Serializable{
	
	@Id
	@SequenceGenerator(name="pk_pessoa",sequenceName="messsounds_pessoa", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_pessoa")
	@SerializedName("id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="enum_Aux_Tipo_Identificador", nullable=true)
	@SerializedName("enum_Aux_Tipo_Identificador")
	private Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador;
	
	
	@Column(name="descricao", length = 90, nullable=false)
	@SerializedName("descricao")
	private String descricao;
	
	@Column(name="fantasia_Apelido", length = 90, nullable=true)
	@SerializedName("fantasia_Apelido")
	private String fantasia_Apelido;
	@Column(name="identificador", length = 40, nullable=false)
	@SerializedName("identificador")
	private String identificador;
	
	@Column(name="dataNascimento", nullable=true)
	@Temporal(TemporalType.DATE)
	@SerializedName("dataNascimento")
	private Date dataNascimento;
	
	
	@Column(name="cpf_Cnpj", length=20)
	@SerializedName("cpf_Cnpj")
	private String cpf_Cnpj;
	@Column(name="rg_Insc", length=20)
	@SerializedName("rg_Insc")
	private String rg_Insc;
	@Enumerated(EnumType.STRING)
	@SerializedName("sexo")
	private Enum_Aux_Sexo sexo; 
	
	@Column(name="fone_1", length=20, nullable=true)
	@SerializedName("fone_1")
	private String fone_1;
	
	@Column(name="fone_2", length=20,nullable=true)
	@SerializedName("fone_2")
	private String fone_2;
	@Column(name="fone_3", length=20,nullable=true)
	@SerializedName("fone_3")
	private String fone_3;
	@Column(name="fone_4", length=20,nullable=true)
	@SerializedName("fone_4")
	private String fone_4;
	@Column(name="fone_5", length=20,nullable=true)
	@SerializedName("fone_5")
	private String fone_5;
	@Column(name="email", length=90,nullable=true)
	@SerializedName("email")	
	private String email;
	
	@OneToOne
	@Expose
	@JoinColumn ( name ="id_Pessoa_Registro")	
	@SerializedName("id_Pessoa_Registro")
	private Pessoa id_Pessoa_Registro;
	
	@OneToOne
	@Expose
	@JoinColumn ( name ="id_Pessoa_indicacao")
	@SerializedName("id_Pessoa_indicacao")
	private Pessoa id_Pessoa_Indicacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="AutoPontuacao")
	@SerializedName("AutoPontuacao")
	private Enum_Aux_Sim_ou_Nao autoPontuacao;
	
	@OneToOne
	@JoinColumn ( name ="id_Profissao")
	@SerializedName("id_Profissao")
	private Profissao id_Profissao;
	
	@Column(name="fcm_Tokem", length=255)
	@SerializedName("fcm_Tokem")
	private String fcm_Tokem;
	
	public String getFcm_Tokem() {
		return fcm_Tokem;
	}
	public void setFcm_Tokem(String fcm_Tokem) {
		this.fcm_Tokem = fcm_Tokem;
	}
	@Transient
	@Column(name = "caminhodaImagem")	
	private String caminhodaImagem;
	@Transient
	@Column(name = "caminhoTemp")	
	private String caminhoTemp;
	
	@Transient
	@Column(name = "tipodeImagem")	
	private String tipodeImagem;
	
	@Transient
	private String mensagem;
	@Transient
	private boolean cadastrado;
	@Transient
	private boolean cpf_cnpjValido;
	@Transient
	private String senha;
	@Transient
	private double pontosAnt;
	@Transient
	private double pontosdoMovimento;
	@Transient
	private double pontosAtuais;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Enum_Aux_Tipo_Identificador getEnum_Aux_Tipo_Identificador() {
		return enum_Aux_Tipo_Identificador;
	}
	public void setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador enum_Aux_Tipo_Identificador) {
		this.enum_Aux_Tipo_Identificador = enum_Aux_Tipo_Identificador;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFantasia_Apelido() {
		return fantasia_Apelido;
	}
	public void setFantasia_Apelido(String fantasia_Apelido) {
		this.fantasia_Apelido = fantasia_Apelido;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf_Cnpj() {
		return cpf_Cnpj;
	}
	public void setCpf_Cnpj(String cpf_Cnpj) {
		this.cpf_Cnpj = cpf_Cnpj;
	}
	public String getRg_Insc() {
		return rg_Insc;
	}
	public void setRg_Insc(String rg_Insc) {
		this.rg_Insc = rg_Insc;
	}
	public Enum_Aux_Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Enum_Aux_Sexo sexo) {
		this.sexo = sexo;
	}
	public String getFone_1() {
		return fone_1;
	}
	public void setFone_1(String fone_1) {
		this.fone_1 = fone_1;
	}
	public String getFone_2() {
		return fone_2;
	}
	public void setFone_2(String fone_2) {
		this.fone_2 = fone_2;
	}
	public String getFone_3() {
		return fone_3;
	}
	public void setFone_3(String fone_3) {
		this.fone_3 = fone_3;
	}
	public String getFone_4() {
		return fone_4;
	}
	public void setFone_4(String fone_4) {
		this.fone_4 = fone_4;
	}
	public String getFone_5() {
		return fone_5;
	}
	public void setFone_5(String fone_5) {
		this.fone_5 = fone_5;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Pessoa getId_Pessoa_Registro() {
		return id_Pessoa_Registro;
	}
	public void setId_Pessoa_Registro(Pessoa id_Pessoa_Registro) {
		this.id_Pessoa_Registro = id_Pessoa_Registro;
	}
	public Enum_Aux_Sim_ou_Nao getAutoPontuacao() {
		return autoPontuacao;
	}
	public void setAutoPontuacao(Enum_Aux_Sim_ou_Nao autoPontuacao) {
		this.autoPontuacao = autoPontuacao;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isCadastrado() {
		return cadastrado;
	}
	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}
	public boolean isCpf_cnpjValido() {
		return cpf_cnpjValido;
	}
	public void setCpf_cnpjValido(boolean cpf_cnpjValido) {
		this.cpf_cnpjValido = cpf_cnpjValido;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public double getPontosAnt() {
		return pontosAnt;
	}
	public void setPontosAnt(double pontosAnt) {
		this.pontosAnt = pontosAnt;
	}
	public double getPontosdoMovimento() {
		return pontosdoMovimento;
	}
	public void setPontosdoMovimento(double pontosdoMovimento) {
		this.pontosdoMovimento = pontosdoMovimento;
	}
	public double getPontosAtuais() {
		return pontosAtuais;
	}
	public void setPontosAtuais(double pontosAtuais) {
		this.pontosAtuais = pontosAtuais;
	}	
	public Pessoa getId_Pessoa_Indicacao() {
		return id_Pessoa_Indicacao;
	}
	public void setId_Pessoa_Indicacao(Pessoa id_Pessoa_Indicacao) {
		this.id_Pessoa_Indicacao = id_Pessoa_Indicacao;
	}	
	public String getTipodeImagem() {
		return tipodeImagem;
	}
	public void setTipodeImagem(String tipodeImagem) {
		this.tipodeImagem = tipodeImagem;
	}
	
	
	
	 public String toString() {
		return "Pessoa ["
				+ "id=" + id + ", "
				+ "enum_Aux_Tipo_Identificador=" + enum_Aux_Tipo_Identificador + 
				", descricao="+ descricao + 
				", fantasia_Apelido=" + fantasia_Apelido + 
				", identificador=" + identificador	+ 
				", dataNascimento=" + dataNascimento + 
				", cpf_Cnpj=" + cpf_Cnpj + 
				", rg_Insc=" + rg_Insc + 
				", sexo="+ sexo + 
				", fone_1=" + fone_1 + 
				", fone_2=" + fone_2 + 
				", fone_3=" + fone_3 + 
				", fone_4=" + fone_4 + 
				", fone_5=" + fone_5 + 
				", email=" + email + 
				", id_Profissao=" + id_Profissao + 
				", fcm_Tokem=" + fcm_Tokem + "]";
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cpf_Cnpj == null) ? 0 : cpf_Cnpj.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pessoa other = (Pessoa) obj;
		if (cpf_Cnpj == null) {
			if (other.cpf_Cnpj != null)
				return false;
		} else if (!cpf_Cnpj.equals(other.cpf_Cnpj))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		
		
		return true;
	}
	public Profissao getId_Profissao() {
		return id_Profissao;
	}
	public void setId_Profissao(Profissao id_Profissao) {
		this.id_Profissao = id_Profissao;
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
			
}
