package com.senac.mb;

import com.senac.bean.Veiculo;
import com.senac.rn.VeiculoRN;
import com.senac.utilidades.EnviarMensagen;
import com.senac.utilidades.Mensageiro;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class VeiculoMB
{
	private Veiculo veiculo;
	private VeiculoRN rn;
	private Mensageiro mensageiro;
	private String ano;
	private String cilindradas;
	private String preco;

	public VeiculoMB() {
		this.veiculo = new Veiculo();
		this.rn = new VeiculoRN();
		this.mensageiro = new Mensageiro();
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
		try {
			this.veiculo.setAno(Integer.parseInt(ano));
		} catch (NumberFormatException e) {
		}
	}

	public String getCilindradas() {
		return cilindradas;
	}

	public void setCilindradas(String cilindradas) {
		this.cilindradas = cilindradas;
		try {
			this.veiculo.setCilindradas(Integer.parseInt(cilindradas));
		} catch (NumberFormatException e) {
		}
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
		try {
			this.veiculo.setPreco(Double.parseDouble(preco));
		} catch (NumberFormatException e) {
		}
	}

	public Mensageiro getMensageiro() {
		return mensageiro;
	}

	public void validar(FacesContext context, UIComponent toValidate, Object value) {
		String campo = toValidate.getClientId();
		String valor = (value == null ? "" : value.toString());

		try {
			rn.validar(campo, valor);
		} catch (RuntimeException e) {
			EnviarMensagen.enviarErroValidacao(toValidate, e.getMessage());
		}
	}

	public void resetar() {
		this.veiculo = new Veiculo();
		this.ano = "";
		this.cilindradas = "";
		this.preco = "";
	}

	public String salvar() {
		try {
			rn.salvar(veiculo);
			resetar();
			return "listarVeiculo";
		} catch (RuntimeException e) {
		}
		return null;
	}

	public String excluir() {
		try {
			rn.excluir(veiculo);
			return "listarVeiculo";
		} catch (RuntimeException e) {
		}
		return null;
	}
	
	public void excluir(Veiculo reg) {
		try {
			rn.excluir(reg);
		} catch (RuntimeException e) {
		}
	}

	public void editar(Veiculo veiculo) {
		this.veiculo = veiculo;
		this.ano = veiculo.getAno().toString();
		this.cilindradas = veiculo.getCilindradas().toString();
		this.preco = veiculo.getPreco().toString();
	}

	public List<Veiculo> listar() {
		try {
			return rn.listar(veiculo);
		} catch (RuntimeException e) {
		}
		return null;
	}
}
