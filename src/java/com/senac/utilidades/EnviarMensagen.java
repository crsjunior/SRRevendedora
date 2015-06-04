package com.senac.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

public class EnviarMensagen
{
	public static void enviarErroValidacao(UIComponent elemento, String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		((UIInput) elemento).setValid(false);
		FacesMessage facesMessage = new FacesMessage(mensagem);
		context.addMessage(elemento.getClientId(context), facesMessage);
	}

	public static void addMsgNormal(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensagem));
	}

	public static void addMsgErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, ""));
	}
}
