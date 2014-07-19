package br.com.caelum.tarefas.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.caelum.tarefas.models.Tarefas;

public class CarregaTarefa {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
		EntityManager manager = factory.createEntityManager();
		
		Tarefas encontrada = manager.find(Tarefas.class, 1L);
		System.out.println(encontrada.getDescricao());
		
		manager.close();
	}
}
