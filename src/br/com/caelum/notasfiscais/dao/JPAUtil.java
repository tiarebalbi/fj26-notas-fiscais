package br.com.caelum.notasfiscais.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Tiarê Balbi Bonamini
 * @date Mar 16, 2013
 * @package br.com.caelum.notasfiscais.dao
 *
 */
public class JPAUtil {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("notas");

	/**
	 * @return EM
	 */
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	/**
	 * Fecha o escopo da requisicao
	 * 
	 * @param em
	 */
	public void fecha(@Disposes EntityManager em) {
		em.getTransaction().commit();
		em.close();
	}
}
