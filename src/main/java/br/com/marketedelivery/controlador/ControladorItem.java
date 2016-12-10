package br.com.marketedelivery.controlador;

import java.util.List;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.IItemDAO;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Produto;

public class ControladorItem
{
	private IItemDAO itemDAO;

	public void cadastrarItem(Item item)
	{
		itemDAO = DAOFactory.getItemDAO();
		itemDAO.inserir(item);
	}

	public void atualizarItem(Item item)
	{
		itemDAO = DAOFactory.getItemDAO();
		itemDAO.alterar(item);
	}

	public List<Item> listarTodosItens()
	{
		itemDAO = DAOFactory.getItemDAO();
		List<Item> lista = itemDAO.consultarTodos();
		return lista;
	}

	public void removerProdutoItem(Item item)
	{
		itemDAO = DAOFactory.getItemDAO();
		itemDAO.remover(item);
	}

	public Item buscarItemProduto(Produto produto)
	{
		itemDAO = DAOFactory.getItemDAO();
		Item i = itemDAO.buscarItemProduto(produto);
		return i;
	}

	public List<Item> consultarItensPorLista(ListaDeCompras lista)
	{
		itemDAO = DAOFactory.getItemDAO();
		List<Item> list = itemDAO.consultarItensPorLista(lista);
		return list;
	}
}