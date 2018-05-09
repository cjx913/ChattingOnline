<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cjx913
  Date: 2018/5/6
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>information</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/custom.css"/>
    <style type="text/css">
        #information {
            min-width: 450px;
            min-height: 300px;
            width: 50%;
            height: auto;
            margin: auto;
            margin-top: 50px;
            margin-right: auto;
            margin-bottom: auto;
            margin-left: auto;
            padding-left: 30px;
            background-color: darkgrey;
        }
    </style>
</head>
<body>
<div style="font-size: 40px;text-align: center">您登陆的账号：<strong style="color: red">${user.account}</strong>
</div>
<div>

    <form id="information" action="${pageContext.request.contextPath}/user/eidtInformation" method="post">
        <c:set value="${user.userInformation}" var="information"></c:set>
        <h2 style="text-align: center">请您完善您的信息</h2>
        <input type="hidden" name="id" value="${user.id}"/>
        <div>
            <label class="fieldError">${fieldError.gender}<br/></label>
            <label>性　别：<input type="radio" name="gender" value="男"/>男
                <input type="radio" name="gender" value="女"/>女</label>
        </div>
        <div>
            <label class="fieldError">${fieldError.age}<br/></label>
            <label>年　龄：<input type="text" name="age" value="${information.age}"/></label>
        </div>
        <div>
            <label class="fieldError">${fieldError.birth}<br/></label>
            <label>生　日：<input type="date" name="birth" value="${information.birth}"/></label><br/>
            <label style="color: lavender">　　　IE浏览器请输入“yyyy-MM-dd”格式</label>
        </div>
        <div>
            <label class="fieldError">${fieldError.address}<br/></label>
            <label>地　址：<input type="text" name="address" value="${information.address}"/></label>
        </div>
        <div>
            <label class="fieldError">${fieldError.email}<br/></label>
            <label>邮　箱：<input type="text" name="email" value="${information.email}"/></label>
        </div>
        <div>
            <label class="fieldError">${fieldError.phone}<br/></label>
            <label>电　话：<input type="text" name="phone" value="${information.phone}"/></label>
        </div>
        <br/>
        <div>
            <input class="btn" type="reset" value="清空"/><input class="btn" type="submit" value="提交"/>
        </div>
        <br/>
    </form>

</div>
</body>
</html>
