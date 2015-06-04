package com.senac.rn;

import com.senac.bd.VendaBD;
import com.senac.bean.Venda;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Validadores;
import java.util.List;

public class VendaRN implements CrudGenerido<Venda>
{
	private VendaBD bd;

	public VendaRN() {
		this.bd = new VendaBD();
	}

	public void validar(String campo, String valor) {
		String mensagemValidacao = null;

		switch (campo) {
			case "nome":
//				mensagemValidacao = Validadores.validarNome(valor, true);
				break;
			case "email":
//				mensagemValidacao = Validadores.validarEmail(valor, true);
				break;
			case "senha":
//				mensagemValidacao = Validadores.validarSenha(valor);
				break;
			case "senhaConfirma":
//				mensagemValidacao = Validadores.validarSenha(valor);
				break;
		}

		if (mensagemValidacao != null) {
			throw new RuntimeException(mensagemValidacao);
		}
	}

	@Override
	public void salvar(Venda bean) {
		try {
			bd.salvar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar salvar a venda");
		}
	}

	@Override
	public void excluir(Venda bean) {
		try {
			bd.excluir(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar excluir a venda");
		}
	}

	@Override
	public List<Venda> listar(Venda bean) {
		try {
			return bd.listar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar listar as vendas");
		}
	}

	@Override
	public Venda consultar(Venda bean) {
		try {
			return bd.consultar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar consultar uma venda");
		}
	}
}
