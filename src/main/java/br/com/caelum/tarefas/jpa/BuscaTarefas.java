package br.com.caelum.tarefas.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.caelum.tarefas.models.Tarefas;

public class BuscaTarefas {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		
		// cuidado, use o import javax.persistence.Query
		Query query = manager.createQuery("select t from Tarefas as t where t.finalizado = :paramFinalizado");
		query.setParameter("paramFinalizado", true);
		
		@SuppressWarnings("unchecked")
		List<Tarefas> lista = query.getResultList();
		
		for (Tarefas t : lista) {
			System.out.println(t.getDescricao()+"\n"+t.getId());
		}
		
		manager.close();
	}
}
