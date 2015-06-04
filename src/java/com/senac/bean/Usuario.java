package com.senac.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario", nullable = false)
	private Integer idUsuario;

	@Column(name = "nome")
	private String nome;

	@Column(name = "email")
	private String email;

	@Column(name = "senha")
	private String senha;

	@Column(name = "gerente")
	private boolean gerente;

	public Usuario() {
	}

	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isGerente() {
		return gerente;
	}

	public void setGerente(boolean gerente) {
		this.gerente = gerente;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario o = (Usuario) obj;
		return (idUsuario != null && o.idUsuario != null && idUsuario.equals(o.idUsuario));
	}

	@Override
	public String toString() {
		return "com.senac.bean.Usuario[ idUsuario=" + idUsuario + " ]";
	}
}
