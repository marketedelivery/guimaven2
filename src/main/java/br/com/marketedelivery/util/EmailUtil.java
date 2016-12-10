package br.com.marketedelivery.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.marketedelivery.classesBasicas.Usuario;

public class EmailUtil
{
	public EmailUtil()
	{}

	public void enviarEmail(Usuario u) throws EmailException
	{
		String mensagem = "<H4>Ol� sr.(a) " + u.getNome() + "</H4>" + "<BR>"
				+ "<P> Enviamo a nova senha de acesso ao marketedelivary</P>" + "<BR>" + "<P>A nova senha �: </P>"
				+ "<UL>" + "<LI><B> - Senha: </B>" + u.getSenha() + "</UL>" + "<BR>" + "<P> Att, </p>" + "<BR>"
				+ "<P>Por favor n�o respoder este email." + "<BR>" + " att: " + "Equipe MartekeDelivery </P>" + "<BR>"
				+ "<BR> \n";
		HtmlEmail email = new HtmlEmail();
		try
		{
			// configura��o do gmail par enviar um email
			email.setHostName("smtp.gmail.com");
			email.setSslSmtpPort("465");
			email.setStartTLSRequired(true);
			email.setSSLOnConnect(true);
			//
			email.setDebug(true);
			email.setAuthenticator(new DefaultAuthenticator("fabioemidiosouza@gmail.com", "88351077"));
			email.setSubject("Altera��o de senha");
			email.setFrom("fabioemidiosouza@gmail.com", "fabio");
			email.setHtmlMsg(mensagem);
			email.addTo(u.getEmail(), "");
			email.send();
		}
		catch (EmailException e)
		{
			e.printStackTrace();
		}
	}
}