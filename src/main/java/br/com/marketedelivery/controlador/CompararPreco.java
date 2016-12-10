package br.com.marketedelivery.controlador;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.marketedelivery.classesBasicas.Produto;

public class CompararPreco
{
	public List<Produto> consultarTodosProdutosBompreco()
	{
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Bompreco_WS/rest/produto/bompreco/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>()
		{}.getType());
		return lista;
	}

	public List<Produto> consultarTodosProdutosCarrefour()
	{
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Carrefour_WS/rest/produto/carrefour/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>()
		{}.getType());
		return lista;
	}

	public List<Produto> consultarTodosProdutosExtra()
	{
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/Extra_WS/rest/produto/extra/consultarTodosProdutos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		List<Produto> lista = new ArrayList<Produto>();
		lista = gson.fromJson(json, new TypeToken<List<Produto>>()
		{}.getType());
		return lista;
	}

	// metodos que evita duas chamada no servidor
	@SuppressWarnings("unused")
	public List<Produto> trazerListaProdutoBompreco()
	{
		List<Produto> retorno = null;
		if (retorno == null)
		{
			retorno = new ArrayList<Produto>();
			retorno = consultarTodosProdutosBompreco();
			return retorno;
		}
		return retorno;
	}

	@SuppressWarnings("unused")
	public List<Produto> trazerListaProdutoCarrefour()
	{
		List<Produto> retorno = null;
		if (retorno == null)
		{
			retorno = new ArrayList<Produto>();
			retorno = consultarTodosProdutosBompreco();
			return retorno;
		}
		return retorno;
	}

	@SuppressWarnings("unused")
	public List<Produto> trazerListaProdutoExtra()
	{
		List<Produto> retorno = null;
		if (retorno == null)
		{
			retorno = new ArrayList<Produto>();
			retorno = consultarTodosProdutosBompreco();
			return retorno;
		}
		return retorno;
	}

	// metodos tr�s os produtos da lista que tem no estoque do supermecado
	public List<Produto> listaProdutoSuperBompreco(List<Produto> listProd)
	{
		List<Produto> produtoPrimeiro = new ArrayList<Produto>();
		produtoPrimeiro = trazerListaProdutoBompreco();
		produtoPrimeiro.retainAll(listProd);
		return produtoPrimeiro;
	}

	public List<Produto> listaProdutoSuperCarrefour(List<Produto> listProd)
	{
		List<Produto> produtoSegundo = new ArrayList<Produto>();
		produtoSegundo = trazerListaProdutoCarrefour();
		return produtoSegundo;
	}

	public List<Produto> listaProdutoSuperExtra(List<Produto> listProd)
	{
		List<Produto> produtoTerceiro = new ArrayList<Produto>();
		produtoTerceiro.retainAll(listProd);
		System.out.println(produtoTerceiro.toString());
		listProd.removeAll(produtoTerceiro);
		return produtoTerceiro;
	}

	// metodos tr�s os produtos da lista de nao tem no estoque do supermecado
	public List<Produto> intemEmFaltaSuperBompreco(List<Produto> listProd)
	{
		List<Produto> produtoIndispPrimeiro = new ArrayList<Produto>();
		produtoIndispPrimeiro = trazerListaProdutoBompreco();
		listProd.removeAll(produtoIndispPrimeiro);
		return listProd;
	}

	public List<Produto> intemEmFaltaSuperCerrefour(List<Produto> listProd)
	{
		List<Produto> produtoIndispSegundo = new ArrayList<Produto>();
		produtoIndispSegundo = trazerListaProdutoCarrefour();
		listProd.removeAll(produtoIndispSegundo);
		return listProd;
	}

	public List<Produto> intemEmFaltaSuperExtra(List<Produto> listProd)
	{
		List<Produto> produtoIndispTerceiro = new ArrayList<Produto>();
		produtoIndispTerceiro = trazerListaProdutoExtra();
		listProd.removeAll(produtoIndispTerceiro);
		return listProd;
	}
}