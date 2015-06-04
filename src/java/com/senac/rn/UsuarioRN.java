package com.senac.rn;

import com.senac.bd.UsuarioBD;
import com.senac.bean.Usuario;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Validadores;
import java.util.List;

public class UsuarioRN implements CrudGenerido<Usuario>
{
	private UsuarioBD bd;
	private String valorSenha;

	public UsuarioRN() {
		this.bd = new UsuarioBD();
		this.valorSenha = null;
	}

	public void validar(String campo, String valor) {
		String mensagemValidacao = null;

		switch (campo) {
			case "nome":
				mensagemValidacao = Validadores.validarNome(valor, true);
				break;
			case "email":
				mensagemValidacao = Validadores.validarEmail(valor, true);
				break;
			case "senha":
				mensagemValidacao = Validadores.validarSenha(valor);
				break;
			case "senhaConfirma":
				mensagemValidacao = Validadores.validarSenha(valor);
				break;
		}

		if (mensagemValidacao != null) {
			throw new RuntimeException(mensagemValidacao);
		}
	}

	@Override
	public void salvar(Usuario bean) {
		try {
			bd.salvar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar salvar o usuário");
		}
	}

	@Override
	public void excluir(Usuario bean) {
		try {
			bd.excluir(bean);
		} catch (RuntimeException e) {
			throw new RuntimeException("Erro ao tentar excluir o usuário");
		}
	}

	@Override
	public List<Usuario> listar(Usuario bean) {
		try {
			return bd.listar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar listar os usuários");
		}
	}

	@Override
	public Usuario consultar(Usuario bean) {
		try {
			return bd.consultar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar consultar um usuário");
		}
	}
}
