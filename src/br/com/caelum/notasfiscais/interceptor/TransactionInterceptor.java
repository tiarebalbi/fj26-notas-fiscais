package br.com.caelum.notasfiscais.interceptor;

import java.util.Calendar;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.caelum.notasfiscais.annotations.Transactional;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 16, 2013
 * @package br.com.caelum.notasfiscais.interceptor
 */
@Interceptor
@Transactional
public class TransactionInterceptor {

	@Inject
	private EntityManager em;
	
	/**
	 * @param ctx
	 * @return Object
	 * @throws Exception 
	 */
	@AroundInvoke
	public Object intercept (InvocationContext ctx) throws Exception {
		//TODO Verificar por que ele da o erro de transação aberta neste caso
//		em.getTransaction().begin();
		Object resultado = ctx.proceed();
//		em.getTransaction().commit();
//		em.close();
		System.out.println(Calendar.getInstance().getTime() +" - Commitando nossa Transação!");
		return resultado;
	}
	
}
