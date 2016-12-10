package br.com.marketedelivery.IDAO;

import java.util.List;

import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Produto;

public interface IItemDAO extends IDAOGenerico<Item>
{
	public Item buscarItemProduto(Produto produto);
	public List<Item> consultarItensPorLista(ListaDeCompras lista);
}