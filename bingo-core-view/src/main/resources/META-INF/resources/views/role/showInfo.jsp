<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<title>userInfo</title>
</head>
<body>
	用户信息 ： ${userInfo.name} id:${userInfo.id} 
	

	<br /> ajax显示全部用户信息：
	<div id="show_all_user"></div>
</body>
<script type="text/javascript">
	$.ajax({
		type : "get",
		url : "user/showInfos.action",
		dataType : "json",
		success : function(data) {
			$(data).each(
					function(i, user) {
						var p = "<p>" + user.name + "    id:" + user.id + "</p>";
						$("#show_all_user").append(p);
					});
		},
		async : true
	});
</script>
</html>