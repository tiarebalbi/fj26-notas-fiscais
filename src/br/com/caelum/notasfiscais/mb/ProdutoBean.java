package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.ValidationException;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

/**
 * Produto Bean - Class para manipulação da entidade {@link Produto}
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.mb
 * @see Produto
 *
 */
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432472731736272263L;

	private Produto produto = new Produto();
	
	private List<Produto> produtos;

	private double somatoria = 0;
	
	private String mensagem = "";
	
	private DAO<Produto> dao;
	
	/**
	 * @return DAO<Produto>
	 */
	public DAO<Produto> getDao() {
		return dao;
	}

	/**
	 * @param dao
	 */
	public void setDao(DAO<Produto> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 */
	public ProdutoBean() {
		this.dao = new DAO<Produto>(Produto.class);
	}

	/**
	 * Getter de {@link #produto}
	 * 
	 * @return produto 
	 */
	public Produto getProduto() {
		return produto;
	}

	/**
	 * Setter de {@link #produto}
	 * 
	 * @param produto 
	 */
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	 * Método responsável em gravar um novo produto;
	 * @throws InterruptedException 
	 */
	public void grava() {
		
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
	
	/**
	 * Método responsável em retornar lista completa de produtos cadastrados
	 * 
	 * @return List<Produto> retorna a lista de produtos cadastrado
	 */
	public List<Produto> getProdutos() {
		if(this.produtos == null) {
			this.produtos = dao.listaTodos();
			this.somatoria = this.doSomatoria(produtos);
		}
		this.mensagem = "";
		return this.produtos;
	}
	
	/**
	 * @param produtos
	 */
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/**
	 *  Método para remover um produto já cadastrado
	 *  
	 * @param produto Objeto {@link #produto} que deverá ser removido
	 */
	public void remove(Produto produto) {
		dao.remove(produto);
		this.produtos = dao.listaTodos();
		this.somatoria = this.doSomatoria(produtos);
		this.mensagem = "Registro Removido com Sucesso";
	}
	
	/**
	 * @param ctx
	 * @param component
	 * @param value
	 */
	public void comecaComMaiuscula ( FacesContext ctx, UIComponent component, Object value ) throws ValidatorException {
		String valor = value.toString();
		if(!valor.matches("[A-Z].*")) {
			FacesMessage notification = new FacesMessage("A Primeira letra deve ser maiscula");
			throw new ValidatorException(notification);
		}
	}
	
	/**
	 * @param mensagem
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Limpa o objeto produto
	 */
	public void clean() {
		this.produto = new Produto();
	}
	
	/**
	 * Getter para retorno do preço total
	 * 
	 * @return somatoria Retornar o preço total
	 */
	public double getSomatoria () {
		return this.somatoria;
	}
	
	/**
	 * @return mensagem retorna String com a mensagem definida.
	 */
	public String getMensagem () {
		return this.mensagem;
	}
	
	private double doSomatoria(List<Produto> produtos) {
		double valor = 0;
		
			for (Produto produto : produtos) {
				if(produto.getPreco() != null) {
					valor += produto.getPreco();
				}
			}
		
		return valor;
	}
	
}
