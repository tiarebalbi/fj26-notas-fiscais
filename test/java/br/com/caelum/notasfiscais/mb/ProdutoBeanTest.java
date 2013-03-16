package br.com.caelum.notasfiscais.mb;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.notasfiscais.dao.DAO;
import br.com.caelum.notasfiscais.modelo.Produto;

/**
 * Classe para criação de teste referente ao manager bean {@link ProdutoBean}
 * 
 * @author Tiarê Balbi Bonamini
 * @date Feb 23, 2013
 * @package br.com.caelum.notasfiscais.mb
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProdutoBeanTest {
	
	
	private List<Produto> produtos;
	
	private Produto produto;
	
	private ProdutoBean bean;
	
	@Mock
	private DAO<Produto> dao;
	
	
	/**
	 * Inicializa componentes da class
	 */
	@Before
	public void init () {
		produtos = new ArrayList<Produto>();
		produto = new Produto();
		produto.setNome("Teste1");
		
		dao = mock(DAO.class);
		bean = new ProdutoBean();
		bean.setDao(dao);
		bean.setProduto(produto);
		
	}
	
	/**
	 * Verificando o test dao para o emma para de gritar :)
	 */
	@Test
	public void testeGetDao () {
		Assert.assertEquals(dao, bean.getDao());
	}
	
	/**
	 * Verificando o test produto para o emma para de gritar :)
	 */
	@Test
	public void testeGetProduto () {
		Assert.assertEquals(produto, bean.getProduto());
	}
	
	/**
	 * Verificando o test dao para o emma para de gritar :)
	 */
	@Test
	public void testeGetSomatoria () {
		Assert.assertEquals(new Double(0d), new Double(bean.getSomatoria()));
	}
	
	/**
	 * Verificando o test produto para o emma para de gritar :)
	 */
	@Test
	public void testeGetMensagem () {
		bean.setMensagem("Teste");
		Assert.assertEquals("Teste", bean.getMensagem());
	}
	
	/**
	 * Deve testar a consulta de todos os registros do {@link ProdutoBean#getProdutos()}
	 */
	@Test
	public void deveTestarConsultaDeRegistrosNoProdutoBean () {
		when(dao.listaTodos()).thenReturn(produtos);
		List<Produto> retorno = bean.getProdutos();
		verify(dao, times(1)).listaTodos();
		Assert.assertEquals(retorno.size(), produtos.size());
	}
	
	/**
	 * Deve testar a consulta de todos os registros do {@link ProdutoBean#getProdutos()}
	 */
	@Test
	public void deveConsultarRegistroSemRealizarNovaConsulta() {
		when(dao.listaTodos()).thenReturn(produtos);
		bean.setProdutos(produtos);
		List<Produto> retorno = bean.getProdutos();
		verify(dao, times(0)).listaTodos();
		Assert.assertEquals(retorno.size(), produtos.size());
	}
	
	/**
	 * Deve Salvar um novo produto {@link ProdutoBean#grava()}
	 */
	@Test
	public void deveSalvarUmNovoProduto () {
		produto.setNome("Nome");
		when(dao.adiciona(produto)).thenReturn(produto);
		bean.grava();
		Produto retorno = bean.getProduto();
		verify(dao, times(1)).adiciona(produto);
		Assert.assertEquals(retorno.getNome(), null);
		
	}
	
	/**
	 * Deve Atualizar um produto {@link ProdutoBean#grava()}
	 */
	@Test
	public void deveAtualizarUmNovoProduto () {
		produto.setId(new Long(2));
		when(dao.atualiza(produto)).thenReturn(produto);
		bean.setProduto(produto);
		bean.grava();
		verify(dao, times(1)).atualiza(produto);
		verify(dao, times(1)).listaTodos();
	}
	
	/**
	 * Deve excluir um novo registro
	 */
	@Test
	public void deveExcluirRegistro() {
		produtos = new ArrayList<Produto>();
		produtos.add(produto);
		Assert.assertEquals(1, produtos.size());
		doNothing().when(dao).remove(produto);
		bean.remove(produto);
		verify(dao, times(1)).remove(produto);
		Assert.assertEquals(0, bean.getProdutos().size());
	}
	
	/**
	 * Deve limpar o atributo produto
	 */
	@Test
	public void deveLimparProduto() {
		bean.setProduto(produto);
		bean.clean();
		Assert.assertEquals(null, bean.getProduto().getNome());
	}
}
