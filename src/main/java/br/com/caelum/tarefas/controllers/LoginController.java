package br.com.caelum.tarefas.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.dao.JdbcUsuarioDao;
import br.com.caelum.tarefas.models.Usuario;

@Controller
public class LoginController {

	private final JdbcUsuarioDao dao;

	@Autowired
	public LoginController(JdbcUsuarioDao dao) {

		this.dao = dao;
	}

	@RequestMapping("loginForm")
	public String loginForm() {

		return "login/formulario-login";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session, Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);

		if (dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			model.addAttribute("serverTime", formattedDate );
			return "home";
		}

		return "redirect:loginForm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
