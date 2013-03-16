package br.com.caelum.notasfiscais.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;
/**
 * 
 */

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 16, 2013
 * @package 
 */
@InterceptorBinding
@Target(value={ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {

}
