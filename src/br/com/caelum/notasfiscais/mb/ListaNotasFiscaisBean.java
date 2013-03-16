/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@Named
@SessionScoped
public class ListaNotasFiscaisBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376241700796631182L;

	private LazyDataModel<NotaFiscal> dataModel;
	
	@Inject
	private DAO<NotaFiscal> dao;

	/**
	 * 
	 */
	public ListaNotasFiscaisBean () {
		this.dataModel = new DataModelNotasFiscais();
		this.dataModel.setRowCount(dao.contaTodos());
	}

	/**
	 * @return LazyDataModel
	 */
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}

}
