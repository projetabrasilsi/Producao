package br.com.projetabrasil.utilteste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projetabrasil.model.dao.Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.Modelo_de_Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Classificacao_Objetos;
import br.com.projetabrasil.model.entities.Ler_Excel;
import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.model.entities.Modelo_de_Marca_e_Raca;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.util.viacep.CEP;
import br.com.projetabrasil.util.viacep.ViaCEPClient;

public class TestesImportantes {

	@Test	
	@Ignore	
	public void selecao() {
		Marca_e_Raca marca = new Marca_e_Raca();
		marca.setDescricao("CHEVROLET");
		marca.setEnum_Aux_Classificacao_Objetos(Enum_Aux_Classificacao_Objetos.CARROS);
		Marca_e_RacaDAO marcaDAO = new Marca_e_RacaDAO();
		marca = marcaDAO.verifica_Marca_e_Raca(marca);
		System.out.println("marca: "+marca.getDescricao());
		if (marca != null) {
			Modelo_de_Marca_e_Raca mR = new Modelo_de_Marca_e_Raca();
			Modelo_de_Marca_e_RacaDAO mRDAO = new Modelo_de_Marca_e_RacaDAO();
			List<Modelo_de_Marca_e_Raca> lista = new ArrayList<>();
			

			mR.setDescricao("");
			mR.setMarca_e_Raca(marca);
			mR.setCodigo(marca.getCodigo());
			lista = mRDAO.listar_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca_e_Descricao(mR);
			for (Modelo_de_Marca_e_Raca m : lista) {
				System.out.println("Modelo: " +m.getDescricao()+" - Marca: "+m.getMarca_e_Raca().getDescricao() );
			
			}
		}
	}
	
	@Test
	//Teste 1 
	@Ignore
	public void inclusaodeProfissoes() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("89230906115", "P2a3u0l9");
		Ler_Excel lEx = new Ler_Excel();
		if (us != null)
			lEx.inserirProfissaoBD(us.getPessoa());
	}
	
	@Test	
	@Ignore
	//Teste 2
	public void inclusaodeMarcasERacas() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("89230906115", "P2a3u0l9");
		Ler_Excel lEx = new Ler_Excel();

		if (us != null) {
			String caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\marcas-caminhao.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAMINHOES);

			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\marcas-carros.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CARROS);
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\marcas-motos.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.MOTOS);

			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\marcas-nautica.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.NAUTICOS);
			
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\PETS - CAES.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAES);
			
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\PETS - CAVALOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAVALOS);
			
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\PETS - COELHOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.COELHOS);
			
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\PETS - GATOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.GATOS);
			
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\PETS - HAMSTERS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.HAMSTERS);
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\LISTA DE BICILETAS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.BICILETAS);
		}

	}

	@Test	
	// 3
	public void inclusaodeModelosdeMarcasERacas() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("89230906115", "P2a3u0l9");
		Ler_Excel lEx = new Ler_Excel();

		if (us != null) {
			String caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\modelos-caminhao.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAMINHOES);
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\modelos-carro.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CARROS);
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\modelos-moto.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.MOTOS);
			caminho = "C:\\ProjetaBrasil Repositorio\\marcas-e-modelos\\modelos-nautica.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.NAUTICOS);
			
			
		}

	}

	

	

	@Test
	@Ignore
	public void BuscaCep() throws IOException {

		CEP cep = new CEP();
		cep.setLocalidade("Florianópolis");
		cep.setUf("SC");
		cep.setLogradouro("Madre");
		cep.setCep("");

		cep.setIbge("");
		cep.setBairro("");
		cep.setComplemento("");

		ViaCEPClient client = new ViaCEPClient();
		List<CEP> enderecos = client.getEnderecos("RJ", "Rio de Janeiro", "Avenida Brasil");

		for (CEP v : enderecos) {
			System.out.println("endereços: " + v.toString());

		}

	}

}
