package br.com.consultemed.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.consultemed.models.Agenda;
import br.com.consultemed.models.Medico;
import br.com.consultemed.repository.AgendaRepository;
import br.com.consultemed.utils.CDIServiceLocator;


@FacesConverter(forClass = Agenda.class)
public class AgendaConverter implements Converter {

	private AgendaRepository dao;
	
	public AgendaConverter() {
		dao = CDIServiceLocator.getBean(AgendaRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agenda retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.buscaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Agenda agendamento = (Agenda) value;
			return agendamento.getId() == null ? null : agendamento.getId().toString();
		}
		
		return "";
	}
}
