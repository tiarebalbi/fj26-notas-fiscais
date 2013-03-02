package br.com.caelum.notasfiscais.dao;

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

	/**
	 * @param username
	 * @return boolean
	 */
	public boolean existe(String username) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Chat where username = :username")
						.setParameter("username", username);

		boolean encontrado = !query.getResultList().isEmpty();
		em.getTransaction().commit();
		em.close();

		return encontrado;
	}
	
	/**
	 * @param username
	 */
	public void remove(String username) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<Chat> query = em.createQuery("from Chat where username = :username", Chat.class)
				.setParameter("username", username);

		Chat c = query.getSingleResult();
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * @param username
	 */
	public void adicionar(String username) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Chat chat = new Chat();
		chat.setStatus(true);
		chat.setUsername(username);
		
		em.persist(chat);

		em.getTransaction().commit();
		em.close();
	}
}