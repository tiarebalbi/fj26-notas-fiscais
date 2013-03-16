/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

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
@ManagedBean
public class ListaNotasFiscaisBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3376241700796631182L;
	private LazyDataModel<NotaFiscal> dataModel;

	/**
	 * 
	 */
	public ListaNotasFiscaisBean () {
		
		this.dataModel = new DataModelNotasFiscais();
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		this.dataModel.setRowCount(dao.contaTodos());
	}

	/**
	 * @return LazyDataModel
	 */
	public LazyDataModel<NotaFiscal> getDataModel() {
		return dataModel;
	}

}
