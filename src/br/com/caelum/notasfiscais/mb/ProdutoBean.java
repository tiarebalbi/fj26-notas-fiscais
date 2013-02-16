package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produto = new Produto();
	
	private List<Produto> produtos;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void grava() {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		
		if(this.produto.getId() == null) {
			dao.adiciona(this.produto);
		}else {
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
	}
	
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			DAO<Produto> dao = new DAO<Produto>(Produto.class);
			this.produtos = dao.listaTodos();
		}
		return this.produtos;
	}
	
	public void remove(Produto produto) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
}
