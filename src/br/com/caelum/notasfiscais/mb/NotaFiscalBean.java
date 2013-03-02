/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;

/**
 * Nota Fiscal Bean
 * 
 * @author Tiarê Balbi Bonamini
 * @date Mar 2, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@ManagedBean
@ViewScoped
public class NotaFiscalBean implements Serializable {
	
	private static final long serialVersionUID = -6047290938461793285L;
	private Long idProduto;
	private Item item = new Item();
	private NotaFiscal notaFiscal = new NotaFiscal();
	private String mensagem;
	
	public void guardar() {
		DAO<NotaFiscal> dao = new DAO<NotaFiscal>(NotaFiscal.class);
		dao.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
	}
	
	public void guardaItem() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		Produto produto = dao.buscaPorId(this.idProduto);
		item.setProduto(produto);
		item.setNotaFiscal(this.notaFiscal);
		item.setValorUnitario(produto.getPreco());
		notaFiscal.getItens().add(item);
		item = new Item();
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}