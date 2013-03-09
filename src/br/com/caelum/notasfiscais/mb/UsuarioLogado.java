package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.caelum.notasfiscais.modelo.Usuario;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 9, 2013
 * @package br.com.caelum.notasfiscais.mb
 */
@Named
@SessionScoped
public class UsuarioLogado implements Serializable{
	
	private static final long serialVersionUID = -5888846180418181538L;
	
	private Usuario usuario;
	
	/**
	 * @return boolean
	 */
	public boolean isLogado() {
		return this.usuario != null;
	}

	/**
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
