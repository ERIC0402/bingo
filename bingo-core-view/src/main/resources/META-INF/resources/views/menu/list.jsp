<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head/>
	<bingo:grid items="${resources }" var="resource">
		<bingo:gridbar>
			bar.addItem("新建",action.add());
			bar.addItem("编辑",action.edit());
			bar.addItem("删除",action.remove());
		</bingo:gridbar>
		<bingo:row>
			<bingo:boxcol/>
			<bingo:col property="name" title="名称" width="33.3%"/>
			<bingo:col property="path" title="路径" width="33.3%"/>
			<bingo:col property="enabled" title="资源" width="33.3%">
				<c:choose>
					<c:when test="${resource.scope == 0 }">公共资源</c:when>
					<c:when test="${resource.scope == 1 }">公有资源</c:when>
					<c:when test="${resource.scope == 2 }">私有资源</c:when>
					<c:otherwise>未设置</c:otherwise>
				</c:choose>
			</bingo:col>
		</bingo:row>
	</bingo:grid>
<bingo:foot/>