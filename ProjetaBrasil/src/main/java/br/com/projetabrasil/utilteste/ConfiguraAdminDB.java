package br.com.projetabrasil.utilteste;

import java.text.ParseException;

import org.junit.Test;

import br.com.projetabrasil.model.dao.BairroDAO;
import br.com.projetabrasil.model.dao.CidadeDAO;
import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.dao.EstadoDAO;
import br.com.projetabrasil.model.dao.LogradouroDAO;
import br.com.projetabrasil.model.dao.PaisDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Bairro;
import br.com.projetabrasil.model.entities.Cidade;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Sexo;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Identificador;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Logradouro;
import br.com.projetabrasil.model.entities.Estado;
import br.com.projetabrasil.model.entities.Logradouro;
import br.com.projetabrasil.model.entities.Pais;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.Utilidades;

public class ConfiguraAdminDB {
		/* MÉTODO ABAIXO FAZ A CONFIGURAÇÃO INICIAL DO USUÁRIO ADMIN SOMENTE COM O BANCO VAZIO
		LEMBRE-SE DE MUDAR O SEU CPF E SENHA LOGO À BAIXO, SIGA OS PASSOS */
		@Test
		public void configuraAdmin() throws ParseException {
			Pessoa p = new Pessoa();
			p.setCpf_Cnpj("10554498928"); // <-- MUDE SEU CPF AQUI, FAÇA O MESMO LOGO À BAIXO
			p.setCpf_Cnpj(Utilidades.retiraCaracteres(p.getCpf_Cnpj()));
			
			Pessoa_Vinculo pV = new Pessoa_Vinculo();
			
			boolean vf = false;
			if (p.getCpf_Cnpj().length() == 11)
				vf = Utilidades.isValidCPF(p.getCpf_Cnpj());
			else if (p.getCpf_Cnpj().length() == 14)
				vf = Utilidades.isValidCNPJ(p.getCpf_Cnpj());

			if (vf) {

				p.setIdentificador(p.getCpf_Cnpj());
				PessoaDAO pDAO = new PessoaDAO();
				p = pDAO.retornaPelaIdentificacao(p.getIdentificador());
				
				System.out.println("Aqui é uma pessoa: "+p);

				if (p == null) {
					p = new Pessoa();
					p.setCpf_Cnpj("10554498928"); // <-- MUDE SEU CPF, NÃO SE ESQUEÇA DA SENHA MAIS À BAIXO
					p.setIdentificador(p.getCpf_Cnpj());
					p.setRg_Insc("1111111");
					p.setSexo(Enum_Aux_Sexo.MASCULINO);
					p.setFone_1("11111111111");
					p.setFone_2("");
					p.setFone_3("");
					p.setEmail("admin@gmail.com");
					p.setId_Pessoa_Registro(p);
					p.setAutoPontuacao(Enum_Aux_Sim_ou_Nao.NAO);
					p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CPF);
					p.setDescricao("Admin");
					p.setFantasia_Apelido("");
					p.setDataNascimento(Utilidades.retornaData("20/09/1881"));
				}
				p = pDAO.merge(p);
				Usuario us = new Usuario();
				us.setPessoa(p);
				
				pV.setId_pessoa_d(p);

				UsuarioDAO usDAO = new UsuarioDAO();
				us = usDAO.retornaUsuarioPelaPessoa(p);

				if (us == null) {
					try {
						us = new Usuario();
						us.setPessoa(p);
						us.setAtivo(true);
						us.setSenhaSemCript("11111111"); // <-- MUDE SUA SENHA
						us.setConfSenha("11111111"); // <-- MANTENHA IGUAL A SENHA DE CIMA
						
						// FEITO ISTO VOCÊ JÁ PODE PERSISTIR AS CONFIGURAÇÕES NO BANCO
						
						us.setId_Empresa(1);
						us.setId_Pessoa_Registro(p);
						us.setPessoa(p);
						
					} catch (RuntimeException error) {
						error.printStackTrace();
						throw error;
					}
				}
				
				us = usDAO.merge(us);

				Pessoa_Enum_Aux_Perfil_Pessoa pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
				Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();

				pp.setId_pessoa(p);
				pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);

				pp = ppDAO.retornaPerfildaPessoaPelaPessoa(pp);

				if (pp == null) {
					pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
					pp.setId_pessoa(p);
					pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);
					pp.setId_Empresa(1);

					pp.setId_Pessoa_Registro(p);
					pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
				}
				pp = ppDAO.merge(pp);
				
				//SEGUNDO PERFIL
				pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
				
				pp.setId_pessoa(p);
				pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ATENDENTES);
				
				pp = ppDAO.retornaPerfildaPessoaPelaPessoa(pp);
				
				if (pp == null) {
					pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
					pp.setId_pessoa(p);
					pp.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ATENDENTES);
					pp.setId_Empresa(1);

					pp.setId_Pessoa_Registro(p);
					pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
				}
				pp = ppDAO.merge(pp);
				
				//CADASTRA EMPRESA
				p = new Pessoa();
				p.setCpf_Cnpj("41650839000152");
				p.setCpf_Cnpj(Utilidades.retiraCaracteres(p.getCpf_Cnpj()));

				vf = false;
				if (p.getCpf_Cnpj().length() == 11)
					vf = Utilidades.isValidCPF(p.getCpf_Cnpj());
				else if (p.getCpf_Cnpj().length() == 14)
					vf = Utilidades.isValidCNPJ(p.getCpf_Cnpj());

				if (vf) {

					p.setIdentificador(p.getCpf_Cnpj());
					pDAO = new PessoaDAO();
					p = pDAO.retornaPelaIdentificacao(p.getIdentificador());

					if (p == null) {
						p = new Pessoa();
						p.setCpf_Cnpj("41650839000152");
						p.setIdentificador(p.getCpf_Cnpj());
						p.setRg_Insc("708587489925");
						p.setFone_1("11111111111");
						p.setFone_2("");
						p.setFone_3("");
						p.setEmail("projetabrasil@gmail.com");
						p.setId_Pessoa_Registro(p);
						p.setEnum_Aux_Tipo_Identificador(Enum_Aux_Tipo_Identificador.CNPJ);
						p.setDescricao("Projeta Brasil");
						p.setFantasia_Apelido("Projeta Brasil");
					}
					p = pDAO.merge(p);
					us = new Usuario();
					us.setPessoa(p);

					usDAO = new UsuarioDAO();
					us = usDAO.retornaUsuarioPelaPessoa(p);

					if (us == null) {
						try {
							us = new Usuario();
							us.setPessoa(p);
							us.setAtivo(true);
							us.setSenhaSemCript("11111111");
							us.setConfSenha("11111111");
							//us.setId(null);
							us.setId_Empresa(1);
							us.setId_Pessoa_Registro(p);
							us.setPessoa(p);
							
						} catch (RuntimeException error) {
							error.printStackTrace();
							throw error;
						}
					}
					
					us = usDAO.merge(us);
					
					//VINCULA PERFIS COM EMPRESA
					Pessoa_VinculoDAO pVDAO = new Pessoa_VinculoDAO();
					pV.setId_Empresa(1);
					pV.setId_pessoa_m(p);
					pV.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ADMINISTRADORES);
					pV.setId_Pessoa_Registro(pV.getId_pessoa_d());
					pV.setAtivo(true);
					
					pVDAO.merge(pV);
					
					pV.setEnum_Aux_Perfil_Pessoa(Enum_Aux_Perfil_Pessoa.ATENDENTES);
					
					pVDAO.merge(pV);
					
					//CONFIGURA REGIÕES E ENDEREÇO
					PaisDAO paisDAO = new PaisDAO();
					Pais pais = new Pais();
					pais.setDescricao("Brasil");
					pais.setId(null);
					pais.setId_Empresa(0);
					pais.setId_Pessoa_Registro(pV.getId_pessoa_d());
					pais.setMaskTel("teste123");
					pais.setMaskZip("test123");
					pais.setSigla("BR");
					pais.setUltimaAtualizacao(Utilidades.retornaCalendario());
					pais = paisDAO.merge(pais);
					
					EstadoDAO estadoDAO = new EstadoDAO();
					Estado e = new Estado();
					e.setDescricao("Santa Catarina");
					e.setId(null);				
					e.setId_Empresa(0);
					e.setId_Pessoa_Registro(pV.getId_pessoa_d());
					e.setPais(pais);
					e.setSigla("SC");
					e.setUltimaAtualizacao(Utilidades.retornaCalendario());
					e = estadoDAO.merge(e);
					
					CidadeDAO cidadeDAO = new CidadeDAO();
					Cidade c = new Cidade();
					c.setDescricao("Florianopolis");
					c.setId(null);				
					c.setId_Empresa(0);
					c.setId_Pessoa_Registro(pV.getId_pessoa_d());
					c.setEstado(e);				
					c.setUltimaAtualizacao(Utilidades.retornaCalendario());
					c.setCep("88050000");
					c = cidadeDAO.merge(c);
					
					BairroDAO bairroDAO = new BairroDAO();
					Bairro b = new Bairro();
					b.setDescricao("Santo Antônio de Lisboa");
					b.setId(null);				
					b.setId_Empresa(0);
					b.setId_Pessoa_Registro(pV.getId_pessoa_d());
					b.setCidade(c);				
					b.setUltimaAtualizacao(Utilidades.retornaCalendario());				
					b = bairroDAO.merge(b);
					
					LogradouroDAO logradouroDAO = new LogradouroDAO();
					Logradouro l = new Logradouro();
					l.setDescricao("José Carlos Daux");
					l.setId(null);				
					l.setId_Empresa(0);
					l.setId_Pessoa_Registro(pV.getId_pessoa_d());
					l.setCidade(c);				
					l.setUltimaAtualizacao(Utilidades.retornaCalendario());	
					l.setEnum_Aux_Tipo_Logradouro(Enum_Aux_Tipo_Logradouro.RODOVIA);
					l = logradouroDAO.merge(l);
					
					EnderecoDAO enderecoDAO = new EnderecoDAO();
					Endereco endereco = new Endereco();
					endereco.setBairro(b);
					endereco.setLogradouro(l);
					endereco.setComplemento("Casa");
					endereco.setId(null);
					endereco.setId_Empresa(0);
					endereco.setNumero(34);
					endereco.setPessoa(pV.getId_pessoa_d());
					endereco.setUltimaAtualizacao(Utilidades.retornaCalendario());
					enderecoDAO.merge(endereco);
					
					endereco.setPessoa(pV.getId_pessoa_m());
					endereco.setComplemento("Apartamento");
					endereco.setNumero(80);
					enderecoDAO.merge(endereco);
					
				}
			}
		}
}
