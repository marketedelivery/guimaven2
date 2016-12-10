package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private int codigo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ListaDeCompras lista;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Item> itens;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Item> itensDisponiveis;

	private String qtdDisponiveis;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Item> itensIndisponiveis;

	private String qtdIndisponiveis;

	@Column(length = 10, name = "valorTotalPedido")
	private double valorTotalPedido;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Supermercado supermercado;
	
	@Transient
	private String distancia;
	
	public Pedido()
	{
		super();
		this.lista = new ListaDeCompras();
		this.itens = new ArrayList<Item>();
		this.itensDisponiveis = new ArrayList<Item>();
		this.qtdDisponiveis = "";
		this.itensIndisponiveis = new ArrayList<Item>();
		this.qtdIndisponiveis = "";
		this.supermercado = new Supermercado();
	}

	/**
	 * @param codigo
	 * @param lista
	 * @param itens
	 * @param itensDisponiveis
	 * @param qtdDisponiveis
	 * @param itensIndisponiveis
	 * @param qtdIndisponiveis
	 * @param valorTotalPedido
	 * @param supermercado
	 */
	public Pedido(int codigo, ListaDeCompras lista, List<Item> itens, List<Item> itensDisponiveis,
			String qtdDisponiveis, List<Item> itensIndisponiveis, String qtdIndisponiveis, double valorTotalPedido,
			Supermercado supermercado)
	{
		super();
		this.codigo = codigo;
		this.lista = lista;
		this.itens = itens;
		this.itensDisponiveis = itensDisponiveis;
		this.qtdDisponiveis = qtdDisponiveis;
		this.itensIndisponiveis = itensIndisponiveis;
		this.qtdIndisponiveis = qtdIndisponiveis;
		this.valorTotalPedido = valorTotalPedido;
		this.supermercado = supermercado;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
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

	/**
	 * @return the itens
	 */
	public List<Item> getItens()
	{
		return itens;
	}

	/**
	 * @param itens
	 *            the itens to set
	 */
	public void setItens(List<Item> itens)
	{
		this.itens = itens;
	}

	/**
	 * @return the itensDisponiveis
	 */
	public List<Item> getItensDisponiveis()
	{
		return itensDisponiveis;
	}

	/**
	 * @param itensDisponiveis
	 *            the itensDisponiveis to set
	 */
	public void setItensDisponiveis(List<Item> itensDisponiveis)
	{
		this.itensDisponiveis = itensDisponiveis;
	}

	/**
	 * @return the qtdDisponiveis
	 */
	public String getQtdDisponiveis()
	{
		return qtdDisponiveis;
	}

	/**
	 * @param qtdDisponiveis
	 *            the qtdDisponiveis to set
	 */
	public void setQtdDisponiveis(String qtdDisponiveis)
	{
		this.qtdDisponiveis = qtdDisponiveis;
	}

	/**
	 * @return the itensIndisponiveis
	 */
	public List<Item> getItensIndisponiveis()
	{
		return itensIndisponiveis;
	}

	/**
	 * @param itensIndisponiveis
	 *            the itensIndisponiveis to set
	 */
	public void setItensIndisponiveis(List<Item> itensIndisponiveis)
	{
		this.itensIndisponiveis = itensIndisponiveis;
	}

	/**
	 * @return the qtdIndisponiveis
	 */
	public String getQtdIndisponiveis()
	{
		return qtdIndisponiveis;
	}

	/**
	 * @param qtdIndisponiveis
	 *            the qtdIndisponiveis to set
	 */
	public void setQtdIndisponiveis(String qtdIndisponiveis)
	{
		this.qtdIndisponiveis = qtdIndisponiveis;
	}

	/**
	 * @return the valorTotalPedido
	 */
	public double getValorTotalPedido()
	{
		return valorTotalPedido;
	}

	/**
	 * @param valorTotalPedido
	 *            the valorTotalPedido to set
	 */
	public void setValorTotalPedido(double valorTotalPedido)
	{
		this.valorTotalPedido = valorTotalPedido;
	}

	/**
	 * @return the supermercado
	 */
	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	/**
	 * @param supermercado
	 *            the supermercado to set
	 */
	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}
	
	/**
	 * 
	 * @return distancia
	 */
	public String getDistancia() {
		return distancia;
	}

	/**
	 * 
	 * @param distancia 
	 */
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
}