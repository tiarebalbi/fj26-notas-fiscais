/**
 * 
 */
package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

/**
 * @author Tiarê Balbi Bonamini
 * @date Mar 9, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@Named
@SessionScoped
public class TerminalBean implements Serializable {
	
	private static final long serialVersionUID = 4221199742372702329L;

	@Inject
	private DAO<Produto> dao;

	/**
	 * @return DAO
	 */
	public DAO<Produto> getDao() {
		return dao;
	}
	
	/**
	 * @param command
	 * @param params
	 * @return String
	 */
	public String handleCommand(String command, String[] params) {
		if(command.equals("help")) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("<h4>Central de Ajuda</h4><br/>");
			buffer.append("<p>Segue abaixo lista de comandos disponiveis:</p>");
			buffer.append(" - <b>Novo Produto:</b><br/>");
			buffer.append(" Cadastra um novo produto<br/>");
			buffer.append(" Comando: novo-produto <i>{nomeDoProduto|String} {Descricao|String|140Caracteres} {Preco|Double}</i><br/><br/>");
			buffer.append(" - <b>Total de Produto:</b><br/>");
			buffer.append(" Retorna o total de produtos cadastrados<br/>");
			buffer.append(" Comando: total-produto");
			return buffer.toString();
		} else if(command.equals("novo-produto")) {
			if(params.length != 3) {
				return "Por favor preencha todos os parametros necessários.";
			}
			
			Produto produto = new Produto();
			produto.setNome(params[0]);
			produto.setDescricao(params[1]);
			produto.setPreco(new Double(params[2]));
			this.dao.adiciona(produto);
			
			return "Produto <b>'"+params[0]+"'</b> foi adicionado com sucesso.";
		}else if(command.equals("total-produto")) {
			List<Produto> a = this.dao.listaTodos();
			return "Você possui <b>'"+a.size()+"'</b> produtos cadastrados.";
		}else if(command.equals("lista-produto")) {
			List<Produto> a = this.dao.listaTodos();
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("<h4>Lista de Produtos</h4>");
			buffer.append("<ul>");
			for (Produto produto : a) {
				buffer.append("<li>[ID#"+produto.getId()+"] - " + produto.getNome() + "</li>");
			}
			buffer.append("</ul>");
			
			return buffer.toString();
		}else if(command.equals("remover-produto")) {
			if(params.length != 1) {
				return "Por favor preencha todos os parametros necessários.";
			}
			Produto produto = this.dao.buscaPorId(new Long(params[0]));
			if(produto == null) {
				return "Não foi possível localizar o cadastro";
			}
			this.dao.remove(produto);
			
			return "Produto <b>'"+produto.getNome()+"'</b> foi removido com sucesso.";
		}

		
		return "Não foi possível identificar o seu comando";
	}
	
}
