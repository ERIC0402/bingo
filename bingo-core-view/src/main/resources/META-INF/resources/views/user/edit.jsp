<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head>
	<script type="text/javascript" src="${base}/static/plugin/md5.js"></script>
	<link rel="stylesheet" type="text/css" href="${base}/static/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${base}/static/plugin/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</bingo:head>
	<bingo:toolbar title="用户">bar.addBack();</bingo:toolbar>
	<bingo:form action="${base }/user/save" theme="list" title="基本信息">
		<bingo:textField label="姓名" name="user.fullName" value="${user.fullName }" maxlength="10" required="true"/>
		<bingo:textField label="用户名" name="user.username" value="${user.username }" required="true" check="fn('usernameCheck', true)"/>
		<bingo:password id="pwd" label="密码" name="password"/>
		<fmt:formatDate value="${user.effectiveAt }" var="effectiveAt" pattern="yyyy-MM-dd HH:mm"/>
		<fmt:formatDate value="${user.invalidAt }" var="invalidAt" pattern="yyyy-MM-dd HH:mm"/>
		<fmt:formatDate value="${user.passwordExpiredAt }" var="passwordExpiredAt" pattern="yyyy-MM-dd HH:mm"/>
		<bingo:range label="账户有效时间" name="user.effectiveAt,user.invalidAt" start="${effectiveAt }" end="${invalidAt }" format="datetime" required="true"/>
		<bingo:date label="密码失效时间" name="user.passwordExpiredAt" value="${passwordExpiredAt }" required="true" format="datetime"/>
		<bingo:radios name="user.enabled" items="{'1':'有效','0':'无效'}" label="状态"/>
		<bingo:formfoot>
			<input type="hidden" name="user.id" value="${user.id}" />
			<bingo:redirectParams/>
			<bingo:reset/>
			<bingo:submit onsubmit="validateUser"/>
			<input type="hidden" id="password" name="user.password" value="${user.password }">
		</bingo:formfoot>
	</bingo:form>
<script>
	jQuery.validator.addMethod("usernameCheck", function(value, element) {
		var tel = /^[a-zA-z0-9]\w{2,15}$/;  
		return this.optional(element) || (tel.test(value));  
	}, "用户名由字母、数字、下划线组成，3-16位");
	
	function validateUser() {
		if($("#pwd").val()) {
			$("#password").val(hex_md5($("#pwd").val()));
		}
	}
	$("#hh").datetimepicker({
		language: 'zh-CN',
	    format: "yyyy-mm-dd hh:ii",
	    weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
		fontAwesome: true,
        showMeridian: 1
	});
</script>
<bingo:foot/>
	<%-- <div id="toolbar" class="table-toolbar">
		<!-- <span class="ico_default pull-left"><i class="icon-th-large" style="color: #658db3"> </i>修改&nbsp;用户</span>
		<a href="javascript:void(0);" class="btn button-previous pull-right"><i class="icon-arrow-left"></i>返回</a> -->
	</div>
	<script type="text/javascript">
		var bar = bg.ui.toolbar("toolbar", "用户");
		bar.addBack();
	</script>
	<div class="row">
		<div class="col-md-12">
			<div class="widget box">
				<form class="form-horizontal row-border" id="userForm" action="${base }/user/save" method="post" onsubmit="return onsubmitForm()">
				<div class="widget-header">
					<h4>
						基本信息
					</h4>
					<div class="toolbar no-padding">
	                    <div class="btn-group">
	                      <span class="btn btn-xs widget-collapse">
	                        <i class="icon-angle-down">
	                        </i>
	                      </span>
	                    </div>
                   </div>
				</div>
				<div class="widget-content">
						<div class="form-group">
							<label class="col-md-3 control-label">
								用户名
								<span class="required"> * </span>
							</label>
							<div class="col-md-9">
								<input type="text" id="username" name="user.name" class="form-control required" value="${user.name }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								密码
								<span class="required"> * </span>
							</label>
							<div class="col-md-9">
								<input type="password" name="user.password" class="form-control required email" minlength="5" value="${user.password }" />
							</div>
						</div>
						<div class="form-actions">
							<input type="hidden" name="user.id" value="${user.id}" />
							<input name="params" type="hidden" value='<c:if test="${not empty param.params}"><%=URLDecoder.decode(request.getParameter("params")) %></c:if>' />
							<input type="submit" id="userFormSubmit" value="提交" onclick="bg.form.submit('userForm', null, null, null); return false;" class="btn btn-primary pull-right">
							<input type="reset" id="userFormReset" value="重置" class="btn btn-primary pull-right">
						</div>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var form = $('#userForm');
		ValidateForm(form);
		/* var rules = {};
		rules[$("#username").attr("name")] = new ValidateStr().required().getConfig();
		ValidateForm(form, rules); */
		function onsubmitForm(){
			return form.valid();
		}
		
	</script>
</body>
</html> --%>