package com.senac.utilidades;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validadores
{
	public static String tamanhoMinimo(String nomeCampo, String valor, int tamanhoMinimo, boolean obrigatorio) {
		if (!valor.isEmpty()) {
			if (valor.length() < tamanhoMinimo) {
				return nomeCampo + ": mínimo de " + tamanhoMinimo + " caracteres";
			}
		} else if (obrigatorio) {
			return nomeCampo + " é obrigatório";
		}

//		if (valor.isEmpty()) {
//			if (obrigatorio) {
//				return " é obrigatório";
//			} else {
//				return null;
//			}
//		}
//		if (valor.length() < tamanhoMinimo) {
//			return ": mínimo de " + tamanhoMinimo + " caracteres";
//		}
		return null;
	}

	public static String tamanhoMinimoMaximo(String nomeCampo, String valor, int tamanhoMinimo, int tamanhoMaximo, boolean obrigatorio) {
		if (!valor.isEmpty()) {
			if (valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo) {
				return nomeCampo + ": deve ter entre " + tamanhoMinimo + " e " + tamanhoMaximo + " caracteres";
			}
		} else if (obrigatorio) {
			return nomeCampo + " é obrigatório";
		}

//		if (valor.isEmpty()) {
//			if (obrigatorio) {
//				return nomeCampo + " é obrigatório";
//			} else {
//				return null;
//			}
//		}
//		if (valor.length() < tamanhoMinimo || valor.length() > tamanhoMaximo) {
//			return nomeCampo + ": deve ter entre " + tamanhoMinimo + " e " + tamanhoMaximo + " caracteres";
//		}
		return null;
	}

	/**
	 * Valida um valor como sendo um numero inteiro, podendo-se especificar os
	 * valores minimo e maximo permitidos.
	 *
	 * @param nomeCampo O nome do campo.
	 * @param valor O valor a ser validado.
	 * @param min O valor minimo permitido. Se <code>null</code>, sera ignorado.
	 * @param max O valor maximo permitido. Se <code>null</code>, sera ignorado.
	 * @param obrigatorio Indica se o valor eh de preenchimento obrigatorio.
	 * @return Se o valor eh valido, retorna <code>null</code>, caso contrario,
	 * retorna uma <code>String</code> com a descricao do erro.
	 */
	public static String numeroInteiro(String nomeCampo, String valor, Integer min, Integer max, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return nomeCampo + " é obrigatório";
			} else {
				return null;
			}
		}
		int numero;
		try {
			numero = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			return nomeCampo + ": deve ser um número inteiro";
		}
		//sem especificacao de valores minimo e maximo
		if (min == null && max == null) {
			return null;
		}
		//se minimo == null, entao, validar maximo
		if (min == null) {
			if (numero > max) {
				return nomeCampo + ": deve ser no máximo " + Integer.toString(max);
			}
			return null;
		}
		//se maximo == null, entao, validar minimo
		if (max == null) {
			if (numero < min) {
				return nomeCampo + ": deve ser no mínimo " + Integer.toString(min);
			}
			return null;
		}
		//validar minimo e maximo
		if (numero < min || numero > max) {
			return nomeCampo + ": deve ser entre " + Integer.toString(min) + " e " + Integer.toString(max);
		}
		return null;
	}

	public static String validarPreco(String nomeCampo, String valor, Double min, Double max, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return nomeCampo + " é obrigatório";
			} else {
				return null;
			}
		}
		Double numero;
		DecimalFormat df = new DecimalFormat("#,##0.00");
		try {
			numero = df.parse(valor).doubleValue();
		} catch (ParseException e) {
			return nomeCampo + ": formato de valor inválido";
		}
		//sem especificacao de valores minimo e maximo
		if (min == null && max == null) {
			return null;
		}
		//se minimo == null, entao, validar maximo
		if (min == null) {
			if (numero > max) {
				return nomeCampo + ": deve ser no máximo " + String.format("%.2f", max);
			}
			return null;
		}
		//se maximo == null, entao, validar minimo
		if (max == null) {
			if (numero < min) {
				return nomeCampo + ": deve ser no mínimo " + String.format("%.2f", min);
			}
			return null;
		}
		//validar minimo e maximo
		if (numero < min || numero > max) {
			return nomeCampo + ": deve ser entre " + String.format("%.2f", min) + " e " + String.format("%.2f", max);
		}
		return null;
	}

	public static String validarLogin(String valor) {
		if (valor.isEmpty()) {
			return "Login é obrigatório";
		}
		if (valor.length() < 3) {
			return "Login: mínimo de 3 caracteres";
		}
		return null;
	}

	public static String validarSenha(String valor) {
		if (valor.isEmpty()) {
			return "Senha é obrigatório";
		}
		if (valor.length() < 6) {
			return "Senha: mínimo de 6 caracteres";
		}
		return null;
	}

	public static String validarCPF(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "CPF é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() != 11) {
			return "CPF: deve possuir 11 caracteres";
		}
		try {
			Long.parseLong(valor);
		} catch (NumberFormatException e) {
			return "CPF: deve possuir somente números";
		}
		return null;
	}

	public static String validarCNPJ(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "CNPJ é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() != 14) {
			return "CNPJ: deve possuir 14 caracteres";
		}
		try {
			Long.parseLong(valor);
		} catch (NumberFormatException e) {
			return "CNPJ: deve possuir somente números";
		}
		return null;
	}

	public static String validarNome(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "Nome é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() < 3) {
			return "Nome: mínimo de 3 caracteres";
		}
		return null;
	}

	public static String validarEndereco(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "Endereço é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() < 3) {
			return "Endereço: mínimo de 3 caracteres";
		}
		return null;
	}

	public static String validarCidade(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "Cidade é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() < 2) {
			return "Cidade: mínimo de 2 caracteres";
		}
		return null;
	}

	public static String validarCEP(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "CEP é obrigatório";
			} else {
				return null;
			}
		}
		if (valor.length() != 8) {
			return "CEP: deve possuir 8 caracteres";
		}
		try {
			Long.parseLong(valor);
		} catch (NumberFormatException e) {
			return "CEP: deve possuir somente números";
		}
		return null;
	}

	public static String validarUfSigla(String valor, boolean obrigatorio) {
		for (int i = 0; i < Valores.UFS_SIGLAS.length; i++) {
			if (valor.equals(Valores.UFS_SIGLAS[i])) {
				return null;
			}
		}
		if (obrigatorio) {
			return "UF inválido";
		}
		return null;
	}

	public static String validarUfNome(String valor, boolean obrigatorio) {
		for (int i = 0; i < Valores.UFS_NOMES.length; i++) {
			if (valor.equals(Valores.UFS_NOMES[i])) {
				return null;
			}
		}
		if (obrigatorio) {
			return "UF inválido";
		}
		return null;
	}

	public static String validarTelefone(String valor, boolean obrigatorio) {
		if (!valor.isEmpty()) {
			String regex = "^(?:[0-9] ?){7,10}[0-9]$";
			Pattern pattern = Pattern.compile(regex);
			if (!pattern.matcher(valor).matches()) {
				return "Telefone inválido";
			}
		} else if (obrigatorio) {
			return "Telefone é obrigatório";
		}
		return null;
	}

	public static String validarEmail(String valor, boolean obrigatorio) {
		if (valor.isEmpty()) {
			if (obrigatorio) {
				return "E-mail é obrigatório";
			} else {
				return null;
			}
		}
		return null;
	}
}
