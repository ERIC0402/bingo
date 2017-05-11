<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>登录</title>
<link rel="stylesheet" href="static/plugin/AmaAdmin/css/style.default.css" type="text/css" />
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/custom/general.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/custom/index.js"></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<base href="<%=basePath%>"> 
</head>
<body class="loginpage">
	<div class="loginbox">
    	<div class="loginboxinner">
        	
            <div class="logo">
            	<h1 class="logo">Bingo<span>System</span></h1>
				<span class="slogan">后台管理系统</span>
            </div><!--logo-->
            
            <br clear="all" /><br />
            
            <div <c:if test="${empty SPRING_SECURITY_LAST_EXCEPTION.message }">class="nousername"</c:if>>
				<div class="loginmsg">${SPRING_SECURITY_LAST_EXCEPTION.message}.</div>
            </div><!--nousername-->
            
            <form name="loginForm" action="<c:url value='/login' />" onsubmit="return checkLogin();" method="post">
            	
                <div class="username">
                	<div class="usernameinner">
                    	<input type="text" name="username" id="username" autofocus value="${param.username }"/>
                    </div>
                </div>
                
                <div class="password">
                	<div class="passwordinner">
                    	<input type="password" name="password" id="password" />
                    </div>
                </div>
                
                <div id="captcha" class="password" onclick="this.style.background=this.style.background" style="background: #eee url('login/captcha') 100% no-repeat; background-size: 40% 100%; display: none;">
                	<div style="text-align: left;">
                    	<input style="width: 60%; padding: 15px 0px 15px 10px;" type="text" id="code" name="code" placeholder="验证码" />
                    </div>
                </div>
                
                <button type="submit">登录</button>
                
                <div class="keep"><input id="remember_me" name="remember-me" type="checkbox" /> 记住密码</div>
            
            	<!-- 防止跨站提交攻击 -->
				<security:csrfInput/>
            </form>
            
        </div><!--loginboxinner-->
    </div><!--loginbox-->
	<script type="text/javascript">
		var loginFailSize = parseInt("${empty LOGIN_FAIL_SIZE? 0 : LOGIN_FAIL_SIZE }");
		
		if(loginFailSize > 3) {
			document.getElementById("captcha").style.display = "block";
		}
		document.getElementById('code').onclick = function(event) {
			event.stopPropagation(); 
		}
		
		function checkLogin() {
			if(!document.getElementById("username").value) {
				alert("用户名不能为空！");
				return false;
			}
			
			if(!document.getElementById("password").value) {
				alert("密码不能为空！");
				return false;
			}
			
			if(loginFailSize > 3) {
				if(!document.getElementById("code").value) {
					alert("验证码不能为空！");
					return false;
				}
			}
			return true;
		}
	</script>
</body>
</html>