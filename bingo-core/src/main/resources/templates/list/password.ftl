[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	<input type="password" id="${tag.id}" name="${tag.name}" class="form-control" value="${tag.value! }">
[/@]