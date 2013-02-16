package br.com.caelum.notasfiscais.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.notasfiscais.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Usuario where login = :login and senha = :senha")
						.setParameter("login", usuario.getLogin())
						.setParameter("senha", usuario.getSenha());

		boolean encontrado = !query.getResultList().isEmpty();
		em.getTransaction().commit();
		em.close();

		return encontrado;
	}
}