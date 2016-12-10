package br.com.marketedelivery.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polyline;

import com.google.gson.Gson;

import br.com.marketedelivery.DAO.FacesUtil;
import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.ProdutoPromocao;
import br.com.marketedelivery.classesBasicas.Supermercado;

@ManagedBean(name = "supermercadoMB", eager = true)
@ApplicationScoped
public class SupermercadoMB extends AbstractMB implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int EARTH_RADIUS_KM = 6371;

	IFachada fachada;

	Supermercado supermercado;

	private Supermercado supermercadoSelecionado;

	private String nomeProduto;

	private List<Supermercado> listaSupermercados;

	private List<Supermercado> listaSupermercadosFiltrados;

	@SuppressWarnings("unused")
	private String retornaJsonComPosicaoUsuario;

	private List<Supermercado> converterListaSuper;

	private String latitude;

	private String longitude;

	MapModel mapModel;

	private String longPesquisa;

	private String latPesquisa;

	@PostConstruct
	public void init()
	{
		setLatPesquisa("-8.062845");
		setLongPesquisa("-34.871181");
	}

	public IFachada getFachada()
	{
		if (fachada == null)
		{
			return fachada = new Fachada();
		}
		return fachada;
	}

	public void setFachada(IFachada fachada)
	{
		this.fachada = fachada;
	}

	public List<Produto> listaProtudoDoSupermercado;

	public SupermercadoMB()
	{}

	public Supermercado getSupermecado()
	{
		if (supermercado == null)
		{
			supermercado = new Supermercado();
		}
		return supermercado;
	}

	public List<Supermercado> getListarTodosSupermercados()
	{
		converterListaSuper = new ArrayList<Supermercado>();
		converterListaSuper = getFachada().listarTodosSupermercados();
		return converterListaSuper;
	}

	public Supermercado getSupermercadoSelecionado()
	{
		return supermercadoSelecionado;
	}

	public void setSupermercadoSelecionado(Supermercado supermercadoSelecionado)
	{
		this.supermercadoSelecionado = supermercadoSelecionado;
	}

	public List<Supermercado> getListaSupermercadosFiltrados()
	{
		return listaSupermercadosFiltrados;
	}

	public void setListaSupermercadosFiltrados(List<Supermercado> listaSupermercadosFiltrados)
	{
		this.listaSupermercadosFiltrados = listaSupermercadosFiltrados;
	}

	// Responsï¿½vel em carregar uma lista de dados na tela do cadastro de
	// supermercados
	public void carregarPesquisa()
	{
		try
		{
			// fachada.ListarTodosSupermercados();
			// listaSupermercados = fachada.ListarTodosSupermercados();
			IFachada fachada = getFachada();
			listaSupermercados = fachada.listarTodosSupermercados();
		}
		catch (Exception ex)
		{
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os supermercados" + ex.getMessage());
		}
	}

	@PostConstruct
	public void carregarCadastro()
	{
		try
		{
			String valor = FacesUtil.getParam("supCod");
			// int codigo_pessoa = 0;
			if (valor != null)
			{
				// int codigo = Integer.parseInt(valor);
				supermercado = fachada.pesquisarPorCodigo(supermercado);
			}
		}
		catch (Exception ex)
		{
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os usuarios" + ex.getMessage());
		}
	}

	/**
	 * Metodo que faz a localizaï¿½ï¿½o por supermecado
	 * 
	 * @return mapModel
	 */
	public MapModel getLocalizacaoSurpermercado()
	{
		mapModel = new DefaultMapModel();
		Polyline polyline = new Polyline();
		if (getSupermercadoSelecionado() != null)
		{
			Double latitude = Double.parseDouble(getSupermercadoSelecionado().getLatitude());
			Double longitude = Double.parseDouble(getSupermercadoSelecionado().getLongitude());
			LatLng coord1 = new LatLng(latitude, longitude);
			polyline.getPaths().add(coord1);
			polyline.setStrokeColor("blue");
			polyline.setStrokeWeight(5);
			polyline.setStrokeOpacity(0.7);
			mapModel.addOverlay(new Marker(coord1, "Supermercado " + getSupermercadoSelecionado().getNome()));
			return mapModel;
		}
		return null;
	}

	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}

	public String getNomeProduto()
	{
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto)
	{
		this.nomeProduto = nomeProduto;
	}

	public List<Supermercado> getListaSupermercados()
	{
		return listaSupermercados;
	}

	public void setListaSupermercados(List<Supermercado> listaSupermercados)
	{
		this.listaSupermercados = listaSupermercados;
	}

	public MapModel getSupermencadoProximo()
	{
		fachada = getFachada();
		return fachada.getSupermencadoProximo();
	}

	public void onPointSelect()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String x = params.get("latitude");
		String xx = params.get("longitude");
		setLatitude(x);
		setLongitude(xx);
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getLongPesquisa()
	{
		mapModel = new DefaultMapModel();
		if (getSupermercadoSelecionado() != null)
		{
			Double longitude = Double.parseDouble(getSupermercadoSelecionado().getLongitude());
			return longPesquisa = longitude.toString();
		}
		return longPesquisa;
	}

	public void setLongPesquisa(String longPesquisa)
	{
		this.longPesquisa = longPesquisa;
	}

	public String getLatPesquisa()
	{
		mapModel = new DefaultMapModel();
		if (getSupermercadoSelecionado() != null)
		{
			Double latitude = Double.parseDouble(getSupermercadoSelecionado().getLatitude());
			return longPesquisa = latitude.toString();
		}
		return latPesquisa;
	}

	public void setLatPesquisa(String latPesquisa)
	{
		this.latPesquisa = latPesquisa;
	}

	public void setRetornaJsonComPosicaoUsuario(String retornaJsonComPosicaoUsuario)
	{
		this.retornaJsonComPosicaoUsuario = retornaJsonComPosicaoUsuario;
	}

	public String getRetornaJsonComPosicaoUsuario()
	{
		Gson json = new Gson();
		List<Supermercado> lista = new ArrayList<Supermercado>();
		lista = retornaSuperProxUser();
		return json.toJson(lista);
	}

	private List<Supermercado> retornaSuperProxUser()
	{
		List<Supermercado> lista = new ArrayList<Supermercado>();
		List<Supermercado> listaRetorno = new ArrayList<Supermercado>();
		lista = getListaSupermercados();
		double calculo = 0;
		double aux = 99999999;
		try
		{
			if (getLatitude() == null || getLongitude() == null)
			{
				displayErrorMessageToUser("não foi possivel localizar o usuario");
				return null;
			}
			for (int i = 0; i < lista.size(); i++)
			{
				// Converter de graus pra radianos das latitudes
				double firstLatToRad = Math.toRadians(Double.parseDouble(getLatitude()));
				double secondLatToRad = Math.toRadians((Double.parseDouble(lista.get(i).getLatitude())));
				// Diferença das longitudes
				double deltaLongitudeInRad = Math.toRadians(
						Double.parseDouble(lista.get(i).getLongitude()) - (Double.parseDouble(getLongitude())));
				// C⭣ula da distançia entre os pontos
				calculo = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad) * Math.cos(deltaLongitudeInRad)
						+ Math.sin(firstLatToRad) * Math.sin(secondLatToRad)) * EARTH_RADIUS_KM;
				if (calculo < aux)
				{
					listaRetorno.clear();
					aux = calculo;
					listaRetorno.add(lista.get(i));
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return listaRetorno;
	}
	
	public MapModel getRetornaTresSuperProximoUser(){
		 MapModel simpleModel = new DefaultMapModel();
		 if (getSupermercadoSelecionado() != null)
			{		 
				Double latitude = Double.parseDouble(getSupermercadoSelecionado().getLatitude());
				Double longitude = Double.parseDouble(getSupermercadoSelecionado().getLongitude());
				LatLng coord1 = new LatLng(latitude, longitude);
				simpleModel.addOverlay(new Marker(coord1, "Supermercado " + getSupermercadoSelecionado().getNome()));
				supermercadoSelecionado = null;
				return simpleModel;
			}
		List<Supermercado> lista = new ArrayList<Supermercado>();
		lista = getListaSupermercados();
		double calculo = 0 ;
		double aux  = 99999999; 
		double aux1 = 99999999; 
		double aux2 = 99999999;
		int auxPrim  = 0;
		int auxSegun = 0;
		int auxTerc  = 0;
		try{
			if(getLatitude() == null || getLongitude() ==  null){
			displayErrorMessageToUser("não foi possivel localizar o usuario");
				return null;
			}
		
		for(int i = 0; i < lista.size();i++){
		// Converter de graus pra radianos das latitudes
		double firstLatToRad = Math.toRadians(Double.parseDouble(getLatitude()));
		double secondLatToRad = Math.toRadians((Double.parseDouble(lista.get(i).getLatitude())));
		// Diferença das longitudes
		double deltaLongitudeInRad = Math.toRadians(Double.parseDouble(lista.get(i).getLongitude())
		- (Double.parseDouble(getLongitude())));
		// Calcula da distançia entre os pontos
		 calculo = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
		* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
		* Math.sin(secondLatToRad))
		* EARTH_RADIUS_KM;
		 if(calculo < aux){
			 auxPrim = i;
			 aux = calculo;
		} 
		}
		
		if(aux > 10.00){
			displayErrorMessageToUser("O senhor não tem nenhum supermercado proximo a menos de 10Km de onde vc esta!");
			return null;
		}
		LatLng coord1 = new LatLng(Double.parseDouble(lista.get(auxPrim).getLatitude()), Double.parseDouble(lista.get(auxPrim).getLongitude()));
		simpleModel.addOverlay(new Marker(coord1, "O Supermercado mais próximo do Senhor é o Supermercado " + lista.get(auxPrim).getNome()));
		
		for(int j = 0; j < lista.size();j++){
			double firstLatToRad = Math.toRadians(Double.parseDouble(getLatitude()));
			double secondLatToRad = Math.toRadians((Double.parseDouble(lista.get(j).getLatitude())));
			// Diferença das longitudes
			double deltaLongitudeInRad = Math.toRadians(Double.parseDouble(lista.get(j).getLongitude())
			- (Double.parseDouble(getLongitude())));
			// C⭣ula da distançia entre os pontos
			 calculo = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
			* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
			* Math.sin(secondLatToRad))
			* EARTH_RADIUS_KM;
			 if (calculo < aux1 && calculo != aux) {
				 auxSegun = j;
				 aux1 = calculo;
			}
			 
		}
		if(aux1 > 10.00){
			displayErrorMessageToUser("O senhor possui apenas 1 Supermercado proximo no raio de 10Km de onde esta!");
			return simpleModel;
		}
		LatLng coord2 = new LatLng(Double.parseDouble(lista.get(auxSegun).getLatitude()), Double.parseDouble(lista.get(auxSegun).getLongitude()));
		simpleModel.addOverlay(new Marker(coord2, "Segundo mais próximo do senhor é o Supermercado " + lista.get(auxSegun).getNome()));
		
		
		for(int j = 0; j < lista.size();j++){
			double firstLatToRad = Math.toRadians(Double.parseDouble(getLatitude()));
			double secondLatToRad = Math.toRadians((Double.parseDouble(lista.get(j).getLatitude())));
			// Diferença das longitudes
			double deltaLongitudeInRad = Math.toRadians(Double.parseDouble(lista.get(j).getLongitude())
			- (Double.parseDouble(getLongitude())));
			// C⭣ula da distançia entre os pontos
			 calculo = Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
			* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
			* Math.sin(secondLatToRad))
			* EARTH_RADIUS_KM;
			 if (calculo < aux2 && calculo != aux && calculo != aux1) {
				 auxTerc = j;
				 aux2 = calculo;
			}
		 
		}
		if(aux2 > 10.00){
			displayInfoMessageToUser("O senhor possui apenas 2 Supermercado proximo no raio de 10Km de onde esta!");
			return simpleModel;
		}
		LatLng coord3 = new LatLng(Double.parseDouble(lista.get(auxTerc).getLatitude()), Double.parseDouble(lista.get(auxTerc).getLongitude()));
		simpleModel.addOverlay(new Marker(coord3, "Terceiro mais próximo do senhor é o Supermercado " + lista.get(auxTerc).getNome()));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return simpleModel;
		
	}

		public List<ProdutoPromocao> getListaPromocao(){
			fachada = new Fachada();
			List<ProdutoPromocao> listaProm = new ArrayList<ProdutoPromocao>();
			if (getSupermercadoSelecionado() != null)
			{		 
				listaProm =fachada.listaProdutoPromocaoSupermercado(getSupermercadoSelecionado());
				return listaProm;
			}
			return null;
		}
}
