package br.com.caelum.tarefas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.models.Tarefas;

public class AteraTarefa {
	
	public static void main(String[] args) {
		
		Tarefas tarefa = new Tarefas();
		tarefa.setId(3L);
		tarefa.setDescricao("Estudando JPA e Hibernate. Alterando tarefa");
		tarefa.setFinalizado(false);
		tarefa.setDataFinalizacao(null);
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(tarefa);
		manager.getTransaction().commit();
		
		System.out.println("Tarefa: "+tarefa.getId()+" Alterada com sucesso!");
		
		
		manager.close();
	}
}
