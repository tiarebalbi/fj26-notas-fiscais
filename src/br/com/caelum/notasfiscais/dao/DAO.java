package br.com.caelum.notasfiscais.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public T adiciona(T t) {
		//consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		//persiste o objeto
		em.persist(t);
		
		return t;
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public T atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
		
		return t;
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}
	
	public T buscaPorId(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instancia = em.find(classe, id);
		em.close();
		return instancia;
	}
	
	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();
		em.close();
		
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}


}
