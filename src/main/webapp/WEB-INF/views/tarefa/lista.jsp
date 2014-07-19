<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="link"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/jquery.js" />"></script>
</head>
<body>
<div id="menu" style="magin-left: 35em;">
	<p>
		<a href="logout">Sair do sistema</a>
	</p>
	
</div>

	<dir id="msg" style="background-color: aqua; width: 35em; text-align: center; position: fixed; top: 1em;">
		<c:if test="${not empty param.tarefaMsg}">
			 <span style="color: blue; ">${param.tarefaMsg}</span>
		</c:if>
	</dir>
	<table style="margin-left: 1em;">
		<tr id="tempo">
			<th>Id</th>
			<th>Descrição</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
			<th><link:tagInserir id="adicionaTarefa" /></th>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa" varStatus="id">
			<tr id="tarefa_${tarefa.id}" bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
					<td><a href="javascript:void(0);"
						onClick="finalizaAgora(${tarefa.id})"> Finaliza agora! </a>
				</c:if>
				<c:if test="${tarefa.finalizado eq true}">
					<td>Finalizado</td>
				</c:if>
				<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
						pattern="dd/MM/yyyy" /></td>
				<td><link:tagExcluir id="${tarefa.id}" /> <link:tagAlterar
						id="mostraTarefa?id=${tarefa.id}" /></td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		
		$("#msg").fadeOut(3000);
		
		function finalizaAgora(id) {
			$.post("finalizaTarefa", {'id' : id}, function(resposta){
				$("#tarefa_"+id).html(resposta);
			});
		};
	</script>
</body>
</html>