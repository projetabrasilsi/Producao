package br.com.projetabrasil.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.projetabrasil.util.Utilidades;



@ManagedBean
@ApplicationScoped
public class ImagensjsfController {

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		
		String caminho="";
		StreamedContent foto = null;
		caminho =Utilidades.getBranco();
		foto = transforma(Utilidades.getBranco());	  
		
		caminho = context.getExternalContext().getRequestParameterMap().get("caminho");
		if (caminho==null || caminho.length()==0)
		caminho = Utilidades.getBranco();		
		Utilidades.gravaDiretorio(caminho);
		Path path = Paths.get(caminho);
		
		if (Files.exists(path))		
		foto = transforma(caminho);
		return foto;
	}

	public StreamedContent transforma(String caminho) {
		InputStream stream = null;
		Path path = Paths.get(caminho);
		StreamedContent foto = null;
		

		if (Files.exists(path)) {
			try {
				stream = Files.newInputStream(path);
			} catch (IOException e) {			
				e.printStackTrace();
			}
			foto = new DefaultStreamedContent(stream);
		} else
			foto = new DefaultStreamedContent(stream);

		return foto;
	}

}
