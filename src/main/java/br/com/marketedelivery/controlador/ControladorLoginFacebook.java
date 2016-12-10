package br.com.marketedelivery.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.brickred.socialauth.exception.ServerDataException;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.util.AccessGrant;
import org.brickred.socialauth.util.Constants;
import org.brickred.socialauth.util.HttpUtil;
import org.brickred.socialauth.util.MethodType;
import org.brickred.socialauth.util.Response;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.marketedelivery.DAOFactory.DAOFactory;
import br.com.marketedelivery.IDAO.IUsuarioDAO;
import br.com.marketedelivery.classesBasicas.Usuario;
import br.com.marketedelivery.managedBean.LoginFacebookMB;

public class ControladorLoginFacebook implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Map<String, String> endpoints;

	private AccessGrant accessGrant;

	private IUsuarioDAO usuarioDAO;
	static
	{
		endpoints = new HashMap<String, String>();
		endpoints.put(Constants.OAUTH_AUTHORIZATION_URL, "https://graph.facebook.com/oauth/authorize");
		endpoints.put(Constants.OAUTH_ACCESS_TOKEN_URL, "https://graph.facebook.com/oauth/access_token");
	}

	// Aqui define-se os atributos desejados para a classe extra�dos do facebook
	private static final String PROFILE_FIELDS = "?fields=id,name,picture,age_range,birthday,email,first_name,last_name,about,gender,location,locale,education";

	private static final String PROFILE_URL = "https://graph.facebook.com/me" + PROFILE_FIELDS;

	public Usuario authFacebookLogin() throws Exception
	{
		String presp;
		try
		{
			Response response = executeFeed(PROFILE_URL);
			presp = response.getResponseBodyAsString(Constants.ENCODING);
		}
		catch (Exception ex)
		{
			throw new SocialAuthException("Error enquanto recebe o perfil " + PROFILE_FIELDS, ex);
		}
		Usuario usuario = new Usuario();
		try
		{
			// Basta extrair da response um JSONObject e intepreta-lo como
			// quiser
			JSONObject resp = new JSONObject(presp);
			System.out.println(resp);
			usuario.setCodigoFacebook(resp.getLong("id"));
			long id = usuario.getCodigoFacebook();
			usuario.setNome(resp.getString("name"));
			if (resp.isNull("email") == false)
			{
				usuario.setEmail(resp.getString("email"));
				System.out.println("email nulo " + resp.isNull("email"));
			}
			usuario = cadastraUsuarioFacebook(id, usuario);
			// usuario.setImagemPerfilFacebook(resp.getJSONObject("picture").getJSONObject("data").getString("url"));
			System.out.println(usuario.getCodigo() + " " + usuario.getNome() + "  " + usuario.getEmail());
			return usuario;
		}
		catch (Exception ex)
		{
			throw new ServerDataException("Falhou ao analizar o perfil do usuario no json : " + presp, ex);
		}
	}

	public void codificar(String codigo, String methodType) throws Exception
	{
		String acode;
		String accessToken = null;
		try
		{
			acode = URLEncoder.encode(codigo, "UTF-8");
		}
		catch (Exception ex)
		{
			acode = codigo;
		}
		StringBuffer sb = new StringBuffer();
		if (MethodType.GET.toString().equals(methodType))
		{
			sb.append(endpoints.get(Constants.OAUTH_ACCESS_TOKEN_URL));
			char separador = endpoints.get(Constants.OAUTH_ACCESS_TOKEN_URL).indexOf('?') == -1 ? '?' : '&';
			sb.append(separador);
		}
		sb.append("client_id=").append(LoginFacebookMB.FACEBOOK_APP_ID);
		sb.append("&redirect_uri=").append(LoginFacebookMB.REDIRECT_TO);
		sb.append("&client_secret=").append(LoginFacebookMB.FACEBOOK_APP_SECRET);
		sb.append("&code=").append(acode);
		sb.append("&grant_type=authorization_code");
		Response response;
		String authURL = null;
		try
		{
			if (MethodType.GET.toString().equals(methodType))
			{
				authURL = sb.toString();
				response = HttpUtil.doHttpRequest(authURL, methodType, null, null);
			} else
			{
				authURL = endpoints.get(Constants.OAUTH_ACCESS_TOKEN_URL);
				response = HttpUtil.doHttpRequest(authURL, methodType, sb.toString(), null);
			}
		}
		catch (Exception e)
		{
			throw new SocialAuthException("Error na url : " + authURL, e);
		}
		String result;
		try
		{
			result = response.getResponseBodyAsString(Constants.ENCODING);
		}
		catch (IOException io)
		{
			throw new SocialAuthException(io);
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		Integer expires = null;
		if (result.indexOf("{") < 0)
		{
			String[] pairs = result.split("&");
			for (String pair : pairs)
			{
				String[] kv = pair.split("=");
				if (kv.length != 2)
				{
					throw new SocialAuthException("Unexpected auth response from " + authURL);
				} else
				{
					if (kv[0].equals("access_token"))
					{
						accessToken = kv[1];
					} else if (kv[0].equals("expires"))
					{
						expires = Integer.valueOf(kv[1]);
					} else if (kv[0].equals("expires_in"))
					{
						expires = Integer.valueOf(kv[1]);
					} else
					{
						attributes.put(kv[0], kv[1]);
					}
				}
			}
		} else
		{
			try
			{
				JSONObject jObj = new JSONObject(result);
				if (jObj.has("access_token"))
				{
					accessToken = jObj.getString("access_token");
				}
				// expires_in can come in several different types, and newer
				// org.json versions complain if you try to do getString over an
				// integer...
				if (jObj.has("expires_in") && jObj.opt("expires_in") != null)
				{
					String str = jObj.get("expires_in").toString();
					if (str != null && str.length() > 0)
					{
						expires = Integer.valueOf(str);
					}
				}
				if (accessToken != null)
				{
					Iterator<String> keyItr = jObj.keys();
					while (keyItr.hasNext())
					{
						String key = keyItr.next();
						if (!"access_token".equals(key) && !"expires_in".equals(key) && jObj.opt(key) != null)
						{
							attributes.put(key, jObj.opt(key).toString());
						}
					}
				}
			}
			catch (JSONException je)
			{
				throw new SocialAuthException("Unexpected auth response from " + authURL);
			}
		}
		if (accessToken != null)
		{
			accessGrant = new AccessGrant();
			accessGrant.setKey(accessToken);
			accessGrant.setAttribute(Constants.EXPIRES, expires);
			if (attributes.size() > 0)
			{
				accessGrant.setAttributes(attributes);
			}
			accessGrant.setProviderId(LoginFacebookMB.FACEBOOK_APP_ID);
		}
	}

	public Response executeFeed(final String url) throws Exception
	{
		if (accessGrant == null)
		{
			throw new SocialAuthException("Por favor, verificar a fun��o de Resposta primeiro a receber Access Token");
		}
		char separator = url.indexOf('?') == -1 ? '?' : '&';
		String urlStr = url + separator + Constants.ACCESS_TOKEN_PARAMETER_NAME + "=" + accessGrant.getKey();
		return HttpUtil.doHttpRequest(urlStr, MethodType.GET.toString(), null, null);
	}

	public Usuario cadastraUsuarioFacebook(long id, Usuario usuario) throws Exception
	{
		
		usuarioDAO = DAOFactory.getUsuarioDAO();
		Usuario us = new Usuario();
		us = usuarioDAO.buscarUsuarioCodigoFacebook(id);
		if (us == null)
		{
			usuarioDAO = DAOFactory.getUsuarioDAO();
			usuarioDAO.inserir(usuario);
			usuarioDAO = DAOFactory.getUsuarioDAO();
			usuario = usuarioDAO.buscarUsuarioCodigoFacebook(id);
			
			return usuario;
		} else
		{
			return us;
		}
	}
}