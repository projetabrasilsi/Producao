package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.projetabrasil.util.Utilidades;



@RequestScoped
@ManagedBean(name = "fotoItem")
public class ItemImagesjsfController {
	@ManagedProperty("#{param.caminho}")
	private String caminhodaImagem;
	
	@ManagedProperty("#{param.caminhoTemp}")
	private String caminhodaImagemTemp;
	
	@ManagedProperty("#{param.caminho2}")
	private String caminhodaImagem2;
	
	
	
	private String caminho;

	private StreamedContent foto;
	private StreamedContent foto2;

	public StreamedContent getFoto() throws IOException {
		if (getCaminhodaImagem() == null || getCaminhodaImagem().isEmpty()) {			 											
			Path path = Paths.get(Utilidades.getBranco());

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			}

		} else {
			
			Path path = Paths.get(caminhodaImagem);
			
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto = new DefaultStreamedContent(stream);
			} else {
				
				path = Paths.get(Utilidades.getBranco());

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					foto = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto;
	}

	public String getCaminhodaImagem() {
		return caminhodaImagem;
	}

	public void setCaminhodaImagem(String caminhodaImagem) {
		this.caminhodaImagem = caminhodaImagem;
	}

	public String getCaminhodaImagem2() {
		return caminhodaImagem2;
	}

	public void setCaminhodaImagem2(String caminhodaImagem2) {
		this.caminhodaImagem2 = caminhodaImagem2;
	}

	

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public StreamedContent getFoto2() {
		return foto2;
	}

	public void setFoto2(StreamedContent foto2) {
		this.foto2 = foto2;
	}

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public String getCaminhodaImagemTemp() {
		return caminhodaImagemTemp;
	}

	public void setCaminhodaImagemTemp(String caminhodaImagemTemp) {
		this.caminhodaImagemTemp = caminhodaImagemTemp;
	}

	

	

}
