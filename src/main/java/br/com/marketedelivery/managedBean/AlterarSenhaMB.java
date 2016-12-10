package br.com.marketedelivery.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Usuario;
import br.com.marketedelivery.util.EmailUtil;

@ViewScoped
@ManagedBean(name = "alterarSenhaMB")
public class AlterarSenhaMB extends AbstractMB
{
	Usuario usuario;

	IFachada fachada;

	EmailUtil emailUtil;

	public AlterarSenhaMB()
	{
		fachada = new Fachada();
		emailUtil = new EmailUtil();
	}

	public Usuario getUsuario()
	{
		if (usuario == null)
		{
			return usuario = new Usuario();
		} else
		{
			return usuario;
		}
	}

	public boolean getRecuperarSenha() throws EmailException
	{
		Usuario us = new Usuario();
		try
		{
			us = fachada.listarPorCPF(usuario);
			if (us == null)
			{
				displayInfoMessageToUser("CPF Invalido!");
				return false;
			}
			String emailRetornado = us.getEmail();
			if (!emailRetornado.equals(usuario.getEmail()))
			{
				return false;
			} else
			{
				if (fachada.alteraSenha(us))
				{
					emailUtil.enviarEmail(us);
					return true;
				}
			}
		}
		catch (EmailException e)
		{
			displayInfoMessageToUser("email ivalido!" + e.getMessage());
		}
		return false;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public void enviarSenhaUsuario() throws EmailException
	{
		if (getRecuperarSenha() == true)
		{
			displayInfoMessageToUser("Nova senha enviada para o seu email cadastrado!");
		} else
		{
			displayInfoMessageToUser("email ivalido!");
		}
	}
}
