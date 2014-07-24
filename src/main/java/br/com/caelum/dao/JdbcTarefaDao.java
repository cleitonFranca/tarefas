package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.models.Tarefas;

@Repository
public class JdbcTarefaDao {
	

	private Connection connection;

	@Autowired
	public JdbcTarefaDao(DataSource dataSource) {

		try {
			this.connection = dataSource.getConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Tarefas tarefa) {
		String sql = "insert into tarefas" + "(descricao, finalizado)"
				+ "values (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException("Erro de inserção de dados", e);
		}
	}

	public void altera(Tarefas tarefa) {
		String sql = "update tarefas set descricao = ?, finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, tarefa.getDataFinalizacao() != null ? new Date(
					tarefa.getDataFinalizacao().getTimeInMillis()) : null);
			stmt.setLong(4, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void finaliza(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefas não deve ser nula.");
		}

		String sql = "update tarefas set finalizado = ?, dataFinalizacao = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefas não deve ser nula.");
		}

		String sql = "delete from tarefas where id = ?";
		PreparedStatement stmt;

		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

	public List<Tarefas> lista() {
		try {
			List<Tarefas> tarefas = new ArrayList<Tarefas>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Tarefas tarefa = new Tarefas();
				// adiciona a tarefa na lista
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				Date data = rs.getDate("dataFinalizacao");

				if (data != null) {
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(data);
					tarefa.setDataFinalizacao(dataFinalizacao);
				}

				tarefas.add(tarefa);
			}

			rs.close();
			stmt.close();

			return tarefas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefas> listaLastId() {
		try {
			List<Tarefas> tarefas = new ArrayList<Tarefas>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select max(id) as id from tarefas");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Tarefas tarefa = new Tarefas();

				tarefa.setId(rs.getLong("id"));

				tarefas.add(tarefa);
			}

			rs.close();
			stmt.close();

			return tarefas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Tarefas buscaPorId(Long id) {

		if (id == null) {
			throw new IllegalStateException("Id da tarefas não deve ser nula.");
		}

		try {
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from tarefas where id = ?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return populaTarefa(rs);
			}

			rs.close();
			stmt.close();

			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Tarefas populaTarefa(ResultSet rs) throws SQLException {
		Tarefas tarefa = new Tarefas();

		// popula o objeto tarefa
		tarefa.setId(rs.getLong("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setFinalizado(rs.getBoolean("finalizado"));

		// popula a data de finalizacao da tarefa, fazendo a conversao
		Date data = rs.getDate("dataFinalizacao");
		if (data != null) {
			Calendar dataFinalizacao = Calendar.getInstance();
			dataFinalizacao.setTime(data);
			tarefa.setDataFinalizacao(dataFinalizacao);
		}
		return tarefa;
	}

}
