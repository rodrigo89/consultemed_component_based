/**
 * 
 */
package br.com.consultemed.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import br.com.consultemed.models.Paciente;
import br.com.consultemed.models.Usuario;
import br.com.consultemed.utils.JPAUtils;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class UsuarioRepository {

	EntityManagerFactory emf = JPAUtils.getEntityManagerFactory();
	EntityManager factory = emf.createEntityManager();
	
	public List<Usuario> listaUsuarios() {
		Query query = this.factory.createQuery("SELECT object(u) FROM Usuario as u");
		return query.getResultList();
	}
	public Usuario buscaPorId(Long idUsuario) {
		this.factory.getTransaction().begin();
		Usuario a = this.factory.find(Usuario.class, idUsuario);
		return a;
	}
}
