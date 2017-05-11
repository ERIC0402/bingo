(function(window, undefined) {
	if (bingo.history)
		return;
	
	window.bingo.history = {
		init : function() {
			if (document.location.protocol === 'file:') {
				bg.alert('The HTML5 History API (and thus History.js) do not work on files, please upload it to a server.');
			}
			jQuery(document).ready(function() {
				History.Adapter.bind(window, 'statechange', function(e) {
					var currState = History.getState();
					if (jQuery.type((currState.data || {}).target) != "undefined" && jQuery.type((currState.data || {}).html) != "undefined") {
						jQuery(currState.data.target).empty();
						jQuery(currState.data.target).html(currState.data.html);
					}
				});
			});
		},
		back : function(queue) {
			return History.back(queue);
		},
		forward : function(queue) {
			return History.forward(queue);
		},
		historyGo : function(url, target) {
			var off = url.indexOf(" ");
			if (off >= 0) {
				var selector = url.slice(off, url.length);
				url = url.slice(0, off);
			}
			jQuery.ajax({
				url : url,
				cache : false,
				type : "GET",
				dataType : "html",
				complete : function(jqXHR, status, responseText) {
					responseText = jqXHR.responseText;
					if (jqXHR.success()) {//isResolved
						jqXHR.done(function(r) {
							responseText = r;
						});
						var html = selector ? jQuery("<div>").append(responseText.replace(rscript, "")).find(selector) : responseText;
						try{
							/*History.pushState({
								html : html,
								target : "#" + target
							}, document.title, url);*/
							$("#" + target).html(html);
						}catch(e){}
					}
				}
			});
		},
		historySubmit : function(form, action, target) {
			if (jQuery.type(form) == "string"
					&& form.indexOf("#") != 0) {
				form = "#" + form;
			}
			if (jQuery.type(target) == "string"
					&& target.indexOf("#") != 0) {
				target = "#" + target;
			}
			jQuery(form).ajaxForm(function(result, message, response) {
				var html = result;
				if (message === "success" && response.status == 200 && response.readyState == 4) {
					/*History.pushState({
						html : html,
						target : target
					}, document.title, action);*/
					$(target).html(html);
				}
				return false;
			});
		}
	}
	/*bingo.history.init();*/
})(window);