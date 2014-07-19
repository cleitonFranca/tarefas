<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="caelum"%>
<html>
<head>
	<title>:: Alterar Tarefas ::</title>
	<link href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
</head>
<body>
	<h3>Alterar tarefa - ${tarefa.id}</h3>
	<form action="alteraTarefa" method="post">
		<input type="hidden" name="id" value="${tarefa.id}" /> Descrição:<br />
		<textarea name="descricao" cols="100" rows="5">
${tarefa.descricao}
</textarea>
		<br /> Finalizado? <input type="checkbox" name="finalizado"
			value="true" ${tarefa.finalizado? 'checked' : '' } /> <br /> 
			Data de finalização: <br />
			<caelum:campoData id="dataFinalizacao"  />
			<input type="submit" value="Alterar" />
	</form>
</body>