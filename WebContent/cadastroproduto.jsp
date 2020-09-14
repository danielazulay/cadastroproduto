<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de produto</title>
</head>
<body>
<table>
<form action="salvarproduto" method="post">

<tr>
						<td>Id:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}" class="field-long"></td>
					</tr>
<tr>
<td>Nome:</td>
<td><input type="text" name="nome" id="nome" value="${produto.nome}">
</td>
</tr>

<tr>
<td>Quantiade:</td>
<td><input type="number" name="quantidade" id="quantidade" value="${produto.quantidade}">
</td>
</tr>

<tr>
<td>Preco:</td>
<td><input type="preco" name="preco" id="preco" value="${produto.preco}">
</td>
</tr>

<tr>
<td><input type="submit" name="cadastrar">
</td>
</tr>
</form>
</table>
<table>

<tr>
<th>ID</th>
<th>nome</th>
<th>preco</th>
<th>quantidade</th>
<th>editar</th>
<th>excluir</th>

</tr>




<c:forEach items="${produtos}" var="product">

<tr>
<td><c:out value="${product.id}"></c:out></td>
<td><c:out value="${product.nome}"></c:out></td>
<td ><c:out value="${product.quantidade}"></c:out></td>
<td ><c:out value="${product.preco}"></c:out></td>
<td><a href="salvarproduto?acao=editar&produto=${product.id}">editar</a></td>
<td><a href="salvarproduto?acao=delete&produto=${product.id}">deletar</a></td>
</tr>
</c:forEach>

</table>

</body>
</html>