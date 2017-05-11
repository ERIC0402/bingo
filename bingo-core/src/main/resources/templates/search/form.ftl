[#ftl]
<div class="panel panel-default" style="margin-bottom: 0px">
		<div id="search-form" class="panel-body">
			<form class="form-inline" id="${tag.id}" action="${tag.action}" method="${tag.method}" target="${tag.target}">
				<!-- 防止跨站提交攻击 -->
				<security:csrfInput/>
				<div class="form-group col-md-10">
					${tag.body}
				</div>
				<div class="text-right form-group col-md-2">
					<button id="search-form-submit" class="btn btn-primary searchButton" type="submit" onclick="bg.form.submit('${tag.id}');return false;">
						<i class="icon-search"></i>
						搜 索
					</button>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function initExpandBtn() {
			var box = $("#${tag.id} div").eq(0);
			var height = box.height();
			if (height > 40) {
				var sbtn = $("#${tag.id} button[type='submit']");
				var btn = $('<a href="#" class="expand_search_btn"></a>');
				btn.insertAfter(sbtn);
				btn.click(function() {
					if ($(this).attr('class') == 'expand_search_btn') {
						box.animate({
							height : height + "px"
						});
						$(this).attr('class', 'closed_search_btn');
					} else {
						box.animate({
							height : "32px"
						});
						$(this).attr('class', 'expand_search_btn');
					}

					return false;
				});
				box.css("height", "32px");
			}
		}
		initExpandBtn();
		[#-- bg.Go('${base}/user/list', 'grid'); --]
	</script>