[#ftl/]
<link rel="stylesheet" type="text/css" href="${base}/static/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
[#import "/templates/xml/comm.ftl" as c /]
[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	<div id="${tag.id}" class="input-group date-picker input-daterange">
	[#list tag.dates as date]
		[#if date_index=1]<div class="input-group-addon" style="padding: 0;background: none;border: 0;vertical-align: top; border-top: 1px solid #ececec;"><span class="input-group-addon" style="height: 32px;">to</span></div>[/#if]
		<div class="form-group" style="position: relative;padding: 0px; margin:0px;">
			<input type="text" id="${date.id}" class="form-control startend date${date_index}" name="${date.name}" value="${(date.value)?if_exists}" placeholder=" " style="padding-right: 30px!important;">
			<i class="icon-calendar field-icon" style="right: 10px;" onclick="$('#${date.id}').datetimepicker('show');"></i>
		</div>
	[/#list]
	</div>
[/@]
<script>
var dateFormat = '${tag.format}';
var showtime=false;
if(dateFormat=='datetime' || dateFormat=='yyyy-MM-dd HH:mm'){
	showtime=true;
	$(".startend").datetimepicker({
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
	}).on('show', function(ev){
		if(ev.target.className.indexOf("date0") != -1) {
    		$(ev.target).datetimepicker('setEndDate', $("#${tag.id} .date1").val());
    	}else {
    		$(ev.target).datetimepicker('setStartDate', $("#${tag.id} .date0").val());
    	}
	});
}else{
	$(".startend").datepicker({
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
	}).on('changeDate', function(ev){
    	if(ev.target.className.indexOf("date0")) {
    		$("#${tag.id} .date1").datetimepicker('setStartDate', ev.target.value);
    	}else {
    		$("#${tag.id} .date0").datetimepicker('setEndDate', ev.target.value);
    	}
	});
}
</script>
[#--[@c.line label="${tag.label!}" required="${tag.required!'false'}" comment="${tag.comment!}"]
	<div id="${tag.id}" class="input-group date form_meridian_datetime">
		<input type="text" id="input${tag.id}" size="16" class="form-control" >
		<span class="input-group-btn" onclick="$('#input${tag.id}').click();">
			<button class="btn default date-set" type="button"><i class="fa fa-calendar"></i></button>
		</span>
		[#list tag.dates as date]
			[#if date_index==0]
				<input type="hidden" class="date${tag.id!} start${tag.id!}" id="${date.id}" name="${date.name}" value="${(date.value)?if_exists}">
			[#else]
				<input type="hidden" class="date${tag.id!} end${tag.id!}" id="${date.id}" name="${date.name}" value="${(date.value)?if_exists}">
			[/#if]
		[/#list]
	</div>
[/@]
<script>
var dateFormat = '${tag.format}';
var showtime=false;

var dateF = 'YYYY-MM-DD'
if(dateFormat=='datetime' || dateFormat=='yyyy-mm-dd hh:mm'){
	dateF = 'YYYY-MM-DD HH:mm'
	showtime=true;
}

var startD = $(".start${tag.id!}").val();
var endD = $(".end${tag.id!}").val();

$('#input${tag.id!}').daterangepicker({
        opens: 'right',
        defaultStartDate: startD!=null&&startD!=""?startD:null,
        defaultEndDate: endD!=null&&endD!=""?endD:null,
        minDate: '${tag.minDate!}',
        maxDate: '${tag.maxDate!}',
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: showtime,
        timePickerIncrement: 1,
        timePicker12Hour: true,
        buttonClasses: ['btn'],
        applyClass: 'green',
        cancelClass: 'default',
        format: dateF,
        separator: ' to ',
        locale: {
            applyLabel: '确定',
            format: dateF,
            cancelLabel: '取消',
            fromLabel: '开始',
            toLabel: '截止',
            customRangeLabel: '自定义',
            daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            firstDay: 1
        }
    },
    function (start, end) {
    	debugger;
        console.log("Callback has been called!");
        $("#input${tag.id}").val(start.format(dateF) + " - " + end.format(dateF));
        $('.date${tag.id!} ').each(function(index){
        	if(index==0){
        		$(this).val(start.format(dateF));
        	}else{
        		$(this).val(end.format(dateF));
        	}
        });
    }
);
</script>
--]