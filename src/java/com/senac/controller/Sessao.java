package com.senac.controller;

import com.senac.bean.Usuario;

public class Sessao
{
	private static Sessao instance = null;
	private Usuario usuario = null;

	private Sessao() {
	}

	public static Sessao getInstance() {
		if (instance == null) {
			instance = new Sessao();
		}
		return instance;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
