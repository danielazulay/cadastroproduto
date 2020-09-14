package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Beans.usuario;
import connection.SingleConnection;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class daoCadastro {
	
	
private Connection connection;
	
	public daoCadastro() {
	connection = SingleConnection.getConnection();
	}

	
public void salvar(usuario user) {
	try {
		String sql ="insert into usuarios (login,senha,nome,telefone) values(?,?,?,?)";
		PreparedStatement insert = connection.prepareStatement(sql);
	
		insert.setString(1, user.getLogin());
		insert.setString(2, user.getSenha());
		insert.setString(3, user.getNome());
		insert.setString(4, user.getTelefone());
		insert.execute();
		
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
public List<usuario> listar() throws Exception{
	
	List<usuario> listar = new ArrayList<usuario>();
	
	String sql ="select *from usuarios";
	
	PreparedStatement statement = connection.prepareStatement(sql);
	ResultSet resultSet = statement.executeQuery();
	while(resultSet.next()) {
		usuario user = new usuario();
		user.setId(resultSet.getLong("id"));
		user.setLogin(resultSet.getString("login"));
		user.setSenha(resultSet.getString("senha"));
		user.setNome(resultSet.getString("nome"));
		user.setTelefone(resultSet.getString("telefone"));
		listar.add(user);
	}
	
	return listar;
	
	
}


public void delete (String login) {
	try {
	String sql="delete from usuarios where login='"+login+"'";
	PreparedStatement preparar = connection.prepareStatement(sql);
	preparar.execute();
	
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


public usuario consultar(String login) throws Exception {

String sql ="select * from usuarios where login='"+login+"'";
	
PreparedStatement prepareStatement= connection.prepareStatement(sql);
ResultSet resultado = prepareStatement.executeQuery();


if(resultado.next()) {
	usuario user = new usuario();
	user.setId(resultado.getLong("id"));
	user.setLogin(resultado.getString("login"));
	user.setSenha(resultado.getString("senha"));
	user.setNome(resultado.getString("nome"));
	user.setTelefone(resultado.getString("telefone"));
	
	return user;
}
return null;

}

public boolean validarLogin(String login) throws Exception {

String sql ="select count(1) as qtd from usuarios where login='"+login+"'";
	
PreparedStatement prepareStatement= connection.prepareStatement(sql);
ResultSet resultado = prepareStatement.executeQuery();


if(resultado.next()) {
	return resultado.getInt("qtd")<=0;
}
return false;

}


public boolean validarLoginUpdate(String login, String id) throws Exception {

String sql ="select count(1) as qtd from usuarios where login='"+login+"' and id <>'"+id+"'";
	
PreparedStatement prepareStatement= connection.prepareStatement(sql);
ResultSet resultado = prepareStatement.executeQuery();


if(resultado.next()) {
	return resultado.getInt("qtd")<=0;
}
return false;

}



public void atualizar(usuario user) {
try {
		String sql ="update usuarios set login = ?, senha = ? ,nome=? ,telefone=?   where id ="+user.getId();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getSenha());
		preparedStatement.setString(3, user.getNome());
		preparedStatement.setString(4, user.getTelefone());
		preparedStatement.executeUpdate();
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





}
