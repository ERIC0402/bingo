[#ftl]
[#import "/templates/xml/comm.ftl" as c /]
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${base}/static/plugin/bootstrap-maxlength/bootstrap-maxlength.min.js"></script>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/bootstrap-inputmask/jquery.inputmask.min.js"></script>
<script type="text/javascript" src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${base}/static/script/bingo/bingo.validate.js"></script>
<div class="row">
	<div class="col-md-12">
		<div class="widget box">
				[#if tag.title??]
					[@c.group title=tag.title!'']
						<form class="form-horizontal row-border" id="${tag.id}" action="${tag.action}" method="${tag.method}" onsubmit="return onsubmit${tag.id}()">
						${tag.body!}
						</form>
					[/@]
				[#else]
					<form class="form-horizontal row-border" id="${tag.id}" action="${tag.action}" method="${tag.method}" onsubmit="return onsubmit${tag.id}()">
					${tag.body!}
					</form>
				[/#if]
		</div>
	</div>
</div>
<script type="text/javascript">
	var form = $('#${tag.id}');
	[#if tag.checks?? && tag.checks?size > 0 || tag.onsubmit??]
		var rules = {};
		[#list tag.checks?keys as id]
		[#assign check=tag.checks[id]/]
		rules[$("#${id?replace('.','\\\\.')}").attr("name")] = new ValidateStr()${check}.getConfig();
		[/#list]
		ValidateForm(form, rules);
		function onsubmit${tag.id}(){
			var result = false;
			result = form.valid();
			if(result) {
				[#if tag.onsubmit??]
					result = ${tag.onsubmit};
				[/#if]
			}
			return result;
		}
	[/#if]
</script>