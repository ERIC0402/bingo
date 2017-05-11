[#ftl/]
<link rel="stylesheet" type="text/css" href="${base}/static/plugin/meadmin/plugins/daterangepicker/daterangepicker.css" />
<script type="text/javascript" src="${tag.base}/static/plugin/meadmin/plugins/daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="${tag.base}/static/plugin/meadmin/plugins/daterangepicker/daterangepicker.js"></script>
<div id="report${tag.id!}" class="form-group search-input">
	<span style="position: relative;">
		<i class="glyphicon glyphicon-calendar fa fa-calendar" style="position: absolute;top: 8px;left: 8px;"></i>
	</span>
	<input type="text" id="${tag.id!}" class="form-control" placeholde="${tag.label!}" style="padding:0 6px 0 22px!important;">
	[#list tag.dates as date]
		<input type="hidden" class="date${tag.id!} date${date_index}" id="${date.id}" name="${date.name}" value="${date.value!}">
	[/#list]
</div>
<script>
var dateFormat = '${tag.format}';
var showtime=false;

var dateF = 'YYYY-MM-DD'
if(dateFormat=='datetime' || dateFormat=='yyyy-MM-dd HH:mm'){
	dateF = 'YYYY-MM-DD HH:mm'
	showtime=true;
}

var startD = $("#report${tag.id!} .date0").val();
var endD = $("#report${tag.id!} .date1").val();

$('#${tag.id!}').daterangepicker({
        opens: 'right',
        defaultStartDate: startD!=null&&startD!=""?startD:null,
        defaultEndDate: endD!=null&&endD!=""?endD:null,
        minDate: '${(tag.minDate)!}',
        maxDate: '${(tag.maxDate)!}',
        showDropdowns: true,
        showWeekNumbers: true,
        timePicker: showtime,
        timePickerIncrement: 1,
        timePicker12Hour: true,
        ranges: {
            '今日': [moment().startOf('day'), moment()],  
            '昨日': [moment().subtract('days', 1).startOf('day'), moment().subtract('days', 1).endOf('day')],  
            '最近7日': [moment().subtract('days', 6), moment()],  
            '近三十天': [moment(), moment().subtract('days', -29)],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '下月': [moment().subtract('month', -1).startOf('month'), moment().subtract('month', -1).endOf('month')]
        },
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
        console.log("Callback has been called!");
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