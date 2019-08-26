/**
 * 
 */
package br.com.consultemed.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.consultemed.models.Agenda;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class AgendaRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();


	public List<Agenda> listarAgendas() throws Exception {
		this.factory = emf.createEntityManager();
		List<Agenda> agendas = new ArrayList<Agenda>();
		try {
			factory.getTransaction().begin();
			Query query = this.factory.createQuery("SELECT object(a) FROM Agenda as a");
			factory.getTransaction().commit();
			return query.getResultList();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

		return agendas;
	}

	public void salvarAgenda(Agenda agenda) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			if (agenda.getId() == null) {
				agenda.setAtiva(true);
				factory.persist(agenda);
			} else {
				agenda.setAtiva(true);
				factory.merge(agenda);
			}
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

	public void deleteById(Long id) throws Exception {
		this.factory = emf.createEntityManager();
		Agenda agenda = new Agenda();

		try {

			agenda = factory.find(Agenda.class, id);
			factory.getTransaction().begin();
			factory.remove(agenda);
			factory.getTransaction().commit();

		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();
		} finally {
			factory.close();
		}

	}
	
	public Agenda buscaPorId(Long idAgenda) {
		this.factory.getTransaction().begin();
		Agenda a = this.factory.find(Agenda.class, idAgenda);
		return a;
	}
	
	public void cancelarAgenda(Agenda agenda) {
		this.factory = emf.createEntityManager();
		try {
			factory.getTransaction().begin();
			
				if(agenda.isAtiva())
					agenda.setAtiva(false);
				//else
					//agenda.setAtiva(true);
				factory.merge(agenda);
			
			factory.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
			this.factory.getTransaction().rollback();

		} finally {
			factory.close();
		}
	}

}
