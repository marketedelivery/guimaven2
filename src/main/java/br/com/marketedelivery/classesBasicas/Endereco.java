package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_endereco")
public class Endereco implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="codigo")
	int codigo;
	
	@Column(name = "cep", length = 9, nullable = false)
	private String cep;

	@Column(name = "logradouro", length = 30, nullable = false)
	private String logradouro;

	@Column(name = "numero", nullable = false)
	private int numero;

	@Column(name = "complemento", length = 30, nullable = true)
	private String complemento;

	@Column(name = "bairro", length = 30, nullable = false)
	private String bairro;

	@Column(name = "cidade", length = 30, nullable = false)
	private String cidade;

	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	
	public Endereco()
	{
		this.cep = "";
		this.logradouro = "";
		this.complemento = "";
		this.bairro = "";
		this.cidade = "";
		
	}

	public Endereco(String cep, String logradouro, int numero, String complemento, String bairro, String cidade,
			Estado estado)
	{
		super();
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getLogradouro()
	{
		return logradouro;
	}

	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}

	public int getNumero()
	{
		return numero;
	}

	public void setNumero(int numero)
	{
		this.numero = numero;
	}

	public String getComplemento()
	{
		return complemento;
	}

	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	public String getCidade()
	{
		return cidade;
	}

	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}
}