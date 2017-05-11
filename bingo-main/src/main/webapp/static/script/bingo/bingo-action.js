/**
 * 处理
 */
function Action(entity, page) {
	
	var selfaction = this;
	
	
	function NamedFunction(name,func){
		this.name=name;
		this.func=func;
	}
	
	this.formid = "form_" + bg.randomInt();
	
	this.page = page;
	
	this.entity = entity;
	
	function applyMethod(action,method){
		var last1=action.lastIndexOf("/"), shortAction=action;
		if(-1!=last1){
			shortAction=action.substring(0,last1);
		}
		return shortAction+"/"+method;
	}
	
	/**
	 * 提交id前相关操作
	 * 1、判断是否选择数据
	 * 2、创建提交的表单并设置参数
	 */
	this.beforeSubmmitId = function(method, isMulti) {
		var ids = bg.input.getCheckBoxValues(entity+".id");
		if (ids == null || ids == "") {
			bg.alert(isMulti ? "请选择一个或多个进行操作" : "请选择一个进行操作");
			return false;
		}
		if (!isMulti && (ids.indexOf(",") > 0)) {
			bg.alert("请仅选择一条数据");
			return;
		}
		// 获取当前page的表单
		var form=this.page.getForm();
		form.action = applyMethod(this.page.actionurl, method);
		if(this.page.paramstr){
			bg.form.addHiddens(form,this.page.paramstr);
			bg.form.addInput(form, "params", this.page.paramstr, "hidden");
		}
		return true;
	};
	
	/**
	 * 提交选中的id至后台处理器
	 */
	this.submitIdAction=function (method, multiId, confirmMsg, isAjax){
		if (this.beforeSubmmitId(method, multiId)) {
			bingo.confirm(confirmMsg, function(){
				bingo.form.submitId(selfaction.page.getForm(), selfaction.entity + ".id", multiId, null, isAjax);
			});
		}
	};
	
	this.add = function(methodName){
		return new NamedFunction('add',function(){
			if(undefined == methodName || null == methodName){
				methodName = "edit";
			}
			var form= selfaction.page.getForm();
			if("" != selfaction.page.paramstr) bg.form.addHiddens(form, selfaction.page.paramstr);
			bg.form.addInput(form, selfaction.entity + '.id',"");
			if(""!=selfaction.page.paramstr) bg.form.addInput(form, "params", selfaction.page.paramstr, "hidden");
			bg.form.submit(form, applyMethod(selfaction.page.actionurl,methodName));
		});
	};
	
	this.edit = function (){
		return new NamedFunction('edit', function(){
			selfaction.submitIdAction('edit', false);
		});
	};
	
	this.info = function(){
		return new NamedFunction('info',function(){
			selfaction.submitIdAction('info',false);
		});
	};
	
	this.remove=function(confirmMsg){
		confirmMsg=confirmMsg||'您是否确认要删除选中的数据?';
		return new NamedFunction('remove',function(){
			selfaction.submitIdAction('remove', true, confirmMsg);
		});
	};
	
	this.single = function(methodName, confirmMsg, extparams){
		return new NamedFunction(methodName, function(){
			var form=selfaction.getForm();
			if(null!=extparams) bg.form.addHiddens(form,extparams);
			selfaction.submitIdAction(methodName, false, confirmMsg);
		});
	};
	
	this.multi = function(methodName,confirmMsg,extparams,ajax){
		return new NamedFunction(methodName,function(){
			try {
				var form = selfaction.page.getForm();
				if(null!=extparams) bg.form.addHiddens(form, extparams);
				selfaction.submitIdAction(methodName, true, confirmMsg, ajax);
			}catch(e){
				bg.alert(e);
			}
		});
	};
}