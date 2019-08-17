/**
 * 
 */
package br.com.consultemed.services;

import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.models.Usuario;
import br.com.consultemed.repository.UsuarioRepository;

/**
 * @author carlosbarbosagomesfilho
 *
 */
public class UsuarioService {

	@Inject
	private UsuarioRepository dao;
	
	public List<Usuario> listaUsuarios(){
		return this.dao.listaUsuarios();
	}
}
