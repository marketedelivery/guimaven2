package br.com.marketedelivery.DAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marketedelivery.IDAO.IUsuarioDAO;
import br.com.marketedelivery.classesBasicas.Usuario;

public class UsuarioDAO extends DAOGenerico<Usuario> implements IUsuarioDAO
{

	private EntityManager manager;


	public UsuarioDAO(EntityManager em) {
		super(em);
        this.manager = em;

	}
	public Usuario buscarPorEmail(String email) {
        String consulta = "SELECT u FROM Usuario u WHERE u.email = :N";
        TypedQuery<Usuario> retorno = getEntityManager().createQuery(consulta, Usuario.class);
        retorno.setParameter("N", email);
        Usuario resultado;
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
	
	public Usuario buscarUsuarioPorCPF(String cpf)
    {
        String consulta = "SELECT c FROM Usuario c WHERE c.cpf = :N";
        TypedQuery<Usuario> retorno = getEntityManager().createQuery(consulta, Usuario.class);
        retorno.setParameter("N", cpf);
        Usuario resultado;
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
	
	public Usuario buscarUsuarioPorNome(String nome)
    {
        String consulta = "SELECT c FROM Usuario c WHERE c.nome = :N";
        TypedQuery<Usuario> retorno = getEntityManager().createQuery(consulta, Usuario.class);
        retorno.setParameter("N", nome);
        Usuario resultado;
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
	@Override
	public Usuario pesquisarPorCodigo(int codigo) {
		return null;
	}
	@Override
	public Usuario buscarUsuarioCodigoFacebook(long idFacebook)
    {
        String consulta = "SELECT c FROM Usuario c WHERE c.codigoFacebook = :N";
        TypedQuery<Usuario> retorno = getEntityManager().createQuery(consulta, Usuario.class);
        retorno.setParameter("N", idFacebook);
        Usuario resultado;
        try {
            resultado = retorno.getSingleResult();
            return resultado;

        } catch (Exception e) {
            return null;

        }finally 
        {
        	manager.close();
		}
    } 
}

