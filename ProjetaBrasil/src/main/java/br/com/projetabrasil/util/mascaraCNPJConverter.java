package br.com.projetabrasil.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("mascaraCPFConverter")
public class mascaraCNPJConverter implements Converter {
	

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {        
         String semMascara = value;
         if (value!= null && !value.equals(""))
              semMascara = Utilidades.retiraCaracteres(value);

         return semMascara;
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        /*
         * Irá converter CPF não formatado para um com pontos e traço.
         * Ex.: 35524519887 torna-se 355.245.198-87.
         */
         String resultado= (String)  value;
         if (resultado != null && resultado.length() == 14)
              resultado = resultado.substring(0, 2) + "." + resultado.substring(2, 5) + "." + resultado.substring(5, 8) + "/" 
         + resultado.substring(8, 12)+"-"+resultado.substring(12, 14);

         return resultado;
    }

}
