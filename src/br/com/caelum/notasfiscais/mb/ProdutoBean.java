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

	private double somatoria = 0;
	
	private String mensagem = "";
	
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
			this.mensagem = "Registro Realizado com Sucesso";
		}else {
			this.mensagem = "Registro Atualizado com Sucesso";
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
		this.somatoria = this.doSomatoria(produtos);
	}
	
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			DAO<Produto> dao = new DAO<Produto>(Produto.class);
			this.produtos = dao.listaTodos();
			this.somatoria = this.doSomatoria(produtos);
		}
		this.mensagem = "";
		return this.produtos;
	}
	
	public void remove(Produto produto) {
		DAO<Produto> dao = new DAO<Produto>(Produto.class);
		dao.remove(produto);
		this.produtos = dao.listaTodos();
		this.somatoria = this.doSomatoria(produtos);
		this.mensagem = "Registro Removido com Sucesso";
	}
	
	public void clean() {
		this.produto = new Produto();
	}
	
	public double getSomatoria () {
		return this.somatoria;
	}
	
	public String getMensagem () {
		return this.mensagem;
	}
	
	private double doSomatoria(List<Produto> produtos) {
		double valor = 0;
		
		for (Produto produto : produtos) {
			valor += produto.getPreco();
		}
		
		return valor;
	}
	
}
