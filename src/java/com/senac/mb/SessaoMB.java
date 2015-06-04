package com.senac.mb;

import com.senac.bd.UsuarioBD;
import com.senac.bean.Usuario;
import com.senac.controller.Sessao;
import com.senac.rn.UsuarioRN;
import com.senac.utilidades.EnviarMensagen;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class SessaoMB
{
	private Usuario usuario;
	private UsuarioRN rn;
	private boolean logado;

	public SessaoMB() {
		this.usuario = new Usuario();
		this.rn = new UsuarioRN();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public boolean estaLogadoComoGerente() {
		return (logado && usuario.isGerente());
	}

	public void validar(FacesContext context, UIComponent toValidate, Object value) {
		String campo = toValidate.getClientId();
		String valor = (String) value;

		try {
			rn.validar(campo, valor);
		} catch (RuntimeException e) {
			EnviarMensagen.enviarErroValidacao(toValidate, e.getMessage());
		}
	}

	public void resetar() {
		this.usuario = new Usuario();
	}

	public void checarLogin() {
		if (!logado) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
			} catch (IOException e) {
			}
		}
	}

	public String efetuarLogin() {
		List<Usuario> l = new UsuarioBD().listar(usuario);
		if (!l.isEmpty()) {
			usuario = l.get(0);
			Sessao.getInstance().setUsuario(usuario);
			logado = true;
			return "index";
		}
		return null;
	}

	public String efetuarLogoff() {
		resetar();
		Sessao.getInstance().setUsuario(usuario);
		logado = false;
		return "login";
	}
}
