<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head title="MeAdmin管理系统模版"/>
	<script>
		var Home = function() {
			var d = function() {
			    var z = "icon-angle-down",
			    y = "icon-angle-left";
			    $("li:has(ul)", "#sidebar-content ul").each(function() {
			        if ($(this).hasClass("current") || $(this).hasClass("open-default")) {
			            $(">a", this).append("<i class='arrow " + z + "'></i>");
			        } else {
			            $(">a", this).append("<i class='arrow " + y + "'></i>");
			        }
			    });
			    if ($("#sidebar").hasClass("sidebar-fixed")) {
			        $("#sidebar-content").append('<div class="fill-nav-space"></div>')
			    }
			    $("#sidebar-content ul > li > a").on("click",
			    function(C) {
			        if ($(this).next().hasClass("sub-menu") == false) {
			            return
			        }
			        if ($(window).width() > 767) {
			            var B = $(this).parent().parent();
			            B.children("li.open").children("a").children("i.arrow").removeClass(z).addClass(y);
			            B.children("li.open").children(".sub-menu").slideUp(200);
			            B.children("li.open-default").children(".sub-menu").slideUp(200);
			            B.children("li.open").removeClass("open").removeClass("open-default")
			        }
			        var A = $(this).next();
			        if (A.is(":visible")) {
			            $("i.arrow", $(this)).removeClass(z).addClass(y);
			            $(this).parent().removeClass("open");
			            A.slideUp(200,
			            function() {
			                $(this).parent().removeClass("open-fixed").removeClass("open-default");
			                q();
			            })
			        } else {
			            $("i.arrow", $(this)).removeClass(y).addClass(z);
			            $(this).parent().addClass("open");
			            A.slideDown(200,
			            function() {
			            	q();
			            })
			        }
			        C.preventDefault()
			    });
			    var x = function() {
		            $("#divider.resizeable").mousedown(function(B) {
		                B.preventDefault();
		                var A = $("#divider").width();
		                $(document).mousemove(function(D) {
		                    var C = D.pageX + A;
		                    if (C <= 300 && C >= (A * 2 - 3)) {
		                        if (C >= 240 && C <= 260) {
		                            $("#sidebar").css("width", 250);
		                            $("#sidebar-content").css("width", 250);
		                            $("#content").css("margin-left", 250);
		                            $("#divider").css("margin-left", 250)
		                        } else {
		                            $("#sidebar").css("width", C);
		                            $("#sidebar-content").css("width", C);
		                            $("#content").css("margin-left", C);
		                            $("#divider").css("margin-left", C)
		                        }
		                    }
		                })
		            });
		            $(document).mouseup(function(A) {
		                $(document).unbind("mousemove")
		            })
		        };
		        x();
			    var q = function() {
			    	$("body").height("100%");
	                var A = $(".header");
	                var C = A.outerHeight();
	                var x = $(document).height();
	                var z = $(window).height();
	                var y = x - z;
	                if (y <= C) {
	                    var B = x - y
	                } else {
	                    var B = x
	                }
	                B = B - C;
	                var x = $(document).height();
	                $("body").height(B);
			    };
			};
			
			$(document).ready(function() {
				d();
			});
		}();
		
		function logout() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<header class="header navbar navbar-fixed-top" role="banner">
		<div class="container">
			<ul class="nav navbar-nav">
				<li class="nav-toggle">
					<a href="javascript:void(0);" title="">
						<i class="icon-reorder"> </i>
					</a>
				</li>
			</ul>
			<a class="navbar-brand" href="home">
				<img src="${base}/static/plugin/meadmin/assets/img/logo.png" alt="logo" />
				<strong> Me </strong>
				Admin
			</a>
			<a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="切换导航">
				<i class="icon-reorder"> </i>
			</a>
			<ul class="nav navbar-nav navbar-left hidden-xs hidden-sm">
				<li>
					<a href="home"> 控制台 </a>
				</li>
				<li>
					<a href="#"> 系统管理 </a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs hidden-sm">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-envelope"> </i>
						<span class="badge"> 1 </span>
					</a>
					<ul class="dropdown-menu extended notification">
						<li class="title">
							<p id="basic-alert">你有2条新消息</p>
						</li>
						<li>
							<a href="javascript:void(0);">
								<span class="photo">
									<img src="${base}/static/plugin/meadmin/assets/img/demo/avatar-1.jpg" alt="" />
								</span>
								<span class="subject">
									<span class="from"> Bob Carter </span>
									<span class="time"> Just Now </span>
								</span>
								<span class="text"> Consetetur sadipscing elitr... </span>
							</a>
						</li>
						<li>
							<a href="javascript:void(0);">
								<span class="photo">
									<img src="${base}/static/plugin/meadmin/assets/img/demo/avatar-2.jpg" alt="" />
								</span>
								<span class="subject">
									<span class="from"> Jane Doe </span>
									<span class="time"> 45 mins </span>
								</span>
								<span class="text"> Sed diam nonumy... </span>
							</a>
						</li>
						<li class="footer">
							<a href="javascript:void(0);"> View all messages </a>
						</li>
					</ul>
				</li>
				<li class="dropdown user">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-male"> </i>
						<span class="username"> ${fullname } </span>
						<i class="icon-caret-down small"> </i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="pages_user_profile.html">
								<i class="icon-user"> </i>
								我的资料
							</a>
						</li>
						<li>
							<a href="pages_calendar.html">
								<i class="icon-calendar"> </i>
								我的日历
							</a>
						</li>
						<li>
							<a href="#">
								<i class="icon-tasks"> </i>
								我的任务
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="#" onclick="logout(); return false;">
								<i class="icon-key"> </i>
								退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</header>
	<div id="container">
		<div id="sidebar" class="sidebar-fixed">
			<div id="sidebar-content">
				<ul id="nav">
					<li>
						<strong>
							<i class="icon-dashboard"> </i>
							控制台
						</strong>
					</li>
					<li class="current">
						<a href="javascript:void(0);">
							<i class="icon-desktop"> </i>
							UI 组件
							<span class="label label-info pull-right"> 2 </span>
						</a>
						<ul class="sub-menu">
							<li>
								<a href="${base}/user/index" onclick="bg.Go('${base}/user/index', 'main', true); return false;">
									<i class="icon-angle-right"> </i>
									用户
								</a>
							</li>
							<li>
								<a href="${base}/role/index" onclick="bg.Go('${base}/role/index', 'main', true); return false;">
									<i class="icon-angle-right"> </i>
									角色
								</a>
							</li>
							<li>
								<a href="${base}/resource/index" onclick="bg.Go('${base}/resource/index', 'main', true); return false;">
									<i class="icon-angle-right"> </i>
									资源
								</a>
							</li>
						</ul>
					</li>
				</ul>
				<div class="sidebar-widget align-center">
					<div class="btn-group" data-toggle="buttons" id="theme-switcher">
						<label class="btn active">
							<input type="radio" name="theme-switcher" data-theme="bright">
							<i class="icon-sun"> </i>
							白天
						</label>
						<label class="btn">
							<input type="radio" name="theme-switcher" data-theme="dark">
							<i class="icon-moon"> </i>
							夜晚
						</label>
					</div>
				</div>
			</div>
			<div id="divider" class="resizeable"></div>
		</div>
		<div id="content">
			<div class="container">
				<div class="crumbs">
					<ul id="breadcrumbs" class="breadcrumb">
						<li>
							<i class="icon-home"></i>
							<a href="home">首页</a>
						</li>
						<li class="current">
							<a href="pages_calendar.html" title="">Calendar</a>
						</li>
					</ul>
				</div>
				<div class="page-header">
				</div>
				<div id="main">
				</div>
			</div>
		</div>
	</div>
<bingo:foot/>