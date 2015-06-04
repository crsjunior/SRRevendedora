package com.senac.utilidades;

import java.io.Serializable;

public class Mensageiro implements Serializable
{
	public enum TipoAlerta
	{
		INFO("alert-info"),
		SUCCESS("alert-success"),
		WARNING("alert-warning"),
		DANGER("alert-danger");

		private String name;

		private TipoAlerta(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	private String titulo;
	private String mensagem;
	private TipoAlerta tipoAlerta;

	public Mensageiro() {
	}

	public Mensageiro(String titulo, String mensagem, TipoAlerta tipoAlerta) {
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.tipoAlerta = tipoAlerta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TipoAlerta getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(TipoAlerta tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}
}
