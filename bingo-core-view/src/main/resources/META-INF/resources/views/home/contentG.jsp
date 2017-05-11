<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/plugin/meadmin/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="static/plugin/meadmin/plugins/datatables/tabletools/TableTools.min.js"></script>
<script type="text/javascript" src="static/plugin/meadmin/plugins/datatables/colvis/ColVis.min.js"></script>
<script type="text/javascript" src="static/plugin/meadmin/plugins/datatables/columnfilter/jquery.dataTables.columnFilter.js"></script>
<script type="text/javascript" src="static/plugin/meadmin/plugins/datatables/DT_bootstrap.js"></script>
<base href="<%=basePath%>" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="widget box">
				<div class="widget-content no-padding">
					<table class="table table-striped table-bordered table-hover" id="id">
						<thead>
							<tr>
								<th style="background-color:#e5e5e5;" width="5%" class="table-checkbox">
									<input type="checkbox" class="group-checkable" data-set="#sample_1 .checkboxes" name="aaaaaaaaaaaabox" onclick="bg.input.toggleCheckBox(document.getElementsByName('aaaaaaaaaaaa'),event)" title="aaaaaaaaaaaa"/>
								</th>
								<th style="background-color:#e5e5e5;" width="19%" class="sorting" id="sort">
									aaaaaaaaaaaaaaaa
								</th>
								<th style="background-color:#e5e5e5;" width="19%" class="sorting" id="sort">
									aaaaaaaaaaaaaaaa
								</th>
								<th style="background-color:#e5e5e5;" width="19%" class="sorting" id="sort">
									aaaaaaaaaaaaaaaa
								</th>
								<th style="background-color:#e5e5e5;" width="19%" class="sorting" id="sort">
									aaaaaaaaaaaaaaaa
								</th>
								<th style="background-color:#e5e5e5;" width="19%" class="sorting" id="sort">
									aaaaaaaaaaaaaaaa
								</th>
							</tr>
						</thead>
						<tbody id="id_data">body</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>