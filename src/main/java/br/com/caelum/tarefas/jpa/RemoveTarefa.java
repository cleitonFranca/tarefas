package br.com.caelum.tarefas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.models.Tarefas;

public class RemoveTarefa {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		
		Tarefas encontrada = manager.find(Tarefas.class, 2L);
		System.out.println("Tarefa: "+encontrada.getId()+" removida com sucesso!");
		
		manager.getTransaction().begin();
		manager.remove(encontrada);
		manager.getTransaction().commit();
		
		manager.close();
	}
}
