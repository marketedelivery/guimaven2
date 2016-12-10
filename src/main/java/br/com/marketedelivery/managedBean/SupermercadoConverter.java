package br.com.marketedelivery.managedBean;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.marketedelivery.Fachada.Fachada;
import br.com.marketedelivery.IFachada.IFachada;
import br.com.marketedelivery.classesBasicas.Supermercado;

@FacesConverter(value="supermercadoConverter")
public class SupermercadoConverter implements Converter
{
	private IFachada fachada;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent componente, String valor) 
	{
		try 
		{
			fachada = new Fachada();
			int codigo = Integer.parseInt(valor);
			Supermercado su = new Supermercado();
			su.setCodigo(codigo);
			Supermercado supermercado = fachada.consultarPorID(su);
			//List<Produto>pdt = fachada.buscarProdutoPorSupermercado(su);
			//supermercado.setProdutos(pdt);
			return supermercado;
			
		} catch (RuntimeException ex) 
		{
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent componente,Object objeto) 
	{
		try 
		{
			Supermercado supermercado = (Supermercado)objeto;
			Integer valor = supermercado.getCodigo();
			return valor.toString();
		} catch (RuntimeException ex) 
		{
			return null;
		}
	}

}
