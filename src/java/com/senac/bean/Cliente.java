package com.senac.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable
{
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Integer idCliente;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "nome")
	private String nome;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@Column(name = "cep")
	private String cep;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "email")
	private String email;

	public Cliente() {
	}

	public Cliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCliente != null ? idCliente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente o = (Cliente) obj;
		return (idCliente != null && o.idCliente != null && idCliente.equals(o.idCliente));
	}

	@Override
	public String toString() {
		return "com.senac.bean.Cliente[ idUsuario=" + idCliente + " ]";
	}
}
