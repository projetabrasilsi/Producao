package br.com.projetabrasil.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.model.entities.DAM;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Status_Insc_Municipal;
import br.com.projetabrasil.model.entities.InscricaoMunicipal;
import br.com.projetabrasil.model.entities.Parcelamentos;
import br.com.projetabrasil.util.PDFManager;
import br.com.projetabrasil.util.ReadExcel;
import br.com.projetabrasil.util.Utilidades;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class IptuJsfController {
	private UploadedFile file;
	private StreamedContent excel = null;

	private String caminhoTemp;
	private List<DAM> listaDam;
	private List<DAM> listDamRet;

	private List<InscricaoMunicipal> listaIm;
	private List<InscricaoMunicipal> listaImRet;

	private StreamedContent fileDownload = null;
	private boolean iniciar = false;
	private boolean download = false;
	private InscricaoMunicipal iMGeral;
	private List<Parcelamentos> parcs = new ArrayList<>();
	private List<Parcelamentos> Listparcs = new ArrayList<>();
    
	public InscricaoMunicipal getiMGeral() {
		return iMGeral;
	}

	public void setiMGeral(InscricaoMunicipal iMGeral) {
		this.iMGeral = iMGeral;
	}

	public void retornaListaImRet() {
		getListaImRet();
	}

	public boolean isIniciar() {
		return iniciar;
	}

	public void setIniciar(boolean iniciar) {
		this.iniciar = iniciar;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(StreamedContent fileDownload) {
		this.fileDownload = fileDownload;
	}

	public List<Parcelamentos> getParcs() {
		return parcs;
	}

	public void setParcs(List<Parcelamentos> parcs) {
		this.parcs = parcs;

	}

	public List<InscricaoMunicipal> getListaIm() {
		return listaIm;
	}

	public void setListaIm(List<InscricaoMunicipal> listaIm) {
		this.listaIm = listaIm;
	}

	private StreamedContent arquivoExcel = null;

	public void iniciarDownload() throws IOException {

		// Get the file from specific location or database
		File file = new File(Utilidades.getCaminhopdf() + "resultado.zip");

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.reset();
		// response.setHeader("Content-Disposition", "attachment;filename=" +
		// "resultado.xls" /*file.getName()*/);

		response.setHeader("Content-Disposition", "filename=\"" + "resultado.zip" + "\"");

		response.setHeader("Content-Type", "application/zip");

		OutputStream outputStream = response.getOutputStream();

		FileInputStream fileInputStream = new FileInputStream(file);

		byte[] bytesBuffer = new byte[2048];

		int bytesRead = 0;

		while ((bytesRead = fileInputStream.read(bytesBuffer)) > 0) {
			outputStream.write(bytesBuffer, 0, bytesRead);
		}

		outputStream.flush();

		fileInputStream.close();
		outputStream.close();

		facesContext.responseComplete();
	}

	public void atualizaListaIM() {
		listaImRet = listaIm;
		

	}

	public void atualizaListaIM2() {
		listaImRet = listaIm;

	}

	public void atualizaDam() {
		listDamRet = listaDam;
	}

	public void atualizaParcs() {

		Listparcs = parcs;
	}

	public void atualizaTudo() {
		atualizaListaIM2();
		atualizaDam();
		atualizaParcs();
	}

	public void buscarRelacaoDams() {

		listaDam = new ArrayList<>();

		int i = 0;

		for (InscricaoMunicipal im : listaIm) {
			im.setInicioProcesso(Utilidades.retornaCalendario());
			im.setStatus(Enum_Aux_Status_Insc_Municipal.BUSCANDO);
			listaIm.set(i, im);
			atualizaListaIM();

			try {
				iMGeral = im;
				List<DAM> lDam = GetDams(im);
				listaDam.addAll(lDam);
				im.setFimProcesso(Utilidades.retornaCalendario());
				im.setStatus(Enum_Aux_Status_Insc_Municipal.FINALIZADO);
				im.setQtdeDam(lDam.size());
				listaIm.set(i, im);
				listaIm.set(i, iMGeral);
				atualizaListaIM();
				atualizaDam();

				
				i++;

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void iniciarDownloadPdfs() {
		int i = 0;
		Utilidades.criarPasta(Utilidades.getCaminhopdf());
		for (InscricaoMunicipal l : listaIm) {
			List<DAM> dams;

			try {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Iniciando Download de Pdf: "+i+"/"+listaIm.size(), ""));
 
				if (!Utilidades.arquivoExiste(Utilidades.getCaminhopdf() + l.getnInsc() + ".pdf")) {
					dams = GetDams(l);
					try {
						l.setInicioProcesso(Utilidades.retornaCalendario());
						if (l.getQtdeDam() > 0) {
							boolean ok = downloadUrl(l.getnInsc() + ".pdf", dams);
							l.setFimProcesso(Utilidades.retornaCalendario());
							if (ok) {
								l.setDownloadPdfOk(Enum_Aux_Sim_ou_Nao.SIM);

							} else
								l.setDownloadPdfOk(Enum_Aux_Sim_ou_Nao.NAO);
							listaIm.set(i, l);
							atualizaListaIM2();
							atualizaDam();
						}

					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			i++;

		}

	}

	public void iniciardownloadResposta() throws IOException {

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gerando arquivo Excel: ", ""));

		PDFManager pdfManager = new PDFManager();

		try {
			setParcs(pdfManager.ToText(listaIm));
			atualizaParcs();

		} catch (IOException e) { // TODO
			e.printStackTrace();
		}
		setDownload(Utilidades.arquivoExiste(Utilidades.getCaminhopdf() + "relacao.xls"));

		List<String> lista = new ArrayList<>();
		for (InscricaoMunicipal im : listaIm) {
			if (im.getQtdeDam() > 0) {
				if (!lista.contains(im.getnInsc() + ".pdf")) {
					if (Utilidades.arquivoExiste(Utilidades.getCaminhopdf() + im.getnInsc() + ".pdf"))
						lista.add(im.getnInsc() + ".pdf");
				}
			}
		}
		lista.add("relacao.xls");
		String arquivoZip = "resultado.zip";
		Utilidades.apagarArquivo(Utilidades.getCaminhopdf() + arquivoZip);
		if (Utilidades.compactarArquivos(Utilidades.getCaminhopdf(), Utilidades.getCaminhopdf(), arquivoZip, lista)) {
			iniciarDownload();
		}

	}

	public String getCaminhoTemp() {
		return caminhoTemp;
	}

	public void setCaminhoTemp(String caminhoTemp) {
		this.caminhoTemp = caminhoTemp;
	}

	public void upload(FileUploadEvent event) {
		try {
			try {
				excel = new DefaultStreamedContent(event.getFile().getInputstream());
				this.setFile(event.getFile());
			} catch (IOException e) {

			}

			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(file.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			setCaminhoTemp(arquivoTemp.toString());
			listaIm = new ArrayList<>();
			List<String> listagem = new ArrayList<>();
			ReadExcel re = new ReadExcel();
			listaIm = re.readFromExcel(getCaminhoTemp());
			atualizaListaIM();
			if (listaIm != null && listaIm.size() > 0)
				setIniciar(true);

			Utilidades.mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			Utilidades.mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}

	}

	public List<DAM> GetDams(InscricaoMunicipal im) throws IOException {
		String request = "http://adm2.pmf.sc.gov.br/stmt/online_emissao_2a_via_iptu.php";

		URL url = new URL(request);
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("cd_refr_ano", "2017");
		params.put("cd_refr", im.getnInsc());
		params.put("pesquisa", "1");
		String data = Utilidades.retornaDataDoDiaString();
		params.put("data_hoje", data);
		params.put("libera_impressao", "imprimir");

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		conn.setReadTimeout(15 * 1000);
		conn.setConnectTimeout(15 * 1000);
		List<DAM> dams = new ArrayList<>();
		System.out.println("tamanho inicial: " + dams.size());

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			String decodedString = null;

			int i = 0;
			while ((decodedString = in.readLine()) != null) {
				String linha = new String();
				linha = in.readLine();
				

				if (linha != null && linha.contains("id=nu_dam value=")) {
					DAM dam = new DAM();
					dam.setChave("nu_dam[]");
					dam.setValor(linha.replaceAll("[^0-9]", ""));
					im.setDownloadDamOk(Enum_Aux_Sim_ou_Nao.SIM);

					dam.setIm(im);
					dams.add(dam);
					iMGeral = im;
				}
				i++;
			}
			in.close();
			i = 1;

			for (DAM dam : dams) {
				dam.setnDams("" + i + "/" + dams.size());
				i++;
			}

		} catch (MalformedURLException ex) {
			System.out.println(ex);
			System.out.println(url);
		} catch (IOException ex) {
			System.out.println(ex);
			System.out.println(url);
		}

		List<DAM> damsRet = dams;
		System.out.println("tamanho final: " + damsRet.size());
		return damsRet;

	}

	public boolean downloadUrl(String nomePdf, List<DAM> dams) throws IOException {
		String request = "http://adm2.pmf.sc.gov.br/stmt/segunda_via_internet.pdf.php";
		final int BUFFER_SIZE = 4096;

		URL url = new URL(request);
		Map<String, Object> params = new LinkedHashMap<>();
		int i = 0;
		for (DAM dam : dams) {
			params.put("nu_dam[" + i + "]", dam.getValor());
			i++;

		}

		params.put("data", "");
		params.put("controle", "ADMIN");
		params.put("pesquisa", "");
		String data = Utilidades.retornaDataDoDiaString();
		params.put("data_hoje", data);
		params.put("libera_impressao", "imprimir");
		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			String chave = param.getKey();
			if (chave.contains("nu_dam"))
				chave = "nu_dam[]";
			else
				chave = param.getKey();

			postData.append(URLEncoder.encode(chave, "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		File destino = new File(Utilidades.getCaminhopdf() + nomePdf);
		final PDDocument document;
		try (InputStream urlStream = url.openStream()) {
			document = PDDocument.load(conn.getInputStream(), "UTF-8");
			Utilidades.gravaDiretorio(Utilidades.getCaminhopdf());
			Utilidades.apagarArquivo(Utilidades.getCaminhopdf() + nomePdf);

			document.save(destino);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"" + nomePdf + ", download realizado com sucesso!.", ""));
		}

		return true;

	}

	public StreamedContent getArquivoExcel() {
		return arquivoExcel;
	}

	public void setArquivoExcel(StreamedContent arquivoExcel) {
		this.arquivoExcel = arquivoExcel;
	}

	public List<DAM> getListaDam() {
		return listaDam;
	}

	public void setListaDam(List<DAM> listaDam) {
		this.listaDam = listaDam;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<InscricaoMunicipal> getListaImRet() {
		return listaImRet;
	}

	public void setListaImRet(List<InscricaoMunicipal> listaImRet) {
		this.listaImRet = listaImRet;
	}

	public StreamedContent getExcel() {
		return excel;
	}

	public void setExcel(StreamedContent excel) {
		this.excel = excel;
	}

	public List<DAM> getListDamRet() {
		return listDamRet;
	}

	public void setListDamRet(List<DAM> listDamRet) {
		this.listDamRet = listDamRet;
	}

	public List<Parcelamentos> getListparcs() {
		return Listparcs;
	}

	public void setListparcs(List<Parcelamentos> listparcs) {
		Listparcs = listparcs;
	}

}
