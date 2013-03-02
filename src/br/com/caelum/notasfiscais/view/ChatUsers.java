/**
 * 
 */
package br.com.caelum.notasfiscais.view;

import java.util.List;

import br.com.caelum.notasfiscais.dao.ChatDAO;
import br.com.caelum.notasfiscais.modelo.Chat;

/**
 * 
 * 
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.view
 *
 */
public class ChatUsers {
	
	private ChatDAO dao;
	
	private String username;
	
	public ChatDAO getDao() {
		return dao;
	}

	public void setDao(ChatDAO dao) {
		this.dao = dao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 */
	public ChatUsers() {
		this.dao = new ChatDAO();
	}

	/**
	 * @param username
	 * @return boolean
	 */
	public boolean contains(String username) {
		return this.dao.existe(username);
	}

	/**
	 * @param username
	 */
	public void addUser(String username) {
		this.username = username;
		this.dao.adicionar(username);
	}

	/**
	 * @param username
	 */
	public void removeUser(String username) {
		this.username = "";
		this.dao.remove(username);
		
	}
	
	public List<Chat> lista () {
		return null;
	}

}
