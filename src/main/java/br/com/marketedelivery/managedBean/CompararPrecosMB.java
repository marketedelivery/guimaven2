package br.com.marketedelivery.managedBean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Pedido;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.Supermercado;

@ManagedBean
@ViewScoped
public class CompararPrecosMB {
	// Atributos
	private static int EARTH_RADIUS_KM = 6371;
	
	private Pedido pedidoSelecionado;

	private List<Pedido> pedidos;

	private List<Pedido> pedidosFiltrados;

	private Pedido pedidoExtra;

	private Pedido pedidoCarrefour;

	private Pedido pedidoBompreco;

	private IFachada fachada;

	private ListaDeCompras lista;

	private List<Item> itens;

	private List<Item> itensDisponiveisExtra;

	private String percentualItensDisponiveisExtra;

	private List<Item> itensIndisponiveisExtra;

	private String percentualItensIndisponiveisExtra;

	private double valorTotalListaExtra;

	private List<Item> itensDisponiveisCarrefour;

	private String percentualItensDisponiveisCarrefour;

	private List<Item> itensIndisponiveisCarrefour;

	private String percentualItensIndisponiveisCarrefour;

	private double valorTotalListaCarrefour;

	private List<Item> itensDisponiveisBompreco;

	private String percentualItensDisponiveisBompreco;

	private List<Item> itensIndisponiveisBompreco;

	private String percentualItensIndisponiveisBompreco;

	private double valorTotalListaBompreco;

	private String latitude;
	private String longitude;
	@PostConstruct
	public void init() {
		itens = new ArrayList<Item>();
		itensDisponiveisExtra = new ArrayList<Item>();
		itensIndisponiveisExtra = new ArrayList<Item>();
		itensDisponiveisCarrefour = new ArrayList<Item>();
		itensIndisponiveisCarrefour = new ArrayList<Item>();
		itensDisponiveisBompreco = new ArrayList<Item>();
		itensIndisponiveisBompreco = new ArrayList<Item>();
		pedidos = new ArrayList<Pedido>();
		pedidoExtra = new Pedido();
		pedidoCarrefour = new Pedido();
		pedidoBompreco = new Pedido();
	}

	// Métodos
	public Produto pesquisarProdutoComParametrosExtra(String nome, String tipo, String marca) {
		Client c = Client.create();
		String resource = "http://localhost:8080/Extra_WS/rest/produto/extra/pesquisarProdutoComParametros/" + nome
				+ ", " + tipo + ", " + marca;
		resource = resource.replaceAll(" ", "%20");
		WebResource wr = c.resource(resource);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}

	public Produto pesquisarProdutoComParametrosCarrefour(String nome, String tipo, String marca) {
		Client c = Client.create();
		String resource = "http://localhost:8080/Carrefour_WS/rest/produto/carrefour/pesquisarProdutoComParametros/"
				+ nome + ", " + tipo + ", " + marca;
		resource = resource.replaceAll(" ", "%20");
		WebResource wr = c.resource(resource);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}

	public Produto pesquisarProdutoComParametrosBompreco(String nome, String tipo, String marca) {
		Client c = Client.create();
		String resource = "http://localhost:8080/Bompreco_WS/rest/produto/bompreco/pesquisarProdutoComParametros/"
				+ nome + ", " + tipo + ", " + marca;
		resource = resource.replaceAll(" ", "%20");
		WebResource wr = c.resource(resource);
		String json = wr.get(String.class);
		Gson gson = new Gson();
		Produto p = gson.fromJson(json, new TypeToken<Produto>() {
		}.getType());
		return p;
	}

	public void compararPrecosProdutos() {
		limparValores();
		if (itens.isEmpty())
			itens.addAll(getItens());
		if (!itens.isEmpty()) {
			List<Produto> produtosExtra = new ArrayList<Produto>();
			List<Produto> produtosCarrefour = new ArrayList<Produto>();
			List<Produto> produtosBompreco = new ArrayList<Produto>();
			for (int i = 0; i < itens.size(); i++) {
				Item it = itens.get(i);
				produtosExtra.add(pesquisarProdutoComParametrosExtra(it.getProduto().getNome(),
						it.getProduto().getTipo(), it.getProduto().getMarca()));
				if (produtosExtra.get(i) != null && !produtosExtra.get(i).getNome().equals("")
						&& produtosExtra.get(i).getQtdEstoque() != 0) {
					valorTotalListaExtra += it.getPrecoTotal();
					itensDisponiveisExtra.add(it);
				} else {
					itensIndisponiveisExtra.add(it);
				}
				produtosCarrefour.add(pesquisarProdutoComParametrosCarrefour(it.getProduto().getNome(),
						it.getProduto().getTipo(), it.getProduto().getMarca()));
				if (produtosCarrefour.get(i) != null && !produtosCarrefour.get(i).getNome().equals("")
						&& produtosCarrefour.get(i).getQtdEstoque() != 0) {
					valorTotalListaCarrefour += it.getPrecoTotal();
					itensDisponiveisCarrefour.add(it);
				} else {
					itensIndisponiveisCarrefour.add(it);
				}
				produtosBompreco.add(pesquisarProdutoComParametrosBompreco(it.getProduto().getNome(),
						it.getProduto().getTipo(), it.getProduto().getMarca()));
				if (produtosBompreco.get(i) != null && !produtosBompreco.get(i).getNome().equals("")
						&& produtosBompreco.get(i).getQtdEstoque() != 0) {
					valorTotalListaBompreco += it.getPrecoTotal();
					itensDisponiveisBompreco.add(it);
				} else {
					itensIndisponiveisBompreco.add(it);
				}
			}
			String recuperaValor = null;

			DecimalFormat dfExtra = new DecimalFormat("0.##");
			String dxExtra = dfExtra.format(valorTotalListaExtra);
			recuperaValor = dxExtra.replace(",", ".");
			valorTotalListaExtra = Double.parseDouble(recuperaValor);

			DecimalFormat dfCarrefour = new DecimalFormat("0.##");
			String dxCarrefour = dfCarrefour.format(valorTotalListaCarrefour);
			recuperaValor = dxCarrefour.replace(",", ".");
			valorTotalListaCarrefour = Double.parseDouble(recuperaValor);

			DecimalFormat dfBompreco = new DecimalFormat("0.##");
			String dxBompreco = dfBompreco.format(valorTotalListaCarrefour);
			recuperaValor = dxBompreco.replace(",", ".");
			valorTotalListaCarrefour = Double.parseDouble(recuperaValor);
		
		}
	}

	public void limparValores() {
		// Itens
		if (!itens.isEmpty())
			itens.clear();
		// Itens Disponiveis
		this.itensDisponiveisExtra.clear();
		this.itensDisponiveisCarrefour.clear();
		this.itensDisponiveisBompreco.clear();
		// Itens Indisponiveis
		this.itensIndisponiveisExtra.clear();
		this.itensIndisponiveisCarrefour.clear();
		this.itensIndisponiveisBompreco.clear();
		// Pedidos
		this.pedidoExtra = new Pedido();
		this.pedidoCarrefour = new Pedido();
		this.pedidoBompreco = new Pedido();
		// Percentuais Disponiveis
		this.percentualItensDisponiveisExtra = "";
		this.percentualItensDisponiveisCarrefour = "";
		this.percentualItensDisponiveisBompreco = "";
		// Percentuais Indisponiveis
		this.percentualItensIndisponiveisExtra = "";
		this.percentualItensIndisponiveisCarrefour = "";
		this.percentualItensIndisponiveisBompreco = "";
	}
	// Gets e Sets

	public List<Item> getItens() {
		if (lista != null) {
			itens = getFachada().consultarItensPorLista(lista);
		}
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public List<Item> getItensDisponiveisExtra() {
		return itensDisponiveisExtra;
	}

	public void setItensDisponiveisExtra(List<Item> itensDisponiveisExtra) {
		this.itensDisponiveisExtra = itensDisponiveisExtra;
	}

	public List<Item> getItensIndisponiveisExtra() {
		return itensIndisponiveisExtra;
	}

	public void setItensIndisponiveisExtra(List<Item> itensIndisponiveisExtra) {
		this.itensIndisponiveisExtra = itensIndisponiveisExtra;
	}

	public double getValorTotalListaExtra() {
		return valorTotalListaExtra;
	}

	public void setValorTotalListaExtra(double valorTotalListaExtra) {
		this.valorTotalListaExtra = valorTotalListaExtra;
	}

	public List<Item> getItensDisponiveisCarrefour() {
		return itensDisponiveisCarrefour;
	}

	public void setItensDisponiveisCarrefour(List<Item> itensDisponiveisCarrefour) {
		this.itensDisponiveisCarrefour = itensDisponiveisCarrefour;
	}

	public List<Item> getItensIndisponiveisCarrefour() {
		return itensIndisponiveisCarrefour;
	}

	public void setItensIndisponiveisCarrefour(List<Item> itensIndisponiveisCarrefour) {
		this.itensIndisponiveisCarrefour = itensIndisponiveisCarrefour;
	}

	public double getValorTotalListaCarrefour() {
		return valorTotalListaCarrefour;
	}

	public void setValorTotalListaCarrefour(double valorTotalListaCarrefour) {
		this.valorTotalListaCarrefour = valorTotalListaCarrefour;
	}

	public List<Item> getItensDisponiveisBompreco() {
		return itensDisponiveisBompreco;
	}

	public void setItensDisponiveisBompreco(List<Item> itensDisponiveisBompreco) {
		this.itensDisponiveisBompreco = itensDisponiveisBompreco;
	}

	public List<Item> getItensIndisponiveisBompreco() {
		return itensIndisponiveisBompreco;
	}

	public void setItensIndisponiveisBompreco(List<Item> itensIndisponiveisBompreco) {
		this.itensIndisponiveisBompreco = itensIndisponiveisBompreco;
	}

	public double getValorTotalListaBompreco() {
		return valorTotalListaBompreco;
	}

	public void setValorTotalListaBompreco(double valorTotalListaBompreco) {
		this.valorTotalListaBompreco = valorTotalListaBompreco;
	}

	public ListaDeCompras getLista() {
		return lista;
	}

	public void setLista(ListaDeCompras lista) {
		this.lista = lista;
	}

	public IFachada getFachada() {
		if (fachada == null) {
			return fachada = new Fachada();
		} else {
			return fachada;
		}
	}

	public String getPercentualItensDisponiveisExtra() {
		double resultado = 0;
		if (!itensDisponiveisExtra.isEmpty()) {
			double todo = itens.size();
			double parte = itensDisponiveisExtra.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfExtra = new DecimalFormat("0.##");
		percentualItensDisponiveisExtra = dfExtra.format(resultado);
		return percentualItensDisponiveisExtra;
	}

	public void setPercentualItensDisponiveisExtra(String percentualItensDisponiveisExtra) {
		this.percentualItensDisponiveisExtra = percentualItensDisponiveisExtra;
	}

	public String getPercentualItensIndisponiveisExtra() {
		double resultado = 0;
		if (!itensIndisponiveisExtra.isEmpty()) {
			double todo = itens.size();
			double parte = itensIndisponiveisExtra.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfExtra = new DecimalFormat("0.##");
		percentualItensIndisponiveisExtra = dfExtra.format(resultado);
		return percentualItensIndisponiveisExtra;
	}

	public void setPercentualItensIndisponiveisExtra(String percentualItensIndisponiveisExtra) {
		this.percentualItensIndisponiveisExtra = percentualItensIndisponiveisExtra;
	}

	public String getPercentualItensDisponiveisCarrefour() {
		double resultado = 0;
		if (!itensDisponiveisCarrefour.isEmpty()) {
			double todo = itens.size();
			double parte = itensDisponiveisCarrefour.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfCarrrefour = new DecimalFormat("0.##");
		percentualItensDisponiveisCarrefour = dfCarrrefour.format(resultado);
		return percentualItensDisponiveisCarrefour;
	}

	public void setPercentualItensDisponiveisCarrefour(String percentualItensDisponiveisCarrefour) {
		this.percentualItensDisponiveisCarrefour = percentualItensDisponiveisCarrefour;
	}

	public String getPercentualItensIndisponiveisCarrefour() {
		double resultado = 0;
		if (!itensIndisponiveisCarrefour.isEmpty()) {
			double todo = itens.size();
			double parte = itensIndisponiveisCarrefour.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfCarrrefour = new DecimalFormat("0.##");
		percentualItensIndisponiveisCarrefour = dfCarrrefour.format(resultado);
		return percentualItensIndisponiveisCarrefour;
	}

	public void setPercentualItensIndisponiveisCarrefour(String percentualItensIndisponiveisCarrefour) {
		this.percentualItensIndisponiveisCarrefour = percentualItensIndisponiveisCarrefour;
	}

	public String getPercentualItensDisponiveisBompreco() {
		double resultado = 0;
		if (!itensDisponiveisBompreco.isEmpty()) {
			double todo = itens.size();
			double parte = itensDisponiveisBompreco.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfBompreco = new DecimalFormat("0.##");
		percentualItensDisponiveisBompreco = dfBompreco.format(resultado);
		return percentualItensDisponiveisBompreco;
	}

	public void setPercentualItensDisponiveisBompreco(String percentualItensDisponiveisBompreco) {
		this.percentualItensDisponiveisBompreco = percentualItensDisponiveisBompreco;
	}

	public String getPercentualItensIndisponiveisBompreco() {
		double resultado = 0;
		if (!itensIndisponiveisBompreco.isEmpty()) {
			double todo = itens.size();
			double parte = itensIndisponiveisBompreco.size();
			if (todo != 0 && parte != 0) {
				resultado = (parte / todo) * 100;
			}
		}
		DecimalFormat dfBompreco = new DecimalFormat("0.##");
		percentualItensIndisponiveisBompreco = dfBompreco.format(resultado);
		return percentualItensIndisponiveisBompreco;
	}

	/**
	 * @return the pedidoExtra
	 */
	public Pedido getPedidoExtra() {
		pedidoExtra.setItens(itens);
		pedidoExtra.setItensDisponiveis(itensDisponiveisExtra);
		pedidoExtra.setItensIndisponiveis(itensIndisponiveisExtra);
		pedidoExtra.setLista(lista);
		pedidoExtra.setValorTotalPedido(valorTotalListaExtra);
		pedidoExtra.setQtdDisponiveis(getPercentualItensDisponiveisExtra());
		pedidoExtra.setQtdIndisponiveis(getPercentualItensIndisponiveisExtra());
		return pedidoExtra;
	}

	public void setPedidoExtra(Pedido pedidoExtra) {
		this.pedidoExtra = pedidoExtra;
	}

	public Pedido getPedidoCarrefour() {
		pedidoCarrefour.setItens(itens);
		pedidoCarrefour.setItensDisponiveis(itensDisponiveisCarrefour);
		pedidoCarrefour.setItensIndisponiveis(itensIndisponiveisCarrefour);
		pedidoCarrefour.setLista(lista);
		pedidoCarrefour.setValorTotalPedido(valorTotalListaCarrefour);
		pedidoCarrefour.setQtdDisponiveis(getPercentualItensDisponiveisCarrefour());
		pedidoCarrefour.setQtdIndisponiveis(getPercentualItensIndisponiveisCarrefour());
		return pedidoCarrefour;
	}

	public void setPedidoCarrefour(Pedido pedidoCarrefour) {
		this.pedidoCarrefour = pedidoCarrefour;
	}

	public Pedido getPedidoBompreco() {
		pedidoBompreco.setItens(itens);
		pedidoBompreco.setItensDisponiveis(itensDisponiveisBompreco);
		pedidoBompreco.setItensIndisponiveis(itensIndisponiveisBompreco);
		pedidoBompreco.setLista(lista);
		pedidoBompreco.setValorTotalPedido(valorTotalListaBompreco);
		pedidoBompreco.setQtdDisponiveis(getPercentualItensDisponiveisBompreco());
		pedidoBompreco.setQtdIndisponiveis(getPercentualItensIndisponiveisBompreco());
		return pedidoBompreco;
	}

	public void setPedidoBompreco(Pedido pedidoBompreco) {
		this.pedidoBompreco = pedidoBompreco;
	}

	public void setPercentualItensIndisponiveisBompreco(String percentualItensIndisponiveisBompreco) {
		this.percentualItensIndisponiveisBompreco = percentualItensIndisponiveisBompreco;
	}

	public List<Pedido> getPedidos() {
		if (pedidos.isEmpty()) {
			if (itens.isEmpty())
				itens.addAll(getItens());
			if (!itens.isEmpty()) {
				exibirComparacaoPrecos();
			}
		} else if (pedidos.get(0).getLista().getCodigo() != lista.getCodigo()) {
			exibirComparacaoPrecos();
		}
		return pedidos;
	}

	public void exibirComparacaoPrecos() {
		pedidos.clear();
		compararPrecosProdutos();
		this.pedidos.add(getPedidoExtra());
		this.pedidos.add(getPedidoCarrefour());
		this.pedidos.add(getPedidoBompreco());
		List<Supermercado> supermercados = getFachada().listarTodosSupermercados();
		for (int i = 0; i < pedidos.size(); i++) {
			Pedido p = pedidos.get(i);
			Supermercado s;
			if (supermercados.isEmpty()) {
				s = new Supermercado();
			} else {
				
				s = supermercados.get(i);
			}
			double firstLatToRad = Math.toRadians(Double.parseDouble(s.getLatitude()));
			double secondLatToRad = Math.toRadians((Double.parseDouble(getLatitude())));
			// Diferença das longitudes
			double deltaLongitudeInRad = Math.toRadians(Double.parseDouble(s.getLongitude())
			- (Double.parseDouble(getLongitude())));
			// C⭣ula da distançia entre os pontos
			double calculo = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
			* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
			* Math.sin(secondLatToRad))
			* EARTH_RADIUS_KM;
			DecimalFormat dfCalculo = new DecimalFormat("0.##");
			p.setDistancia(dfCalculo.format(calculo));
			p.setSupermercado(s);
		}
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedidoSelecionado() {
		if (pedidoSelecionado == null) {
			pedidoSelecionado = new Pedido();
		}
		return pedidoSelecionado;
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
		this.pedidosFiltrados = pedidosFiltrados;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public void pointSelect()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String x = params.get("latitude");
		String xx = params.get("longitude");
		setLatitude(x);
		setLongitude(xx);
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}