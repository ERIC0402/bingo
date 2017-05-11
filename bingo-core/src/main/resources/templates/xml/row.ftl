[#ftl]
[#if tag.index > 0]
	<tr>${tag.body!}</tr>
[#else]
	<tr class="odd">
		<td valign="top" colspan="${tag.table.cols?size}" class="dataTables_empty">
			<div>没有找到记录</div>
		</td>
	</tr>
[/#if]