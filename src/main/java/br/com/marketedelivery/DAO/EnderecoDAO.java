package br.com.marketedelivery.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IEnderecoDAO;
import br.com.marketedelivery.classesBasicas.Endereco;

public class EnderecoDAO extends DAOGenerico<Endereco> implements IEnderecoDAO{
	
	private EntityManager manager;
	
	public EnderecoDAO(EntityManager em) 
	{
		super(em);
		this.manager = em;
	}
	
	public Endereco pesquisarCep(String cep)
	{
		String consulta = "SELECT e FROM endereco e WHERE e.cep = :N";
		TypedQuery<Endereco> retorno = getEntityManager().createQuery(consulta, Endereco.class);
        retorno.setParameter("N", cep);
        Endereco resultado;
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
	
	public Endereco pesquisarLogradouro(String logradouro)
	{
		String consulta = "SELECT e FROM endereco e WHERE e.logradouro = :N";
		TypedQuery<Endereco> retorno = getEntityManager().createQuery(consulta, Endereco.class);
        retorno.setParameter("N", logradouro);
        Endereco resultado;
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