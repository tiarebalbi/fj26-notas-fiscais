package br.com.caelum.notasfiscais.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.notasfiscais.modelo.Usuario;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 9, 2013
 * @package br.com.caelum.notasfiscais.dao
 *
 */
public class UsuarioDAO {
	
	@Inject
	private EntityManager em;

	/**
	 * @param usuario
	 * @return boolean
	 */
	public boolean existe(Usuario usuario) {
		
		Query query = em.createQuery("from Usuario where login = :login and senha = :senha")
						.setParameter("login", usuario.getLogin())
						.setParameter("senha", usuario.getSenha());

		return !query.getResultList().isEmpty();
	}
}