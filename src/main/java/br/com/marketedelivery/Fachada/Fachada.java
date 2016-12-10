package br.com.marketedelivery.Fachada;

import java.util.List;

import org.primefaces.model.map.MapModel;

import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Endereco;
import br.com.marketedelivery.classesBasicas.Item;
import br.com.marketedelivery.classesBasicas.ListaDeCompras;
import br.com.marketedelivery.classesBasicas.Pagamento;
import br.com.marketedelivery.classesBasicas.Pedido;
import br.com.marketedelivery.classesBasicas.Perfil;
// import br.com.marketedelivery.classesBasicas.Perfil;
import br.com.marketedelivery.classesBasicas.Produto;
import br.com.marketedelivery.classesBasicas.ProdutoPromocao;
import br.com.marketedelivery.classesBasicas.Supermercado;
import br.com.marketedelivery.classesBasicas.Usuario;
import br.com.marketedelivery.controlador.ControladorEndereco;
import br.com.marketedelivery.controlador.ControladorItem;
import br.com.marketedelivery.controlador.ControladorListadeCompra;
import br.com.marketedelivery.controlador.ControladorLoginFacebook;
import br.com.marketedelivery.controlador.ControladorPagamento;
import br.com.marketedelivery.controlador.ControladorPedido;
// import br.com.marketedelivery.controlador.ControladorPerfil;
import br.com.marketedelivery.controlador.ControladorProduto;
import br.com.marketedelivery.controlador.ControladorSupermercado;
import br.com.marketedelivery.controlador.ControladorUsuario;

public class Fachada implements IFachada
{
	private IFachada instancia;

	private ControladorEndereco conEndereco;

	private ControladorSupermercado conSupermercado;

	private ControladorUsuario conUsuario;

	private ControladorListadeCompra conLista;

	private ControladorPagamento conPagamento;

	private ControladorProduto conProduto;

	private ControladorItem conItem;

	private ControladorPedido conPedido;

	private ControladorLoginFacebook loginFacebook;

	public IFachada getInstancia()
	{
		if (instancia == null)
		{
			instancia = new Fachada();
		}
		return instancia;
	}

	public Fachada()
	{
		conEndereco = new ControladorEndereco();
		conSupermercado = new ControladorSupermercado();
		conUsuario = new ControladorUsuario();
		conLista = new ControladorListadeCompra();
		conPagamento = new ControladorPagamento();
		// conPerfil = new ControladorPerfil();
		conProduto = new ControladorProduto();
		conItem = new ControladorItem();
		conPedido = new ControladorPedido();
		loginFacebook = new ControladorLoginFacebook();
	}

	public void cadastrarUsuario(Usuario usuario)
	{
		conUsuario.cadastrarUsuario(usuario);
	}

	public void atualizarUsuario(Usuario usuario)
	{
		conUsuario.atualizarUsuario(usuario);
	}

	public List<Usuario> listarTodosUsuarios()
	{
		return conUsuario.listarTodosUsuarios();
	}

	public Usuario listarPorNome(Usuario usuario)
	{
		return conUsuario.listarPorNome(usuario);
	}

	public Usuario listarPorCPF(Usuario usuario)
	{
		return conUsuario.listarPorCPF(usuario);
	}

	@Override
	public Usuario pesquisarPorCodigo(Usuario usuario)
	{
		return conUsuario.pesquisarUsuarioPorCodigo(usuario);
	}

	public Usuario pesquisarPorEmail(Usuario usuario)
	{
		return conUsuario.pesquisarPorEmail(usuario);
	}

	public Usuario efetuarLogin(Usuario usuario)
	{
		// conUsuario.efetuarLogin;
		return null;
	}

	public boolean alteraSenha(Usuario u)
	{
		return conUsuario.alteraSenha(u);
	}
	// --------------------------------------------- Supermercado
	// -------------------------------------------------------------------

	public void cadastrarSupermercado(Supermercado supermercado)
	{
		conSupermercado.cadastrarSupermercado(supermercado);
	}

	public void atualizarSupermercado(Supermercado supermercado)
	{
		conSupermercado.atualizarSupermercado(supermercado);
	}

	public List<Supermercado> listarSupermercado()
	{
		return conSupermercado.listarTodosSupermercados();
	}

	public Supermercado listarPorNome(Supermercado supermercado)
	{
		return conSupermercado.pesquisarPorNome(supermercado);
	}

	public Supermercado consultarPorID(Supermercado supermercado)
	{
		return conSupermercado.consultarPorID(supermercado);
	}

	public List<Produto> listarProdutosDoSupermercado(String nomeProduto, String supermercado)
	{
		return null;
	}

	@Override
	public Produto pesquisarPorCodigo(Produto produto)
	{
		return null;
	}

	@Override
	public List<Supermercado> listarTodosSupermercados()
	{
		return conSupermercado.listarTodosSupermercados();
	}

	@Override
	public Supermercado pesquisarPorCodigo(Supermercado supermercado)
	{
		return conSupermercado.pesquisarPorCodigo(supermercado);
	}
	
	public List<Produto> retornarProdutoPorNome(Produto produto)
	{
		return conProduto.retornarProdutoPorNome(produto);
	}
	
	public List<Produto> retornarProdutoPorMarca(Produto produto)
	{
		return conProduto.retornarProdutoPorMarca(produto);
	}
	
	public List<Produto> retornarProdutoPorTipo(Produto produto)
	{
		return conProduto.retornarProdutoPorTipo(produto);
	}

	// --------------------------------------------- Endereco
	// -------------------------------------------------------------------
	public void cadastrarEndereco(Endereco endereco)
	{
		conEndereco.cadastrarEndereco(endereco);
	}

	public void atualizarEndereco(Endereco endereco)
	{
		conEndereco.atualizarEndereco(endereco);
	}

	public List<Endereco> listarEndereco()
	{
		return conEndereco.listarTodosEnderecos();
	}

	public Endereco listarPorCep(Endereco endereco)
	{
		return conEndereco.pesquisarPorCep(endereco);
	}

	public Endereco listarPorLogradouro(Endereco endereco)
	{
		return conEndereco.pesquisarPorLogradouro(endereco);
	}

	// --------------------------------------------- Lista de Compra
	// -------------------------------------------------------------------
	public void cadastrarLista(ListaDeCompras lista)
	{
		conLista.cadastrarLista(lista);
	}

	public void atualizarLista(ListaDeCompras lista)
	{
		conLista.atualizarLista(lista);
	}

	public List<ListaDeCompras> listarTodasListas()
	{
		return conLista.listarTodasListas();
	}

	public ListaDeCompras buscarPorCodigo(ListaDeCompras lista)
	{
		return conLista.buscarPorCodigo(lista);
	}

	public List<ListaDeCompras> buscarListaPorUsuario(Usuario usuario)
	{
		return conLista.buscaListaPorUsuario(usuario);
	}

	public void removerLista(ListaDeCompras lista)
	{
		conLista.removerLista(lista);
	}
	// --------------------------------------------- Perfil
	// -------------------------------------------------------------------

	public void cadastrarPerfil(Perfil perfil)
	{
		// conPerfil.CadastrarPerfil(perfil);
	}

	public void atualizarPerfil(Perfil perfil)
	{
		// conPerfil.AtualizarPerfil(perfil);
	}

	public List<Perfil> listarTodosPerfis()
	{
		return null;
		// return conPerfil.ListarTodosPerfil();
	}

	public Perfil pesquisarPorCodigo(Perfil perfil)
	{
		return null;
		// return conPerfil.PesquisarPorCodigo(perfil);
	}

	public Perfil pesquisarPorNome(Perfil perfil)
	{
		return null;
		// return conPerfil.PesquisarPorNome(perfil);
	}

	// --------------------------------------------- Produto
	// -------------------------------------------------------------------
	public void cadastrarProduto(Produto produto)
	{
		conProduto.cadastrarProduto(produto);
	}

	public void atualizarProduto(Produto produto)
	{
		conProduto.atualizarProduto(produto);
	}

	public List<Produto> listarTodosProdutos()
	{
		return conProduto.listarTodosProdutos();
	}

	public Produto pesquisarProdutoPorNome(Produto produto)
	{
		return conProduto.pesquisarProdutoPorNome(produto);
	}

	public List<Produto> buscarProdutoPorSupermercado(Supermercado supermercado)
	{
		return conProduto.buscarProdutoPorSupermercado(supermercado);
	}

	public Produto pesquisarProdutoPorTipo(Produto produto)
	{
		return conProduto.pesquisarProdutoPorTipo(produto);
	}

	public void removerProduto(Produto produto)
	{
		conProduto.removerProduto(produto);
	}

	// --------------------------------------------- Pagamento
	// -------------------------------------------------------------------
	public void cadastrarPagamento(Pagamento pagamento)
	{
		conPagamento.cadastrarPagamento(pagamento);
	}

	public void atualizarPagamento(Pagamento pagamento)
	{
		conPagamento.atualizarPagamento(pagamento);
	}

	public List<Pagamento> listarTodosPagamentos()
	{
		return conPagamento.listarTodosPagamentos();
	}

	public Pagamento listarPorCodigo(Pagamento pagamento)
	{
		return conPagamento.listarPorCodigo(pagamento);
	}

	// --------------------------------------------- Item
	// -------------------------------------------------------------------
	public void cadastrarItem(Item item)
	{
		conItem.cadastrarItem(item);
	}

	public void atualizarItem(Item item)
	{
		conItem.atualizarItem(item);
	}

	public List<Item> listarTodosItens()
	{
		return conItem.listarTodosItens();
	}

	public void removerProdutoItem(Item item)
	{
		conItem.removerProdutoItem(item);
	}

	public Item buscarItemProduto(Produto produto)
	{
		return conItem.buscarItemProduto(produto);
	}

	public List<Item> consultarItensPorLista(ListaDeCompras lista)
	{
		return conItem.consultarItensPorLista(lista);
	}
	
	// --------------------------------------------- Pedido
	// -------------------------------------------------------------------
	public void cadastrarPedido(Pedido pedido)
	{
		conPedido.cadastrarPedido(pedido);
	}

	public void alterarPedido(Pedido pedido)
	{
		conPedido.atualizarPedido(pedido);
	}

	public List<Pedido> listarTodosPedidos()
	{
		return conPedido.listarTodosPedidos();
	}

	public Pedido listarPedidoPorCodigo(Pedido pedido)
	{
		return conPedido.listarPedidoPorCodigo(pedido);
	}

	// TRAZER SUPERMERCADO PR�XIMO
	@Override
	public MapModel getSupermencadoProximo()
	{
		return conSupermercado.supermencadoProximo();
	}

	// Login facebook -----//
	@Override
	public Usuario authFacebookLogin() throws Exception
	{
		return loginFacebook.authFacebookLogin();
	}

	@Override
	public void codificar(String codigo, String methodType) throws Exception
	{
		loginFacebook.codificar(codigo, methodType);
	}
	
	public List<ProdutoPromocao> listaProdutoPromocaoSupermercado(Supermercado s){
		return conProduto.listaProdutoPromocaoSupermercado(s);
	}
}