package br.com.caelum.tarefas.controllers;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.dao.TarefaDao;
import br.com.caelum.tarefas.models.Tarefas;

@Transactional
@Controller
public class TarefasController {

	@Autowired
	private TarefaDao dao;
	private Calendar dataFinalizacao;
	private DateFormat simpleDate;

	public TarefasController() {

		this.dataFinalizacao = Calendar.getInstance();
		this.simpleDate = SimpleDateFormat.getDateInstance();

	}

	@RequestMapping("novaTarefa")
	public String form() {

		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefas tarefa, BindingResult result,
			Model model) throws SQLException {

		try {

			if (result.hasFieldErrors("descricao")) {
				return "tarefa/formulario";
			}

			dao.adiciona(tarefa);
			model.addAttribute("tarefaMsg", "Registro adicionado com sucesso!");

		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		}

		return "redirect:listaTarefas";

	}

	@RequestMapping("alteraTarefa")
	public String altera(@Valid Tarefas tarefa, BindingResult result,
			Model model, HttpServletRequest req, Date date) throws SQLException {

		try {

			if (req.getParameter("dataFinalizacao") == "") {
				dao.altera(tarefa);

			} else {
				date = simpleDate.parse(req.getParameter("dataFinalizacao"));
				dataFinalizacao.setTime(date);

				tarefa.setDataFinalizacao(dataFinalizacao);

				dao.altera(tarefa);
				model.addAttribute("tarefaMsg",
						"Registro Atualizado com sucesso!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		}

		return "redirect:listaTarefas";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) throws SQLException {

		try {
			model.addAttribute("tarefas", dao.lista());

		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		}

		return "tarefa/lista";
	}

	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id, Model model) {

		dao.finaliza(id);
		model.addAttribute("tarefa", dao.buscaPorId(id));

		return "tarefa/finalizada";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefas tarefa) {

		dao.remove(tarefa);
		return "redirect:listaTarefas";
		// response.setStatus(200);

	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) throws SQLException {

		try {
			model.addAttribute("tarefa", dao.buscaPorId(id));

		} catch (Exception e) {
			// TODO: handle exception
			throw new SQLException(e);
		}

		return "tarefa/mostra";
	}

}
