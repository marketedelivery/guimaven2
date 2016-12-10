package br.com.marketedelivery.controlador;

import java.util.List;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.ISupermercadoDAO;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.Supermercado;

public class ControladorSupermercado
{
	private ISupermercadoDAO supermercadoDAO;

	public void cadastrarSupermercado(Supermercado supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		supermercadoDAO.inserir(supermercado);
	}

	public void atualizarSupermercado(Supermercado supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		
	}

	public List<Supermercado> listarTodosSupermercados()
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		List<Supermercado> lista = supermercadoDAO.consultarTodos();
		return lista;
	}

	public Supermercado pesquisarPorNome(Supermercado supermercado)
	{
		// supermercadoDAO = DAOFactorySupermercado.getSupermercadoDAO();
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		String nome = supermercado.getNome();
		Supermercado retorno = supermercadoDAO.buscarPorNome(nome);
		return retorno;
	}

	public Supermercado pesquisarPorCodigo(Supermercado supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado sup = supermercadoDAO.consultarPorId(supermercado.getCodigo());
		return sup;
	}

	public List<Produto> listarProdutosDoSupermercado(String nomeProduto, String supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		return null;
	}

	public Supermercado consultarPorID(Supermercado supermercado)
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		Supermercado s = supermercadoDAO.consultarPorId(supermercado.getCodigo());
		return s;
	}

	/**
	 * 
	 * @return retorna MapModel
	 */
	public MapModel supermencadoProximo()
	{
		supermercadoDAO = DAOFactory.getSupermercadoDAO();
		try
		{
			MapModel mapModel = new DefaultMapModel();
			List<Supermercado> lista = supermercadoDAO.consultarTodos();
			if (lista != null)
			{
				for (int i = 0; i < lista.size(); i++)
				{
					Double latitude = Double.parseDouble(lista.get(i).getLatitude());
					Double logitude = Double.parseDouble(lista.get(i).getLongitude());
					LatLng latLng = new LatLng(latitude, logitude);
					mapModel.addOverlay(new Marker(latLng, "Supermercado " + lista.get(i).getNome()));
					latLng = null;
				}
			}
			return mapModel;
		}
		catch (Exception e)
		{
			e.getMessage();
			return null;
		}
	}
}