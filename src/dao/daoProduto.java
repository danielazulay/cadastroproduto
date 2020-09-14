package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.produto;
import connection.SingleConnection;

public class daoProduto {
	
	private Connection connection;
	
	public daoProduto() {
		
		connection=SingleConnection.getConnection();
	}

	public void salvar(produto product)  {
	try {
		
		String sql= "insert into produtos(nome,quantidade,preco) values(?,?,?)";
			PreparedStatement inserir = connection.prepareStatement(sql);
			

			inserir.setString(1, product.getNome());
			inserir.setDouble(2, product.getPreco());
			inserir.setDouble(3, product.getPreco());
			
			inserir.execute();
			
			connection.commit();
		} catch (SQLException e) {
			
			e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	
		
		
	}

	public List<produto>listar() {
		
		List<produto> listar = new ArrayList<produto>();
		
		try {
		String sql = "select * from produtos";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet result = preparedStatement.executeQuery();
		
		while(result.next()){
			produto product = new produto();
			product.setId(result.getLong("id"));	
		product.setNome(result.getString("nome"));
		product.setPreco(Double.parseDouble((result.getString("preco"))));
		product.setQuantidade(Double.parseDouble(result.getString("quantidade")));	
		
		listar.add(product);
		}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		
		return listar;
	}

	public void deletar(String id) {


		try {
			
			String sql ="delete from produtos where id='"+id+"'";
			
			PreparedStatement deletar= connection.prepareStatement(sql);
			deletar.execute();
			connection.commit();
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	public void editar(produto product) {
	
		try {
		String sql= "update produtos set nome=?, quantidade=?, preco=? where id="+product.getId();
	
		PreparedStatement editar = connection.prepareStatement(sql);
		editar.setString(1, product.getNome());
		editar.setDouble(2, product.getQuantidade());
	
			editar.setDouble(3, product.getPreco());
			
			editar.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	public produto buscar(String id) throws SQLException {
		
		String sql = "select * from produtos where id='"+id+"'";
		PreparedStatement buscar = connection.prepareStatement(sql);
		ResultSet resultado = buscar.executeQuery();
		if(resultado.next()) {
      produto product = new produto();

     product.setId(resultado.getLong("id"));
      product.setNome(resultado.getString("nome"));
      product.setPreco(Double.parseDouble(resultado.getString("quantidade")));
      product.setQuantidade(Double.parseDouble(resultado.getString("preco")));
		
      return product;
		}

		
		
		
		return null;
	
		
	}

}
