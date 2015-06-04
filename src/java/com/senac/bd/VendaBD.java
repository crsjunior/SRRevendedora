package com.senac.bd;

import com.senac.bean.Venda;
import com.senac.contracts.CrudGenerido;
import com.senac.utilidades.Constantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class VendaBD implements CrudGenerido<Venda>
{
	private EntityManager em;

	public VendaBD() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		this.em = emf.createEntityManager();
	}

	@Override
	public void salvar(Venda bean) {
		try {
			em.getTransaction().begin();
			em.merge(bean);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluir(Venda bean) {
		try {
			em.getTransaction().begin();
			em.remove(em.find(Venda.class, bean.getIdVenda()));
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Venda> listar(Venda bean) {
		StringBuilder sb = new StringBuilder("SELECT v FROM Venda v WHERE 1=1 ");
		if (bean.getIdVenda() != null && bean.getIdVenda() != 0) {
			sb.append("and v.idVenda like :v ");
		}
		sb.append("order by v.dataVenda");

		Query q = em.createQuery(sb.toString());
		if (bean.getIdVenda() != null && bean.getIdVenda() != 0) {
			q.setParameter("v", bean.getIdVenda());
		}

		return q.getResultList();
	}

	@Override
	public Venda consultar(Venda bean) {
		return em.find(Venda.class, bean.getIdVenda());
	}
}
