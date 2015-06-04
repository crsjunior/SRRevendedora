package com.senac.mb;

import com.senac.bean.Cliente;
import com.senac.rn.ClienteRN;
import com.senac.utilidades.EnviarMensagen;
import com.senac.utilidades.Mensageiro;
import com.senac.utilidades.Valores;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ClienteMB
{
	private Cliente cliente;
	private ClienteRN rn;
	private Mensageiro mensageiro;
	private List<String> listaComboUf;

	public ClienteMB() {
		this.cliente = new Cliente();
		this.rn = new ClienteRN();
		this.mensageiro = new Mensageiro();
		this.listaComboUf = new ArrayList<>(Arrays.asList(Valores.UFS_NOMES));
		this.listaComboUf.add(0, "Escolha o estado:");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Mensageiro getMensageiro() {
		return mensageiro;
	}

	public List<String> getListaComboUf() {
		return listaComboUf;
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
		this.cliente = new Cliente();
	}

	public String salvar() {
		try {
			rn.salvar(cliente);
			resetar();
			return "listarCliente";
		} catch (RuntimeException e) {
		}
		return null;
	}

	public String excluir() {
		try {
			rn.excluir(cliente);
			return "listarCliente";
		} catch (RuntimeException e) {
		}
		return null;
	}
	
	public void excluir(Cliente reg) {
		try {
			rn.excluir(reg);
		} catch (RuntimeException e) {
		}
	}

	public void editar(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> listar() {
		try {
			return rn.listar(cliente);
		} catch (RuntimeException e) {
		}
		return null;
	}
}
