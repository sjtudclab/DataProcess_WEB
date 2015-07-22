var loading_gif="&nbsp;&nbsp;&nbsp;&nbsp;<img src='../img/icon/wheel_throbber.gif'/>";
var base_path="/SocialNetworkWebService";
	
function ajax(_context,_url,_params,_callback){
	$.ajax({
		type: "post",
        dataType: "json",
        url : _context+"/ws",
        data: "action_name="+_url+"&"+_params,
        success: function(data){
        	_callback(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
};

function local_ajax(_context,_url,_params,_callback){
	$.ajax({
		type: "post",
        dataType: "json",
        url : _context+"/admin/"+_url,
        data: _params,
        success: function(data){
        	_callback(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
};

function local_ajax_xml(_context,_url,_params,_callback){
	$.ajax({
		type: "post",
        dataType: "xml",
        url : _context+"/admin/"+_url,
        data: _params,
        success: function(data){
        	_callback(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		}
	});
};