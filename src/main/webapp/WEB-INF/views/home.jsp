<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Welcome</title>
</head>
<body>
	<h2>Página inicial da Lista de Tarefas</h2>
	<p>Bem vindo, ${usuarioLogado.login}</p>
	<a href="listaTarefas">Clique aqui</a> para acessar a lista de tarefas
	
	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
