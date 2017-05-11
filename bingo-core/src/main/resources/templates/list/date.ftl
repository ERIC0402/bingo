[#ftl/]
<link rel="stylesheet" type="text/css" href="${base}/static/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
		<input type="text" id="${tag.id}" size="16" class="form-control" name="${tag.name}" value="${(tag.value)!}" >
		<i class="icon-calendar field-icon"  onclick="$('#input${tag.id}').datetimepicker('show');"></i>
		[#--
		<span class="input-group-btn">
			<button class="btn default date-reset" type="button"><i class="fa fa-times"></i></button>
		</span>
		--]
[/@]
<script>
var dateFormat = '${tag.format}';
if(dateFormat=='datetime' || dateFormat=='yyyy-MM-dd HH:mm'){
	$("#${tag.id}").datetimepicker({
		language: 'zh-CN',
	    format: "yyyy-mm-dd hh:ii",
	    showMeridian: 1,
	    startDate: '${(tag.minDate)!}',
        endDate: '${(tag.maxDate)!}',
        todayHighlight: 1,
	    autoclose: 1,
	    fontAwesome: 1,
	    forceParse: 0,
        todayBtn: 1
	});
}else{
	$("#${tag.id}").datetimepicker({
		language: 'zh-CN',
	    format: "yyyy-mm-dd",
	    showMeridian: 1,
	    startDate: '${(tag.minDate)!}',
        endDate: '${(tag.maxDate)!}',
        todayHighlight: 1,
	    autoclose: 1,
	    minView: 2,
	    container: "#container",
	    fontAwesome: 1,
	    forceParse: 0,
        todayBtn: 1
	});
}
</script>