package com.senac.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable
{
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVeiculo")
	private Integer idVeiculo;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "fabricante")
	private String fabricante;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "cilindradas")
	private Integer cilindradas;

	@Column(name = "preco")
	private Double preco;

	@Column(name = "comSom")
	private Boolean comSom;

	@Column(name = "comAlarme")
	private Boolean comAlarme;

	@Column(name = "comArcond")
	private Boolean comArcond;

	@Column(name = "comDirhid")
	private Boolean comDirhid;

	@Column(name = "comAirbag")
	private Boolean comAirbag;

	@Column(name = "comAbs")
	private Boolean comAbs;

	public Veiculo() {
	}

	public Veiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public Integer getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(Integer cilindradas) {
		this.cilindradas = cilindradas;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Boolean getComSom() {
		return comSom;
	}

	public void setComSom(Boolean comSom) {
		this.comSom = comSom;
	}

	public Boolean getComAlarme() {
		return comAlarme;
	}

	public void setComAlarme(Boolean comAlarme) {
		this.comAlarme = comAlarme;
	}

	public Boolean getComArcond() {
		return comArcond;
	}

	public void setComArcond(Boolean comArcond) {
		this.comArcond = comArcond;
	}

	public Boolean getComDirhid() {
		return comDirhid;
	}

	public void setComDirhid(Boolean comDirhid) {
		this.comDirhid = comDirhid;
	}

	public Boolean getComAirbag() {
		return comAirbag;
	}

	public void setComAirbag(Boolean comAirbag) {
		this.comAirbag = comAirbag;
	}

	public Boolean getComAbs() {
		return comAbs;
	}

	public void setComAbs(Boolean comAbs) {
		this.comAbs = comAbs;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idVeiculo != null ? idVeiculo.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Veiculo)) {
			return false;
		}
		Veiculo o = (Veiculo) obj;
		return (idVeiculo != null && o.idVeiculo != null && idVeiculo.equals(o.idVeiculo));
	}

	@Override
	public String toString() {
		return "com.senac.bean.Veiculo[ idUsuario=" + idVeiculo + " ]";
	}
}
