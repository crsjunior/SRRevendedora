package com.senac.bd;

import com.senac.bean.Cliente;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ClienteBD implements CrudGenerido<Cliente>
{
	private EntityManager em;

	public ClienteBD() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		this.em = emf.createEntityManager();
	}

	@Override
	public void salvar(Cliente bean) {
		try {
			em.getTransaction().begin();
			em.merge(bean);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Cliente bean) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(Cliente.class, bean.getIdCliente()));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Cliente> listar(Cliente bean) {
		StringBuilder sb = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1 ");
		if (bean.getIdCliente() != null && bean.getIdCliente() != 0) {
			sb.append("AND c.idCliente :c ");
		}
		if (bean.getNome() != null && !bean.getNome().equals("")) {
			sb.append("AND c.nome :n ");
		}
		sb.append("ORDER BY c.nome");

		Query q = em.createQuery(sb.toString());
		if (bean.getIdCliente() != null && bean.getIdCliente() != 0) {
			q.setParameter("c", bean.getIdCliente());
		}
		if (bean.getNome() != null && !bean.getNome().equals("")) {
			q.setParameter("n", bean.getNome());
		}

		return q.getResultList();
	}

	@Override
	public Cliente consultar(Cliente bean) {
		return em.find(Cliente.class, bean.getIdCliente());
	}
}
