[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	${tag.body!}
[/@]