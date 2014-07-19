<%@ attribute name="id" required="true" %>
<a href="javascript:void(0);" onclick="Inserir('${id}');" >Inserir Contato</a>
<script>
	function Inserir(id) {
		location.href= id;
	}
</script>		