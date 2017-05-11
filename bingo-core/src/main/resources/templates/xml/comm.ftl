[#ftl]
[#macro group title=""]
<div class="widget-header">
	<h4>
		${title!}
	</h4>
	<div class="toolbar no-padding">
        <div class="btn-group">
          <span class="btn btn-xs widget-collapse">
            <i class="icon-angle-down"></i>
          </span>
        </div>
   </div>
</div>
<div class="widget-content">
	[#nested/]
</div>
[/#macro]

[#macro line label="" required="false" comment=""]
	<div class="form-group">
		<label class="col-md-2 control-label">
			${label!}
			[#if required == "true"]
			<span class="required"> * </span>
			[/#if]
		</label>
		<div class="col-md-10">
			[#nested/]
			[#if comment != ""]<span class="help-block">${comment!}</span>[/#if]
		</div>
	</div>
[/#macro]