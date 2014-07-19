<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="link"%>
<td>${tarefa.id}</td>
<td>${tarefa.descricao}</td>
<td>Finalizado</td>
<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
		pattern="dd/MM/yyyy" /></td>
<td>
	<link:tagExcluir id="${tarefa.id}" /> 
	<link:tagAlterar id="mostraTarefa?id=${tarefa.id}" />
</td>
