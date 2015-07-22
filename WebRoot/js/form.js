function form_submit(_formId,_context,_callback){
	var values=document.getElementById(_formId).getElementsByClassName("form_value");
	var i=0;
	var params="";
	for(i=0;i<values.length;i++){
		if(values[i].type=="radio"&&!values[i].checked){
			continue;
		}
		if(values[i].type=="textarea"){
			params+="&"+values[i].name+"="+textareaSubmit(values[i]);
			continue;
		}
		params+="&"+values[i].name+"="+values[i].value;
	}
	
	var checkcode=document.getElementById(_formId).getElementsByClassName("form_checkcode");
	if(checkcode.length==1){
		params+="&checkCode="+checkcode[0].value;
	}
	
	params+="&authToken="+getCookie("auth_token");
	
	ajax(_context,document.getElementById(_formId).getAttribute("form_url"),params,function(data){
		_callback(data);
	});
}

function local_form_submit(_formId,_context,_callback){
	var values=document.getElementById(_formId).getElementsByClassName("form_value");
	var i=0;
	var params="";
	for(i=0;i<values.length;i++){
		if(values[i].type=="radio"&&!values[i].checked){
			continue;
		}
		if(values[i].type=="textarea"){
			params+="&"+values[i].name+"="+textareaSubmit(values[i]);
			continue;
		}
		params+="&"+values[i].name+"="+values[i].value;
	}
	
	var checkcode=document.getElementById(_formId).getElementsByClassName("form_checkcode");
	if(checkcode.length==1){
		params+="&checkCode="+checkcode[0].value;
	}
	
	local_ajax(_context,document.getElementById(_formId).getAttribute("form_url"),params,function(data){
		_callback(data);
	});
}

function default_submit(_form,_context,_params,_callback,_validator,_lastRequest){
//	var fields=document.getElementById(_form).getElementsByClassName("form_checkfield");
//	var j=0;
//	for(j=0;j<fields.length;j++){
//		var field=fields[j];
//		var url=field.getAttribute("action");
//		var param=field.getElementsByClassName("form_value")[0].name+"="+field.getElementsByClassName("form_value")[0].value;
//		
//		ajax(_context,url,param,function(data){
//			if(data.status="failure"){
//				field.className="control-group error";
//				field.getElementsByClassName("form_status")[0].className="form_status icon-remove";
//				
//				var error_div=document.getElementById(document.getElementById(_form).getAttribute("error_div"));
//				error_div.getElementsByClassName("form_error")[0].innerText=data.message;
//				error_div.className=error_div.className.replace(" hide","");
//				
//				return;
//			}
//		});
//	}
	
	var fields=document.getElementById(_form).getElementsByClassName("form_checkfield");
	var j=0;
	for(j=0;j<fields.length;j++){
		var field=fields[j];
		var s=_validator+".validate"+field.getElementsByClassName("form_value")[0].name.charAt(0).toUpperCase()+field.getElementsByClassName("form_value")[0].name.substring(1)+"(field.getElementsByClassName('form_value')[0].value)";
		if(!eval(s)){
			return;
		}
	}
	
	form_submit(_form,_context,function(data){
		if(_callback!=null){
			_callback(data);
		}
		var redirect=eval("redirectMap."+document.getElementById(_form).getAttribute("form_url").replace("?","_")+"_"+data.status);
		if(_lastRequest!=null&&_lastRequest!="?"&&_lastRequest!=""&&data.status=="success"){
			window.location.href=_context+_lastRequest;
		}else if(redirect==null){
			if(data.status=="failure"&&data.message!=undefined){
				var error_div=document.getElementById(document.getElementById(_form).getAttribute("error_div"));
				error_div.getElementsByClassName("alert-heading")[0].innerHTML="Error!";
				error_div.getElementsByClassName("form_error")[0].innerText=data.message;
				error_div.className="alert alert-error";
			}else if(data.status=="success"&&data.message!=undefined){
				var error_div=document.getElementById(document.getElementById(_form).getAttribute("error_div"));
				error_div.getElementsByClassName("alert-heading")[0].innerHTML="Success!";
				error_div.getElementsByClassName("form_error")[0].innerText=data.message;
				error_div.className="alert alert-success";
			}
			default_clean_form(_form);
		}else{
			var param_str="";
			if(_params!=null){
				var p=_params.split(" ");
				var i=0;
				for(i=0;i<p.length;i++){
					if(p[i]!=""){
						param_str+="&"+p[i]+"="+eval("data."+p[i]);
					}
				}
			}
			window.location.href=_context+redirect.namespace+"/"+redirect.action+"?"+param_str;
		}
	});
}

function default_clean_form(_form){
	var fields=document.getElementById(_form).getElementsByClassName("form_clean");
	var i;
	for(i=0;i<fields.length;i++){
		if(fields[i].type=="radio"){
			if(fields[i].className.indexOf("form_default")!=-1){
				fields[i].checked=true;
				break;
			}
		}else if(fields[i].tagName=="select"||fields[i].tagName=="SELECT"){
			//TODO
			var options=fields[i].options;
			var j;
			for(j=0;j<options.length;j++){
				if(options[j].className.indexOf("form_default")!=-1){
					options[j].selected=true;
					break;
				}
			}
		}else{
			fields[i].value="";
		}
	}
	
	var fields=document.getElementById(_form).getElementsByClassName("form_checkfield");
	var k=0;
	for(k=0;k<fields.length;k++){
		var field=fields[k];
		field.className="control-group form_checkfield";
		field.getElementsByClassName("form_status")[0].className="form_status";
	}
	
	var check_codes=document.getElementById(_form).getElementsByClassName("form_checkcode");
	if(check_codes.length!=0){
		check_codes[0].value="";
		changecode(document.getElementById(_form).getElementsByClassName("form_checkcode_img")[0]);
	}
}

function default_submit_check(_field,_context,_validator){
	var field=document.getElementById(_field);
	field.getElementsByClassName("form_value")[0].onblur=function(){
		if(_validator!=null){
			var s=_validator+".validate"+field.getElementsByClassName("form_value")[0].name.charAt(0).toUpperCase()+field.getElementsByClassName("form_value")[0].name.substring(1)+"(field.getElementsByClassName('form_value')[0].value)";
			var check=eval(s);
			if(!check){
				field.className="control-group form_checkfield error";
				field.getElementsByClassName("form_status")[0].className="form_status icon-remove";
				return;
			}
		}
		
		var url=field.getAttribute("action");
		var param=field.getElementsByClassName("form_value")[0].name+"="+field.getElementsByClassName("form_value")[0].value;
		ajax(_context,url,param,function(data){
			if(data.status=="success"){
				field.className="control-group form_checkfield success";
				field.getElementsByClassName("form_status")[0].className="form_status icon-ok";
			}else if(data.status=="failure"){
				field.className="control-group form_checkfield error";
				field.getElementsByClassName("form_status")[0].className="form_status icon-remove";
			}
		});
	};
}

function default_local_check(_field,_validator){
	var field=document.getElementById(_field);
	var i=0;
	var fields=field.getElementsByClassName("form_value");
	for(;i<fields.length;i++){
		fields[i].onblur=function(){
			var s=_validator+".validate"+field.getElementsByClassName("form_value")[0].name.charAt(0).toUpperCase()+field.getElementsByClassName("form_value")[0].name.substring(1)+"(field.getElementsByClassName('form_value')[0].value)";
			var check=eval(s);
			if(check){
				field.className="control-group form_checkfield success";
				field.getElementsByClassName("form_status")[0].className="form_status icon-ok";
			}else{
				field.className="control-group form_checkfield error";
				field.getElementsByClassName("form_status")[0].className="form_status icon-remove";
			}
		};
	}
}

function default_init(_forms,_url,_namespace,_context){
	var form_list=_forms.split(",");
	var delays="";
	ajax(_context,_url,"authToken="+getCookie("auth_token"),function(data){
		var i=0;
		for(i=0;i<form_list.length;i++){
			var fields=document.getElementById(form_list[i]).getElementsByClassName("form_value");
			var j=0;
			for(j=0;j<fields.length;j++){
				if(fields[j].type=="radio"){
					if(fields[j].value==eval("data."+_namespace+"."+fields[j].name)){
						fields[j].checked=true;
					}
				}else if(fields[j].tagName=="select"||fields[j].tagName=="SELECT"){
					if(fields[j].className.indexOf("field_delay_init")!=-1){
						delays+=fields[j].id+":"+eval("data."+_namespace+"."+fields[j].name)+";";
					}else{
						fields[j].value=eval("data."+_namespace+"."+fields[j].name);
					}
				}else{
					fields[j].value=eval("data."+_namespace+"."+fields[j].name);
				}
			}
		}
		
		var delay=delays.split(";");
		for(j=0;j<delay.length-1;j++){
			var id=delay[j].split(":")[0];
			var value=delay[j].split(":")[1];
			
			var f=document.getElementById(id);
			var parent_value=document.getElementById(f.getAttribute("parent_field")).value;
			var url=f.getAttribute("delay_pattern").replace("{}",parent_value);
			
			load_option(_context,$("#"+id),url,value);
			f.value=value;
		}
	});
}

function param_init(_forms,_url,_namespace,params,_context){
	var form_list=_forms.split(",");
	var delays="";
	params+="&authToken="+getCookie("auth_token");
	ajax(_context,_url,params,function(data){
		var i=0;
		for(i=0;i<form_list.length;i++){
			var fields=document.getElementById(form_list[i]).getElementsByClassName("form_value");
			var j=0;
			for(j=0;j<fields.length;j++){
				var field_content=eval("data."+_namespace+"."+fields[j].getAttribute("name"));
				if(fields[j].getAttribute("data_format")=="date"){
					field_content=JSONDateToJSDate(field_content);
				}
				if(fields[j].type=="radio"){
					if(fields[j].value==field_content){
						fields[j].checked=true;
					}
				}else if(fields[j].tagName=="textarea"||fields[j].tagName=="TEXTAREA"){
					textareaInit(fields[j],field_content);
				}else if(fields[j].tagName=="select"||fields[j].tagName=="SELECT"){
					if(fields[j].className.indexOf("field_delay_init")!=-1){
						delays+=fields[j].id+":"+field_content+";";
					}else{
						fields[j].value=field_content;
					}
				}else if(fields[j].tagName=="label"||fields[j].tagName=="LABEL"||fields[j].tagName=="span"||fields[j].tagName=="SPAN"||fields[j].tagName=="textarea"||fields[j].tagName=="TEXTAREA"){
					fields[j].innerHTML=field_content;
				}else if(fields[j].tagName=="ul"||fields[j].tagName=="UL"){
					if(field_content!=null){
						var vs=field_content.split(",");
						var k=0;
						for(;k<vs.length;k++){
							var li=document.createElement("li");
							li.innerHTML=vs[k];
							fields[j].appendChild(li);
						}
					}
				}else if(fields[j].tagName=="div"||fields[j].tagName=="DIV"){
					if(field_content!=null){
						var vs=field_content.split(",");
						var k=0;
						for(;k<vs.length;k++){
							var span=document.createElement("span");
							span.innerHTML=vs[k];
							fields[j].appendChild(span);
						}
					}
				}else{
					fields[j].value=field_content;
				}
			}
			
			var srcs=document.getElementById(form_list[i]).getElementsByClassName("form_src");
			for(j=0;j<srcs.length;j++){
				var field_content=eval("data."+_namespace+"."+srcs[j].getAttribute("name"));
				srcs[j].src=field_content;
			}
		}
		
		var delay=delays.split(";");
		for(j=0;j<delay.length-1;j++){
			var id=delay[j].split(":")[0];
			var value=delay[j].split(":")[1];
			
			var f=document.getElementById(id);
			var parent_value=document.getElementById(f.getAttribute("parent_field")).value;
			var url=f.getAttribute("delay_pattern").replace("{}",parent_value);
			
			load_option(_context,$("#"+id),url,value);
			f.value=value;
		}
	});
}

function textareaInit(_textarea,_content){
	if(_textarea.className =="ckeditor" || _textarea.className.match(new RegExp("(^|\\s)" + "ckeditor" + "(\\s|$)"))){
		var editor=eval("CKEDITOR.instances."+_textarea.id);
		editor.setData(_content);
	} else {
		_textarea.innerHTML=_content;
	}
}

function textareaSubmit(_textarea){
	if(_textarea.className =="ckeditor" || _textarea.className.match(new RegExp("(^|\\s)" + "ckeditor" + "(\\s|$)"))){
		var editor=eval("CKEDITOR.instances."+_textarea.id);
		return editor.getData();
	} else {
		return _textarea.value;
	}
}


var GLOBAL={};
GLOBAL.namespace=function(str){
  var arr=str.split("."),o=GLOBAL;
  for( i=(arr[0]=="GLOBAL") ? 1:0; i<arr .length; i++){
    o[arr[i]]=o[arr[i]] || {};
    o=o[arr[i]];
  }
};
GLOBAL.namespace("Dom");
GLOBAL.Dom.getElementsByClassName=function(str,root,tag){
      if(root){
        root=typeof root=="string"?document.getElementById(root):root;
      }
      else{
        root=document.body;
      }
      tag=tag || "*";
      var els=root.getElementsByTagName(tag),arr=[];
      for(var i=0;i<els.length;i++){
        for(var j=0,k=els[i].className.split(" "),l=k.length;j<l;j++){
          if(k[j]==str){
            arr.push(els[i]);
            break;
          }
        }
      }
      return arr;
};
   
function setCookie(name,value,expires,path) {
	     if(expires == "") {
	         var now = new Date();
	         now.setMonth(now.getMonth() + 6);
	         expires = now.toUTCString();
	     }
	     if(path != "") {
	         path = ";path=" + path;
	     }
	     document.cookie = name + "=" + value + ";expires=" + expires + path;
}
function getCookie(name)
{
   var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
   if(arr != null) return decodeURI(arr[2]); return null;
}
function delCookie(name)//ɾ��cookie
{
   var exp = new Date();
   exp.setTime(exp.getTime() - 1);
   var cval=getCookie(name);
   if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}