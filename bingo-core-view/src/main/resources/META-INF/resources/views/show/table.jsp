<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>控制台页面_AmaAdmin后台管理系统</title>

<link rel="stylesheet" href="static/plugin/AmaAdmin/css/style.default.css" type="text/css" />
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/custom/general.js"></script>
<script type="text/javascript" src="static/plugin/AmaAdmin/js/custom/tables.js"></script>

<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/plugins/excanvas.min.js"></script><![endif]-->
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
<body>
	<div class="bodywrapper">
	    <div class="topheader">
	        <div class="left">
	            <h1 class="logo">Ama.<span>Admin</span></h1>
	            <span class="slogan">后台管理系统</span>
	            
	            <br clear="all" />
	            
	        </div><!--left-->
	        
	        <div class="right">
	        	<!--<div class="notification">
	                <a class="count" href="ajax/notifications.html"><span>9</span></a>
	        	</div>-->
	            <div class="userinfo">
	            	<img src="static/plugin/AmaAdmin/images/thumbs/avatar.png" alt="" />
	                <span>管理员</span>
	            </div><!--userinfo-->
	            
	            <div class="userinfodrop">
	            	<div class="avatar">
	                	<a href=""><img src="static/plugin/AmaAdmin/images/thumbs/avatarbig.png" alt="" /></a>
	                    <div class="changetheme">
	                    	切换主题: <br />
	                    	<a class="default"></a>
	                        <a class="blueline"></a>
	                        <a class="greenline"></a>
	                        <a class="contrast"></a>
	                        <a class="custombg"></a>
	                    </div>
	                </div><!--avatar-->
	                <div class="userdata">
	                	<h4>Juan</h4>
	                    <span class="email">youremail@yourdomain.com</span>
	                    <ul>
	                    	<li><a href="editprofile.html">编辑资料</a></li>
	                        <li><a href="accountsettings.html">账号设置</a></li>
	                        <li><a href="help.html">帮助</a></li>
	                        <li><a href="index.html">退出</a></li>
	                    </ul>
	                </div><!--userdata-->
	            </div><!--userinfodrop-->
	        </div><!--right-->
	    </div><!--topheader-->
	    
	    
	    <div class="header">
	    	<ul class="headermenu">
	        	<li class="current"><a href="dashboard.html"><span class="icon icon-flatscreen"></span>首页</a></li>
	            <li><a href="manageblog.html"><span class="icon icon-pencil"></span>博客管理</a></li>
	            <li><a href="messages.html"><span class="icon icon-message"></span>查看消息</a></li>
	            <li><a href="reports.html"><span class="icon icon-chart"></span>统计报表</a></li>
	        </ul>
	        
	    </div><!--header-->
	    
	    <div class="vernav2 iconmenu">
	    	<ul>
	        	<li><a href="#formsub" class="editor">表单提交</a>
	            	<span class="arrow"></span>
	            	<ul id="formsub">
	               		<li><a href="forms.html">基础表单</a></li>
	                    <li><a href="wizard.html">表单验证</a></li>
	                    <li><a href="editor.html">编辑器</a></li>
	                </ul>
	            </li>
	        </ul>
	        <a class="togglemenu"></a>
	        <br /><br />
	    </div><!--leftmenu-->
	        
	    <div class="centercontent">
	    	<div class="contenttitle2">
                	<h3>Dynamic Table with Checkbox Column</h3>
                </div><!--contenttitle-->
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2">
                    <colgroup>
                        <col class="con0" style="width: 4%" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                    </colgroup>
                    <thead>
                        <tr>
                          <th class="head0 nosort"><input type="checkbox" /></th>
                            <th class="head0">Rendering engine</th>
                            <th class="head1">Browser</th>
                            <th class="head0">Platform(s)</th>
                            <th class="head1">Engine version</th>
                            <th class="head0">CSS grade</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                          <th class="head0"><span class="center">
                            <input type="checkbox" />
                          </span></th>
                            <th class="head0">Rendering engine</th>
                            <th class="head1">Browser</th>
                            <th class="head0">Platform(s)</th>
                            <th class="head1">Engine version</th>
                            <th class="head0">CSS grade</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <tr class="gradeA">
                          <td align="center"><span class="center">
                            <input type="checkbox" />
                          </span></td>
                            <td>Gecko</td>
                            <td>Firefox 3.0</td>
                            <td>Win 2k+ / OSX.3+</td>
                            <td class="center">1.9</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                          <td align="center"><span class="center">
                            <input type="checkbox" />
                          </span></td>
                            <td>Gecko</td>
                            <td>Camino 1.0</td>
                            <td>OSX.2+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                        <tr class="gradeA">
                          <td align="center"><span class="center">
                            <input type="checkbox" />
                          </span></td>
                            <td>Gecko</td>
                            <td>Camino 1.5</td>
                            <td>OSX.3+</td>
                            <td class="center">1.8</td>
                            <td class="center">A</td>
                        </tr>
                    </tbody>
                </table>
	        
	        <br clear="all" />
	        
		</div><!-- centercontent -->
	    
	    
	</div><!--bodywrapper-->
</body>
</html>