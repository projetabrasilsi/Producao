package br.com.projetabrasil.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("mascaraTelefoneConverter")
public class MascaraTelefoneConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String semMascara = value;
		if (value != null && !value.equals(""))
			semMascara = Utilidades.retiraCaracteres(value);

		return semMascara;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		
		String resultado = (String) value;
		if (resultado != null && resultado.length() == 11) {
			if (resultado.length()==10)
			resultado ="("+ resultado.substring(0, 2) + ") " + resultado.substring(2, 6) + "-" + resultado.substring(6, 10);
			else
			resultado ="("+ resultado.substring(0, 2) + ") " + resultado.substring(2, 7) + "-" + resultado.substring(7, 11);
		}

		return resultado;
	}

}
