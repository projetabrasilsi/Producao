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
@ManagedBean(name = "fotos")
public class BrindeImagesjsfController {
	@ManagedProperty("#{param.caminho}")
	private String caminhodaImagem;
	@ManagedProperty("#{param.caminho2}")
	private String caminhodaImagem2;

	private StreamedContent foto;
	private StreamedContent foto2;
	private final String branco = Utilidades.getCaminhobase()+"branco"+Utilidades.getTipoImagem();

	public StreamedContent getFoto() throws IOException {
		
		if (caminhodaImagem == null || caminhodaImagem.isEmpty()) {
			
			Path path = Paths.get(branco);

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
				
				path = Paths.get(branco);

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

	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}

	public StreamedContent getFoto2() throws IOException {		
		if (caminhodaImagem2 == null || caminhodaImagem2.isEmpty()) {		
			Path path = Paths.get(branco);

			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto2 = new DefaultStreamedContent(stream);
			}

		} else {
			Path path = Paths.get(caminhodaImagem2);
			if (Files.exists(path)) {
				InputStream stream = Files.newInputStream(path);
				foto2 = new DefaultStreamedContent(stream);
			} else {				
				path = Paths.get(branco);

				if (Files.exists(path)) {
					InputStream stream = Files.newInputStream(path);
					foto2 = new DefaultStreamedContent(stream);
				}
			}
		}
		return foto2;
	}

	public void setFoto2(StreamedContent foto2) {
		this.foto2 = foto2;
	}

	public String getCaminhodaImagem2() {
		return caminhodaImagem2;
	}

	public void setCaminhodaImagem2(String caminhodaImagem2) {
		this.caminhodaImagem2 = caminhodaImagem2;
	}

}
