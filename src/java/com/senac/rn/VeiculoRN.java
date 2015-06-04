package com.senac.rn;

import com.senac.bd.VeiculoBD;
import com.senac.bean.Veiculo;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Validadores;
import java.util.Calendar;
import java.util.List;

public class VeiculoRN implements CrudGenerido<Veiculo>
{
	private VeiculoBD bd;

	public VeiculoRN() {
		this.bd = new VeiculoBD();
	}

	public void validar(String campo, String valor) {
		String mensagemValidacao = null;

		switch (campo) {
			case "modelo":
				mensagemValidacao = Validadores.tamanhoMinimoMaximo("Modelo", valor, 1, 20, true);
				break;
			case "fabricante":
				mensagemValidacao = Validadores.tamanhoMinimoMaximo("Fabricante", valor, 1, 40, true);
				break;
			case "ano":
				int anoMaximo = Calendar.getInstance().get(Calendar.YEAR) + 1;
				mensagemValidacao = Validadores.numeroInteiro("Ano", valor, 1910, anoMaximo, true);
				break;
			case "cilindradas":
				mensagemValidacao = Validadores.numeroInteiro("Cilindradas", valor, 20, 10000, true);
				break;
			case "preco":
				mensagemValidacao = Validadores.validarPreco("Preço", valor, 500d, 50000000d, true);
				break;
		}

		if (mensagemValidacao != null) {
			throw new RuntimeException(mensagemValidacao);
		}
	}

	@Override
	public void salvar(Veiculo bean) {
		try {
			bd.salvar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar salvar o veículo");
		}
	}

	@Override
	public void excluir(Veiculo bean) {
		try {
			bd.excluir(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar excluir o veículo");
		}
	}

	@Override
	public List<Veiculo> listar(Veiculo bean) {
		try {
			return bd.listar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar listar os veículos");
		}
	}

	@Override
	public Veiculo consultar(Veiculo bean) {
		try {
			return bd.consultar(bean);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar consultar um veículo");
		}
	}
}
