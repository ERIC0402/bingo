<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head/>
	<bingo:form action="${base }/user/list" target="userGrid" theme="search">
		<bingo:textField name="user.name" label="用户名"></bingo:textField>
		<bingo:textField name="user.password" label="密码"></bingo:textField>
		<bingo:range label="账户有效时间" name="user.effectiveAt,user.invalidAt" start="${user.effectiveAt }" end="${user.invalidAt }" format="datetime"/>
	</bingo:form>
	<bingo:div id="userGrid" href="${base }/user/list"/>
<bingo:foot/>