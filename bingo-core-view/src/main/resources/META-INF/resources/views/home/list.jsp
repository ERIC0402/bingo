<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/tabletools/TableTools.min.js"></script>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/colvis/ColVis.min.js"></script>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/columnfilter/jquery.dataTables.columnFilter.js"></script>
<script type="text/javascript" src="${base}/static/plugin/meadmin/plugins/datatables/DT_bootstrap.js"></script>
<base href="<%=basePath%>" />
</head>
<body>
	<div id="toolbar" class="table-toolbar">
		<button id="btn_add" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			新增
		</button>
		<button id="btn_edit" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			修改
		</button>
		<button id="btn_delete" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			删除
		</button>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="widget box">
				<div class="widget-content no-padding">

					<div class="row hidden-xs">
						<div class="dataTables_header clearfix">
							<div class="col-md-12">
								<div id="DataTables_Table_0_length" class="dataTables_length" style="float: right;">
									<label>
										每页最多显示
										<select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0" class="select2-offscreen" tabindex="-1">
											<option value="5" selected="selected">5</option>
											<option value="10">10</option>
											<option value="25">25</option>
											<option value="50">50</option>
											<option>All</option>
										</select>
										条记录
									</label>
								</div>
							</div>
						</div>
					</div>

					<table class="table table-striped table-bordered table-hover table-checkable table-columnfilter atatable">
						<thead>
							<tr>
								<th class="checkbox-column" style="width:2%">
									<input type="checkbox" class="uniform">
								</th>
								<th class="sorting" style="width:24.5%">姓名</th>
								<th class="sorting_asc" style="width:24.5%">年龄</th>
								<th class="hidden-xs" style="width:24.5%">等级</th>
								<th class="sorting_desc" style="width:24.5%">状态</th>
							</tr>
						</thead>
						<tbody>
							<tr class="odd">
								<td valign="top" colspan="5" class="dataTables_empty">
									<div>没有找到记录</div>
								</td>
							</tr>
							<c:forEach var="item" items="${page.rows }" varStatus="status">
								<tr>
									<td class="checkbox-column">
										<input type="checkbox" name="index.id" class="uniform" value="${status.index }">
									</td>
									<td>${item.name }</td>
									<td>${item.age }</td>
									<td class="hidden-xs">${item.level }</td>
									<td>
										<span class="label label-success">Approved</span>
										<span class="label label-warning">Suspended</span>
										<span class="label label-info">Pending</span>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="dataTables_footer clearfix">
							<div class="col-md-6">
								<div class="dataTables_info hidden-xs" id="DataTables_Table_0_info">当前显示 第 ${page.total > 0 ? page.pageNo : 0 } 条 到 第 ${page.total > (page.pageNo * page.pageSize) ? page.pageNo * page.pageSize : page.total } 条记录 总共 ${page.total } 条记录</div>
							</div>
							<div class="col-md-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul class="pagination">
										<li class="prev disabled">
											<a href="#">← 上一页</a>
										</li>
										<li class="active">
											<a href="#">1</a>
										</li>
										<li>
											<a href="#">2</a>
										</li>
										<li class="next disabled">
											<a href="#">下一页 → </a>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		page = bg.page("${pageContext.request.requestURI}","grid");
		action = bg.addEntityAction('index', page);
	</script>
</body>
</html>