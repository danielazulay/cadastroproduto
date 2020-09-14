<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="resources/form.css">
<link rel="stylesheet" href="resources/table.css">
<title>Insert title here</title>
</head>
<body>
<h1>Cadastre o novo usuario</h1>


<center><h3 style="color: red">${msg}</h3></center>
<center><h3 style="color: red">${msg1}</h3></center>

<form action="salvarusuario" method="post" id="formUser" onsubmit="return validarcampos() ? true : false;">

<ul class="form-style-1">
    <li>
    <table>


<tr>
<td>Id:</td>
<td><input type="text" id="id" readonly="readonly" name="id" value="${user.id}" ></td>
</tr>

<tr>
<td>Login:</td>
<td><input type="text" id="login" name="login" value="${user.login}"></td>
</tr>

<tr>
<td>Nome:</td>
<td><input type="text" id="nome" name="nome" value="${user.nome}"></td>
</tr>
<tr>
<td>Telefone:</td>
<td><input type="text" id="telefone" name="telefone" value="${user.telefone}"></td>
</tr>

<tr>
<td>Senha:</td>
<td><input type="password" id="senha" name="senha" value="${user.senha}"></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="cadastrar"> <input type="submit" value="cancelar" onclick="document.getElementById('formUser').action='salvarusuario?acao=reset'" ></td>
</table>
</li>
</ul>
</form>

 <h2></h2>

<div class="container">
  <table class="responsive-table">
  <tr>
<th>Id</th> 
<th>Login</th> 
<th>Nome</th>
 <th>telefone</th> 
 <th>Delete</th>
<th>Editar</th>

</tr>
<tr>
<c:forEach items="${usuarios}" var="user">
<td><c:out value="${user.id}"></c:out></td>
<td><c:out value="${user.login}"></c:out></td>
<td><c:out value="${user.nome}"></c:out></td>
<td><c:out value="${user.telefone}"></c:out></td>

<td><a href="salvarusuario?acao=delete&user=${user.login}"><img src="resources/img/excluir2.png"width="20px" alt="excluir" title="excluir" height="20px"></a></td>
<td><a href="salvarusuario?acao=editar&user=${user.login}"><img src="resources/img/editar.jpg"width="20px" alt="editar" title="excluir" height="20px"></a></td>
</tr>
</c:forEach>
</table>
</div>

<script type="text/javascript">
function validarcampos(){
	
if(document.getElementById("login").value==''){
	alert('informe o login');
	return false;
	
}else
	if(document.getElementById("senha").value==''){
		alert('informe o senha');
		return false;
		
	}else 
		if(document.getElementById("nome").value==''){
			alert('informe o nome');
			return false;
			
		}
	return true;
}



</script>

</body>
</html>