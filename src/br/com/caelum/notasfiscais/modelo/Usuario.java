package br.com.caelum.notasfiscais.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * //TODO Descreva por que você esta criando esta classe ?
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.modelo
 *
 */
@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;

	private String login;

	private String senha;

	/**
	 * @return Long
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
