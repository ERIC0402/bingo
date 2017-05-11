[#ftl/]
<div id="${tag.id}" class="table-toolbar"></div>
<script type="text/javascript">
	$(function(){
		var bar = bg.ui.toolbar("${tag.id}", "${tag.title!}");
		${tag.body!}
	});
</script>