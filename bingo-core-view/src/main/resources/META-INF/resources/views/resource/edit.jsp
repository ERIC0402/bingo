<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head/>
	<bingo:toolbar title="资源">bar.addBack();</bingo:toolbar>
	<bingo:form action="${base }/resource/save" theme="list" title="基本信息">
		<bingo:textField label="路径" name="resource.path" value="${resource.path }" maxlength="100" required="true"/>
		<bingo:textField label="名称" name="resource.name" value="${resource.name }" maxlength="100" required="true"/>
		<bingo:radios label="权限" name="resource.scope" value="${resource.scope }" items="{'0':'公开资源,不限权限','1':'公有资源,需要登录即可访问','2':'私有资源,需要权限分配'}" required="true"/>
		<bingo:formfoot>
			<input type="hidden" name="resource.id" value="${resource.id}" />
			<bingo:redirectParams/>
			<bingo:reset/>
			<bingo:submit/>
		</bingo:formfoot>
	</bingo:form>
<bingo:foot/>