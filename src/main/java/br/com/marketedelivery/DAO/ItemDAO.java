package br.com.marketedelivery.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IItemDAO;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Produto;

public class ItemDAO extends DAOGenerico<Item> implements IItemDAO
{
	private EntityManager manager;
	
	public ItemDAO(EntityManager em)
	{
		super(em);
		this.manager = em;
	}

	public Item buscarItemProduto(Produto produto)
	{
		int codigo = produto.getCodigo();
		String consulta = "SELECT i FROM Item i WHERE i.produto.codigo = :N";
		TypedQuery<Item> retorno = getEntityManager().createQuery(consulta, Item.class);
		retorno.setParameter("N", codigo);
		Item resultado;
		try
		{
			resultado = retorno.getSingleResult();
			return resultado;
		}
		catch (Exception e)
		{
			return null;
		}
		finally 
		{
			manager.close();
		}
	}

	@Override
	public List<Item> consultarItensPorLista(ListaDeCompras lista)
	{
		String consulta = "SELECT i FROM Item i WHERE i.lista.codigo = :codigo";
		TypedQuery<Item> retorno = getEntityManager().createQuery(consulta, Item.class);
		retorno.setParameter("codigo", lista.getCodigo());
		try
		{
			List<Item> resultado = retorno.getResultList();
			return resultado;
		}
		catch (Exception e)
		{
			return null;
		}
		finally
		{
			manager.close();
		}
	}
}