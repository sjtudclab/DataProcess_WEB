<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <link href="../css/bootstrap.css" rel="stylesheet">
</head>
<body>
<form class="form-horizontal" action="updateJob.action?id=${job.id }" method="post">
  <div class="control-group">
    <label class="control-label" for="name">name</label>
    <div class="controls">
      <input type="text" id="name" name="name" value="${job.name }" placeholder="name">
    </div>
  </div>
  <!-- 
  <div class="control-group">
    <label class="control-label" for="mapper">mapper</label>
    <div class="controls">
      <input type="text" id="mapper" name="mapper" value="${job.mapper }" placeholder="mapper">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="reducer">reducer</label>
    <div class="controls">
      <input type="text" id="reducer" name="reducer" value="${job.reducer }" placeholder="reducer">
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="inputFormat">inputFormat</label>
    <div class="controls">
      <input type="text" id="inputFormat" name="inputFormat" value="${job.inputFormat }" placeholder="inputFormat">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mapInputKey">mapInputKey</label>
    <div class="controls">
      <input type="text" id="mapInputKey" name="mapInputKey" value="${job.mapInputKey }" placeholder="mapInputKey">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="mapInputValue">mapInputValue</label>
    <div class="controls">
      <input type="text" id="mapInputValue" name="mapInputValue" value="${job.mapInputValue }" placeholder="mapInputValue">
    </div>
  </div>
  
  <div class="control-group">
    <label class="control-label" for="mapOutputKey">mapOutputKey</label>
    <div class="controls">
      <input type="text" id="mapOutputKey" name="mapOutputKey" value="${job.mapOutputKey }" placeholder="mapOutputKey">
    </div>
  </div>
    <div class="control-group">
    <label class="control-label" for="mapOutputValue">mapOutputValue</label>
    <div class="controls">
      <input type="text" id="mapOutputValue" name="mapOutputValue" value="${job.mapOutputValue }" placeholder="mapOutputValue">
    </div>
  </div>

  <div class="control-group">
    <label class="control-label" for="iterative">iterative</label>
    <div class="controls">
      <input type="text" id="iterative" name="iterative" value="${job.iterative }" placeholder="iterative">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="parameters">parameters</label>
    <div class="controls">
      <input type="text" id="parameters" name="parameters" value="${job.parameters }" placeholder="parameters">
    </div>
  </div>
   -->
   
  <div class="control-group">
    <label class="control-label" for="description">description</label>
    <div class="controls">
      <textarea rows="3" id="description" name="description" placeholder="description">${job.description }</textarea>
    </div>
  </div>
  
  <div class="control-group">
    <div class="controls">
      <button type="submit" class="btn">update job</button>
    </div>
  </div>
</form>
</body>
</html>