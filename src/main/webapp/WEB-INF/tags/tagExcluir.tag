<%@ attribute name="id" required="true"%>
<a href="javascript:void(0);" onclick="Remover('${id}')">Excluir</a>
<script>
	function Remover(id) {
		if (confirm(' Deseja realmente excluir? ')) {
			$.post("removeTarefa", {
				'id' : id
			}, function() {
				$("#tarefa_" + id).closest("tr").hide();
			});

		} else {
			history.go();
		}
	}
</script>