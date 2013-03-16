/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.annotations.Transactional;
import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@Named
@Transactional
public class ListaNotasFiscaisBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376241700796631182L;
	
	@Inject
	private LazyDataModel<NotaFiscal> dataModel;
	
	@Inject
	private DAO<NotaFiscal> dao;

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.dataModel.setRowCount(dao.contaTodos());
		this.dataModel.setPageSize(5);
	}

	/**
	 * @return LazyDataModel
	 */
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}

}
