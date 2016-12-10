package br.com.marketedelivery.classesBasicas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_supermercado")
public class Supermercado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "codigo")
	private int codigo;
	@Column(name = "nome")
	private String nome;
	@Column(name = "latitude")
	private String latitude;
	@Column(name = "longitude")
	private String longitude;
	@Column(name="logoimage")
	private String logoimage;
	
	public Supermercado() 
	{
		super();
	}
	
	public Supermercado(String nome, int codigo,String logoimage)
	{
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.logoimage = logoimage;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLogoimage() {
		return logoimage;
	}

	public void setLogoimage(String logoimage) {
		this.logoimage = logoimage;
	}

	@Override
	public String toString() {
		return "Supermercado [codigo=" + codigo + ", nome=" + nome + ", latitude=" + latitude + ", longitude="
				+ longitude + ", logoimage=" + logoimage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((logoimage == null) ? 0 : logoimage.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supermercado other = (Supermercado) obj;
		if (codigo != other.codigo)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (logoimage == null) {
			if (other.logoimage != null)
				return false;
		} else if (!logoimage.equals(other.logoimage))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}