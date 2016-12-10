package br.com.marketedelivery.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.marketedelivery.DAO.FacesUtil;
import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Endereco;
import br.com.marketedelivery.classesBasicas.Estado;
import br.com.marketedelivery.classesBasicas.Perfil;
import br.com.marketedelivery.classesBasicas.Usuario;
import br.com.marketedelivery.util.ValidarCpf;
import br.com.marketedelivery.util.ValidarEmail;

@RequestScoped
@ViewScoped
@ManagedBean(name = "usuarioMB")
public class UsuarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private Endereco endereco;

	private IFachada fachada;

	private List<Usuario> listar;

	private List<Usuario> listaUsuarios;

	private List<Usuario> listaUsuariosFiltrados;
	
	private List<Usuario>todosUsuarios;

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}
	

	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}



	public void setTodosUsuarios(List<Usuario> todosUsuarios) {
		this.todosUsuarios = todosUsuarios;
	}



	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public IFachada getFachada() {
		if (fachada == null) {
			fachada = new Fachada();
		}
		return fachada;
	}

	public void setFachada(IFachada fachada) {
		this.fachada = fachada;
	}

	public List<Usuario> getListar() {
		return listar;
	}

	public void setListar(List<Usuario> listar) {
		this.listar = listar;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public List<Usuario> getListaUsuariosFiltrados() {
		return listaUsuariosFiltrados;
	}

	public void setListaUsuariosFiltrados(List<Usuario> listaUsuariosFiltrados) {
		this.listaUsuariosFiltrados = listaUsuariosFiltrados;
	}

	public Estado[] getUFs() {
		return Estado.values();
	}

	public void salvar() {
		usuario.setEndereco(endereco);
		fachada = getFachada();
		Usuario user = fachada.listarPorCPF(usuario);
		Usuario user2 = fachada.pesquisarPorEmail(usuario);
		ValidarCpf vl = new ValidarCpf();
		try {
			if (user == null) {
				if (user2 == null) {
					if (ValidarEmail.emailValido(usuario.getEmail()) != false) {
						if (vl.validarCpf(usuario.getCpf()) == true) 
						{
							usuario.setPerfil(Perfil.Usuario);
							fachada.cadastrarUsuario(usuario);
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage("Cliente cadastrado com sucesso"));
							FacesContext fc = FacesContext.getCurrentInstance();
							ExternalContext ec = fc.getExternalContext();

							/* Manter a mensagem ap�s o redirect */
							ec.getFlash().setKeepMessages(true);
							NavigationHandler nh = fc.getApplication().getNavigationHandler();
							nh.handleNavigation(fc, null, "/pages/public/login.xhtml?faces-redirect=true");
						} else {
							FacesContext.getCurrentInstance().addMessage(null,
									new FacesMessage("Esse cpf � inv�lido!"));
							return;
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Esse e-mail � inv�lido!"));
						return;
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Esse e-mail j� est� cadastrado!"));
					return;
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usu�rio j� Cadastrado"));
				return;
			}
			usuario = new Usuario();
			endereco = new Endereco();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Erro ao cadastrar, tente novamente mais tarde"));
			System.out.println(e.getMessage());
		}
	}

	public void carregarPesquisa() {
		try {
			// fachada.ListarTodosUsuarios();
			// listaUsuarios = fachada.ListarTodosUsuarios();
			IFachada fachada = getFachada();
			listaUsuarios = fachada.listarTodosUsuarios();
		} catch (Exception ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os usuarios" + ex.getMessage());
		}
	}

	@PostConstruct
	public void carregarCadastro() {
		try {
			String valor = FacesUtil.getParam("clicod");
			// int codigo_pessoa = 0;
			if (valor != null) {
				// int codigo = Integer.parseInt(valor);
				usuario = fachada.pesquisarPorCodigo(usuario);
			}
		} catch (Exception ex) {
			FacesUtil.adicionarMsgErro("Erro ao tentar listar os usuarios" + ex.getMessage());
		}
	}

	public void alterar(Usuario usuario) {
		{
			fachada = getFachada();
			Usuario u = fachada.listarPorCPF(usuario);
			try {
				if (u != null) {
					u = usuario;
					fachada.atualizarUsuario(u);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Cadastro Alterado com Sucesso"));
				}
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao Atualizar"));
				System.out.println(e.getMessage());
			}
		}
	}
	
	public List<Usuario>getListarUsuarios()
	{
		List<Usuario> tmp = getFachada().listarTodosUsuarios();
		return tmp;
		
	}
	
	public void carregarLista()
	{
		getListarUsuarios();
	}
}