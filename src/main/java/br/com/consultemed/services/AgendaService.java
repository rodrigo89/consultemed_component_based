/**
 * 
 */
package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Agenda;
import br.com.consultemed.repository.AgendaRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class AgendaService {

	@Inject
	private AgendaRepository dao;
	
	public List<Agenda> listaAgenda() throws Exception{
		return this.dao.listarAgendas();
	}
	
	public void salvarAgenda(Agenda agenda) {
		this.dao.salvarAgenda(agenda);
	}
	
	public void deletarAgenda(Long id) throws Exception {
		this.dao.deleteById(id);
	}
}
