<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/context/mytags.jsp"%>
</head>
<body>
	<div class="panel panel-default" style="margin-bottom: 0px">
		<div class="panel-body">
			<form class="form-inline" id="search-form" action="role/list" method="POST" target="grid">
				<input type="hidden" name="actionUrl" value="role/list" />
				<!-- 防止跨站提交攻击 -->
				<security:csrfInput/>
				<div class="form-group col-md-10">
					<div class="form-group search-input">
						<input name="role.name" type="text" alt="角色名" placeholder="角色名" class="form-control">
					</div>
				</div>
				<div class="text-right form-group col-md-2">
					<button id="search-form-submit" class="btn btn-primary searchButton" type="submit" onclick="bg.form.submit('search-form');return false;">
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
		bg.Go('${base}/role/list', 'grid');
	</script>
	<div id="grid" class="_ajax_target" url="${base }/role/list"></div>
</body>
</html>