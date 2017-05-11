<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
	<link href="${base}/static/metronic1.5.4/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<title>Insert title here</title>
<base href="<%=basePath%>" />
</head>
<body>
	<style>
		.search_items {
			padding: 0px 5px;
		}
		
		.search_items input, select, textarea {
			margin-top: 5px;
		}
		
		.search-form-default {
			margin-bottom: 5px;
			/*
		  background:#f0f6fa;
		  */
			padding: 0px 14px;
		}
		
		.search_items .search_item {
			float: right;
			margin-right: 5px;
			height: 42px;
			padding: 2px 0;
			margin-bottom: 7px;
		}
		
		.search_items .search_item input, .search_items .item select {
			width: 100%;
		}
		
		.search_items .search_item select {
			border-color: #ccc;
		}
		
		.search_items .search_item2 {
			width: 38%;
			margin-left: 2%;
		}
		
		.expand_search_btn {
			float: right;
			background: url(${base}/static/themes/default/expand_ico_2.gif)
				no-repeat left center;
			width: 10px;
			height: 25px;
			margin-left: 5px;
		}
		
		.closed_search_btn {
			float: right;
			background: url(${base}/static/themes/default/closed_ico_2.gif)
				no-repeat left center;
			width: 10px;
			height: 25px;
			margin-left: 5px;
		}
		
		#search_div span {
			padding-top: 5px;
		}
	</style>

	<div class="row search-form-default">
		<div class="col-md-12" style="background: #f0f6fa; padding: 2px 0px 0px 0px;">
			<form class="form-inline" id="aaaaaaa" name="aaaaaaaaaaaa" action="aaaaaaaaaa" method="post" target="aaaaaaaaaaaaaa">
				<div class="search_items" style="overflow: hidden;">
					<span class="" style="float: right; padding-top: 7px;">
						<button class="btn green searchButton" type="submit" onclick="bg.form.submit('aaaaaaaaaaaaa');return false;">
							搜索 &nbsp;
							<i class="m-icon-swapright m-icon-white"></i>
						</button>
					</span>
					<div id="search_div" style="float: left; width: 86%;">
						<div class="search_item">
							<input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/><input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/>
							<input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/>
							<input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/>
							<input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/><input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/>
							<input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/><input type="text" class="form-control input-medium" id="aaaaa" name="aaaaaaaaa" alt="aaaaaaaaaaaaaa"  placeholder="aaaaaaaa" maxlength="10" value="aaaaaaaaaaaa"/>
						</div>
					</div>
				</div>
				<div style="clear: both"></div>
			</form>
		</div>
	</div>
</body>
</html>