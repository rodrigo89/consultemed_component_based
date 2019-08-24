package br.com.consultemed.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.consultemed.models.Medico;
import br.com.consultemed.utils.JPAUtils;

@FacesConverter(forClass = Medico.class, value = "medicoConverter")
public class MedicoConverter implements Converter, Serializable {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager manager = emf.createEntityManager();

	public String getAsString(FacesContext context, UIComponent component, Object object) {
		Medico medico = (Medico) object;
		if (medico == null || medico.getId() == null)
			return null;
		return String.valueOf(medico.getId());
	}

	public Object getAsObject(FacesContext context, UIComponent component, String string) {
		if (string == null || string.isEmpty())
			return null;
		Long id = Long.valueOf(string);
		Medico medico = manager.find(Medico.class, id);
		return medico;
	}
}
