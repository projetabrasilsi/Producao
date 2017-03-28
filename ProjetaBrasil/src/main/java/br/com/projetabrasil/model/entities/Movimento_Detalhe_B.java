package br.com.projetabrasil.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Movimento_Detalhe_B extends GenericDomain implements Serializable {
	
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
		@JoinColumn(name = "id_Pessoa_Cliente")
		private Pessoa id_Pessoa_Cliente;
		
		@ManyToOne	
		@JoinColumn(name="id_Movimento_Detalhe_A" )
		private Movimento_Detalhe id_Movimento_Detalhe_A;
		 
		@Column(name="qtde",precision=18, scale=4, nullable=false)
		private double qtde;
		@Column(name="codigo", length=12, nullable=false)
		String codigo;
		@Column(name="data")
		@Temporal(TemporalType.DATE)
		private Date data;
		@Temporal(TemporalType.TIME)
		private Date horario;
		

}
