[#ftl]
<div id="${tag.id}" class="${tag.tagClass}"></div>
[#if tag.href??]
<script type="text/javascript">
	bg.Go('${tag.href }', '${tag.id}');
</script>
[/#if]