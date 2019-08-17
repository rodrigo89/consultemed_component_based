package br.com.consultemed.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.utils.JPAUtils;

@FacesConverter(forClass = Paciente.class, value = "pacienteConverter")
public class PacienteConverter implements Converter, Serializable {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Paciente paciente = (Paciente) object;
		if (paciente == null || paciente.getId() == null)
			return null;
		return String.valueOf(paciente.getId());
	}

	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = Long.valueOf(string);
		Paciente paciente = manager.find(Paciente.class, id);
		return paciente;
	}
}
