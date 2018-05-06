<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>index</title>
</head>
<body>
<h2>index</h2>

<a href="${pageContext.request.contextPath}/user/toLogin">Login</a>
<a href="/user/toLogin.action">Login.action</a>
<a href="/user/toLogin.html">Login.html</a>
<a href="${pageContext.request.contextPath}//user/toRegister">Register</a>
<a href="/user/toRegister.action">Register.action</a>
<a href="/user/toRegister.html">Register.html</a>
</body>
</html>
