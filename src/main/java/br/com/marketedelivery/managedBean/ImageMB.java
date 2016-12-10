package br.com.marketedelivery.managedBean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ReferencedBean;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "imageBean")
@ReferencedBean
public class ImageMB
{
	@ManagedProperty("#{param.caminho}") // receber o caminho da imagem
	private String caminho;

	private StreamedContent fotoCarregada;

	public String getCaminho()
	{
		return caminho;
	}

	public void setCaminho(String caminho)
	{
		this.caminho = caminho;
	}

	public StreamedContent getFotoCarregada() throws IOException
	{
		if (caminho == null || caminho.isEmpty())
		{
			Path foto = Paths.get("C:/ImagemMarketeDelivery/semproduto.png");
			InputStream fluxo = Files.newInputStream(foto);
			fotoCarregada = new DefaultStreamedContent(fluxo);
		} else
		{
			Path foto = Paths.get(caminho);
			InputStream fluxo = Files.newInputStream(foto);
			fotoCarregada = new DefaultStreamedContent(fluxo);
		}
		return fotoCarregada;
	}

	public void setFotoCarregada(StreamedContent fotoCarregada)
	{
		this.fotoCarregada = fotoCarregada;
	}
}
