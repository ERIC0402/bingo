[#ftl/]
	[#assign messages=""/]
	[#if tag.hasActionMessages()]
		[#assign shortCutFunction="success"/]
		[#assign title="提示消息"/]
		[#assign timeOut="5000"/]
		[#list tag.actionMessages as message]
			[#if messages != ""][#assign messages=message+"<br/>"/][/#if]
			[#assign messages=message/]
		[/#list]
	[/#if]
	[#if tag.hasActionErrors()]
		[#assign shortCutFunction="error"/]
		[#assign title="错误消息"/]
		[#assign timeOut="500000"/]
		[#list tag.actionErrors as message]
			[#if messages != ""][#assign messages=message+"<br/>"/][/#if]
			[#assign messages=message/]
		[/#list]
	[/#if]
	[#if tag.hasActionMessages() || tag.hasActionErrors()]
	<script>
		$(function (){
			toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "positionClass": "toast-top-center",
			  "onclick": null,
			  "showDuration": "1000",
			  "hideDuration": "1000",
			  "timeOut": "${timeOut}",
			  "extendedTimeOut": "10000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
			var $toast = toastr["${shortCutFunction}"]("${messages}", "${title}"); 
		});
	</script>
	[/#if]