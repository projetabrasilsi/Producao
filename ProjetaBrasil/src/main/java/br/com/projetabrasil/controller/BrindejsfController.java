package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.projetabrasil.model.dao.Item_de_MovimentoDAO;
import br.com.projetabrasil.model.entities.Enum_Aux_Sim_ou_Nao;
import br.com.projetabrasil.model.entities.Enum_Aux_Tipo_Item_de_Movimento;
import br.com.projetabrasil.model.entities.Item_de_Movimento;
import br.com.projetabrasil.model.entities.PerfilLogado;
import br.com.projetabrasil.util.Utilidades;



@ViewScoped
@ManagedBean(name = "brinde")
public class BrindejsfController extends GenericController {
	@ManagedProperty(value = "#{autenticacaojsfController}")
	private AutenticacaojsfController autenticacao;
	@ManagedProperty(value = "#{autenticacaojsfController.perfilLogado}")
	private PerfilLogado perfilLogado;
	private List<Item_de_Movimento> itens;
	private Item_de_Movimento item;
	private UploadedFile upLoaded;
	private byte[] fileContents;
	private String branco = Utilidades.getCaminhobase()+"branco"+Utilidades.getTipoImagem();
	private StreamedContent foto = null;
	private final String tipoDeImagem = Utilidades.getTipoImagemSemPonto();
	

	@PostConstruct
	public void listar() {
		item = new Item_de_Movimento();
		item.setCaminhodaImagem(branco);
		try {
			setFoto(convertFoto());
		} catch (IOException e) {			
			e.printStackTrace();
		}
		item.setCaminhoTemp(item.getCaminhodaImagem());
		listadecaminhosdeimagem();
	}

	public  void listadecaminhosdeimagem(){
		itens = new ArrayList<Item_de_Movimento>(); 
		
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		List<Item_de_Movimento> l = iMDAO.listar(perfilLogado.getAssLogado(),Enum_Aux_Tipo_Item_de_Movimento.BRINDE,null);
		
		
		int x = 0;
		for (Item_de_Movimento i : l) {			
			i.setCaminhodaImagem(Utilidades.getCaminhofotobrinde()+""+i.getId()+Utilidades.getTipoimagem());
			
			
			
			i.setTipodeImagem(Utilidades.tipodeImagem());
						
			itens.add(x,i);
			x++;
		}
		
	}

	

	public void novo() {
		item = new Item_de_Movimento(perfilLogado.getUsLogado().getPessoa(), perfilLogado.getAssLogado() , 
				 Enum_Aux_Sim_ou_Nao.SIM,Enum_Aux_Tipo_Item_de_Movimento.BRINDE);
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);
	}

	

	public void merge() {
		Path caminhoTemp;
		if (item.getCaminhoTemp() == null || item.getCaminhoTemp()=="") {
			mensagensDisparar("Imagem é obrigatória!!!");
			return;
		} else {
			caminhoTemp = Paths.get( item.getCaminhoTemp());
			if (!Files.exists(caminhoTemp)) {
				mensagensDisparar("Imagem é obrigatória!!!");
				return;
			}
		}
		
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		
		
		item = iMDAO.merge(item);
		item.setCaminhodaImagem(Utilidades.getCaminhofotobrinde()+""+item.getId()+Utilidades.getTipoimagem());

		Path origem = caminhoTemp;
		Path destino = Paths.get(item.getCaminhodaImagem());
		try {
			Utilidades.gravaDiretorio(item.getCaminhodaImagem());
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException error) {
			mensagensDisparar("Ocorreu um erro ao tentar salvar a imagem");

			error.printStackTrace();
		}

		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		listar();
	}

	public void editar(ActionEvent event) {
		item = (Item_de_Movimento) event.getComponent().getAttributes().get("registroAtual");
		item.setCaminhodaImagem(Utilidades.getCaminhofotobrinde()+""+item.getId()+Utilidades.getTipoimagem());
		item.setCaminhoTemp(item.getCaminhodaImagem());
		Utilidades.abrirfecharDialogos("dialogoCadastro", true);

	}

	public void upload(FileUploadEvent event) {		
		try {
			try {
	            foto = new DefaultStreamedContent(event.getFile().getInputstream());
	            this.setUpLoaded(event.getFile());
	        } catch (IOException e) {
	            
	        }
			
			UploadedFile arquivoUpload = event.getFile();
			
			
			// Messages.addGlobalInfo(arquivoUpload.getContentType()+"-"+arquivoUpload.getSize()+"-"+arquivoUpload.getFileName()+"-");
			Path arquivoTemp = Files.createTempFile(null, null);
			
			Files.copy(arquivoUpload.getInputstream(), arquivoTemp, StandardCopyOption.REPLACE_EXISTING);
			
			
			item.setCaminhoTemp(arquivoTemp.toString());
			item.setCaminhodaImagem(item.getCaminhoTemp());
			mensagensDisparar("Arquivo carregado com sucesso");
		} catch (IOException erro) {
			mensagensDisparar("Ocorreu um erro ao tentar realizar carregamento do arquivo");
			erro.printStackTrace();
		}
	}
	
	public StreamedContent convertFoto() throws IOException {
		
		if (item.getCaminhodaImagem() == null || item.getCaminhodaImagem().isEmpty()) {
			 
											
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(item.getCaminhodaImagem());
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			} else {
				
				path = Paths.get(branco);

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					foto = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto;
	}
	

	public void cancelar() {

		Utilidades.abrirfecharDialogos("dialogoCadastro", false);
		listar();
	}

	public void excluir(ActionEvent event) {
		item = (Item_de_Movimento) event.getComponent().getAttributes().get("registroAtual");
		item.setCaminhodaImagem(Utilidades.getCaminhofotobrinde()+""+item.getId()+Utilidades.getTipoimagem());
		Path arquivo = Paths.get(item.getCaminhodaImagem());
		try {
			Files.deleteIfExists(arquivo);
		} catch (IOException error) {

			error.printStackTrace();
		}
		Item_de_MovimentoDAO iMDAO = new Item_de_MovimentoDAO();
		iMDAO.excluir(item);
		listar();
	}

	public AutenticacaojsfController getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(AutenticacaojsfController autenticacao) {
		this.autenticacao = autenticacao;
	}

	public PerfilLogado getPerfilLogado() {
		return perfilLogado;
	}

	public void setPerfilLogado(PerfilLogado perfilLogado) {
		this.perfilLogado = perfilLogado;
	}

	public List<Item_de_Movimento> getItens() {
		return itens;
	}

	public void setItens(List<Item_de_Movimento> itens) {
		this.itens = itens;
	}

	public Item_de_Movimento getItem() {
		return item;
	}

	public void setItem(Item_de_Movimento item) {
		this.item = item;
	}

	public UploadedFile getUpLoaded() {
		return upLoaded;
	}

	public void setUpLoaded(UploadedFile upLoaded) {
		this.upLoaded = upLoaded;
	}

	public byte[] getFileContents() {
		return fileContents;
	}

	public void setFileContents(byte[] fileContents) {
		this.fileContents = fileContents;
	}

	

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public String getBranco() {
		return branco;
	}

	public void setBranco(String branco) {
		this.branco = branco;
	}

	public StreamedContent getFoto() {
		return foto;
	}

	public String getTipoDeImagem() {
		return tipoDeImagem;
	}

	
}
