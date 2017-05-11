[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	[#list tag.radios as radio]
		<label class="radio-inline">
			<input type="radio" id="${radio.id}" class="uniform" name="${tag.name}" value="${radio.value}" [#if (tag.value!)== radio.value]checked[/#if]> ${radio.title!}
		</label>
	[/#list]
[/@]