<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="../js/jquery.js"></script>
<script src="http://twitter.github.com/bootstrap/1.4.0/bootstrap-modal.js"></script>

<link rel="stylesheet" type="text/css" href="../css/bootstrap.css" />
<script>
			$(document).ready(function() {
				$('#windowTitleDialog').bind('show', function () {
					document.getElementById ("xlInput").value = document.title;
					});
				});
			function closeDialog () {
				$('#windowTitleDialog').modal('hide'); 
				};
			function okClicked () {
				document.title = document.getElementById ("xlInput").value;
				closeDialog ();
				};
			</script>

</head>

<body>
<div id="windowTitleDialog" class="modal hide fade">
				<div class="modal-header">
					<a href="#" class="close">&times;</a>
					<h3>Please enter a new title for this window.</h3>
					</div>
				<div class="modal-body">
					<div class="divDialogElements">
						<input class="xlarge" id="xlInput" name="xlInput" type="text" />
						</div>
					</div>
				<div class="modal-footer">
					<a href="#" class="btn primary" onclick="okClicked ();">OK</a>
					<a href="#" class="btn secondary" onclick="closeDialog ();">Cancel</a>
					</div>
				</div>
				<button data-controls-modal="windowTitleDialog" data-backdrop="true" data-keyboard="true" class="btn danger" onmouseover="sandwich='pastrami';">Launch Modal</button>
	
</body>
</html>