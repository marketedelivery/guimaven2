package br.com.marketedelivery.IDAO;

import java.util.List;

import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Usuario;

public interface IListaDeComprasDAO extends IDAOGenerico<ListaDeCompras> 
{
	public ListaDeCompras buscarPorCodigo(int codigo);
	public List<ListaDeCompras> buscaListaPorUsuario(Usuario usuario);
}
