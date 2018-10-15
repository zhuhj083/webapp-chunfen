<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>TestJspView</title>
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,shrink-to-fit=no">
    <meta name="format-detection" content="telephone=no">
    <script src="http://www.jq22.com/jquery/jquery-1.9.1.js"></script>
</head>
<body>
<div>
    时间 : <span id="times"></span>
</div>
<br><br>
<div>
Hello ${name};
</div>
<script type="application/javascript">
    function getTime() {
        var time = new Date();
        $("#times").html(time.toLocaleString());
    }
    $(function(){
        setInterval("getTime()",1000);
    });
</script>


</body>
</html>
