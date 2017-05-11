/**
 * 页面
 */
function Page(action, target) {
	
	this.formid = "form_" + bg.randomInt();
	this.actionurl = action;
	this.target = target;
	this.pageNo;
	this.pageSize;
	this.total;
	this.orderby;
	this.paramMap = {};
	
	/**
	 * 获取当前页面的一个表单
	 * 第一次获取将以formid创建一个表单
	 * 之后获取将不再创建
	 */
	this.getForm = function() {
		myForm = document.getElementById(this.formid);
		if (null == myForm) {
			myForm = document.createElement("form");
			myForm.setAttribute("id", this.formid);
			myForm.setAttribute("action", this.actionurl);
			myForm.setAttribute("method", "POST");
			if (document.getElementById(this.target)) {
				document.getElementById(this.target).appendChild(myForm);
				myForm.setAttribute("target", this.target);
			} else {
				document.body.appendChild(myForm);
			}
			this.paramMap["actionUrl"] = this.actionurl;
		}
		return myForm;
	}
	
	this.addParams = function(paramSeq) {
		if(paramSeq) {
			this.paramstr = paramSeq;
			var paramArray = paramSeq.split("&"), i, name, value;
			for (i = 0; i < paramArray.length; i++) {
				oneParam = paramArray[i];
				if (oneParam != "") {
					name = oneParam.substr(0, oneParam.indexOf("="));
					value = oneParam.substr(oneParam.indexOf("=") + 1);
					this.paramMap[name] = value;
				}
			}
		}
		return this;
	}
	
	this.orderBy = function(newstring) {
		this.orderby = newstring;
		return this;
	}
	
	this.pageInfo = function(pageNo, pageSize, total) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.total = total;
		if (null != total && null != pageSize && null != pageNo) {
			quotient = Math.floor(total / pageSize);
			this.maxPageNo = (0 == total % pageSize) ? quotient
					: (quotient + 1);
			this.startNo = (pageNo - 1) * pageSize + 1;
			this.endNo = (this.startNo + pageSize - 1) <= total ? (this.startNo
					+ pageSize - 1)
					: total;
			if(total==0){
				this.startNo = 0;
			}
		} else {
			this.maxPageNo = 1;
		}
		return this;
	}

	// 检查分页参数
	this.checkPageParams = function(pageNo, pageSize, orderBy) {
		if (null != pageNo) {
			if (isNaN(pageNo)) {
				bg.alert("输入分页的页码是:" + pageNo + ",它不是个整数");
				return false;
			}
			if (this.maxPageNo != null) {
				if (pageNo > this.maxPageNo) {
					pageNo = this.maxPageNo;
				}
			}
			this.paramMap['pageNo'] = pageNo;
		}
		if (null != pageSize) {
			if (isNaN(pageSize)) {
				bg.alert("输入分页的页长是:" + pageSize + ",它不是个整数");
				return false;
			}
			this.paramMap["pageSize"] = pageSize;
		}
		if (null != orderBy && orderBy != "null") {
			this.paramMap["orderBy"] = orderBy;
		}
		return true;
	}
	
	// jump to page using form submit
	this.goPageNormal = function(pageNo, pageSize, orderBy) {
		var myForm = document.createElement("form"), key, value;
		myForm.setAttribute("action", this.actionurl);
		myForm.setAttribute("method", "POST");
		for (key in this.paramMap) {
			value = this.paramMap[key];
			if (value != "") {
				bg.form.addInput(myForm, key, value, "hidden");
			}
		}
		document.body.appendChild(myForm);
		myForm.submit();
	}
	
	// jump to page using ajax
	this.goPageAjax = function(pageNo, pageSize, orderBy) {
		var myForm = this.getForm(), key, value, submitBtnId, submitx, options_submit;
		for (key in this.paramMap) {
			value = this.paramMap[key];
			if (value != "") {
				bg.form.addInput(myForm, key, value, "hidden");
			}
		}
		submitBtnId = this.formid + "_submitx";
		submitx = document.getElementById(submitBtnId);
		if (null == submitx) {
			submitx = document.createElement('button');
			submitx.setAttribute("id", submitBtnId);
			submitx.setAttribute("type", 'submit');
			submitx.style.display = 'none';
			myForm.appendChild(submitx);
		}
		bg.history.historySubmit(this.formid, this.actionurl, this.target);
		submitx.click();
	}
	
	this.goPage = function(pageNo, pageSize, orderBy) {
		if (this.checkPageParams(pageNo, pageSize, orderBy)) {
			if (this.target && document.getElementById(this.target)) {
				this.goPageAjax(pageNo, pageSize, orderBy);
			} else {
				this.goPageNormal(pageNo, pageSize, orderBy);
			}
		}
	}
	
}

bg.extend({
	'ui.page':{
		// 鼠标经过和移出排序表格的表头时
		overSortTableHeader : function  (){
			this.style.color='white';
			this.style.backgroundColor ='#334d63'
		},
		outSortTableHeader : function (){
			this.style.borderColor='';
			this.style.color='';
			this.style.backgroundColor ='';
		},
		/**
		 * 行选函数。单击行内的任一处，可以选定行头的checkbox或者radio 用法:onclick="onRowChange(event)"
		 */
		onRowChange : function (event){
			var ele = event.target;
			var ck = $(this).find(".checkbox-column :checkbox");
			var isCheck;
        	if(ck && "checkbox" != ele.type) {
        		isCheck = !ck.prop("checked");
            	ck.prop("checked", isCheck).trigger("change");
            	$.uniform.update(ck);
        	}
        	if(isCheck) {
        		this.className = "checked";
        	}else {
        		this.className = "";
        	}
		},
		//列排序对应的onePage和选中的列
		sort : function (onePage,ele){
			if(null==onePage){
				bg.alert("无法找到元素对应的排序表格！");return;
			}
			var orderByStr=null;
			if(ele.className.indexOf("sorting_asc") != -1){
				orderByStr=ele.id.replace(/\,/g," desc,")+" desc";
			}else if(ele.className.indexOf("sorting_desc") != -1){
				orderByStr="";
			}else if(ele.className.indexOf("sorting") != -1){
				orderByStr=ele.id+" asc";
			}
			onePage.goPage(1,onePage.pageSize,orderByStr);
		},

		/**
		 * 初始化排序表格<br/>
		 * 此函数主要是向已经待排序表格的列头1)添加鼠标事件响应和显示效果. 2)负责将事件传递到用户定义的函数中.
		 * 
		 * 凡是要排序的列,请注名排序单元格的id 和class. 其中id是排序要传递的字段,class为定值gridhead-kable.
		 * 除此之外,用户(使用该方法的人)需要自定义一个钩子函数"sortBy(what)",以备调用.
		 * 
		 * @param tableId 待排序表格的id
		 * @param onePage 与表格对应的page对象
		 */
		init : function (tableId,onePage){
			var table= document.getElementById(tableId), thead = table.tHead, tbody =  table.tBodies[0], columnSort ,i ,j, head, row, cell, desc, asc, orignRowCls;
			if(!thead || thead.rows.length==0){
				//bg.alert("sortTable ["+tableId+"] without thead"); 
				return;
			}
			var pageNo = parseInt(onePage.pageNo);
			var pageSize = onePage.pageSize;
			var orderBy=onePage.orderby;
			columnSort = function(event){
				// this is a td/th
				bg.ui.page.sort(onePage,this);
			}
			for(j=0;j<thead.rows.length;j++){
				head=thead.rows[j];
				for(i=0;i<head.cells.length;i++){
					cell=head.cells[i];
					if(cell.className.indexOf("sorting") != -1 && null!=cell.id){
						cell.onclick = columnSort;
						cell.onmouseover=bg.ui.page.overSortTableHeader;
						cell.onmouseout=bg.ui.page.outSortTableHeader;
						cell.title="点击按 ["+cell.innerHTML+"] 排序";
						desc=cell.id +" desc";
						if(orderBy == desc){
							cell.className="sorting_desc";
								//cell.innerHTML=cell.innerHTML+'<img src="'+bg.getContextPath()+'/static/themes/' + bg.uitheme + '/icons/16x16/actions/sort-desc.png"  style="border:0"  alt="Arrow" />'
							continue;
						}
						asc = cell.id +" asc";
						if(orderBy==asc){
							cell.className="sorting_asc";
								//cell.innerHTML=cell.innerHTML+'<img src="'+bg.getContextPath()+'/static/themes/' + bg.uitheme + '/icons/16x16/actions/sort-asc.png"  style="border:0"  alt="Arrow" />'
							continue;
						}
					}
				}
			}
			$(":radio.uniform, :checkbox.uniform").uniform();
			
			// 初始化分页选择框
			if(pageSize) {
				$(".dataTables_length select").find("option").each(function() {
					if(pageSize == this.value) {
						this.selected = true;
						return;
					}
				});
			}
			$(".dataTables_length select").select2({
	            minimumResultsForSearch: "-1"
	        });
			$(".dataTables_length select").change(function (){onePage.goPage(1,this.value, onePage.orderby)});
			
			var pagebar = document.getElementById(tableId + "_pagebar");
			if(pagebar) {
				addLi=function(clazz, isDisabled, text, pageNumber){
					var li=document.createElement('li');
					if(isDisabled) {
						clazz += " disabled";
					}
					if(clazz) {
						li.setAttribute("class", clazz);
					}
					var pageHref=document.createElement('a');
					pageHref.setAttribute("href","#");
					pageHref.innerHTML=text;
					li.appendChild(pageHref);
					jQuery(pageHref).click(function(){
						if(!isDisabled) {
							onePage.goPage(pageNumber, pageSize, onePage.orderby);
						}
						return false;
					});
					pagebar.appendChild(li);
				}
				
				addLi("prev", 1 == pageNo, " ← 上一页", pageNo - 1);
				var pageIndex = 0;
				if(pageNo > 3) {
					pageIndex = pageNo - 3;
					if((pageIndex + 5) > onePage.maxPageNo) {
						pageIndex = onePage.maxPageNo - 5;
					}
				}
				for(var i = 1; i <= 5; i++) {
					var page = i + pageIndex;
					if(page < 1) {
						continue;
					}
					if(page > onePage.maxPageNo) {
						break;
					}
					var clazz = "";
					if(page == pageNo) {
						clazz = "active";
					}
					addLi(clazz, false, page, page);
				}
				/*if(onePage.pageNo != 1) {
					addAnchor(null, false, "...");
				}*/
				addLi("next", onePage.maxPageNo == 0 || onePage.maxPageNo == pageNo, "下一页 → ", pageNo + 1);
			}
			if(!tbody)	return;
			for(j=0;j<tbody.rows.length;j++){
				row=tbody.rows[j];
				orignRowCls=row.className;
				if(orignRowCls){
					orignRowCls=" "+orignRowCls;
				}else{
					orignRowCls="";
				}
//				if(j%2==1){
//					row.className="griddata-odd" + orignRowCls;
//				}else{
//					row.className="griddata-even" + orignRowCls;
//				}
				row.onclick = bg.ui.page.onRowChange;
			}
		}
	}
});