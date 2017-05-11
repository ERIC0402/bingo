/**
 * 生成一个工具栏
 * 
 * @param divId
 *            工具栏对应的div
 * @param title
 *            工具栏的标题
 * @param imageName
 *            工具栏顶头的图片名称
 */
function ToolBar(id, title, imageName) {
	this.itemCount = 0;
	this.bar = document.getElementById(id);
	if (null == this.bar) {
		bg.alert("cannot find div with id " + id);
		return;
	}
	this.id = id;
	var defaultItemImageClass = "fa-file-o", defaultItemColorClass = "default",imageRoot = bg.getContextPath() + "/static/themes/" + bg.uitheme + "/icons/", imagePath = imageRoot + "16x16/actions/";

	this.init = function (title,imageName){
		if(title) {
			var titleSpan = $("<span class='ico_default pull-left'><i class='icon-th-large' style='color: #658db3;vertical-align: middle'> </i><font style='font-size: 15px;'>" + title + "</font></span>")[0];
			this.bar.appendChild(titleSpan);
		}
	}
	this.init(title,imageName);
	
	function getDefaultImageClass(action) {
		if (null == action)
			return defaultItemImageClass;
		if (typeof action == "object") {
			action = action.name;
		}
		if (typeof action == "string") {
			if (action.indexOf("add") != -1 || action.indexOf("new") != -1)
				return "fa-plus";
			if (action.indexOf("update") != -1 || action.indexOf("edit") != -1 || action.indexOf("Edit") != -1)
				return "fa-edit";
			if (action.indexOf("remove") != -1 || action.indexOf("delete") != -1)
				return "fa-times";
			if (action.indexOf("export") != -1)
				return "ico_export";
			if (action.indexOf("copy") != -1)
				return "fa-copy";
			if (action.indexOf("print") != -1)
				return "fa-print";
			if (action.indexOf("refresh") != -1)
				return "fa-refresh";
			if (action.indexOf("close") != -1)
				return "fa-times";
			if (action.indexOf("save") != -1)
				return "fa-save";
			if (action.indexOf("download") != -1)
				return "fa-download";
			else
				return defaultItemImageClass;
		} else
			return defaultItemImageClass;
	}

	function getDefaultItemColor(action) {
		if (null == action)
			return defaultItemColorClass;
		if (typeof action == "object") {
			action = action.name;
		}
		if (typeof action == "string") {
			if (action.indexOf("add") != -1 || action.indexOf("new") != -1)
				return defaultItemColorClass;
			if (action.indexOf("update") != -1 || action.indexOf("edit") != -1 || action.indexOf("Edit") != -1)
				return defaultItemColorClass;
			if (action.indexOf("remove") != -1 || action.indexOf("delete") != -1)
				return "red";
			if (action.indexOf("export") != -1)
				return defaultItemColorClass;
			if (action.indexOf("copy") != -1)
				return defaultItemColorClass;
			if (action.indexOf("print") != -1)
				return defaultItemColorClass;
			if (action.indexOf("refresh") != -1)
				return defaultItemColorClass;
			if (action.indexOf("close") != -1)
				return "red";
			if (action.indexOf("save") != -1)
				return "green";
			// if(action.indexOf("activate")!=-1) return "green";
			// if(action.indexOf("freeze")!=-1) return "red";
			if (action.indexOf("download") != -1)
				return defaultItemColorClass;
			else
				return defaultItemColorClass;
		} else {
				return defaultItemColorClass;
		}
	}
	/**
	 * 设置按钮的动作
	 * 
	 */
	function setAction(item, action) {
		if (null == action) {
			bg.alert("action should not be null");
			return;
		}
		if (typeof action == 'function') {
			item.onclick = action;
			return;
		}
		if (typeof action == 'string') {
			if (action.indexOf('(') != -1) {
				item.onclick = function() {
					eval(action);
				}
			} else if (action.indexOf('.action') != -1) {
				item.onclick = function() {
					Go(action)
				}
			} else {
				bg.alert("unsuported action description:" + action);
			}
		}
		if (typeof action == 'object') {
			item.onclick = action.func;
			return;
		}
	}
	
	/**
	 * 添加菜单
	 * 
	 */
	this.addItem = function(title, action, imageClass, buttonStyle, alt) {
		var itemDiv = document.createElement('div');

		if (null == imageClass) {
			imageClass = getDefaultImageClass(action);
		}
		var item = $(itemDiv);
		item.addClass("btn-group");
		var itemButton = document.createElement('a');
		var iButton = $(itemButton);
		if (buttonStyle == "" || buttonStyle == null) {
			buttonStyle = getDefaultItemColor(action)
		}
		iButton.addClass("btn " + buttonStyle); // btn-lg 大按钮
		
		iButton.css("margin", "5px 5px 0px 0px");

		if ($(this.bar).css("float") == "right") {
			item.addClass("pull-right");
		}
		iButton.append(title);
		if (imageClass.indexOf(".") > 0) {
			iButton.append('&nbsp;<i class="fa"><img style="margin-bottom:2px;height:14px;" src="' + imageClass + '"/></i>');
		} else {
			iButton.append('&nbsp;<i class="fa ' + imageClass + '"></i>');
		}
		if (alt == "" || alt == null) {
			alt = title;
		}

		item.append(iButton);

		itemDiv.alt = alt;
		setAction(itemDiv, action);
		this.bar.appendChild(itemDiv);
		this.itemCount++;
		return itemDiv;
	}

	this.addBack = function(title) {
		if (null == title) {
			title = "返回";
		}
		var back = $("<a href='javascript:void(0);' class='btn button-previous pull-right'><i class='icon-arrow-left'></i>" + title + "</a>")[0];
		back.style.margin = "5px 0px 0px 0px";
		this.bar.appendChild(back);
		back.onclick = function(title, opts) {
			var sinput = $(".searchButton").first();
			if (sinput.length > 0) {
				sinput.click();
				return;
			} else {
				var div = $(this).parents("._ajax_target").first();
				// 2015年01月27日 history remove
				if (div.length > 0) {
					bg.history.historyGo(div.attr("url"), div.attr("id"));
				}else {
					history.back();
				}
			}
		};
	}
	
	this.addHelp = function(module) {
		this.addItem("帮助", function() {
			if (null == module)
				bg.alert("建设中..");
			else
				window.open("help.action?helpId=" + module);
		}, 'help-contents.png');
	}

	this.addPrint = function(msg) {
		if (null == msg) {
			this.addItem("打印", "print()");
		} else {
			this.addItem(msg, "print()");
		}
	}

	this.addClose = function(msg) {
		if ('' == msg || null == msg) {
			msg = "关闭";
		}
		this.addItem(msg, "window.close()", 'close.png');
	}
	
}
bg.extend({
	'ui.toolbar' : function(divId, title, imageName) {
		return new ToolBar(divId, title, imageName);
	}
});