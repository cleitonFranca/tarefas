package br.com.caelum.dao;



import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.caelum.tarefas.models.Tarefas;

@Repository
public class JpaTarefaDao implements TarefaDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public Tarefas buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Tarefas.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Tarefas> lista() {
		// TODO Auto-generated method stub
		return manager.createQuery("select t from Tarefas t").getResultList();
	}

	@Transactional  
	public void adiciona(Tarefas tarefa) {
		manager.persist(tarefa);
	}

	@Transactional
	public void altera(Tarefas tarefa) {
		manager.merge(tarefa);
		
	}

	@Transactional
	public void remove(Tarefas tarefa) {
		Tarefas tarefaAremover = buscaPorId(tarefa.getId());
		manager.remove(tarefaAremover);
		
	}

	@Transactional
	public void finaliza(Long id) {
		/* Esse metodo tem a particularidade de setar valores
		sem usar o hibernate manager.merger() ou outro qualquer.*/
		
		Tarefas tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		
	}
}