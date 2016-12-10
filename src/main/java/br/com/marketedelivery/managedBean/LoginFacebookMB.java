package br.com.marketedelivery.managedBean;

import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;
import org.brickred.socialauth.*;
import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Usuario;

@SessionScoped
@ManagedBean
public class LoginFacebookMB extends AbstractMB implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final String FACEBOOK_APP_ID = "1774193229490241";
	public static final String FACEBOOK_APP_SECRET = "18716d507e0917fb26dfd72fa0acb582";
	public static final String REDIRECT_TO ="http://localhost:8081/MarketeDeliveryGui/pages/public/redirecionaEvent.xhtml";
	private SocialAuthManager manager;
	private String originalURL;
	private String providerID;// = "facebook"
	private Profile profile;
	static Usuario usuario;
	private IFachada fachada;
	private String imagemFacebok;
	public void conectarFacebook() throws Exception{
		// adiciona as chaves e segredos dos provedores aqui  
        Properties props = System.getProperties();
        props.put("graph.facebook.com.consumer_key", FACEBOOK_APP_ID);
        props.put("graph.facebook.com.consumer_secret", FACEBOOK_APP_SECRET);
     // Defina a permiss�o personalizada, se necess�rio
        props.put("graph.facebook.com.custom_permissions", 
        		  "public_profile,user_education_history,publish_actions,user_managed_groups");
     // Iniciado componentes necess�rios
        SocialAuthConfig config = SocialAuthConfig.getDefault();
        config.load(props); 
        manager = new SocialAuthManager();
        manager.setSocialAuthConfig(config);
        
     // 'SuccessURL' � a p�gina que voc� ser� redirecionado para on login bem-sucedido
        ExternalContext externalContext   = FacesContext.getCurrentInstance().getExternalContext();
        String successURL ="http://localhost:8081/"+ externalContext.getRequestContextPath() + "/pages/public/redirecionaEvent.xhtml"; 
        String authenticationURL = manager.getAuthenticationUrl(providerID, successURL);
        FacesContext.getCurrentInstance().getExternalContext().redirect(authenticationURL);
        displayInfoMessageToUser("Cliente logado com facebook no sistema de compras online");
	}
	
	
	public void puxaInfoDoUsuario(ComponentSystemEvent event) throws Exception{
	try{
		usuario = new Usuario();
		Usuario user = new Usuario();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String parameter = request.getParameter("code");
	    
	    if(parameter != null){
	    	getFachada().codificar(parameter,"GET");
	       user = getFachada().authFacebookLogin();
	      // usuario.setImagemPerfilFacebook(user.getImagemPerfilFacebook());
	       // verifica se o usuario que veio do facebook esta castrado no banco de dados da aplica��o
	       // se estiver cadastrado no banco add a sess�o com os dados ddo banco de dados da app
	        //if(getFachada().pesquisarPorEmail(user) == null){
	       // request.getSession().setAttribute("usuario", user);
	       // FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/MarketeDeliveryGui/pages/protected/produtoPesquisa.xhtml?faces-redirect=true");
	       //}else{
	    	   request.getSession().setAttribute("usuario", user);
	    	   usuario = user;
		        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8081/MarketeDeliveryGui/pages/protected/minhasListas.xhtml?faces-redirect=true");
		  // }
	       }
	}catch(Exception ex){
		System.out.println("UserSession - Exception: " + ex.getMessage());
		ex.getCause().printStackTrace();
		displayErrorMessageToUser("Erro ao tentar logar. Tente novamente mais tarde! " + ex.getMessage());
	}
	}
	
	public SocialAuthManager getManager() {
		return manager;
	}

	public void setManager(SocialAuthManager manager) {
		this.manager = manager;
	}

	public String getOriginalURL() {
		return originalURL;
	}

	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	public String getProviderID() {
		
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public Usuario getUser() {
		return usuario;
	}


	public void setUser(Usuario user) {
		this.usuario = user;
	}
	public IFachada getFachada() {
		if (fachada == null) {
			return fachada = new Fachada();
		} else {
			return fachada;
		}
	}


	public String getImagemFacebok() {
		return imagemFacebok;
	}


	public void setImagemFacebok(String imagemFacebok) {
		this.imagemFacebok = imagemFacebok;
	}

}
