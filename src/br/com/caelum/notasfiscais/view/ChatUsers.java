/**
 * 
 */
package br.com.caelum.notasfiscais.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.view
 * 
 */
public class ChatUsers {

	private List<String> users = new ArrayList<String>();

	@PostConstruct
	public void init() {
		this.users = new ArrayList<String>();
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public void addUser(String user) {
		this.users.add(user);
	}

	public void removeUser(String user) {
		this.users.remove(user);
	}

	public boolean contains(String user) {
		return this.users.contains(user);
	}
}
