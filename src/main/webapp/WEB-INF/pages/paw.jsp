<%--
  Created by IntelliJ IDEA.
  User: cjx913
  Date: 2018/5/6
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>password</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/editPassword" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    原密码：<input type="password" name="originalPassword">${fieldError.password}<br/>
    新密码：<input type="password" name="password"><br/>
    再次输入：<input type="password" name="againPassword"><br/>${fieldError.againPassword}
    <input type="submit" value="OK">
</form>
</body>
</html>
