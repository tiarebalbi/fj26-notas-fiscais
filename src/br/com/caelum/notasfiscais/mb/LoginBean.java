/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	private String mensagem = "";
	
	/**
	 * Deve efetuar o login do usuário
	 * @return pagina pagina que deverá ser aberta após enviar o form
	 */
	public String efetuarLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		Boolean status = dao.existe(usuario);
		if(status) {
			return "index";
		}else {
			this.usuario.setSenha("");
			this.mensagem = "Usuário não foi encontrado";
			return "login";
		}
		
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
}
