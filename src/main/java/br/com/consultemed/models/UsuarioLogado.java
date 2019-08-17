/**
 * 
 */
package br.com.consultemed.models;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.consultemed.security.AutenticadorService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@SessionScoped
public class UsuarioLogado implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autenticador == null) ? 0 : autenticador.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioLogado other = (UsuarioLogado) obj;
		if (autenticador == null) {
			if (other.autenticador != null)
				return false;
		} else if (!autenticador.equals(other.autenticador))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}


	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@Inject
	private Usuario usuario;
	
	@Inject
	private AutenticadorService autenticador;

	public void logar() {
		
		Usuario usuario = this.autenticador.autenticador(this.usuario.getLogin(), this.usuario.getSenha());
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuario", usuario);
	}

	public void logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
	}
	
	
	public boolean isLogado() {
		boolean isLogeded = false;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario != null) {
			isLogeded = true;
		}
		return isLogeded;
	}
	

}
