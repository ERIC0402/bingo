<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<bingo:head/>
	<bingo:grid items="${users }" var="user">
		<bingo:gridbar>
			bar.addItem("新建",action.add());
			bar.addItem("编辑",action.edit());
			bar.addItem("删除",action.remove());
		</bingo:gridbar>
		<bingo:row>
			<bingo:boxcol/>
			<bingo:col property="fullName" title="姓名" width="20%"/>
			<bingo:col property="username" title="用户名" width="20%"/>
			<bingo:col property="password" title="密码" width="20%"/>
			<bingo:col title="权限" width="20%">
				<c:forEach items="${user.roles }" var="role" varStatus="rolestatus">
					${role.name }
					<c:if test="${!rolestatus.last }">
						<br />
					</c:if>
				</c:forEach>
			</bingo:col>
			<bingo:col property="enabled" title="状态" width="20%">
				${user.enabled ? "有效" : "无效" }
			</bingo:col>
		</bingo:row>
	</bingo:grid>
<bingo:foot/>	
	<%-- <div id="gridbar" class="table-toolbar"></div>
	<script type="text/javascript">
		var message = "${message}";
		if(message) {
			bingo.alert(message);
		}
		function toolbarInit(){
			var bar = bg.ui.toolbar("gridbar");
			bar.addItem("新建",action.add());bar.addItem("编辑",action.edit());bar.addItem("删除",action.remove());
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

					<table id="tableUser" class="table table-striped table-bordered table-hover table-checkable table-columnfilter atatable">
						<thead>
							<tr>
								<th class="checkbox-column" width="2%">
									<input type="checkbox" class="uniform">
								</th>
								<th id="name" class="sorting" width="24.5%">姓名</th>
								<th id="password" class="sorting" width="24.5%">密码</th>
								<th id="name" class="hidden-xs" width="24.5%">权限</th>
								<th width="24.5%">状态</th>
							</tr>
						</thead>
						<tbody style="word-break: break-all;">
							<c:choose>
								<c:when test="${not empty users and fn:length(users) > 0}">
									<c:forEach var="item" items="${users }" varStatus="status">
										<tr>
											<td class="checkbox-column">
												<input type="checkbox" name="user.id" class="uniform" value="${item.id }">
											</td>
											<td>${item.name }</td>
											<td>${item.password }</td>
											<td class="hidden-xs">
												<c:forEach items="${item.roles }" var="role" varStatus="roletatus">
													${role.name }
													<c:if test="${!roletatus.last }">
														<br />
													</c:if>
												</c:forEach>	
											</td>
											<td>
												<span class="label label-success">Approved</span>
												<span class="label label-warning">Suspended</span>
												<span class="label label-info">Pending</span>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr class="odd">
										<td valign="top" colspan="5" class="dataTables_empty">
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
								<div class="dataTables_info hidden-xs" id="DataTables_Table_0_info">当前 第 ${users.getTotal() > 0 ? ((users.getPageNo() - 1) * users.getPageSize()) + 1 : 0 } 条 到 第 ${users.getTotal() > (users.getPageNo() * users.getPageSize()) ? users.getPageNo() * users.getPageSize() : users.getTotal() } 条记录 总共 ${users.getTotal() } 条记录</div>
							</div>
							<div class="col-md-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul id="pagebar" class="pagination">
										<li class="prev <c:if test="${users.getFirstPageNo() == users.getPageNo() }">disabled</c:if>">
											<a href="#">← 上一页</a>
										</li>
										<c:if test="${users.getPageNo() != users.getFirstPageNo()}">
											<li class="disabled">
												<a href="#">...</a>
											</li>
										</c:if>
										<c:forEach begin="1" end="6" var="current" varStatus="status">
											<c:if test="${user.getPageNo() + current <= users.getMaxPageNo()}">
												<li <c:if test="${current == users.getPageNo() }">class="active"</c:if>>
													<a href="#">${user.getPageNo() + current }</a>
												</li>
											</c:if>
										</c:forEach>
										<c:if test="${users.getPageNo() + 5 < users.getMaxPageNo()}">
											<li class="disabled">
												<a href="#">...</a>
											</li>
										</c:if>
										<li class="next <c:if test="${users.getPageNo() == users.getMaxPageNo() }">disabled</c:if>">
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
		$(document).ready(function() {
			page = bg.page("${base}${bingo:transformRequestURI(pageContext.request)}", bg.findTarget(document.getElementById("tableUser"))).addParams("${bingo:getParamstring(pageContext.request) }").orderBy("${param.orderBy}").pageInfo("${users.getPageNo()}", "${users.getPageSize()}", "${users.getTotal()}");
			bg.ui.page.init('tableUser', page);
			action = bg.addEntityAction('user', page);
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
	</script> --%>