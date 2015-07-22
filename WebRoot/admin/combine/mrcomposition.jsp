<!-- saved from url=(0014)about:internet -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="/DataProcess_WEB/css/bootstrap.css" rel="stylesheet">
<script src="/DataProcess_WEB/admin/combine/AC_OETags.js" language="javascript"></script>
<script src="/DataProcess_WEB/js/jquery.js" language="javascript"></script>
<script src="/DataProcess_WEB/js/ajax.js" language="javascript"></script>
<script src="/DataProcess_WEB/js/bootstrap.js"></script> 
 <script src="../js/bootstrap-confirm.js"></script>
<!--  BEGIN Browser History required section 
<script src="/DataProcess_WEB/admin/combine/history/history.js" language="javascript"></script> -->
<!--  END Browser History required section -->

<style>
body { margin: 0px; overflow:hidden }
</style>
<script language="JavaScript" type="text/javascript">
<!--
// -----------------------------------------------------------------------------
// Globals
// Major version of Flash required
var requiredMajorVersion = 11;
// Minor version of Flash required
var requiredMinorVersion = 1;
// Minor version of Flash required
var requiredRevision = 0;
// -----------------------------------------------------------------------------
// -->
</script>
</head>

<body>
<script language="JavaScript" type="text/javascript">
<!--
// Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
var hasProductInstall = DetectFlashVer(6, 0, 65);

// Version check based upon the values defined in globals
var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);

if ( hasProductInstall && !hasRequestedVersion ) {
	// DO NOT MODIFY THE FOLLOWING FOUR LINES
	// Location visited after installation is complete if installation is required
	var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
	var MMredirectURL = window.location;
    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
    var MMdoctitle = document.title;

	AC_FL_RunContent(
		"src", "playerProductInstall",
		"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
		"width", "100%",
		"height", "100%",
		"align", "middle",
		"id", "mrcomposition",
		"quality", "high",
		"bgcolor", "#ffffff",
		"name", "mrcomposition",
		"allowScriptAccess","sameDomain",
		"type", "application/x-shockwave-flash",
		"pluginspage", "http://www.adobe.com/go/getflashplayer"
	);
} else if (hasRequestedVersion) {
	// if we've detected an acceptable version
	// embed the Flash Content SWF when all tests are passed
	AC_FL_RunContent(
			"src", "combine/mrcomposition",
			"width", "1000",
			"height", "500",
			"align", "middle",
			"id", "mrcomposition",
			"quality", "high",
			"bgcolor", "#ffffff",
			"name", "mrcomposition",
			"allowScriptAccess","sameDomain",
			"type", "application/x-shockwave-flash",
			"pluginspage", "http://www.adobe.com/go/getflashplayer"
	);
  } else {  // flash is too old or we can't detect the plugin
    var alternateContent = 'Alternate HTML content should be placed here. '
  	+ 'This content requires the Adobe Flash Player. '
   	+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
    document.write(alternateContent);  // insert non-flash content
  }

var content="<s:property value="#request.content" escape="false"/>";

		function get_info(_url,_params){
			return $.ajax({
				type: "post",
		        dataType: "json",
		        url : _url,
		        data: "",
		        success: function(data){
		        	mrcomposition.addQueries12(data.jobs);
		        	if(content!="")
		        	mrcomposition.templateInitOut(content);
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert("fail");
				}
			});
		}

		function save_jcdl(_content){
			window.location.href="tempTrans.action?content="+encodeURI(_content);
		}
		
		
		
		


</script>

<div id="tempModal" class="modal hide fade">
				<div class="modal-header">
					<a href="#" onclick="closeDialog()" class="close">&times;</a>
					<h3>Template Detail</h3>
					</div>
				<div class="modal-body">
					</div>
				<div class="modal-footer">
					<button class="btn" onclick="closeDialog()">Cancel</button>
					</div>
				</div>

<noscript>
  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="mrcomposition" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="combine/mrcomposition.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#ffffff" />
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="combine/mrcomposition.swf" quality="high" bgcolor="#ffffff"
				width="1000" height="500" name="mrcomposition" align="middle"
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object>
</noscript>


</body>
</html>