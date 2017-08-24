package br.com.projetabrasil.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.projetabrasil.util.Utilidades;
 
@ManagedBean
@SessionScoped
//contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(path);
//private ;//
public class FileDownloadView {
	 
	public void download() throws IOException {
	 
	// Get the file from specific location or database
	File file = new File(Utilidades.getCaminhopdf()+"resultado.zip");
	 
	FacesContext facesContext = FacesContext.getCurrentInstance();
	 
	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	
	response.reset(); 
	//response.setHeader("Content-Disposition", "attachment;filename=" + "resultado.xls" /*file.getName()*/);
	 
	response.setHeader("Content-Disposition","filename=\"" + "resultado.zip" + "\"");
	
	
	
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
	 
	}