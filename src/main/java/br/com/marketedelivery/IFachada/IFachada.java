package br.com.marketedelivery.IFachada;

import java.util.List;

import org.primefaces.model.map.MapModel;

import br.com.marketedelivery.classesBasicas.Endereco;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Pagamento;
// import br.com.marketedelivery.classesBasicas.Perfil;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.ProdutoPromocao;
import br.com.marketedelivery.classesBasicas.Supermercado;
import br.com.marketedelivery.classesBasicas.Usuario;

public interface IFachada
{
	public void cadastrarUsuario(Usuario usuario);

	public void atualizarUsuario(Usuario usuario);

	public List<Usuario> listarTodosUsuarios();

	public Usuario listarPorNome(Usuario usuario);

	public Usuario listarPorCPF(Usuario usuario);

	public Usuario pesquisarPorCodigo(Usuario usuario);

	public Usuario pesquisarPorEmail(Usuario usuario);

	public Usuario efetuarLogin(Usuario usuario);

	public boolean alteraSenha(Usuario u);
	// --------------------------------------------- Supermercado
	// -------------------------------------------------------------------

	public void cadastrarSupermercado(Supermercado supermercado);

	public void atualizarSupermercado(Supermercado supermercado);

	public List<Supermercado> listarTodosSupermercados();

	public Supermercado pesquisarPorCodigo(Supermercado supermercado);

	public Supermercado listarPorNome(Supermercado supermercado);

	public List<Produto> listarProdutosDoSupermercado(String nomeProduto, String supermercado);

	public MapModel getSupermencadoProximo();

	public Supermercado consultarPorID(Supermercado supermercado);
	// --------------------------------------------- Endereco
	// -------------------------------------------------------------------

	public void cadastrarEndereco(Endereco endereco);

	public void atualizarEndereco(Endereco endereco);

	public List<Endereco> listarEndereco();

	public Endereco listarPorCep(Endereco endereco);

	public Endereco listarPorLogradouro(Endereco endereco);

	// --------------------------------------------- Lista de Compra
	// -------------------------------------------------------------------
	public void cadastrarLista(ListaDeCompras lista);

	public void atualizarLista(ListaDeCompras lista);

	public List<ListaDeCompras> listarTodasListas();

	public ListaDeCompras buscarPorCodigo(ListaDeCompras lista);

	public List<ListaDeCompras> buscarListaPorUsuario(Usuario usuario);

	public void removerLista(ListaDeCompras lista);
	
	// --------------------------------------------- Produto
	// -------------------------------------------------------------------
	public void cadastrarProduto(Produto produto);

	public void atualizarProduto(Produto produto);

	public List<Produto> listarTodosProdutos();

	public Produto pesquisarProdutoPorNome(Produto produto);

	public Produto pesquisarPorCodigo(Produto produto);

	public Produto pesquisarProdutoPorTipo(Produto produto);

	public List<Produto> buscarProdutoPorSupermercado(Supermercado supermercado);

	public void removerProduto(Produto produto);
	
	public List<Produto> retornarProdutoPorNome(Produto produto);
	
	public List<Produto> retornarProdutoPorMarca(Produto produto);
	
	public List<Produto> retornarProdutoPorTipo(Produto produto);
	
	public List<ProdutoPromocao> listaProdutoPromocaoSupermercado(Supermercado s);
	
	// --------------------------------------------- Pagamento
	// -------------------------------------------------------------------
	public void cadastrarPagamento(Pagamento pagamento);

	public void atualizarPagamento(Pagamento pagamento);

	public List<Pagamento> listarTodosPagamentos();

	public Pagamento listarPorCodigo(Pagamento pagamento);

	// --------------------------------------------- Item
	// -------------------------------------------------------------------
	public void cadastrarItem(Item item);

	public void atualizarItem(Item item);

	public List<Item> listarTodosItens();

	public void removerProdutoItem(Item item);

	public Item buscarItemProduto(Produto produto);

	public List<Item> consultarItensPorLista(ListaDeCompras lista);

	// Login facebook -----//
	Usuario authFacebookLogin() throws Exception;

	void codificar(String codigo, String methodType) throws Exception;
}
