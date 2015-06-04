package com.senac.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "venda")
public class Venda implements Serializable
{
	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenda")
	private Integer idVenda;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuarioId", referencedColumnName = "idUsuario")
	private Usuario usuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "clienteId", referencedColumnName = "idCliente")
	private Cliente cliente;

	@ManyToOne(optional = false)
	@JoinColumn(name = "veiculoId", referencedColumnName = "idVeiculo")
	private Veiculo veiculo;

	@Column(name = "dataVenda")
	@Temporal(TemporalType.DATE)
	private Date dataVenda;

	public Venda() {
	}

	public Venda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idVenda != null ? idVenda.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Venda)) {
			return false;
		}
		Venda o = (Venda) obj;
		return (idVenda != null && o.idVenda != null && idVenda.equals(o.idVenda));
	}

	@Override
	public String toString() {
		return "com.senac.bean.Usuario[ idVenda=" + idVenda + " ]";
	}
}
