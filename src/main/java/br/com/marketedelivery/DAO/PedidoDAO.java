package br.com.marketedelivery.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IPedidoDAO;
import br.com.marketedelivery.classesBasicas.Pedido;

public class PedidoDAO extends DAOGenerico<Pedido> implements IPedidoDAO
{
	
	private EntityManager manager;
    
    public PedidoDAO(EntityManager em)
    {
        super(em);
        this.manager = em;
    }
    public Pedido buscarPorCodigo(int codigo)
    {
        String consulta = "SELECT s FROM Supermercado s WHERE s.nome = :N";
        TypedQuery<Pedido> retorno = getEntityManager().createQuery(consulta, Pedido.class);
        retorno.setParameter("N", codigo);
        Pedido resultado;
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
}