package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private int codigo;

	@Column(name = "nome", length = 100)
	private String nome;

	@Column(name = "marca", length = 50)
	private String marca;

	@Column(name = "qtdEstoque", length = 12)
	private int qtdEstoque;

	@Column(name = "valorUnitario", length = 12)
	private Double valorUnitario;

	@Column(name = "tipo", length = 50)
	private String tipo;

	@ManyToOne
	private Supermercado supermercado;

	@Column(name = "image")
	private String imagem;

	@Transient // n�o gera coluna, guarda informa��es temporaria
	private String caminhoImagem;

	public Produto(int codigo, String nome, String marca, int qtdEstoque, Double valorUnitario,
			Supermercado supermercado, String tipo, String imagem)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.marca = marca;
		this.qtdEstoque = qtdEstoque;
		this.valorUnitario = valorUnitario;
		this.supermercado = supermercado;
		this.tipo = tipo;
		this.imagem = imagem;
	}

	public Produto()
	{
		super();
	}

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getMarca()
	{
		return marca;
	}

	public void setMarca(String marca)
	{
		this.marca = marca;
	}

	public int getQtdEstoque()
	{
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque)
	{
		this.qtdEstoque = qtdEstoque;
	}

	public Double getValorUnitario()
	{
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario)
	{
		this.valorUnitario = valorUnitario;
	}

	public Supermercado getSupermercado()
	{
		return supermercado;
	}

	public void setSupermercado(Supermercado supermercado)
	{
		this.supermercado = supermercado;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getCaminhoImagem()
	{
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem)
	{
		this.caminhoImagem = caminhoImagem;
	}

	public String getImagem()
	{
		return imagem;
	}

	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}

	@Override
	public String toString()
	{
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", marca=" + marca + ", qtdEstoque=" + qtdEstoque
				+ ", valorUnitario=" + valorUnitario + ", supermercado=" + supermercado + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + qtdEstoque;
		result = prime * result + ((supermercado == null) ? 0 : supermercado.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	/*
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; Produto other = (Produto) obj; if (codigo != other.codigo)
	 * return false; if (marca == null) { if (other.marca != null) return false;
	 * } else if (!marca.equals(other.marca)) return false; if (nome == null) {
	 * if (other.nome != null) return false; } else if
	 * (!nome.equals(other.nome)) return false; if (qtdEstoque !=
	 * other.qtdEstoque) return false; if (supermercado == null) { if
	 * (other.supermercado != null) return false; } else if
	 * (!supermercado.equals(other.supermercado)) return false; if (tipo ==
	 * null) { if (other.tipo != null) return false; } else if
	 * (!tipo.equals(other.tipo)) return false; if (valorUnitario == null) { if
	 * (other.valorUnitario != null) return false; } else if
	 * (!valorUnitario.equals(other.valorUnitario)) return false; return true; }
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Produto other = (Produto) obj;
		if (marca == null)
		{
			if (other.marca != null) return false;
		} else if (!marca.equals(other.marca)) return false;
		if (nome == null)
		{
			if (other.nome != null) return false;
		} else if (!nome.equals(other.nome)) return false;
		if (tipo == null)
		{
			if (other.tipo != null) return false;
		} else if (!tipo.equals(other.tipo)) return false;
		return true;
	}

	public Produto(String nome, String marca, String tipo)
	{
		super();
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo;
	}
}
