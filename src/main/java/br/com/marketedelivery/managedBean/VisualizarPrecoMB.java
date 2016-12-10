package br.com.marketedelivery.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.marketedelivery.classesBasicas.Produto;

@ManagedBean
@ViewScoped
public class VisualizarPrecoMB {
	private List<Produto> listatemp;
	Produto produto;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListatemp() 
	{
		if(listatemp == null)
		{
			listatemp = new ArrayList<Produto>();
		}else
		if(produto != null)
		{
			listatemp.clear();
			listatemp.addAll(pesquisarProdutoPorNome(produto));
		}
		return listatemp;
	}

	public void visualizarPreco(ActionEvent evento) {
		produto = (Produto) evento.getComponent().getAttributes().get("produto");
		listatemp = pesquisarProdutoPorNome(produto);
	}

	public List<Produto> pesquisarProdutoPorNome(Produto produto) {
		
		List<Produto>listaTemporaria = new ArrayList<Produto>();
		
		Produto temp1 = pesquisarProdutoComParametrosBompreco(produto.getCodigo());
		Produto temp2 = pesquisarProdutoComParametrosCarrefour(produto.getCodigo());
		Produto temp3 = pesquisarProdutoComParametrosExtra(produto.getCodigo());
		
		listaTemporaria.add(temp1);
		listaTemporaria.add(temp2);
		listaTemporaria.add(temp3);
		return listaTemporaria;

	}

	public Produto pesquisarProdutoComParametrosExtra(int produtoID) {
		Client c = Client.create();
		WebResource wr = 
				c.resource("http://localhost:8080/Extra_WS/rest/produto/extra/consultarProdutoPorId/"
				 + produtoID);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}

	public Produto pesquisarProdutoComParametrosCarrefour(int produtoID) {
		Client c = Client.create();
		WebResource wr = c
				.resource("http://localhost:8080/Carrefour_WS/rest/produto/carrefour/consultarProdutoPorId/"
						+ produtoID);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}

	public Produto pesquisarProdutoComParametrosBompreco(int produtoID) {
		Client c = Client.create();
		WebResource wr = c
				.resource("http://localhost:8080/Bompreco_WS/rest/produto/bompreco/consultarProdutoPorId/"
						+ produtoID);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}
}