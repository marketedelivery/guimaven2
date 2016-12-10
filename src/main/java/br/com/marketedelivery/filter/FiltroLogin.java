package br.com.marketedelivery.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.marketedelivery.classesBasicas.Usuario;

public class FiltroLogin extends AbstractFilter implements Filter{
	
	private String LOGIN_ACTION_URI;
		
	@Override
	public void destroy() {
		
	}
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
        Usuario user = (Usuario)req.getSession(true).getAttribute("usuario");
        if(session.isNew() || user == null) {
        	//System.out.println("inicio");
        	doLogin(request, response, req);
           // System.out.println("passou");
            return;
        } 
          chain.doFilter(request, response);
    }
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
		
	}

	public String getLOGIN_ACTION_URI() {
		return LOGIN_ACTION_URI;
	}

	public void setLOGIN_ACTION_URI(String lOGIN_ACTION_URI) {
		LOGIN_ACTION_URI = lOGIN_ACTION_URI;
	}
}