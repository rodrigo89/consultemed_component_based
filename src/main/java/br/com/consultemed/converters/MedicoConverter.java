package br.com.consultemed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.consultemed.models.Medico;
import br.com.consultemed.repository.MedicoRepository;
import br.com.consultemed.utils.CDIServiceLocator;


@FacesConverter(value = "medicoConverter")
public class MedicoConverter implements Converter {

	private MedicoRepository dao;
	
	public MedicoConverter() {
		dao = new MedicoRepository();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Medico retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.buscaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Medico medico = (Medico) value;
			return medico.getId() == null ? null : medico.getId().toString();
		}
		
		return "";
	}
}
