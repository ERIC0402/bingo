(function(window) {
	if (bingo)
		return;
	var bingo = function() {
		return true;
	};
	bingo.extend = function(map) {
		for (attr in map) {
			var attrs = attr.split("."), obj = bingo, i;
			for (i = 0; i < attrs.length - 1; i++) {
				obj[attrs[i]] = obj[attrs[i]] || {};
				obj = obj[attrs[i]];
			}
			obj[attrs[attrs.length - 1]] = map[attr];
		}
	}
	window.bingo = bingo;
	window.bg = bingo;
	
	bingo.extend({
		Go : function(obj, target, ajaxHistory) {
			var url = obj;
			if (typeof obj == "object" && obj.tagName.toLowerCase() == "a") {
				url = obj.href;
				if (!target) {
					target = bg.findTarget(obj);
				}
				var a = $(obj);
				if(a.attr("anchor")){
					var iurl = location.href;
					if(iurl.indexOf("#")>0){
						iurl = iurl.substring(0, iurl.indexOf("#")-1);
					}
					location = iurl + "#" + a.attr("anchor");
				}
			}
			if (!target)
				target = "_self";
			if ("_self" == target) {
				self.location = url;
			} else if ("_parent" == target) {
				self.parent.location = url;
			} else if ("_top" == target) {
				self.top.location = url;
			} else if ("_new" == target || "_blank" == target) {
				windown.open(url);
			} else {
				$('#' + target).attr("url", url);
				if (!bg.isAjaxTarget(target)) {
					if (document.getElementById(target))
						document.getElementById(target).src = url;
				} else {
					if (!ajaxHistory) {
						jQuery('#' + target).load(url);
					} else {
						bg.history.historyGo(url, target);
					}
				}
			}
			//window.top.bg.goToTop();
			return false;
		},
		randomInt : function() {
			var num = Math.random() * 10000000;
			num -= num % 1;
			return num;
		},
		alert : function(message) {
			bootbox.alert(message);
		},
		confirm : function(confirmMsg,fn){
			if(null!=confirmMsg && ''!=confirmMsg){
				bootbox.confirm(confirmMsg, function(result) {
					if(result){
						fn();
					}
				});
			}else{
				fn();
			}
		},
		getContextPath : function() {
			if (contextPath != undefined) {
				return contextPath;
			} else {
				return self.location.pathname.substring(0,
						self.location.pathname.substring(1).indexOf('/') + 1);
			}
		},
		ready : function(fn) {
			if (window.addEventListener) {
				window.addEventListener("load", fn, false);
			} else if (window.attachEvent) {
				window.attachEvent("onload", fn);
			} else {
				window.onload = fn;
			}
		},
		isAjaxTarget : function(target) {
			if (!target)
				return false;
			if (target == "" || target == "_new" || target == "_blank"
					|| target == "_self" || target == "_parent"
					|| target == "_top") {
				return false;
			}
			targetEle = document.getElementById(target);
			if (!targetEle)
				return false;
			tagName = targetEle.tagName.toLowerCase();
			if (tagName == "iframe" || tagName == "object") {
				return false;
			}
			return true;
		},
		findTarget : function(ele) {
			if (!ele)
				return "_self";
			var p = ele.parentNode, finalTarget = "_self";
			while (p) {
				// FIXME ui-tabs-panel
				if (p.id && p.className
						&& (p.className.indexOf("_ajax_target") > -1) ) {// ||p.className.indexOf("ui-tabs-panel")>-1   忽略列表Div&& (p.className.indexOf("dataList") < 0)
					finalTarget = p.id;
					break;
				} else {
					if (p == p.parentNode)
						p = null;
					else
						p = p.parentNode;
				}
			}
			ele.target = finalTarget;
			return finalTarget;
		}
	});
	
	bg.extend({
		page : function(action, target) {
			return new Page(action, target);
		},
		addEntityAction : function(entity, page) {
			return new Action(entity, page);
		}
	});

	bingo.extend({
				form : {
					submit : function(myForm, action, target, onsubmit, isAjax) {
						var submitTarget, rs, sumbitBtnId, submitx, origin_target, origin_action, options_submit;
						
						if ((typeof myForm) == 'string') {
							myForm = document.getElementById(myForm);
						}
						// 1、执行方法调用时传入的submit方法
						if (onsubmit) {
							rs = null;
							if (typeof onsubmit == "function") {
								try {
									rs = onsubmit(myForm);
								} catch (e) {
									bg.alert(e.message);
									return;
								}
							} else {
								rs = eval(onsubmit);
							}
							if (rs == false) {
								return;
							}
						}

						// 2.执行表单本身submit事件
						if (myForm.onsubmit) {
							rs = null;
							try {
								rs = myForm.onsubmit();
							} catch (e) {
								bg.alert(e);
								return;
							}
							if (rs == false) {
								return;
							}
						}

						// 3. 检查 target 和  action
						submitTarget = (null != target) ? target : myForm.target;
						if (!submitTarget) {
							submitTarget = bg.findTarget(myForm);
						}
						if (action == null) {
							action = myForm.action;
						}else {
							myForm.action = action;
						}
						
						if (null == isAjax || isAjax) {
							isAjax = bg.isAjaxTarget(submitTarget)
						}
						
						if(isAjax) {
							sumbitBtnId = myForm.id + "_submit";
							submitx = document.getElementById(sumbitBtnId);
							if (null == submitx) {
								submitx = document.createElement('input');
								submitx.setAttribute("id", sumbitBtnId);
								submitx.setAttribute("type", 'submit');
								submitx.style.display = "none";
								myForm.appendChild(submitx);
							}
							
							bg.history.historySubmit(myForm.id, action, submitTarget);
							
							submitx.click();
						}else {
							myForm.submit();
						}

					},
					/**
					 * 提交要求含有id的表单
					 * 
					 * @param form
					 *            带提交的表单
					 * @param id
					 *            要提交id的名称
					 * @param isMulti(可选)是否允许多个id选择,默认支持一个
					 * @param action
					 *            (可选) 指定form的action
					 */
					submitId : function(form, id, isMulti, action, isAjax) {
						var selectId = bg.input.getCheckBoxValues(id);
						if (null == isMulti)
							isMulti = false;
						if ("" == selectId) {
							bg.alert(isMulti ? "请选择一个或多个进行操作" : "请选择一个进行操作");
							return;
						}
						if (!isMulti && (selectId.indexOf(",") > 0)) {
							bg.alert("请仅选择一个");
							return;
						}
						if (null != action) {
							form.action = action;
						} else {
							action = form.action;
						}
						bg.form.addInput(form, (isMulti ? (id + 's') : id), selectId, "hidden");
						bg.form.addInput(form, "entityId", selectId, "hidden");
						bg.form.submit(form, action, null, null, isAjax);
					},
					addHiddens : function(form, paramSeq) {
						if(paramSeq) {
							var params = paramSeq.split("&"), i, name, value;
							for (i = 0; i < params.length; i++) {
								if (params[i] != "") {
									name = params[i].substr(0, params[i]
											.indexOf("="));
									value = params[i]
											.substr(params[i].indexOf("=") + 1);
									bg.form.addInput(form, name, value, "hidden");
								}
							}
						}
					},
					/**
					 * 向form中添加一个input。
					 * 
					 * @param form
					 *            要添加输入的form
					 * @param name
					 *            input的名字
					 * @param value
					 *            input的值
					 * @param type
					 *            input的类型，默认为hidden
					 * @author beangle 2006-4-7
					 */
					addInput : function(form, name, value, type) {
						// 防止设置form的属性
						if (form[name] != null
								&& (typeof form[name]) != "string") {
							form[name].value = value;
						} else {
							if (null == type)
								type = "hidden";
							var input = document.createElement('input');
							input.setAttribute("name", name);
							input.setAttribute("value", value);
							input.setAttribute("type", type);
							form.appendChild(input);
						}
					}
				}
			});
	
	// Input----------------------------------------------------
	bingo.extend({
		input : {
			/**
			 * 返回单选列表中选择的值
			 * 
			 * @return 没有选中时,返回""
			 */
			getRadioValue : function(radioName) {
				var val = ""
				for (i = 0; i < box.length; i++) {
					if (box[i].checked) {
						val = box[i].value;
					}
				}
				return bg.input.boxAction(
						document.getElementsByName(radioName), "value");
			},

			/**
			 * 返回多选列表中选择的值
			 * 
			 * @return 多个值以,相隔.没有选中时,返回""
			 */
			getCheckBoxValues : function(chkname) {
				var val = "", box = document.getElementsByName(chkname);
				for (i = 0; i < box.length; i++) {
					if (box[i].checked) {
						if (val != "") {
							val = val + ",";
						}
						val = val + box[i].value;
					}
				}
				if (val == null)
					return "";
				else
					return val;
			},
		}
	});
	
})(window);

$name = function(name) {
	return $("[name='" + name + "']");
}