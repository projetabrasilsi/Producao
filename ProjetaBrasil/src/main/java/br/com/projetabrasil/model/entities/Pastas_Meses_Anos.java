package br.com.projetabrasil.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.projetabrasil.util.Utilidades;



@Entity
@Table(name="Pastas_Meses_Anos")
public class Pastas_Meses_Anos extends GenericDomain implements Serializable{
	@Id
	@SequenceGenerator(name="pk_Pastas_Meses_Anos", sequenceName="messsounds_Pastas_Meses_Anos")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_Pastas_Meses_Anos"  )
	private Long id;
	private Pessoa id_Pessoa_Empresa;	
	private int mes;
	private int ano;
	 
	
	public Pastas_Meses_Anos(Pessoa  id_Pessoa_Empresa){
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas());
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.FLORIANOPOLIS+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.FLORIANOPOLIS+"/"+Utilidades.RetornaMesAnoExtenso()+"/");
		
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.SAOJOSE+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.SAOJOSE+"/"+Utilidades.RetornaMesAnoExtenso()+"/");
		
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.PALHOCA+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.PALHOCA+"/"+Utilidades.RetornaMesAnoExtenso()+"/");
		
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.BIGUACU+"/");
		Utilidades.criarPasta(Utilidades.getCaminhopastasempresas()+id_Pessoa_Empresa.getCpf_Cnpj()+"/"+Enum_Aux_Tipo_WebRobos.IPTUECOLETA.getDescricao()+"/"
				+Enum_Aux_Cidades_Web_Scrapping.BIGUACU+"/"+Utilidades.RetornaMesAnoExtenso()+"/");
		
		
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Pessoa getId_Pessoa_Empresa() {
		return id_Pessoa_Empresa;
	}


	public void setId_Pessoa_Empresa(Pessoa id_Pessoa_Empresa) {
		this.id_Pessoa_Empresa = id_Pessoa_Empresa;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}
	
	

}
