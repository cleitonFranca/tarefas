<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ attribute name="id"%>

<div id="form">
	<h3>Adicionar tarefas</h3>
	<form:errors style="color:red" path="tarefa.descricao" />
	<form action="adicionaTarefa" method="post">
		Descrição: <br />
		<textarea name="descricao" rows="5" cols="100"></textarea>
		<br /> <input type="submit" value="Adicionar">
	</form>
</div>