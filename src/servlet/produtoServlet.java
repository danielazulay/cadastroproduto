package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.produto;
import dao.daoProduto;


@WebServlet("/salvarproduto")
public class produtoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private daoProduto dao = new daoProduto();


    public produtoServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao= request.getParameter("acao");
		String produto= request.getParameter("produto");
		try {		
		if(acao.equalsIgnoreCase("delete")) {
	
			dao.deletar(produto);
			RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
			request.setAttribute("produtos", dao.listar());
			view.forward(request, response);
		
			
			
		}else if(acao.equalsIgnoreCase("editar")) {
			
		
				produto product  =dao.buscar(produto);
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
			request.setAttribute("produto", product);
			view.forward(request, response);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	
		
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String quantidade = request.getParameter("quantidade");
		String preco = request.getParameter("preco");
		
		produto product = new produto();
		
		
		product.setId(!id.isEmpty() ? Long.parseLong(id) : null);
		product.setNome(nome);
		product.setQuantidade(Double.parseDouble(quantidade));
		product.setPreco(Double.parseDouble(preco));
		
		if(id==null||id.isEmpty()) {
		dao.salvar(product);
	
	
		
		}else {
			
			dao.editar(product);
		}
			
		
		try {
		RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
		request.setAttribute("produtos", dao.listar());
		view.forward(request, response);
	
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
