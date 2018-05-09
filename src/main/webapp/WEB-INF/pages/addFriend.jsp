<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>addFriend</title>
    <link rel="stylesheet" type="text/css" href="/static/css/custom.css"/>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/user/searchFriend" method="get">
        <label><input type="hidden" name="id" value="${userId}"/></label>
        <label class="fieldError">${fieldError.verifyCode}<br/></label>
        <label>账号：<input type="text" name="account" value=""/></label><br/>
        <label><input type="submit" value="搜索"/></label>
    </form>
</div>
<div>
    <c:if test="${friend==null}">
        <label class="fieldError">账号不存在</label>
    </c:if>
    <c:if test="${friend!=null}">
        <form action="${pageContext.request.contextPath}/user/addFriend" method="get">
            <div style="float: left">
                <img width="50px" height="50px"
                     src="${pageContext.request.contextPath}/${friend.userInformation.headPortrait}"/>
            </div>
            <div>
                <label><input type="hidden" name="id" value="${userId}"/></label><br/>
                <input type="hidden" name="account" value="${friend.account}"/>
                <span><strong>${friend.name}</strong><label style="float:right;margin-right: 50px"></label></span>
                <label><input type="submit" value="添加好友" /></label>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>
