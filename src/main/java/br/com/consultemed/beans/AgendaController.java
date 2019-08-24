/**
 * 
 */
package br.com.consultemed.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.consultemed.models.Agenda;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.services.AgendaService;
import br.com.consultemed.services.MedicoService;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@RequestScoped
public class AgendaController implements Serializable{
	
	final static Logger logger = Logger.getLogger(AgendaController.class);
	
	@Getter
	@Setter
	private List<Agenda> agendas;

	@Inject
	@Getter
	@Setter
	private Agenda agenda;
	
	@Getter
	@Setter
	private Agenda agendaEditar;
	
	@Inject
	private AgendaService service;

	@Inject
	private PacienteService servicePaciente;
	@Inject
	private MedicoService serviceMedico;

	
	public String editar() {
		this.agenda = this.agendaEditar;
		return "/pages/agendas/addAgendas.xhtml";
	}
	
	public String cancelar() {
		System.out.println(this.agenda.isAtiva());
		this.agenda = this.agendaEditar;
		this.service.cancelarAgenda(this.agenda);

		System.out.println(this.agenda.isAtiva());
		return "/pages/agendas/agendas.xhtml";
	}
	
	public String excluir() throws Exception {
		this.agenda = this.agendaEditar;
		this.service.deletarAgenda(this.agenda.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Agendamento do Paciente: " +agenda.getPaciente().getNome()+ " com o Médico: " +agenda.getMedico().getNome()+ ", excluído com sucesso", null));
		listaAgendas();
		return "/pages/agendas/agendas.xhtml";
	}
	
	public String novoAgenda() {
		this.agenda = new Agenda();
		return "/pages/agendas/addAgendas.xhtml?faces-redirect=true";
	}
	
	public String addAgenda() throws Exception {
		this.service.salvarAgenda(this.agenda);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento do Paciente: " +agenda.getPaciente().getNome()+ " com o Médico: " +agenda.getMedico().getNome()+ ", cadastrado com sucesso", null));
		listaAgendas();
		return "/pages/agendas/agendas.xhtml";
	}
	
	public List<Agenda> listaAgendas() throws Exception{
		this.agendas = this.service.listaAgenda();
		return this.agendas;
	}
	
	public List<Paciente> listaPacientes() throws Exception{
		List<Paciente> pacientes = this.servicePaciente.listaPaciente();
		return pacientes;
	}
	
	public List<Medico> listaMedicos() throws Exception{
		List<Medico> medicos = this.serviceMedico.listaMedico();
		return medicos;
	}
	
	
	
}
