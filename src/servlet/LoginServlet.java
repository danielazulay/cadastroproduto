package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.usuario;
import dao.daoLogin;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private daoLogin daologin = new daoLogin();
	
	usuario Usuario = new usuario();
	
    public LoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		try {
			if(daologin.validarLogin(login, senha)) {
				
				RequestDispatcher acesso = request.getRequestDispatcher("acessoliberado.jsp");
				acesso.forward(request, response);
				
			}else {
				
				RequestDispatcher acessonegado   = request.getRequestDispatcher("acessonegado.jsp");
				acessonegado.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
