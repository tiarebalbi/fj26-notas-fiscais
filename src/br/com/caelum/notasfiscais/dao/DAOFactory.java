/**
 * 
 */
package br.com.caelum.notasfiscais.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 16, 2013
 * @package br.com.caelum.notasfiscais.dao
 *
 */
public class DAOFactory {
	
	@Produces
	public DAO createDAO (InjectionPoint inject, EntityManager em) {
		ParameterizedType type = (ParameterizedType) inject.getType();
		Class classe = (Class) type.getActualTypeArguments()[0];
		return new DAO(classe, em);
	}

}
