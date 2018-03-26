<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Navyblue</title>
</head>
<body>
<div>
    时间:${time}&nbsp;&nbsp;&nbsp;
</div>
<br><br>
<div>
    北京个税计算: 请输入您的税前收入：
    <br>
    <form:form action="/tax" method="post"  modelAttribute="salary" >
        <form:input path="shouru"  ></form:input><form:errors path="shouru"></form:errors>
        <br>
        <input align="right" type="submit" value="提交"/>
    </form:form>
</div>


</body>
</html>
