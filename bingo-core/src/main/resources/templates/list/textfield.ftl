[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	<input type="text" id="${tag.id}" name="${tag.name}" class="form-control" value="${tag.value! }" [#if tag.readonly]readonly[/#if] [#if tag.format??]data-mask="${tag.format}"[/#if]>
[/@]