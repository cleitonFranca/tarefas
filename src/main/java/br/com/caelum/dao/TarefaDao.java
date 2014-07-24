package br.com.caelum.dao;

import java.util.List;
import br.com.caelum.tarefas.models.Tarefas;

public interface TarefaDao {
	
	Tarefas buscaPorId(Long id);
	List<Tarefas> lista();
	void adiciona(Tarefas tarefa);
	void altera(Tarefas tarefa);
	void remove(Tarefas tarefa);
	void finaliza(Long id );
}
