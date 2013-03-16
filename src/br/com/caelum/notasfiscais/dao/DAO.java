package br.com.caelum.notasfiscais.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = -94376922203820151L;
	private Class<T> classe;
	private EntityManager em;

	/**
	 * @param classe
	 * @param em
	 */
	public DAO(Class<T> classe, EntityManager em) {
		this.classe = classe;
		this.em = em;
	}

	public T adiciona(T t) {
		em.persist(t);
		return t;
	}

	public void remove(T t) {
		em.remove(em.merge(t));
	}

	public T atualiza(T t) {
		em.merge(t);
		return t;
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> lista = em.createQuery(query).getResultList();
		return lista;
	}
	
	public T buscaPorId(Long id) {
		T instancia = em.find(classe, id);
		return instancia;
	}
	
	public int contaTodos() {
		long result = (Long) em.createQuery("select count(n) from " + classe.getName() + " n").getSingleResult();
		return (int) result;
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();
		return lista;
	}


}
