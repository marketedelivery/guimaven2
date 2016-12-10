package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_produtoPromocao")
public class ProdutoPromocao implements Serializable{

	private static final long serialVersionUID = -6426816668537022660L;
	
	@Id
	@Column(name = "codigo")
	private int codigo;

	@Column(name = "nome", length = 100)
	private String nome;

	@Column(name = "marca", length = 50)
	private String marca;

	
	@Column(name = "preco", length = 12)
	private Double valorUnitario;
	
	@Column(name = "precoPromocional", length = 12)
	private Double precoPromocional;
	
	@Column(name="pocerntagem")
	private String porcentagemDesconto;
	
	@Column(name = "tipo", length = 50)
	private String tipo;

	@ManyToOne
	private Supermercado supermercado;

	@Column(name = "image")
	private String imagem;

	@Transient // n�o gera coluna, guarda informa��es temporaria
	private String caminhoImagem;

	public ProdutoPromocao(int codigo, String nome, String marca, Double valorUnitario,
			Supermercado supermercado, String tipo, String imagem)
	{
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.marca = marca;
		this.valorUnitario = valorUnitario;
		this.supermercado = supermercado;
		this.tipo = tipo;
		this.imagem = imagem;
	}

	public ProdutoPromocao()
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
	
	public Double getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(Double precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public String getPorcentagemDesconto() {
		return porcentagemDesconto;
	}

	public void setPorcentagemDesconto(String porcentagemDesconto) {
		this.porcentagemDesconto = porcentagemDesconto;
	}

	@Override
	public String toString()
	{
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", marca=" + marca + " valorUnitario=" + valorUnitario + ", supermercado=" + supermercado + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((supermercado == null) ? 0 : supermercado.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ProdutoPromocao other = (ProdutoPromocao) obj;
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

	public ProdutoPromocao(String nome, String marca, String tipo)
	{
		super();
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo;
	}
	

}
