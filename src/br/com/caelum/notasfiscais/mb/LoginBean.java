package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDAO;
import br.com.caelum.notasfiscais.modelo.Usuario;

/**
 * Login Bean
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@Named
@RequestScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = 3497397471709212914L;
	
	@Inject
	private UsuarioLogado usuarioLogado;

	@Inject
	private Usuario usuario;
	
	private String mensagem = "";

	@Inject
	private UsuarioDAO dao;
	
	/**
	 * @return pagina Deve efetuar o login do usuário
	 */
	public String efetuarLogin() {
		Boolean status = dao.existe(usuario);
		if(status) {
			usuarioLogado.setUsuario(usuario);
			return "index?faces-redirect=true";
		}
		usuarioLogado.getUsuario().setSenha("");
		this.mensagem = "Usuário não foi encontrado";
		return "login?faces-redirect=true";
	}
	/**
	 * @return Usuario
	 */
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	/**
	 * @return mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}
	
	/**
	 * Método de deslogar usuário
	 * @return pagina redirect para página de login
	 */
	public String logout () {
		this.usuario = new Usuario();
		return "login?faces-redirect=true";
	}
	
	/**
	 * @return boolean
	 */
	public boolean isLogado() {
		return this.usuarioLogado.isLogado();
	}
	
	
}
