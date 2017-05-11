<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="<%=basePath%>" />
</head>
<style>
.search-input {
	margin-bottom: 2px;
}
.expand_search_btn {
	float: right;
	background: url(${base}/static/themes/default/expand_ico_2.gif)
		no-repeat left center;
	width: 10px;
	height: 32px;
	margin-left: 5px;
}

.closed_search_btn {
	float: right;
	background: url(${base}//static/themes/default/closed_ico_2.gif)
		no-repeat left center;
	width: 10px;
	height: 32px;
	margin-left: 5px;
}

#search-form .form-group {
	overflow: hidden;
	vertical-align: inherit;
}

#search-form input[type='submit'] {
	width: 110px;
}

.table-toolbar {
	margin-bottom: 5px;
}

.dataTables_empty div {
	height: 150px;
    line-height: 150px;
    text-align: center;
}
</style>
<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<form class="form-inline" id="search-form" action="home/list" method="POST" target="grid">
				<div class="form-group col-md-10">
					<div class="form-group search-input">
						<input name="WHC_Name" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_FunctionId" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_FunctionId" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_Name" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_FunctionId" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_Name" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_FunctionId" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_Name" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
					<div class="form-group search-input">
						<input name="WHC_FunctionId" type="text" alt="aaaaaaaaaaaaaa" placeholder="aaaaaaaa" class="form-control">
					</div>
				</div>
				<div class="text-right form-group col-md-2">
					<button id="search-form-submit" class="btn btn-primary" type="submit" onclick="bg.form.submit('search-form');return false;">
						<i class="icon-search"></i>
						搜 索
					</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function initExpandBtn() {
			var box = $("#search-form div").eq(0);
			var height = box.height();
			if (height > 35) {
				var sbtn = $("#search-form button[type='submit']");
				var btn = $('<a href="#" class="expand_search_btn"></a>');
				btn.insertAfter(sbtn);
				btn.click(function() {
					if ($(this).attr('class') == 'expand_search_btn') {
						box.animate({
							height : height + "px"
						});
						$(this).attr('class', 'closed_search_btn');
					} else {
						box.animate({
							height : "32px"
						});
						$(this).attr('class', 'expand_search_btn');
					}

					return false;
				});
				box.css("height", "32px");
			}
		}
		initExpandBtn();
		bg.Go('${base}/home/list', 'grid');
	</script>
	<div id="grid">
	</div>
</body>
</html>