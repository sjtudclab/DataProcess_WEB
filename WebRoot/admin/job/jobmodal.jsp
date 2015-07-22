<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<dd><c:out value="${job.name }"></c:out></dd>
<!--  
<dt>mapper:</dt>
<dd><c:out value="${job.mapper }"></c:out></dd>

<dt>reducer:</dt>
<dd><c:out value="${job.reducer }"></c:out></dd>

<dt>input format:</dt>
<dd><c:out value="${job.inputFormat }"></c:out></dd>

<dt>map input key</dt>
<dd><c:out value="${job.mapInputKey }"></c:out></dd>

<dt>map input value</dt>
<dd><c:out value="${job.mapInputValue }"></c:out></dd>

<dt>map output key</dt>
<dd><c:out value="${job.mapOutputKey }"></c:out></dd>

<dt>map output value:</dt>
<dd><c:out value="${job.mapOutputValue }"></c:out></dd>

<dt>output key:</dt>
<dd><c:out value="${job.outputKey }"></c:out></dd>

<dt>output value:</dt>
<dd><c:out value="${job.outputValue }"></c:out></dd>

<dt>iterative:</dt>
<dd><c:out value="${job.iterative }"></c:out></dd>

<dt>parameters:</dt>
<dd><c:out value="${job.parameters }"></c:out></dd>
-->

<dt>description:</dt>
<dd><c:out value="${job.description }"></c:out></dd>

<dt>upload time:</dt>
<dd><c:out value="${job.uploadTime }"></c:out></dd>

<dt>developer:</dt>
<dd><c:out value="${developer.name }"></c:out></dd>

</dl>
</div>
</body>
</html>