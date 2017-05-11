[#ftl]
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${tag.title!}</title>
	[#if !tag.isAjax]
	<link href="${base}/static/plugin/meadmin/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="${base}/static/plugin/meadmin/assets/css/main.css" rel="stylesheet" type="text/css" />
	<link href="${base}/static/plugin/meadmin/assets/css/plugins.css" rel="stylesheet" type="text/css" />
	<link href="${base}/static/plugin/meadmin/assets/css/responsive.css" rel="stylesheet" type="text/css" />
	<link href="${base}/static/plugin/meadmin/assets/css/icons.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${base}/static/plugin/meadmin/assets/css/fontawesome/font-awesome.min.css" />
	<link href="${base}/static/plugin/Font-Awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${base}/static/plugin/toastr/build/toastr.min.css" />
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/libs/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/event.swipe/jquery.event.move.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/event.swipe/jquery.event.swipe.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/libs/breakpoints.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/respond/respond.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/cookie/jquery.cookie.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/slimscroll/jquery.slimscroll.horizontal.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/blockui/jquery.blockUI.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/fullcalendar/fullcalendar.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/noty/jquery.noty.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/noty/layouts/top.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/noty/themes/default.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/toastr/build/toastr.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/uniform/jquery.uniform.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/app.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/plugins.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/plugins.form-components.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/bootbox/bootbox.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/tabletools/TableTools.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/colvis/ColVis.min.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/columnfilter/jquery.dataTables.columnFilter.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/DT_bootstrap.js"></script>
	
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/custom.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/meadmin/assets/js/demo/pages_calendar.js"></script>
	<script type="text/javascript" src="${base}/static/script/bingo/bingo.js"></script>
	
	<script type="text/javascript" src="${base}/static/script/bingo/bingo-toolbar.js"></script>
	<script type="text/javascript" src="${base}/static/script/bingo/bingo-action.js"></script>
	<script type="text/javascript" src="${base}/static/script/bingo/bingo-page.js"></script>
	<script type="text/javascript" src="${base}/static/plugin/jquery.form.js"></script>
	<script type="text/javascript" src="${base}/static/script/bingo/bingo-history.js"></script>
	<script> window.contextPath = "${base}";</script>
	[/#if]
	<script>
		$(document).ready(function() {
			App.init();
			Plugins.init();
			FormComponents.init();
		});
	</script>
	${tag.body!}
</head>
<body>