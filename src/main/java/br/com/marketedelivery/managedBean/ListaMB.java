package br.com.marketedelivery.managedBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Tipo;
import br.com.marketedelivery.classesBasicas.Usuario;

@SessionScoped
@ManagedBean
public class ListaMB
{
	public ListaDeCompras lista;

	private List<ListaDeCompras> listarTodos;

	private List<ListaDeCompras> listaFiltrados;

	private IFachada fachada;

	public ListaDeCompras getLista()
	{
		return lista;
	}

	public void setLista(ListaDeCompras lista)
	{
		this.lista = lista;
	}

	public List<ListaDeCompras> getListaFiltrados()
	{
		return listaFiltrados;
	}

	public void setListaFiltrados(List<ListaDeCompras> listaFiltrados)
	{
		this.listaFiltrados = listaFiltrados;
	}

	public void setListarTodos(List<ListaDeCompras> listarTodos)
	{
		this.listarTodos = listarTodos;
	}

	public List<ListaDeCompras> getListarTodos()
	{
		return listarTodasLista();
	}

	public IFachada getFachada()
	{
		if (fachada == null)
		{
			return fachada = new Fachada();
		}
		return fachada;
	}

	public List<ListaDeCompras> listarTodasLista()
	{
		Usuario temp = new Usuario();
		temp.setCodigo(LoginMB.codigoUsuario);
		Usuario user = getFachada().pesquisarPorCodigo(temp);
		listarTodos = getFachada().buscarListaPorUsuario(user);
		return listarTodos;
	}

	public void listarListas()
	{
		getTodasAsListas();
	}

	public List<ListaDeCompras> getTodasAsListas()
	{
		List<ListaDeCompras> lista = getFachada().listarTodasListas();
		return lista;
	}

	public void remover(ListaDeCompras lista)
	{
		try
		{
			if (lista == null)
				lista = this.lista;
			
			getFachada().removerLista(lista);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista removida com sucesso"));
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao remover sua lista, tente novamente mais tarde"));
		}
	}

	public Tipo[] getTipos()
	{
		return Tipo.values();
	}
}