<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/context/mytags.jsp"%>
</head>
<body>
	<div id="gridbar" class="table-toolbar"></div>
	<script type="text/javascript">
		var message = "${message}";
		if(message) {
			bingo.alert(message);
		}
		function toolbarInit(){
			var bar = bg.ui.toolbar("gridbar");
			bar.addItem("新建",action.add());bar.addItem("编辑",action.edit());bar.addItem("删除",action.remove());bar.addItem("激活",action.multi("activate", null, "isActive=1"), "fa-check");bar.addItem("冻结",action.multi("activate", null, "isActive=0"), "fa-ban");
		};
	</script>
	<!-- <div id="toolbar" class="table-toolbar">
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
	</div> -->
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
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="5">5</option>
											<option value="10">10</option>
											<option value="20">20</option>
											<option value="30">30</option>
											<option value="50">50</option>
											<option value="70">70</option>
											<option value="100">100</option>
											<option value="200">200</option>
											<option value="1000">1000</option>
										</select>
										条记录
									</label>
								</div>
							</div>
						</div>
					</div>

					<table id="tableRole" class="table table-striped table-bordered table-hover table-checkable table-columnfilter atatable">
						<thead>
							<tr>
								<th class="checkbox-column" style="width:2%">
									<input type="checkbox" class="uniform">
								</th>
								<th id="name" class="sorting" style="width:24.5%">角色名</th>
								<th id="creator" class="sorting" style="width:24.5%">创建人</th>
								<th id="updatedAt" class="hidden-xs" style="width:24.5%">修改时间</th>
								<th style="width:24.5%">状态</th>
							</tr>
						</thead>
						<tbody style="word-break: break-all;">
							<c:choose>
								<c:when test="${not empty roles and fn:length(roles) > 0}">
									<c:forEach var="item" items="${roles }" varStatus="status">
										<tr>
											<td class="checkbox-column">
												<input type="checkbox" name="role.id" class="uniform" value="${item.id }">
											</td>
											<td>${item.name }</td>
											<td>${item.creator.name }</td>
											<td class="hidden-xs"><fmt:formatDate value="${item.updatedAt }" pattern="yyyy年MM月dd日HH点mm分ss秒" /></td>
											<td>
												<c:choose>
													<c:when test="${item.enabled }">
														<span class="label label-success">Approved</span>
													</c:when>
													<c:otherwise>
														<span class="label label-warning">Suspended</span>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="odd">
										<td valign="top" colspan="4" class="dataTables_empty">
											<div>没有找到记录</div>
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<div class="row">
						<div class="dataTables_footer clearfix">
							<div class="col-md-6">
								<div class="dataTables_info hidden-xs" id="DataTables_Table_0_info">当前 第 ${roles.getTotal() > 0 ? ((roles.getPageNo() - 1) * roles.getPageSize()) + 1 : 0 } 条 到 第 ${roles.getTotal() > (roles.getPageNo() * roles.getPageSize()) ? roles.getPageNo() * roles.getPageSize() : roles.getTotal() } 条记录 总共 ${roles.getTotal() } 条记录</div>
							</div>
							<div class="col-md-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul id="pagebar" class="pagination">
										<%-- <li class="prev <c:if test="${roles.getFirstPageNo() == roles.getPageNo() }">disabled</c:if>">
											<a href="#">← 上一页</a>
										</li>
										<c:if test="${roles.getPageNo() != roles.getFirstPageNo()}">
											<li class="disabled">
												<a href="#">...</a>
											</li>
										</c:if>
										<c:forEach begin="1" end="6" var="current" varStatus="status">
											<c:if test="${role.getPageNo() + current <= roles.getMaxPageNo()}">
												<li <c:if test="${current == roles.getPageNo() }">class="active"</c:if>>
													<a href="#">${role.getPageNo() + current }</a>
												</li>
											</c:if>
										</c:forEach>
										<c:if test="${roles.getPageNo() + 5 < roles.getMaxPageNo()}">
											<li class="disabled">
												<a href="#">...</a>
											</li>
										</c:if>
										<li class="next <c:if test="${roles.getPageNo() == roles.getMaxPageNo() }">disabled</c:if>">
											<a href="#">下一页 → </a>
										</li> --%>
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
		$(document).ready(function() {
			page = bg.page("${base}${bingo:transformRequestURI(pageContext.request)}", "grid").addParams("${bingo:getParamstring(pageContext.request) }").orderBy("${param.orderBy}").pageInfo("${roles.getPageNo()}", "${roles.getPageSize()}", "${roles.getTotal()}");
			bg.ui.page.init('tableRole', page);
			action = bg.addEntityAction('role', page);
			toolbarInit();
		});
		$(".table-checkable thead th.checkbox-column :checkbox").on("change",
	        function() {
	            var z = $(this).prop("checked");
	            var x = $(this).parents("table.table-checkable").data("horizontalWidth");
	            if (typeof x != "undefined") {
	                var y = $(this).parents(".dataTables_scroll").find(".dataTables_scrollBody tbody")
	            } else {
	                var y = $(this).parents("table").children("tbody")
	            }
	            y.each(function(B, A) {
	                $(A).find(".checkbox-column").each(function(D, C) {
	                    var E = $(":checkbox", $(C)).prop("checked", z).trigger("change");
	                    if (E.hasClass("uniform")) {
	                        $.uniform.update(E)
	                    }
	                    $(C).closest("tr").toggleClass("checked", z)
	                })
	            })
	        });
	</script>
</body>
</html>