package br.com.marketedelivery.DAOFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.marketedelivery.DAO.EnderecoDAO;
import br.com.marketedelivery.DAO.ItemDAO;
import br.com.marketedelivery.DAO.ListaDeComprasDAO;
import br.com.marketedelivery.DAO.PagamentoDAO;
import br.com.marketedelivery.DAO.PedidoDAO;
import br.com.marketedelivery.DAO.ProdutoDAO;
import br.com.marketedelivery.DAO.SupermercadoDAO;
import br.com.marketedelivery.DAO.UsuarioDAO;
import br.com.marketedelivery.IDAO.IEnderecoDAO;
import br.com.marketedelivery.IDAO.IItemDAO;
import br.com.marketedelivery.IDAO.IListaDeComprasDAO;
import br.com.marketedelivery.IDAO.IPagamentoDAO;
import br.com.marketedelivery.IDAO.IPedidoDAO;
import br.com.marketedelivery.IDAO.IProdutoDAO;
import br.com.marketedelivery.IDAO.ISupermercadoDAO;
import br.com.marketedelivery.IDAO.IUsuarioDAO;

public abstract class DAOFactory
{
	// Atributos
	private static EntityManagerFactory factory;

	public static IUsuarioDAO usuarioDAO;

	public static ISupermercadoDAO supermercadoDAO;

	public static IListaDeComprasDAO listaDAO;

	public static IEnderecoDAO enderecoDAO;

	public static IItemDAO itemDAO;

	public static IPagamentoDAO pagamentoDAO;

	public static IPedidoDAO pedidoDAO;

	public static IProdutoDAO produtoDAO;
	// Construtores
	static
	{
		factory = Persistence.createEntityManagerFactory("bd_market");
	}

	// Métodos
	public static IUsuarioDAO getUsuarioDAO()
	{
		usuarioDAO = new UsuarioDAO(factory.createEntityManager());
		return usuarioDAO;
	}

	public static ISupermercadoDAO getSupermercadoDAO()
	{
		supermercadoDAO = new SupermercadoDAO(factory.createEntityManager());
		return supermercadoDAO;
	}

	public static IListaDeComprasDAO getListaDAO()
	{
		listaDAO = new ListaDeComprasDAO(factory.createEntityManager());
		return listaDAO;
	}

	public static IEnderecoDAO getEnderecoDAO()
	{
		enderecoDAO = new EnderecoDAO(factory.createEntityManager());
		return enderecoDAO;
	}

	public static IItemDAO getItemDAO()
	{
		itemDAO = new ItemDAO(factory.createEntityManager());
		return itemDAO;
	}

	public static IPagamentoDAO getPagamentoDAO()
	{
		pagamentoDAO = new PagamentoDAO(factory.createEntityManager());
		return pagamentoDAO;
	}

	public static IPedidoDAO getPedidoDAO()
	{
		pedidoDAO = new PedidoDAO(factory.createEntityManager());
		return pedidoDAO;
	}

	public static IProdutoDAO getProdutoDAO()
	{
		produtoDAO = new ProdutoDAO(factory.createEntityManager());
		return produtoDAO;
	}

	public static void conectar()
	{
		factory = Persistence.createEntityManagerFactory("bd_market");
	}

	public static void close()
	{
		if (factory != null && factory.isOpen())
		{
			factory.close();
		}
	}
}