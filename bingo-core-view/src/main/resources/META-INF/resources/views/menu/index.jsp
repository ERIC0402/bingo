<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head/>
	<bingo:form action="${base }/resource/list" target="resourceGrid" theme="search">
		<bingo:textField name="resource.name" label="名称"></bingo:textField>
		<bingo:textField name="resource.path" label="路径"></bingo:textField>
	</bingo:form>
	<bingo:div id="resourceGrid" href="${base }/resource/list"/>
<bingo:foot/>