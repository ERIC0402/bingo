[#ftl]
[#include "/templates/xml/messages.ftl" /]
	<script type="text/javascript">
		var message = "${tag.attribute('message')!}";
		if(message) {
			bingo.alert(message);
		}
	</script>
	<div id="${tag.id}_bar1" class="table-toolbar"></div>
	<div class="row">
		<div class="col-md-12">
			<div class="widget box">
				<div class="widget-content no-padding">
					[#if tag.pageable]
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
					[/#if]
					<table  id="${tag.id}" class="table table-striped table-bordered table-hover table-checkable table-columnfilter atatable">
						[#if tag.cols?size>0]
						<thead>
							<tr>
								[#list tag.cols as cln]
									<th id="${cln.property!}" class="[#if cln.type??]checkbox-column [/#if][#if tag.isSortable(cln)]sorting[/#if][#if cln.isHidden] hidden-xs[/#if]" [#if cln.width??]style="width:${cln.width}"[/#if]>[#if cln.type??]<input type="${cln.type}" class="uniform" title="select me">[#else]${cln.title!}[/#if]</th>
								[/#list]
							</tr>
						</thead>
						[/#if]
						<tbody style="word-break: break-all;" id="${tag.id}_data">
							${tag.body!}
						</tbody>
					</table>
					[#if tag.pageable]
					<div class="row">
						<div class="dataTables_footer clearfix">
							<div class="col-md-6">
								<div class="dataTables_info hidden-xs" id="DataTables_Table_0_info">当前 第 [#if tag.total > 0]${((tag.pageNo - 1) * tag.pageSize) + 1}[#else]0[/#if] 条 到 第 [#if tag.total > (tag.pageNo * tag.pageSize)]${tag.pageNo * tag.pageSize}[#else][/#if] 条记录 总共 ${tag.total} 条记录</div>
							</div>
							<div class="col-md-6">
								<div class="dataTables_paginate paging_bootstrap">
									<ul id="${tag.id}_pagebar" class="pagination"></ul>
								</div>
							</div>
						</div>
					</div>
					[/#if]
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			page_${tag.id} = bg.page("${base}${tag.requestURI}", bg.findTarget(document.getElementById("${tag.id}"))).addParams("${tag.params}").orderBy("${tag.parameter('orderBy')!}");
			[#if tag.pageable]
				page_${tag.id}.pageInfo("${tag.pageNo}", "${tag.pageSize}", "${tag.total}");
			[/#if]
			bg.ui.page.init('${tag.id}', page_${tag.id});
			[#if tag.hasbar]
				var bar = bg.ui.toolbar("${tag.id}_bar1");
				[#if tag.var??]action = bg.addEntityAction('${tag.var}', page_${tag.id});[/#if]
				${tag.gridbar}
			[/#if]
		});
		$(".table-checkable thead th.checkbox-column :checkbox").on("change", function() {
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