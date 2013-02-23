package br.com.caelum.notasfiscais.mb;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

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
		dao = mock(DAO.class);
		bean = new ProdutoBean();
		bean.setDao(dao);
		
	}
	
	/**
	 * Verificando o test dao para o emma para de gritar :)
	 */
	@Test
	public void testeGetDao () {
		Assert.assertEquals(dao, bean.getDao());
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

}
