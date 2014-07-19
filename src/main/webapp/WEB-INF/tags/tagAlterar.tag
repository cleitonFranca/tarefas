<%@ attribute name="id" required="true" %>
<a href="javascript:void(0);" onclick="Alterar('${id}')" >Alterar</a>
<script>
	function Alterar(id) {
		location.href= id;
	}
</script>		