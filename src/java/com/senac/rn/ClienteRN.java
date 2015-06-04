package com.senac.rn;

import com.senac.bd.ClienteBD;
import com.senac.bean.Cliente;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Validadores;
import java.util.List;

public class ClienteRN implements CrudGenerido<Cliente>
{
	private ClienteBD bd;

	public ClienteRN() {
		this.bd = new ClienteBD();
	}

	public void validar(String campo, String valor) {
		String mensagemValidacao = null;

		switch (campo) {
			case "nome":
				mensagemValidacao = Validadores.validarNome(valor, true);
				break;
			case "cpf":
				mensagemValidacao = Validadores.validarCPF(valor, true);
				break;
			case "endereco":
				mensagemValidacao = Validadores.validarEndereco(valor, true);
				break;
			case "cidade":
				mensagemValidacao = Validadores.validarCidade(valor, true);
				break;
			case "uf":
				mensagemValidacao = Validadores.validarUfNome(valor, true);
				break;
			case "cep":
				mensagemValidacao = Validadores.validarCEP(valor, false);
				break;
			case "telefone":
				mensagemValidacao = Validadores.validarTelefone(valor, true);
				break;
			case "email":
				mensagemValidacao = Validadores.validarEmail(valor, true);
				break;
		}

		if (mensagemValidacao != null) {
			throw new RuntimeException(mensagemValidacao);
		}
	}

	@Override
	public void salvar(Cliente bean) {
		try {
			bd.salvar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar salvar o cliente");
		}
	}

	@Override
	public void excluir(Cliente bean) {
		try {
			bd.excluir(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar excluir o cliente");
		}
	}

	@Override
	public List<Cliente> listar(Cliente bean) {
		try {
			return bd.listar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar listar os clientes");
		}
	}

	@Override
	public Cliente consultar(Cliente bean) {
		try {
			return bd.consultar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar consultar um cliente");
		}
	}
}
