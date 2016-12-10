package br.com.marketedelivery.managedBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.Supermercado;
import br.com.marketedelivery.classesBasicas.Usuario;

@ManagedBean
@ViewScoped
public class ItemMB
{
	private Produto produto;

	private Item item;

	private ListaDeCompras listaCompras;

	private Usuario usuario;

	private List<Produto> listaProduto;

	private List<Produto> todosProdutos;

	private List<Produto> produtosFiltrados;

	private List<Item> listaItens;

	private ListaDeCompras lista;

	private List<Item> itensDialog;

	private IFachada fachada;

	private List<Supermercado> listaTodoSupermercado;

	@PostConstruct
	public void init()
	{
		listaProduto = new ArrayList<Produto>();
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public ListaDeCompras getListaCompras()
	{
		if (listaCompras == null)
		{
			return listaCompras = new ListaDeCompras();
		} else
		{
			return listaCompras;
		}
	}

	public void setListaCompras(ListaDeCompras listaCompras)
	{
		this.listaCompras = listaCompras;
	}

	public List<Item> getListaItens()
	{
		if (listaItens == null)
		{
			listaItens = new ArrayList<Item>();
		}
		return listaItens;
	}

	public void setListaItens(List<Item> listaItens)
	{
		this.listaItens = listaItens;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	public Produto getProduto()
	{
		if (produto == null)
		{
			return produto = new Produto();
		} else
		{
			return produto;
		}
	}

	public Item getItem()
	{
		if (item == null)
		{
			return item = new Item();
		} else
		{
			return item;
		}
	}

	public List<Produto> getListaProduto()
	{
		return listaProduto;
	}

	public IFachada getFachada()
	{
		if (fachada == null)
		{
			return fachada = new Fachada();
		} else
		{
			return fachada;
		}
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item)
	{
		this.item = item;
	}

	/**
	 * @param listaProduto
	 *            the listaProduto to set
	 */
	public void setListaProduto(List<Produto> listaProduto)
	{
		this.listaProduto = listaProduto;
	}

	/**
	 * @param todosProdutos
	 *            the todosProdutos to set
	 */
	public void setTodosProdutos(List<Produto> todosProdutos)
	{
		this.todosProdutos = todosProdutos;
	}

	/**
	 * @param produtosFiltrados
	 *            the produtosFiltrados to set
	 */
	public void setProdutosFiltrados(List<Produto> produtosFiltrados)
	{
		this.produtosFiltrados = produtosFiltrados;
	}

	/**
	 * @param listaTodoSupermercado
	 *            the listaTodoSupermercado to set
	 */
	public void setListaTodoSupermercado(List<Supermercado> listaTodoSupermercado)
	{
		this.listaTodoSupermercado = listaTodoSupermercado;
	}

	public List<Produto> getTodosProdutos()
	{
		return todosProdutos;
	}

	public List<Produto> getProdutosFiltrados()
	{
		return produtosFiltrados;
	}

	public List<Supermercado> getListaTodoSupermercado()
	{
		listaTodoSupermercado = getFachada().listarTodosSupermercados();
		return listaTodoSupermercado;
	}

	public List<Produto> getCarregarProduto()
	{
		List<Produto> listaProdutos = getFachada().listarTodosProdutos();
		return listaProdutos;
	}

	public void listar()
	{
		getCarregarProduto();
	}

	public void adicionar(Produto produto)
	{
		int podutoPosicao = -1;
		for (int i = 0; i < listaItens.size() && podutoPosicao < 0; i++)
		{
			Item itTemp = listaItens.get(i);
			if (itTemp.getProduto().getCodigo() == produto.getCodigo())
			{
				podutoPosicao = i;
			}
		}
		Item it = new Item();
		it.setProduto(produto);
		if (podutoPosicao < 0)
		{
			it.setQtdProduto(1);
			it.setPrecoTotal(it.getQtdProduto() * it.getProduto().getValorUnitario());
			listaItens.add(it);
		} else
		{
			Item temp = listaItens.get(podutoPosicao);
			it.setQtdProduto(temp.getQtdProduto() + 1);
			it.setPrecoTotal(produto.getValorUnitario() * it.getQtdProduto());
			listaItens.set(podutoPosicao, it); // substitui o valor q ja esta na
												// lista
		}

	}

	public void remover(Item item)
	{
		int produtoPosicao = -1;
		for (int i = 0; i < listaItens.size() && produtoPosicao < 0; i++)
		{
			Item itTemp = listaItens.get(i);
			if (itTemp.getProduto().getCodigo() == item.getProduto().getCodigo())
			{
				produtoPosicao = i;
			}
		}
		if(produtoPosicao > -1)
		{
		listaItens.remove(produtoPosicao);
		}
	}

	public String criarLista()
	{
		Usuario temp = new Usuario();
		temp.setCodigo(LoginMB.codigoUsuario);
		Usuario user = getFachada().pesquisarPorCodigo(temp);
		ListaDeCompras lista = getListaCompras();
		lista.setDataCriacao(new Date());
		lista.setNome(getListaCompras().getNome());
		lista.setQtd(listaItens.size());
		lista.setTipo(getListaCompras().getTipo());
		lista.setUsuario(user);
		getFachada().cadastrarLista(lista);
		for (int i = 0; i < listaItens.size(); i++)
		{
			Item it = listaItens.get(i);
			Item itTemp = new Item();
			itTemp.setLista(lista);
			itTemp.setProduto(it.getProduto());
			itTemp.setQtdProduto(it.getQtdProduto());
			double valorTotal = it.getQtdProduto() * it.getProduto().getValorUnitario();
			itTemp.setPrecoTotal(valorTotal);
			getFachada().cadastrarItem(itTemp);
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista cadastrada com sucesso"));
		listaCompras = new ListaDeCompras();
		listaItens = new ArrayList<Item>();
		return "/pages/protected/minhasListas.xhtml?faces-redirect=true";
	}

	/**
	 * @return the itensDialog
	 */
	// Add
	public List<Item> getItensDialog()
	{
		if (lista != null)
		{
			if (itensDialog == null || itensDialog.isEmpty())
			{
				itensDialog = getFachada().consultarItensPorLista(lista);
			} else
			{
				if (itensDialog.get(0).getLista().getCodigo() != lista.getCodigo())
				{
					itensDialog = getFachada().consultarItensPorLista(lista);
				}
			}
		}
		return itensDialog;
	}

	/**
	 * @param itensDialog
	 *            the itensDialog to set
	 */
	public void setItensDialog(List<Item> itensDialog)
	{
		this.itensDialog = itensDialog;
	}

	/**
	 * @return the lista
	 */
	public ListaDeCompras getLista()
	{
		return lista;
	}

	/**
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(ListaDeCompras lista)
	{
		this.lista = lista;
	}

	public void removerItem(Item item)
	{
		for (int i = 0; i < itensDialog.size(); i++)
		{
			Item it = itensDialog.get(i);
			if (item.getProduto().equals(it.getProduto()))
			{
				getFachada().removerProdutoItem(it);
				itensDialog.remove(i);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(String.valueOf(item.getQtdProduto()) + " " + item.getProduto().getNome()
								+ " \n" + item.getProduto().getTipo() + " " + item.getProduto().getMarca() + "\n"
								+ "no valor de " + item.getPrecoTotal() + " foi excluido com sucesso."));
				it.getLista().setQtd(itensDialog.size());
				getFachada().atualizarLista(item.getLista());
				break;
			}
		}
	}

	public void alterarLista(ListaDeCompras lista)
	{
		if (lista == null)
		{
			lista = this.lista;
		}
		try
		{
			for (int i = 0; i < itensDialog.size(); i++)
			{
				Item it = itensDialog.get(i);
				getFachada().atualizarItem(it);
			}
			lista.setQtd(itensDialog.size());
			getFachada().atualizarLista(lista);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista alterada com sucesso"));
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao alterar sua lista, tente novamente mais tarde"));
		}
	}

	public void removerLista(ListaDeCompras lista)
	{
		try
		{
			if (lista == null) lista = this.lista;
			if (itensDialog == null || itensDialog.isEmpty()) itensDialog = getFachada().consultarItensPorLista(lista);
			if (itensDialog.size() != 0)
			{
				if (itensDialog.get(0).getLista().getCodigo() != lista.getCodigo())
				{
					itensDialog = getFachada().consultarItensPorLista(lista);
				}
				if (!itensDialog.isEmpty())
				{
					for (int i = 0; i < itensDialog.size(); i++)
					{
						Item it = itensDialog.get(i);
						getFachada().removerProdutoItem(it);
					}
				}
			}
			getFachada().removerLista(lista);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Lista removida com sucesso"));
		}
		catch (Exception e)
		{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao remover sua lista, tente novamente mais tarde"));
		}
	}
}