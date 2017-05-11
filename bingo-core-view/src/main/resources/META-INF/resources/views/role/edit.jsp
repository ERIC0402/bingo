<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/validation/jquery.validate.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${base}/static/script/bingo/bingo.validate.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div id="toolbar" class="table-toolbar">
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
				<div class="widget-header">
					<h4>
						用户
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
					<form class="form-horizontal row-border" id="roleForm" action="role/save" target="grid" method="post" onsubmit="return onsubmitForm()">
						<div class="form-group">
							<label class="col-md-3 control-label">
								角色名
								<span class="required"> * </span>
							</label>
							<div class="col-md-9">
								<input type="text" id="rolename" name="role.name" class="form-control required" value="${role.name }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">
								状态
								<span class="required"> * </span>
							</label>
							<div class="col-md-9">
								<label class="radio-inline">
		                          <input type="radio" class="uniform" name="role.enabled" value="1" <c:if test="${role.enabled }">checked</c:if>>
		                          	激活
		                        </label>
		                        <label class="radio-inline">
		                          <input type="radio" class="uniform" name="role.enabled" value="0" <c:if test="${!role.enabled }">checked</c:if>>
		                          	冻结
		                        </label>
							</div>
						</div>
						<div class="form-actions">
							<input type="hidden" name="role.id" value="${role.id}" />
							<input name="params" type="hidden" value='<c:if test="${not empty param.params}"><%=URLDecoder.decode(request.getParameter("params")) %></c:if>' />
							<input type="submit" id="roleFormSubmit" value="提交" onclick="bg.form.submit('roleForm', null, null, null); return false;" class="btn btn-primary pull-right">
							<input type="reset" id="roleFormReset" value="重置" class="btn btn-primary pull-right">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var form = $('#roleForm');
		var rules = {};
		rules[$("#rolename").attr("name")] = new ValidateStr().required().getConfig();
		ValidateForm(form, rules);
		function onsubmitForm(){
			return form.valid();
		}
		
	</script>
</body>
</html>