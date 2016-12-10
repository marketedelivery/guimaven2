package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tb_item")
public class Item implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private int codigo;

	@OneToOne
	private Produto produto;

	@Column(name = "precoTotal", length = 12)
	private Double precoTotal;

	@Column(name = "qtdProduto", length = 12)
	private int qtdProduto;

	@ManyToOne(cascade=CascadeType.REFRESH, fetch = FetchType.EAGER)
	@Fetch(FetchMode.JOIN)
	private ListaDeCompras lista;

	public Item()
	{
		super();
		lista = new ListaDeCompras();
	}

	public Item(int codigo, Produto produto, Double precoTotal, int qtdProduto, ListaDeCompras lista)
	{
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.precoTotal = precoTotal;
		this.qtdProduto = qtdProduto;
		this.lista = lista;
	}

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}

	public Double getPrecoTotal()
	{
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal)
	{
		this.precoTotal = precoTotal;
	}

	public int getQtdProduto()
	{
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto)
	{
		this.qtdProduto = qtdProduto;
	}

	public ListaDeCompras getLista()
	{
		return lista;
	}

	public void setLista(ListaDeCompras lista)
	{
		this.lista = lista;
	}
}
