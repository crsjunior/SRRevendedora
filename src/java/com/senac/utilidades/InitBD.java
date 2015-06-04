package com.senac.utilidades;

import com.senac.bean.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "InitBD", urlPatterns = { "/InitBD" }, loadOnStartup = 1)
public class InitBD extends HttpServlet
{
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println(">>>> DRIVER MYSQL NAO ENCONTRADO <<<<");
		} catch (Exception e) {
			throw new RuntimeException(">>>> DRIVER MYSQL NAO ENCONTRADO : ", e);
		}

		System.out.println(">>>> INICIALIZANDO <<<<");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();

		Usuario usuario = new Usuario(1);
		usuario.setNome("Administrador");
		usuario.setEmail("admin@admin.com");
		usuario.setSenha("administrador");
		usuario.setGerente(true);
		em.getTransaction().begin();
		em.merge(usuario);
		em.getTransaction().commit();

		System.out.println(">>>> BANCO DE DADOS INICIALIZADO COM SUCESSO <<<<");
	}
}
