package br.com.projetabrasil.utilteste;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.jr.ob.JSON;
import com.google.gson.Gson;

import br.com.projetabrasil.model.dao.ContatoDAO;
import br.com.projetabrasil.model.dao.EnderecoDAO;
import br.com.projetabrasil.model.dao.Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.Modelo_de_Marca_e_RacaDAO;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.QRCodeDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Contato;
import br.com.projetabrasil.model.entities.Endereco;
import br.com.projetabrasil.model.entities.Enum_Aux_Classificacao_Objetos;
import br.com.projetabrasil.model.entities.Ler_Excel;
import br.com.projetabrasil.model.entities.Marca_e_Raca;
import br.com.projetabrasil.model.entities.Modelo_de_Marca_e_Raca;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.QRCode;
import br.com.projetabrasil.model.entities.Usuario;
import br.com.projetabrasil.model.json.QRCodeJson;
import br.com.projetabrasil.util.PDFManager;
import br.com.projetabrasil.util.viacep.CEP;
import br.com.projetabrasil.util.viacep.ViaCEPClient;
import br.com.projetabrasil.util.viacep.ViaCEPConstants;

public class TestesImportantes {

	protected boolean usingHTTPS = false;
	protected JSON service;

	@Test
    @Ignore
	public void teste() {
        String cpf="89230906115";
        Long id = 10l;
        QRCode q = new QRCodeDAO().buscaQRCodePorCpfEId(cpf, id);
		

		Pessoa p = (Pessoa) new PessoaDAO().retornaPelaIdentificacao(cpf);

		QRCodeJson qc = new QRCodeJson();
		Endereco e = new Endereco();
		Usuario u = new Usuario();
		List<Contato> c = new ArrayList<Contato>();
		if (p != null) {
			e = new EnderecoDAO().buscaEnderecoPorPessoa(p);			
			c = new ContatoDAO().buscarContatoPelaPessoa(p);
			u = new UsuarioDAO().retornaUsuarioPelaPessoa(p);
		}
		qc.setFoto("");
		qc.setToken("");
		qc.setQr(q);
		qc.setEnd(e);
		qc.setUsur(u);		
		qc.setCont(c);
		Gson g = new Gson();   
		String pes = g.toJson(qc);
		System.out.println("qr: "+pes);

	}

	
	@Test
	@Ignore
	public void teste2() {

	}
	
	
	protected String getHost() {
		String host = (isUsingHTTPS() ? "https://" : "http://") + ViaCEPConstants.SERVICE_HOST;
		return host;
	}

	public List<CEP> getEnderecos(String uf, String localidade, String logradouro) throws IOException {
		service = JSON.std;
		this.service = service;

		String urlString = getHost() + uf + "/" + localidade + "/" + logradouro + "/json/";
		URL url = new URL(urlString);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		try {
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			List<CEP> obj = getService().listOfFrom(CEP.class, in);
			return obj;
		} finally {
			urlConnection.disconnect();
		}
	}

	@Test
	@Ignore
	public void selecao() {
		Marca_e_Raca marca = new Marca_e_Raca();
		marca.setDescricao("CHEVROLET");
		marca.setEnum_Aux_Classificacao_Objetos(Enum_Aux_Classificacao_Objetos.CARROS);
		Marca_e_RacaDAO marcaDAO = new Marca_e_RacaDAO();
		marca = marcaDAO.verifica_Marca_e_Raca(marca);
		System.out.println("marca: " + marca.getDescricao());
		if (marca != null) {
			Modelo_de_Marca_e_Raca mR = new Modelo_de_Marca_e_Raca();
			Modelo_de_Marca_e_RacaDAO mRDAO = new Modelo_de_Marca_e_RacaDAO();
			List<Modelo_de_Marca_e_Raca> lista = new ArrayList<>();

			mR.setDescricao("");
			mR.setMarca_e_Raca(marca);
			mR.setCodigo(marca.getCodigo());
			lista = mRDAO.listar_Modelo_de_Marca_e_Raca_Por_Marca_e_Raca_e_Descricao(mR);
			for (Modelo_de_Marca_e_Raca m : lista) {
				System.out.println("Modelo: " + m.getDescricao() + " - Marca: " + m.getMarca_e_Raca().getDescricao());

			}
		}
	}

	@Test
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
	public void inclusaodeMarcasERacas() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("10554498928", "11111111");
		Ler_Excel lEx = new Ler_Excel();

		if (us != null) {
			String caminho = "C:\\InserirDB\\marca_raca\\marcas-caminhao.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAMINHOES);

			caminho = "C:\\InserirDB\\marca_raca\\marcas-carros.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CARROS);

			caminho = "C:\\InserirDB\\marca_raca\\marcas-motos.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.MOTOS);

			caminho = "C:\\InserirDB\\marca_raca\\marcas-nautica.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.NAUTICOS);

			caminho = "C:\\InserirDB\\marca_raca\\PETS - CAES.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAES);

			caminho = "C:\\InserirDB\\marca_raca\\PETS - CAVALOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAVALOS);

			caminho = "C:\\InserirDB\\marca_raca\\PETS - COELHOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.COELHOS);

			caminho = "C:\\InserirDB\\marca_raca\\PETS - GATOS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.GATOS);

			caminho = "C:\\InserirDB\\marca_raca\\PETS - HAMSTERS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.HAMSTERS);
			caminho = "C:\\InserirDB\\marca_raca\\LISTA DE BICILETAS.xls";
			lEx.inserirMarca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.BICILETAS);
		}

	}

	@Test
	@Ignore
	public void inclusaodeModelosdeMarcasERacas() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario us = usuarioDAO.autenticar("10554498928", "11111111");
		Ler_Excel lEx = new Ler_Excel();

		if (us != null) {
			String caminho = "C:\\InserirDB\\modelos\\modelos-caminhao.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CAMINHOES);
			caminho = "C:\\InserirDB\\modelos\\modelos-carro.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.CARROS);
			caminho = "C:\\InserirDB\\modelos\\modelos-moto.xls";
			lEx.inserirModelo_de_Marca_e_RacaBD(us.getPessoa(), caminho, Enum_Aux_Classificacao_Objetos.MOTOS);
			caminho = "C:\\InserirDB\\modelos\\modelos-nautica.xls";
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

	protected boolean isUsingHTTPS() {
		return usingHTTPS;
	}

	protected void setUsingHTTPS(boolean usingHTTPS) {
		this.usingHTTPS = usingHTTPS;
	}

	protected JSON getService() {
		return service;
	}

	protected void setService(JSON service) {
		this.service = service;
	}

}
