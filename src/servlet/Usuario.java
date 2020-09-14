package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.usuario;

import dao.daoCadastro;

@WebServlet("/salvarusuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private daoCadastro dao = new daoCadastro();

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				dao.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastro.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				usuario users = dao.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastro.jsp");
				request.setAttribute("user", users);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {

				RequestDispatcher view = request.getRequestDispatcher("/cadastro.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {

				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");

			usuario user = new usuario();

			user.setId(!id.trim().isEmpty() ? Long.parseLong(id) : 0);

			user.setLogin(login);
			user.setSenha(senha);
			user.setNome(nome);
			user.setTelefone(telefone);

			try {
				String msg = null;

				boolean podeinserir = true;

				if (login == null || login.isEmpty()) {
					msg = "Login deve ser usado";
					podeinserir = false;
					request.setAttribute("msg", msg);
			
				} else
				if (senha == null || senha.isEmpty()) {
					msg = "senha deve ser usado";
					podeinserir = false;
					request.setAttribute("msg", msg);
			
				} else
				if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser usado";
					podeinserir = false;
					request.setAttribute("msg", msg);
			
				} else

				if (id == null || id.isEmpty() && !dao.validarLogin(login)) {

					request.setAttribute("msg", "usuario ja existe com o mesmo login");
				}
				else if (id == null || id.isEmpty() && dao.validarLogin(login)) {

					dao.salvar(user);
				} else if (id != null && !id.isEmpty()) {
					if (!dao.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "usuario ja existe com o mesmo login");
					} else {
						dao.atualizar(user);
					}

				}
				
				RequestDispatcher view = request.getRequestDispatcher("cadastro.jsp");
				request.setAttribute("usuarios", dao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
