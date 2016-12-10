package br.com.marketedelivery.IDAO;

import br.com.marketedelivery.classesBasicas.Endereco;

public interface IEnderecoDAO extends IDAOGenerico<Endereco>
{
	public Endereco pesquisarCep(String cep);
	public Endereco pesquisarLogradouro(String logradouro);
}
