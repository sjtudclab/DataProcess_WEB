<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/bootstrap.css" rel="stylesheet">
       <script type="text/javascript" src="../js/jquery.js"></script>
    <script src="../js/bootstrap.js"></script> 
    <script src="../js/bootstrap-confirm.js"></script>
</head>
<body>
<div class="container" style="width:500px">
<dl class="dl-horizontal">
<dt>name:</dt>
<dd><c:out value="${temp.name }"></c:out></dd>

<dt>developer:</dt>
<dd><c:out value="${developer.name }"></c:out></dd>

<dt>description:</dt>
<dd><c:out value="${temp.description }"></c:out></dd>

<dt>upload time:</dt>
<dd><c:out value="${temp.uploadTime }"></c:out></dd>


</dl>
</div>
</body>
</html>