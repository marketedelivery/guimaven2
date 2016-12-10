package br.com.marketedelivery.IDAO;

import java.util.List;

import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.ProdutoPromocao;
import br.com.marketedelivery.classesBasicas.Supermercado;

public interface IProdutoDAO extends IDAOGenerico<Produto> 
{
	
	public Produto buscarPorNome(String nome);

	public List<Produto> buscarProdutoPorSupermercado(Supermercado supermercado);

	public List<Produto> retornarProdutoPorNome(String nome);
	
	public List<Produto> retornarProdutoPorMarca(String marca);
	
	public List<Produto> retornarProdutoPorTipo(String tipo);
	
	public List<ProdutoPromocao> listaProdutoPromocaoSupermercado(Supermercado s);
}
