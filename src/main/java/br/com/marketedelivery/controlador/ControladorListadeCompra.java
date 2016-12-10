package br.com.marketedelivery.controlador;

import java.util.List;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.IListaDeComprasDAO;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Usuario;

public class ControladorListadeCompra
{
	IListaDeComprasDAO listaDAO;

	public void cadastrarLista(ListaDeCompras lista)
	{
		listaDAO = DAOFactory.getListaDAO();
		listaDAO.inserir(lista);
	}

	public void atualizarLista(ListaDeCompras lista)
	{
		listaDAO = DAOFactory.getListaDAO();
		listaDAO.alterar(lista);
	}

	public List<ListaDeCompras> listarTodasListas()
	{
		listaDAO = DAOFactory.getListaDAO();
		List<ListaDeCompras> lista = listaDAO.consultarTodos();
		return lista;
	}

	public ListaDeCompras buscarPorCodigo(ListaDeCompras lista)
	{
		int codigo = lista.getCodigo();
		listaDAO = DAOFactory.getListaDAO();
		ListaDeCompras retorno = listaDAO.consultarPorId(codigo);
		return retorno;
	}

	public List<ListaDeCompras> buscaListaPorUsuario(Usuario usuario)
	{
		listaDAO = DAOFactory.getListaDAO();
		List<ListaDeCompras> retorno = listaDAO.buscaListaPorUsuario(usuario);
		return retorno;
	}

	public void removerLista(ListaDeCompras lista)
	{
		listaDAO = DAOFactory.getListaDAO();
		listaDAO.remover(lista);
	}
}