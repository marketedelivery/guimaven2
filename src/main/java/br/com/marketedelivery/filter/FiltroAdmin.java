package br.com.marketedelivery.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.marketedelivery.classesBasicas.Usuario;

public class FiltroAdmin extends AbstractFilter implements Filter{

	
	/*
	 * a parte de configura��o do filtro que fica na web.xml
	  <filter>
		<filter-name>FiltroAdmin</filter-name>
		<filter-class>br.com.marketedelivery.filter.FiltroAdmin</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>FiltroAdmin</filter-name>
		<url-pattern>/pages/protected-admin/*</url-pattern>
	</filter-mapping>
	 */
	public FiltroAdmin() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Usuario usuario =  (Usuario) req.getSession(true).getAttribute("usuario");
	// verifica se o usuario � um administrador para liberar o acesso ao a pagina de admiinistrador
		if(usuario.getPerfil().equals("Administrador")){
			chain.doFilter(request, response);
		}
		doLogin(request, response, req);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
