package com.senac.mb;

import com.senac.bean.Usuario;
import com.senac.rn.UsuarioRN;
import com.senac.utilidades.EnviarMensagen;
import com.senac.utilidades.Mensageiro;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class UsuarioMB
{
	private Usuario usuario;
	private UsuarioRN rn;
	private Mensageiro mensageiro;
	private String senha;
	private String senhaConfirma;

	public UsuarioMB() {
		this.usuario = new Usuario();
		this.rn = new UsuarioRN();
		this.mensageiro = new Mensageiro();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaConfirma() {
		return senhaConfirma;
	}

	public void setSenhaConfirma(String senhaConfirma) {
		this.senhaConfirma = senhaConfirma;
	}

	public Mensageiro getMensageiro() {
		return mensageiro;
	}

	public void validar(FacesContext context, UIComponent toValidate, Object value) {
		String campo = toValidate.getClientId();
		String valor = (String) value;

		if (campo.equals("senha")) {
			// armazenando valor da senha para posteriormente compara-lo com a confirmacao da senha.
			this.senha = valor;
		}

		try {
			rn.validar(campo, valor);
		} catch (RuntimeException e) {
			EnviarMensagen.enviarErroValidacao(toValidate, e.getMessage());
		}

		// confirmando senha:
		if (campo.equals("senhaConfirma") && !valor.equals(senha)) {
			EnviarMensagen.enviarErroValidacao(toValidate, "Confirmação de senha não confere");
		}
	}

	public void resetar() {
		this.usuario = new Usuario();
	}

	public String salvar() {
		try {
			rn.salvar(usuario);
			return "listarUsuario";
		} catch (RuntimeException e) {
		}
		return null;
	}

	public String excluir() {
		System.out.println("Usuario: " + usuario.getIdUsuario());
		try {
			rn.excluir(usuario);
			resetar();
			return "listarUsuario";
		} catch (RuntimeException e) {
		}
		return null;
	}
	
	public void excluir(Usuario reg) {
		try {
			rn.excluir(reg);
		} catch (RuntimeException e) {
		}
	}

	public void editar(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> listar() {
		try {
			return rn.listar(usuario);
		} catch (RuntimeException e) {
		}
		return null;
	}
}
