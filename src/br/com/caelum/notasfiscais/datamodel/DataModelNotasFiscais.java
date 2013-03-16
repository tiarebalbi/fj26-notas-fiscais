package br.com.caelum.notasfiscais.datamodel;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package 
 *
 */
public class DataModelNotasFiscais extends LazyDataModel<NotaFiscal> {
	
	@Inject
	private DAO<NotaFiscal> dao;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6921338747873136511L;
	
	/* (non-Javadoc)
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String, org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<NotaFiscal> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		return dao.listaTodosPaginada(first, pageSize);
	}

}
