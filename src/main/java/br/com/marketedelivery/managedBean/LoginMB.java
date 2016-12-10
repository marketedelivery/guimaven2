package br.com.marketedelivery.managedBean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.marketedelivery.DAOFactory.DAOFactory;

// import com.sun.jersey.api.client.Client;
// import com.sun.jersey.api.client.WebResource;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Usuario;

@SessionScoped
@ManagedBean(name = "loginMB")
public class LoginMB extends AbstractMB implements Serializable
{
	Usuario usuarioMB;

	public static int codigoUsuario;

	private static final long serialVersionUID = 1L;

	Usuario usuario;

	private Usuario usuarioLogado;

	IFachada fachada;

	String menssagem;

	public Usuario getUsuario()
	{
		if (usuario == null)
		{
			return usuario = new Usuario();
		} else
		{
			usuarioLogado = usuario;
			return usuario;
		}
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public IFachada getFachada()
	{
		if (fachada == null)
		{
			return fachada = new Fachada();
		} else
		{
			return fachada;
		}
	}

	public String getMenssagem()
	{
		return menssagem;
	}

	public void setMenssagem(String menssagem)
	{
		this.menssagem = menssagem;
	}

	public void setFachada(IFachada fachada)
	{
		this.fachada = fachada;
	}

	private HttpServletRequest getRequest()
	{
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * Efetua logout do usu�rio do sistema
	 */
	public String getLogOut()
	{
		getRequest().getSession().invalidate();
		return "/pages/public/login.xhtml?faces-redirect=true";
	}

	/**
	 * Efetua logout do usu�rio do sistema
	 */
	public String efetuarLogout()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return null;
	}

	public String efetuarLogin()
	{
		Usuario user = new Usuario();
		user = getFachada().pesquisarPorEmail(usuario);
		if(user == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email n�o cadastrado"));
			return null;
		}
		try
		{
			String email = user.getEmail();
			String senha = user.getSenha();
			codigoUsuario = user.getCodigo();
			if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha))
			{
				this.usuarioLogado = user;
				displayInfoMessageToUser("Cliente logado no sistema de compras online");
				FacesContext context = FacesContext.getCurrentInstance();
				HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
				request.getSession().setAttribute("usuario", user);
				return "/pages/protected/minhasListas.xhtml?faces-redirect=true";
			} else if (usuario.getEmail().equals(email) && usuario.getSenha() != senha)
			{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Email ou Senha incorretos, Por Favor verifique seus dados e tente navamente"));
				return null;
			}
		}
		catch (Exception e)
		{
			
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public String getSairDoSistema()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "/pages/public/login.xhtml?faces-redirect=true";
	}

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado()
	{
		if(LoginFacebookMB.usuario != null){	
			codigoUsuario = LoginFacebookMB.usuario.getCodigo();
			usuarioLogado =  LoginFacebookMB.usuario;
		}
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado
	 *            the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}
	
	public void iniciarServidor()
	{
		DAOFactory.conectar();
	}
}