package br.com.marketedelivery.controlador;

import java.util.List;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.IProdutoDAO;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.ProdutoPromocao;
import br.com.marketedelivery.classesBasicas.Supermercado;

public class ControladorProduto
{
	private IProdutoDAO produtoDAO;

	public void cadastrarProduto(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		produtoDAO.inserir(produto);
	}

	public void atualizarProduto(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		produtoDAO.alterar(produto);
	}

	public List<Produto> listarTodosProdutos()
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> lista = produtoDAO.consultarTodos();
		return lista;
	}

	public Produto pesquisarProdutoPorNome(Produto produto)
	{
		String nome = produto.getNome();
		produtoDAO = DAOFactory.getProdutoDAO();
		Produto p = produtoDAO.buscarPorNome(nome);
		return p;
	}

	public List<Produto> buscarProdutoPorSupermercado(Supermercado supermercado)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> lista = produtoDAO.buscarProdutoPorSupermercado(supermercado);
		return lista;
	}

	public Produto pesquisarProdutoPorTipo(Produto produto)
	{
		String tipo = produto.getNome();
		produtoDAO = DAOFactory.getProdutoDAO();
		Produto p = produtoDAO.buscarPorNome(tipo);
		return p;
	}

	public void removerProduto(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		produtoDAO.remover(produto);
	}
	
	public List<Produto> retornarProdutoPorNome(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> p = produtoDAO.retornarProdutoPorNome(produto.getNome());
		return p;
	}
	
	public List<Produto> retornarProdutoPorMarca(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> p = produtoDAO.retornarProdutoPorMarca(produto.getNome());
		return p;
	}
	
	public List<Produto> retornarProdutoPorTipo(Produto produto)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<Produto> p = produtoDAO.retornarProdutoPorTipo(produto.getNome());
		return p;
	}
	
	public List<ProdutoPromocao> listaProdutoPromocaoSupermercado(Supermercado s)
	{
		produtoDAO = DAOFactory.getProdutoDAO();
		List<ProdutoPromocao> p = produtoDAO.listaProdutoPromocaoSupermercado(s);
		return p;
	}
}