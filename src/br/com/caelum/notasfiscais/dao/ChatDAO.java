package br.com.caelum.notasfiscais.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.caelum.notasfiscais.modelo.Chat;

/**
 * //TODO Descreva por que você esta criando esta classe ?
 * 
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.dao
 *
 */
public class ChatDAO  {
	
	@Inject
	private EntityManager em;

	/**
	 * @param username
	 * @return boolean
	 */
	public boolean existe(String username) {
		Query query = em.createQuery("from Chat where username = :username")
						.setParameter("username", username);

		return !query.getResultList().isEmpty();
	}
	
	/**
	 * @param username
	 */
	public void remove(String username) {
		TypedQuery<Chat> query = em.createQuery("from Chat where username = :username", Chat.class)
				.setParameter("username", username);

		Chat c = query.getSingleResult();
		em.remove(c);
	}
	
	/**
	 * @param username
	 */
	public void adicionar(String username) {
		Chat chat = new Chat();
		chat.setStatus(true);
		chat.setUsername(username);
		
		em.persist(chat);
	}
}