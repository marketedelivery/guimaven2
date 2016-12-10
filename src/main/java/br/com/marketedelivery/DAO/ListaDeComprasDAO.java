package br.com.marketedelivery.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IListaDeComprasDAO;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Usuario;



public class ListaDeComprasDAO extends DAOGenerico<ListaDeCompras> implements IListaDeComprasDAO
{
	private EntityManager manager;
    
    public ListaDeComprasDAO(EntityManager em)
    {
        super(em);
        this.manager = em;
    }
    public ListaDeCompras buscarPorCodigo(int codigo)
    {
        String consulta = "SELECT l FROM ListaDeCompras l WHERE l.codigo = :N";
        TypedQuery<ListaDeCompras> retorno = getEntityManager().createQuery(consulta, ListaDeCompras.class);
        retorno.setParameter("N", codigo);
        ListaDeCompras resultado;
        try {
            resultado = retorno.getSingleResult();
            return resultado;

        } catch (Exception e) {
            return null;

        }
        finally 
		{
			manager.close();
		}
    }
    
    public List<ListaDeCompras> buscaListaPorUsuario(Usuario usuario)
    {
        String consulta = "SELECT lista FROM ListaDeCompras lista WHERE lista.usuario.codigo = :N";
        TypedQuery<ListaDeCompras> retorno = getEntityManager().createQuery(consulta, ListaDeCompras.class);
        retorno.setParameter("N", usuario.getCodigo());
        List<ListaDeCompras> resultado;
        try {
            resultado = retorno.getResultList();
            return resultado;

        } catch (Exception e) {
            return null;

        }finally 
		{
			manager.close();
		}
    }
}