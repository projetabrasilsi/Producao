package br.com.projetabrasil.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;



@ManagedBean
@ApplicationScoped
public class NewImageStreamer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(NewImageStreamer.class.getName());

    private static final String UPLOADS = System.getProperty("uploads");

    public StreamedContent getImage() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so
            // that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            String nomeArquivo = context.getExternalContext().getRequestParameterMap().get("imagemCaminho");
            File arquivo = new File(nomeArquivo);
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(arquivo);
            } catch (Exception e) {
                nomeArquivo = "sem.jpg";
                String caminhoImagem = montaNomeArquivo(nomeArquivo, UPLOADS);
                arquivo = new File(caminhoImagem);
                try {
                    inputStream = new FileInputStream(arquivo);
                } catch (Exception e2) {
                    LOGGER.log(Level.WARNING, e.toString(), e);
                }
            }
            return new DefaultStreamedContent(inputStream);
        }
    }

    private String montaNomeArquivo(String nomeArquivo, String diretorio) {
        String retorno = diretorio;
        retorno += nomeArquivo;
        return retorno;
    }

}