package br.com.marketedelivery.IDAO;

import br.com.marketedelivery.classesBasicas.Usuario;

public interface IUsuarioDAO extends IDAOGenerico<Usuario> {

	public Usuario buscarPorEmail(String email);

	public Usuario buscarUsuarioPorCPF(String cpf);

	public Usuario buscarUsuarioPorNome(String nome);

	public Usuario pesquisarPorCodigo (int codigo);
	
	public Usuario buscarUsuarioCodigoFacebook(long idFacebook);
}
