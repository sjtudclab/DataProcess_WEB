<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="/DataProcess_WEB/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<form class="form-horizontal" action="createTemplate.action" method="post">
<div class="control-group" style="display:none">
    <label class="control-label" for="path">path</label>
    <div class="controls">
      <input type="text" id="path" name="path" value="${path }" placeholder="path">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="name">name</label>
    <div class="controls">
      <input type="text" id="name" name="name" placeholder="name">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="description">description</label>
    <div class="controls">
      <textarea rows="3" id="description" name="description" placeholder="description"></textarea>
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="description">content</label>
    <div class="controls">
      <textarea rows="3" id="content" name="content" placeholder="content">${content }</textarea>
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Create Template</button>
    </div>
  </div>
</form>
</body>
</html>