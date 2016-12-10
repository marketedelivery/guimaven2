package br.com.marketedelivery.controlador;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.Produto;

public class ControladorCompararPreco {
	private List<Produto> listaDeProdutoBompreco;
	private List<Produto> listaDeProdutoCarrefour;
	private List<Produto> listaDeProdutoExtra;

	public List<Produto> getListaDeProdutoBompreco() {
		if (listaDeProdutoBompreco == null) {
			listaDeProdutoBompreco = new ArrayList<Produto>();
			listaDeProdutoBompreco = consultarTodosProdutosBompreco();
		}
		return listaDeProdutoBompreco;
	}

	public void setListaDeProdutoBompreco(List<Produto> listaDeProdutoBompreco) {
		this.listaDeProdutoBompreco = listaDeProdutoBompreco;
	}

	public List<Produto> getListaDeProdutoCarrefour() {
		if (listaDeProdutoCarrefour == null) {
			listaDeProdutoCarrefour = new ArrayList<Produto>();
			listaDeProdutoCarrefour = consultarTodosProdutosCarrefour();
		}
		return listaDeProdutoCarrefour;
	}

	public void setListaDeProdutoCarrefour(List<Produto> listaDeProdutoCarrefour) {
		this.listaDeProdutoCarrefour = listaDeProdutoCarrefour;
	}

	public List<Produto> getListaDeProdutoExtra() {
		if (listaDeProdutoExtra == null) {
			listaDeProdutoExtra = new ArrayList<Produto>();
			listaDeProdutoExtra = consultarTodosProdutosExtra();
		}
		return listaDeProdutoExtra;
	}

	public void setListaDeProdutoExtra(List<Produto> listaDeProdutoExtra) {
		this.listaDeProdutoExtra = listaDeProdutoExtra;
	}

	public List<Produto> consultarTodosProdutosBompreco() {
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Bompreco_WS/rest/produto/bompreco/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>() {
		}.getType());
		return lista;
	}

	public List<Produto> consultarTodosProdutosCarrefour() {
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Carrefour_WS/rest/produto/carrefour/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>() {
		}.getType());
		return lista;
	}

	public List<Produto> consultarTodosProdutosExtra() {
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Extra_WS/rest/produto/extra/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>() {
		}.getType());
		return lista;
	}

	//// metodos tr�s os produtos da lista que tem no estoque do supermecado
	//// ////
	public List<Produto> listaProdutoSuperBompreco(List<Item> lisItem) {
		List<Produto> produtoBomprecoRetorno = new ArrayList<Produto>();
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = passaListaItemParaListaProduto(lisItem);
		produtoBomprecoRetorno = getListaDeProdutoBompreco();
		listaProduto.retainAll(produtoBomprecoRetorno);
		return listaProduto;
	}

	public List<Produto> listaProdutoSuperCarrefour(List<Item> lisItem) {
		List<Produto> produtoCarrefourRetorno = new ArrayList<Produto>();
		List<Produto> produtoCarrefour = new ArrayList<Produto>();
		produtoCarrefour = passaListaItemParaListaProduto(lisItem);
		produtoCarrefourRetorno = getListaDeProdutoCarrefour();
		produtoCarrefour.retainAll(produtoCarrefourRetorno);
		return produtoCarrefour;
	}

	public List<Produto> listaProdutoSuperExtra(List<Item> lisItem) {
		List<Produto> produtoExtraRetorno = new ArrayList<Produto>();
		List<Produto> produtoExtra = new ArrayList<Produto>();
		produtoExtra = passaListaItemParaListaProduto(lisItem);
		produtoExtraRetorno = getListaDeProdutoExtra();
		produtoExtra.retainAll(produtoExtraRetorno);

		// System.out.println(produtoExtra.toString());

		return produtoExtra;
	}

	/**
	 * metodo recebe uma lista de item e retorna um lista produtos indisponiveis
	 * no supermercado bompre�o
	 * 
	 * @param listProd
	 * @return listaProdIndisnivel
	 */
	public List<Produto> intemEmFaltaSuperBompreco(List<Item> lisItem) {
		List<Produto> listaProduto = new ArrayList<Produto>();
		List<Produto> listaProdIndisnivel = new ArrayList<Produto>();
		listaProduto = getListaDeProdutoBompreco();
		listaProdIndisnivel = passaListaItemParaListaProduto(lisItem);
		listaProdIndisnivel.removeAll(listaProduto);
		return listaProdIndisnivel;
	}

	/**
	 * metodo recebe uma lista de item e retorna um lista produtos indisponiveis
	 * no supermercado carrefour
	 * 
	 * @param listProd
	 * @return listaProdIndisnivel
	 */
	public List<Produto> intemEmFaltaSuperCarrefour(List<Item> lisItem) {
		List<Produto> listaProduto = new ArrayList<Produto>();
		List<Produto> listaProdIndisnivel = new ArrayList<Produto>();
		listaProduto = getListaDeProdutoCarrefour();
		listaProdIndisnivel = passaListaItemParaListaProduto(lisItem);
		listaProdIndisnivel.removeAll(listaProduto);
		return listaProdIndisnivel;
	}

	/**
	 * metodo recebe uma lista de item e retorna um lista produtos indisponiveis
	 * no supermercado extra
	 * 
	 * @param listProd
	 * @return listaProdIndisnivel
	 */
	public List<Produto> intemEmFaltaSuperExtra(List<Item> lisItem) {
		List<Produto> listaProduto = new ArrayList<Produto>();
		List<Produto> listaProdIndisnivel = new ArrayList<Produto>();
		listaProduto = getListaDeProdutoExtra();
		listaProdIndisnivel = passaListaItemParaListaProduto(lisItem);
		listaProdIndisnivel.removeAll(listaProduto);
		return listaProdIndisnivel;
	}

	/**
	 * recebe a lista de item e retorna o valor total dos itens disponivel no
	 * supermercado
	 * 
	 * @param lisItem
	 * @return valorTotalBompreco
	 */
	public double retornaValorListaBompreco(List<Item> lisItem) {
		double valorTotalBompreco = 0;
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = passaListaItemParaListaProduto(lisItem);
		List<Produto> retornoProduto = new ArrayList<Produto>();
		retornoProduto = listaProdutoSuperBompreco(lisItem);
		for (int i = 0; i < lisItem.size(); i++) {

			for (int j = 0; j < retornoProduto.size(); j++) {
				if (listaProduto.get(i).getCodigo() == retornoProduto.get(j).getCodigo()) {
					valorTotalBompreco += lisItem.get(i).getQtdProduto() * retornoProduto.get(j).getValorUnitario();
				}

			}
		}
		return valorTotalBompreco;
	}

	/**
	 * recebe a lista de item e retorna o valor total dos itens disponivel no
	 * supermercado
	 * 
	 * @param lisItem
	 * @return valorTotalCarrafour
	 */
	public double retornaValorListaCarrefour(List<Item> lisItem) {
		double valorTotalCarrefour = 0;
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = passaListaItemParaListaProduto(lisItem);
		List<Produto> retornoProduto = new ArrayList<Produto>();
		retornoProduto = listaProdutoSuperCarrefour(lisItem);
		for (int i = 0; i < lisItem.size(); i++) {
			for (int j = 0; j < retornoProduto.size(); j++) {
				if (listaProduto.get(i).getCodigo() == retornoProduto.get(j).getCodigo()) {
					valorTotalCarrefour = +lisItem.get(i).getQtdProduto() * retornoProduto.get(j).getValorUnitario();
				}
			}
			// return valorTotalSuperPrimeiro;
		}
		return valorTotalCarrefour;
	}

	/**
	 * recebe a lista de item e retorna o valor total dos itens disponivel no
	 * supermercado
	 * 
	 * @param lisItem
	 * @return valorTotalExtra
	 */
	public double retornaValorListaExtra(List<Item> lisItem) {
		double valorTotalExtra = 0;
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = passaListaItemParaListaProduto(lisItem);
		List<Produto> retornProduto = new ArrayList<Produto>();
		retornProduto = listaProdutoSuperCarrefour(lisItem);
		for (int i = 0; i < lisItem.size(); i++) {
			for (int j = 0; j < retornProduto.size(); j++) {
				if (listaProduto.get(i).getCodigo() == retornProduto.get(j).getCodigo()) {
					valorTotalExtra = +lisItem.get(i).getQtdProduto() * retornProduto.get(j).getValorUnitario();
				}
			}

		}
		return valorTotalExtra;
	}

	/**
	 * passa os produtos da lista de item para uma lista de produtos
	 * 
	 * @param lisItem
	 * @return
	 */
	public List<Produto> passaListaItemParaListaProduto(List<Item> lisItem) {
		List<Produto> listaProduto = new ArrayList<Produto>();
		for (int i = 0; i < lisItem.size(); i++) {
			listaProduto.add(lisItem.get(i).getProduto());
		}
		return listaProduto;

	}
}