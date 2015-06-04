package com.senac.mb;

import com.senac.bd.ClienteBD;
import com.senac.bd.VeiculoBD;
import com.senac.bean.Cliente;
import com.senac.bean.Veiculo;
import com.senac.bean.Venda;
import com.senac.controller.Sessao;
import com.senac.rn.VendaRN;
import com.senac.utilidades.EnviarMensagen;
import com.senac.utilidades.Mensageiro;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class VendaMB
{
	private Venda venda;
	private VendaRN rn;
	private Mensageiro mensageiro;
	private Integer idUsuario;
	private Integer idCliente;
	private Integer idVeiculo;
	private List<Cliente> listaClientes;
	private List<Veiculo> listaVeiculos;

	public VendaMB() {
		this.venda = new Venda();
		this.rn = new VendaRN();
		this.mensageiro = new Mensageiro();
		this.idUsuario = 0;
		this.idCliente = 0;
		this.idVeiculo = 0;
		this.listaClientes = new ClienteBD().listar(new Cliente());
		this.listaVeiculos = new VeiculoBD().listar(new Veiculo());
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public List<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public Mensageiro getMensageiro() {
		return mensageiro;
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
		this.venda = new Venda();
	}

	public String salvar() {
		try {
			venda.setCliente(new ClienteBD().consultar(new Cliente(idCliente)));
			venda.setVeiculo(new VeiculoBD().consultar(new Veiculo(idVeiculo)));
			venda.setUsuario(Sessao.getInstance().getUsuario());
			venda.setDataVenda(new Date());
			rn.salvar(venda);
			resetar();
			return "listarVenda";
		} catch (RuntimeException e) {
		}
		return null;
	}

	public String excluir() {
		try {
			rn.excluir(venda);
			return "listarVenda";
		} catch (RuntimeException e) {
		}
		return null;
	}

	public void excluir(Venda reg) {
		try {
			rn.excluir(reg);
		} catch (RuntimeException e) {
		}
	}

	public void editar(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> listar() {
		try {
			return rn.listar(venda);
		} catch (RuntimeException e) {
		}
		return null;
	}
}
