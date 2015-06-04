package com.senac.bd;

import com.senac.bean.Usuario;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UsuarioBD implements CrudGenerido<Usuario>
{
	private EntityManager em;

	public UsuarioBD() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		this.em = emf.createEntityManager();
	}

	@Override
	public void salvar(Usuario bean) {
		try {
			em.getTransaction().begin();
			em.merge(bean);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Usuario bean) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(Usuario.class, bean.getIdUsuario()));
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar excluir o usuário");
		}
	}

	@Override
	public List<Usuario> listar(Usuario bean) {
		StringBuilder sb = new StringBuilder("SELECT u FROM Usuario u WHERE 1=1 ");
		if (bean.getIdUsuario() != null && bean.getIdUsuario() != 0) {
			sb.append("and u.idUsuario like :u ");
		}
		if (bean.getNome() != null && !bean.getNome().equals("")) {
			sb.append("and u.nome like :n ");
		}
		if (bean.getEmail() != null && !bean.getEmail().equals("")) {
			sb.append("and u.email like :e ");
		}
		if (bean.getSenha() != null && !bean.getSenha().equals("")) {
			sb.append("and u.senha like :s ");
		}
		sb.append("order by u.nome");

		Query q = em.createQuery(sb.toString());
		if (bean.getIdUsuario() != null && bean.getIdUsuario() != 0) {
			q.setParameter("u", bean.getIdUsuario());
		}
		if (bean.getNome() != null && !bean.getNome().equals("")) {
			q.setParameter("n", bean.getNome());
		}
		if (bean.getEmail() != null && !bean.getEmail().equals("")) {
			q.setParameter("e", bean.getEmail());
		}
		if (bean.getSenha() != null && !bean.getSenha().equals("")) {
			q.setParameter("s", bean.getSenha());
		}

		return q.getResultList();
	}

	@Override
	public Usuario consultar(Usuario bean) {
		return em.find(Usuario.class, bean.getIdUsuario());
	}
}
