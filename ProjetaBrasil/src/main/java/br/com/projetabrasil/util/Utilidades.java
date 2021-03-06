package br.com.projetabrasil.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import antlr.StringUtils;
import br.com.projetabrasil.model.dao.PessoaDAO;
import br.com.projetabrasil.model.dao.Pessoa_Enum_Aux_Perfil_PessoasDAO;
import br.com.projetabrasil.model.dao.Pessoa_VinculoDAO;
import br.com.projetabrasil.model.dao.UsuarioDAO;
import br.com.projetabrasil.model.entities.Agendamento;
import br.com.projetabrasil.model.entities.CEP;
import br.com.projetabrasil.model.entities.Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Movimento_Detalhe_A;
import br.com.projetabrasil.model.entities.Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Enum_Aux_Perfil_Pessoa;
import br.com.projetabrasil.model.entities.Pessoa_Vinculo;
import br.com.projetabrasil.model.entities.Usuario;

@SuppressWarnings("serial")
public class Utilidades implements Serializable {
	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final String caminhoFotoBrinde = System.getProperty("user.home") + "/imagens/brindes/";
	private static final String caminhoFotoVouchers = System.getProperty("user.home") + "/imagens/vouchers/";
	private static final String caminhoFotoAgendamento = System.getProperty("user.home") + "/imagens/agendamento/";
	private static String caminhoFotoComprovante = System.getProperty("user.home") + "/imagens/comprovantes/";
	private static final String caminhoPDF = System.getProperty("user.home") + "/pdfs/";

	private static String tipoImagem = ".jpeg";
	private static String tipoImagemSemPonto = "jpeg";
	private static final String caminhobase = System.getProperty("user.home") + "/imagens/";
	private static final String branco = Utilidades.getCaminhobase() + "branco" + Utilidades.getTipoImagem();
	private static final String naoatingido = "/images/" + "naoatingido" + Utilidades.getTipoImagem();
	private static final String atingido = "/images/" + "atingido" + Utilidades.getTipoImagem();

	public static String retornaCaminho(String diretorio, boolean temporario) {
		String retorno = "";
		if (!temporario) {
			/*
			 * *.* - coloquei porque se não fica apenas o nome do diretorio
			 * atual e pega o diretório absoluto anterior
			 */
			gravaDiretorio(diretorio + "*.*");
		} else {
			retorno = System.getProperty("java.io.tmpdir");
		}

		return retorno;

	}

	public static Date retornaData(String data) throws ParseException {

		if (data == null || data.equals(""))
			return null;
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = (java.util.Date) formatter.parse(data);
		} catch (ParseException e) {
			throw e;
		}
		return date;
	}

	public static void gravaDiretorio(String caminho) {
		File file = new File(caminho);
		String parentPath = file.getAbsoluteFile().getParent();
		Path newDirectoryPath = Paths.get(parentPath);

		if (!Files.exists(newDirectoryPath)) {

			try {

				Files.createDirectory(newDirectoryPath);

			} catch (IOException e) {
				System.err.println(e);
			}
		}

	}

	public static String tipodeImagem() {
		return getTipoimagem();
	}

	public static Calendar retornaCalendario() {
		TimeZone tz = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(tz);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		String sData = sd.format(c.getTime());

		try {
			c.setTime(sd.parse(sData));
			c.clear(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c;

	}

	public static Date retornaCalendario2() {
		TimeZone tz = TimeZone.getDefault();
		Calendar c = Calendar.getInstance(tz);
		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		String sData = sd.format(c.getTime());

		try {
			c.setTime(sd.parse(sData));
			c.clear(Calendar.ZONE_OFFSET);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date data = new Date();
		data = c.getTime();
		return data;

	}

	public static Calendar retornaData() {
		Calendar c = Calendar.getInstance();
		return c;
	}

	public static Date retornaValidade(int diasValidade) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, +diasValidade);

		return c.getTime();
	}

	public static Date retornaHora(String hora) {
		Date d = new Date();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

			d = sdf.parse(hora);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String retiraCaracteres(String texto) {
		if (texto == null)
			texto = "";
		texto = texto.replaceAll("[^\\p{ASCII}]", "").replaceAll("[.-]", "").replaceAll("\\/", "").replaceAll("\\(", "")
				.replaceAll("\\)", "").replaceAll(" ", "");
		texto = texto.replaceAll("_", "");
		return texto;
	}

	public static String retiraVazios(String texto) {
		if (texto == null)
			texto = "";
		texto.trim();
		return texto;
	}

	public static boolean estaVazio(String texto) {
		texto = StringUtils.stripBack(texto, " \t");
		texto = StringUtils.stripFront(texto, " \t");
		return texto.length() <= 0;
	}

	public static String removerAcentos(String str) {
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	public static String formataString(String str){
				
		return removerAcentos(str);
		
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean isValidCPF(String cpf) {
		if ((cpf == null) || (cpf.length() != 11))
			return false;

		Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
		boolean retorno = cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
		return retorno;
	}

	public static boolean isValidCNPJ(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static boolean isEmailValid(String email) {
		if ((email == null) || (email.trim().length() == 0))
			return false;

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static void abrirfecharDialogos(String dialogo, boolean abrir) {
		RequestContext context = RequestContext.getCurrentInstance();
		String finalidade;
		if (abrir)
			finalidade = "show";
		else
			finalidade = "hide";
		context.execute("PF('" + dialogo + "')." + finalidade + "();");
	}

	public static List<Enum_Aux_Sim_ou_Nao> listaSN() {
		List<Enum_Aux_Sim_ou_Nao> lista = new ArrayList<Enum_Aux_Sim_ou_Nao>();
		Enum_Aux_Sim_ou_Nao[] lSN;
		lSN = Enum_Aux_Sim_ou_Nao.values();

		for (Enum_Aux_Sim_ou_Nao l : lSN) {
			lista.add(l);
		}
		return lista;
	}

	public static List<Enum_Aux_Tipo_Item_de_Movimento> listaTipoItemdeMovimento() {
		List<Enum_Aux_Tipo_Item_de_Movimento> lista = new ArrayList<Enum_Aux_Tipo_Item_de_Movimento>();
		Enum_Aux_Tipo_Item_de_Movimento[] lSN;
		lSN = Enum_Aux_Tipo_Item_de_Movimento.values();

		for (Enum_Aux_Tipo_Item_de_Movimento l : lSN) {
			lista.add(l);
		}
		return lista;
	}

	public static Usuario validaEstabelecimento(Pessoa estab) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(estab, Enum_Aux_Perfil_Pessoa.ASSINANTES);
		return usuario;
	}

	public static Pessoa avaliaPessoa(Pessoa p, String destino) {
		PessoaDAO pDAO = new PessoaDAO();
		Pessoa pessoa = p;
		if (pessoa.getIdentificador().length() != 11 && pessoa.getIdentificador().length() != 14) {
			pessoa = Utilidades.criapessoa(destino + " com cpf ou cnpj com número de dígitos inválidos!!!");
		} else {
			if (pessoa.getIdentificador().length() == 11) {
				if (!Utilidades.isValidCPF(pessoa.getIdentificador()))
					pessoa = Utilidades.criapessoa(destino + " com CPF inválido!!!");

			} else if (pessoa.getIdentificador().length() == 14) {
				if (!Utilidades.isValidCNPJ(pessoa.getIdentificador()))
					pessoa = Utilidades.criapessoa(destino + " com CNPJ inválido!!!");
			}

			if (pessoa.isCpf_cnpjValido()) {
				pessoa.setMensagem(destino + " cadastrado");
				p.setCadastrado(false);
				pessoa.setCpf_cnpjValido(true);

				pessoa = pDAO.retornaPelaIdentificacao(p.getIdentificador());
				if (pessoa == null) {
					pessoa = p;
					pessoa.setCpf_cnpjValido(true);
					pessoa.setCadastrado(false);

				} else {
					if (pessoa.getId() == null) {
						pessoa.setCpf_cnpjValido(true);
						pessoa.setCadastrado(false);
					} else {
						pessoa.setCpf_cnpjValido(true);
						pessoa.setCadastrado(true);
					}
				}
			}
		}
		return pessoa;
	}

	public static Pessoa cadastraPessoa(Pessoa p, Pessoa mestre, Pessoa resp, Enum_Aux_Perfil_Pessoa perfil) {
		p.setId_Empresa(1);
		p.setId_Pessoa_Registro(resp);
		p.setUltimaAtualizacao(Utilidades.retornaCalendario());
		Pessoa pes = p;
		PessoaDAO pDAO = new PessoaDAO();

		pes = pDAO.retornaPelaIdentificacao(pes.getIdentificador());
		if (pes == null) {
			pes = p;
			pes = pDAO.merge(pes);
		}

		Pessoa_Enum_Aux_Perfil_Pessoa pp = new Pessoa_Enum_Aux_Perfil_Pessoa();
		pp.setId_Empresa(1);
		pp.setUltimaAtualizacao(Utilidades.retornaCalendario());
		pp.setId_Pessoa_Registro(resp);
		pp.setEnum_Aux_Perfil_Pessoa(perfil);
		pp.setId_pessoa(pes);

		Pessoa_Enum_Aux_Perfil_PessoasDAO ppDAO = new Pessoa_Enum_Aux_Perfil_PessoasDAO();

		pp = ppDAO.merge(pp);

		if (perfil.isPossuiVinculo()) {
			Pessoa_Vinculo pv = new Pessoa_Vinculo();
			Pessoa_Vinculo pvRet = new Pessoa_Vinculo();
			pv.setAtivo(true);
			pv.setEnum_Aux_Perfil_Pessoa(perfil);
			pv.setId_Empresa(1);
			pv.setUltimaAtualizacao(Utilidades.retornaCalendario());
			pv.setId_Pessoa_Registro(resp);
			pv.setId_pessoa_d(p);
			pv.setId_pessoa_m(mestre);
			pvRet = pv;
			Pessoa_VinculoDAO pvDAO = new Pessoa_VinculoDAO();
			pvRet = pvDAO.retornaVinculo_Mestre(p, mestre, perfil);
			if (pvRet == null)
				pv = pvDAO.merge(pv);
		}
		return pes;
	}

	public static CEP buscarCep(String cep) {
		String json;
		try {
			URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder jsonSb = new StringBuilder();
			br.lines().forEach(l -> jsonSb.append(l.trim()));
			json = jsonSb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		CEP cp = new CEP();
		cp = readJson(json);
		if(cp == null)
			cp = new CEP();
		return cp;
		
	}

	@SuppressWarnings("rawtypes")
	public static CEP readJson(String file) {
		JSONParser parser = new JSONParser();
		CEP cep = new CEP();
		try {
			System.out.println("Reading JSON file from Java program");
			FileReader fileReader = new FileReader(file);
			JSONObject json = (JSONObject) parser.parse(fileReader);
		    cep.Bairro = (String) json.get("bairro");
		    cep.Localidade = (String) json.get("localidade");
		    cep.Complemento = (String) json.get("complemento");
		    cep.Ibge = (String) json.get("ibge");
		    cep.Logradouro = (String) json.get("logradouro");
		    cep.Uf = (String) json.get("uf");
		    
			JSONArray characters = (JSONArray) json.get("characters");
			Iterator i = characters.iterator();

			while (i.hasNext()) {
				System.out.println(" " + i.next());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cep;
	}

	

	public static String getCaminhofotobrinde() {
		return caminhoFotoBrinde;
	}

	public static String getCaminhofotovouchers() {
		return caminhoFotoVouchers;
	}

	public static String getTipoimagem() {
		return tipoImagem;
	}

	public static StreamedContent retornaFoto(String caminhodoArquivo) throws IOException {

		StreamedContent foto = null;
		if (caminhodoArquivo == null || caminhodoArquivo.isEmpty()) {
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(caminhodoArquivo);
			if (Files.exists(path.getFileName())) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			} else {
				path = Paths.get(branco);

				if (Files.exists(path.getFileName())) {
					InputStream stream = Files.newInputStream(path);
					foto = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto;
	}

	public static String caminho(String tipo) {
		String caminho;
		if (tipo.toUpperCase().equals("BRINDES"))
			caminho = System.getProperty("user.dir") + getCaminhofotobrinde();
		else
			caminho = getCaminhofotovouchers();
		return caminho;
	}

	public static String getCaminhobase() {
		return caminhobase;
	}

	public static void setTipoimagem(String tipoimagem) {
		tipoImagem = tipoimagem;
	}

	public static String randon(String param) {

		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		Random random = new Random();
		String armazenaChaves = "";
		Long valor;
		String novoValor;
		int index = -1;
		for (int i = 0; i < 4; i++) {
			index = random.nextInt(letras.length());
			armazenaChaves += letras.substring(index, index + 1);
			valor = 0l;
			novoValor = "" + valor;
			while (novoValor.length() <= 1) {
				valor = random.nextInt(10) * Long.valueOf(param);
				novoValor = "" + valor;
			}

			armazenaChaves += novoValor.substring(1, 2);
			if (i % 2 == 0)
				armazenaChaves += "-";
		}

		String invertida = new StringBuilder(armazenaChaves).reverse().toString();
		return invertida;
	}

	public static void GerarPdf(Movimento_Detalhe_A mDA, Agendamento ag) {
		mensagensDisparar("iniciando a construção do comprovante");
		String caminhoPdf = caminhoPDF + mDA.getCodigo() + ".pdf";
		String caminhoDaImagem = Utilidades.getCaminhofotovouchers() + mDA.getId() + Utilidades.getTipoimagem();

		Document document = new Document();
		try {
			// String k = "<html><body> This is my Project </body></html>";

			gravaDiretorio(Utilidades.getCaminhopdf());
			OutputStream file = new FileOutputStream(new File(caminhoPdf));

			PdfWriter writer = PdfWriter.getInstance(document, file);
			document.open();

			Path path = Paths.get(caminhoDaImagem);
			Image img = null;
			if (Files.exists(path))
				img = Image.getInstance(caminhoDaImagem);

			String dados = "Nome          :" + ag.getId_Pessoa_Cliente().getDescricao() + "\n" + "Codigo        : "
					+ mDA.getCodigo() + "\n" + "Telefone      : " + ag.getId_Pessoa_Cliente().getFone_1() + "\n"
					+ "Agendado para : " + getDataPorExtenso(ag.getDataAgendamento().getTime()) + "\n";
			if (img != null)
				document.add(img);
			document.add(new Paragraph(dados));
			InputStream is = new ByteArrayInputStream(mDA.getRegulamento().getBytes());
			mensagensDisparar("finalizando a construção do comprovante");
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			file.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String formataNomeDaRegiao(String nome) {
		String[] nomeSplit = nome.split(" ");
		nome = "";
		for (int i = 0; i < nomeSplit.length; i++) {
			nomeSplit[i] = nomeSplit[i].toUpperCase();
			if (!nomeSplit[i].equals("DA") && !nomeSplit[i].equals("DO") && !nomeSplit[i].equals("DE")) {
				nomeSplit[i] = removerAcentos(nomeSplit[i].substring(0, 1).toUpperCase()
						+ nomeSplit[i].substring(1, nomeSplit[i].length()).toLowerCase());
			} else {
				nomeSplit[i] = removerAcentos(nomeSplit[i].toLowerCase());
			}
			nome = nome + nomeSplit[i] + " ";
		}
		return nome;
	}

	public static Pessoa criapessoa(String mensagem) {
		Pessoa pessoa = new Pessoa();
		pessoa.setMensagem(mensagem);
		pessoa.setCadastrado(false);
		pessoa.setCpf_cnpjValido(false);
		return pessoa;
	}

	public static String getDataPorExtenso(Date data) {
		DateFormat dfmt = new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
		return dfmt.format(data);
	}

	public static void copiaOrigemDestino(String origem, String destino) {
		Path or = Paths.get(origem);
		Path de = Paths.get(destino);
		try {
			Files.copy(or, de, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			error.printStackTrace();
		}
	}

	public static String getCaminhopdf() {
		return caminhoPDF;
	}

	public static String getTipoImagem() {
		return tipoImagem;
	}

	public static String getCaminhofotoagendamento() {
		return caminhoFotoAgendamento;
	}

	public static void mensagensDisparar(String mensagem) {
		Messages.addGlobalInfo(mensagem);
	}

	public static String getBranco() {
		return branco;
	}

	public static String getNaoatingido() {
		return naoatingido;
	}

	public static String getAtingido() {
		return atingido;
	}

	public static String getTipoImagemSemPonto() {
		return tipoImagemSemPonto;
	}

	public static String getCaminhofotocomprovante() {
		return caminhoFotoComprovante;
	}

	public static void setCaminhofotocomprovante(String caminhofotocomprovante) {
		caminhoFotoComprovante = caminhofotocomprovante;
	}

}
