package com.senac.bd;

import com.senac.bean.Veiculo;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class VeiculoBD implements CrudGenerido<Veiculo>
{
	private EntityManager em;

	public VeiculoBD() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		this.em = emf.createEntityManager();
	}

	@Override
	public void salvar(Veiculo bean) {
		try {
			em.getTransaction().begin();
			em.merge(bean);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Veiculo bean) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(Veiculo.class, bean.getIdVeiculo()));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Veiculo> listar(Veiculo bean) {
		StringBuilder sb = new StringBuilder("SELECT v FROM Veiculo v WHERE 1=1 ");
		if (bean.getIdVeiculo() != null && !bean.getIdVeiculo().equals("")) {
			sb.append("AND v.idVeiculo :v ");
		}
		if (bean.getModelo() != null && !bean.getModelo().equals("")) {
			sb.append("AND v.modelo :m ");
		}
		sb.append("ORDER BY v.modelo");

		Query q = em.createQuery(sb.toString());
		if (bean.getIdVeiculo() != null && !bean.getIdVeiculo().equals("")) {
			q.setParameter("v", bean.getIdVeiculo());
		}
		if (bean.getModelo() != null && !bean.getModelo().equals("")) {
			q.setParameter("m", bean.getModelo());
		}

		return q.getResultList();
	}

	@Override
	public Veiculo consultar(Veiculo bean) {
		return em.find(Veiculo.class, bean.getIdVeiculo());
	}
}
