package br.com.marketedelivery.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IPagamentoDAO;
import br.com.marketedelivery.classesBasicas.Pagamento;

public class PagamentoDAO extends DAOGenerico<Pagamento> implements IPagamentoDAO 
{
	
	private EntityManager manager;
    
    public PagamentoDAO(EntityManager em)
    {
        super(em);
        this.manager = em;
    }
    
    public Pagamento buscarPorStatus(Pagamento pagamento)
    {
        String consulta = "SELECT p FROM Pagamento s WHERE p.status = :N";
        TypedQuery<Pagamento> retorno = getEntityManager().createQuery(consulta, Pagamento.class);
        retorno.setParameter("N", pagamento.getStatus());
        Pagamento resultado;
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
