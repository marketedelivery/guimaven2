package br.com.marketedelivery.controlador;

import java.util.List;
import java.util.Random;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.IUsuarioDAO;
import br.com.marketedelivery.classesBasicas.Usuario;

public class ControladorUsuario
{
	private IUsuarioDAO usuarioDAO;

	public void cadastrarUsuario(Usuario usuario)
	{
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.inserir(usuario);
	}

	public void atualizarUsuario(Usuario usuario)
	{
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.alterar(usuario);
	}

	public List<Usuario> listarTodosUsuarios()
	{
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		List<Usuario> lista = usuarioDAO.consultarTodos();
		return lista;
	}

	public Usuario listarPorCPF(Usuario usuario)
	{
		String cpf = usuario.getCpf();
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario retorno = usuarioDAO.buscarUsuarioPorCPF(cpf);
		return retorno;
	}

	public Usuario listarPorNome(Usuario usuario)
	{
		String nome = usuario.getNome();
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario retorno = usuarioDAO.buscarUsuarioPorNome(nome);
		return retorno;
	}

	public Usuario pesquisarUsuarioPorCodigo(Usuario usuario)
	{
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario cp = usuarioDAO.consultarPorId(usuario.getCodigo());
		return cp;
	}

	public Usuario pesquisarPorEmail(Usuario usuario)
	{
		String email = usuario.getEmail();
		// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario retorno = usuarioDAO.buscarPorEmail(email);
		return retorno;
	}

	/**
	 * metodo que recupera a senha do usu�rio
	 * 
	 * @return String
	 */
	public String geraSenha()
	{
		Random gerador = new Random();
		StringBuilder bilder = new StringBuilder();
		// imprime sequ�ncia de 10 n�meros inteiros aleat�rios
		for (int i = 0; i < 9; i++)
		{
			// System.out.println(gerador.nextInt(9));
			bilder.append(Integer.toString(gerador.nextInt(10)));
		}
		return bilder.toString();
	}

	/**
	 * metodo que altera a senha do usuario
	 * 
	 * @param u
	 * @return boolean
	 */
	public boolean alteraSenha(Usuario u)
	{
		boolean resultado = false;
		// Usuario usuario = new Usuario();
		// usuario = pesquisarPorEmail(u);
		if (u != null)
		{
			String senhaGerada = geraSenha();
			u.setSenha(senhaGerada);
			// usuarioDAO = DAOFactoryUsuario.getUsuarioDAO();
			usuarioDAO = DAOFactory.getUsuarioDAO();
			usuarioDAO.alterar(u);
			resultado = true;
		}
		resultado = false;
		return resultado;
	}
}