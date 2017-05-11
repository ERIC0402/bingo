[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	[#list tag.checkboxes as checkbox]
		<label class="checkbox-inline">
			<input type="checkbox" id="${checkbox.id}" class="uniform" name="${tag.name}" value="${checkbox.value}" [#if checkbox.checked]checked="checked"[/#if]> ${checkbox.title!}
		</label>
	[/#list]
[/@]

