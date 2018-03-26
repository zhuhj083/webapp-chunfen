<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,shrink-to-fit=no">
    <meta name="format-detection" content="telephone=no">
    <title>Navyblue</title>
</head>
<body>
<br>
您的税前收入：<fmt:formatNumber pattern="#,###.##">${salary.shouru}</fmt:formatNumber>
<br>
<table align="center" border="1">
    <tr>
        <td>项目</td><td>金额（人民币元￥）</td>
    </tr>
    <tr>
        <td>总收入</td>
        <td><fmt:formatNumber pattern="#,###.##">${salary.shouru}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>养老保险扣除</td>
        <td>-<fmt:formatNumber pattern="#,###.##">${salary.yanglao}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>医疗保险扣除</td>
        <td>-<fmt:formatNumber pattern="#,###.##">${salary.yiliao}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>失业保险扣除</td>
        <td>-<fmt:formatNumber pattern="#,###.##">${salary.shiye}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>公积金扣除</td>
        <td>-<fmt:formatNumber pattern="#,###.##">${salary.gongjijin}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>扣除小计</td>
        <td><fmt:formatNumber pattern="#,###.##">${salary.xiaoji}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>个人所得税</td>
        <td>-<fmt:formatNumber pattern="#,###.##">${salary.geshui}</fmt:formatNumber></td>
    </tr>
    <tr>
        <td>税后收入</td>
        <td><fmt:formatNumber pattern="#,###.##">${salary.shuihou}</fmt:formatNumber></td>
    </tr>
</table>
</body>
</html>
