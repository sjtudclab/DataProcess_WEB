<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
</head>
<body>
<form class="form-horizontal" action="createJob.action" method="post">
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
    <label class="control-label" for="mapper">mapper</label>
    <div class="controls">
      <input type="text" id="mapper" name="mapper" placeholder="mapper">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="reducer">reducer</label>
    <div class="controls">
      <input type="text" id="reducer" name="reducer" placeholder="reducer">
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="inputFormat">inputFormat</label>
    <div class="controls">
      <input type="text" id="inputFormat" name="inputFormat" placeholder="inputFormat">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mapInputKey">mapInputKey</label>
    <div class="controls">
      <input type="text" id="mapInputKey" name="mapInputKey" placeholder="mapInputKey">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="mapInputValue">mapInputValue</label>
    <div class="controls">
      <input type="text" id="mapInputValue" name="mapInputValue" placeholder="mapInputValue">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mapOutputKey">mapOutputKey</label>
    <div class="controls">
      <input type="text" id="mapOutputKey" name="mapOutputKey" placeholder="mapOutputKey">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="mapOutputValue">mapOutputValue</label>
    <div class="controls">
      <input type="text" id="mapOutputValue" name="mapOutputValue" placeholder="mapOutputValue">
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="iterative">iterative</label>
    <div class="controls">
      <input type="text" id="iterative" name="iterative" placeholder="iterative">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="parameters">parameters</label>
    <div class="controls">
      <input type="text" id="parameters" name="parameters" placeholder="parameters">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="description">description</label>
    <div class="controls">
      <textarea rows="3" id="description" name="description" placeholder="description"></textarea>
    </div>
  </div>
  
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">Create Job</button>
    </div>
  </div>
</form>
</body>
</html>