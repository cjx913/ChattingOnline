<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/custom.css"/>
    <style type="text/css">

        #login {
            min-width: 450px;
            min-height: 300px;
            width: 30%;
            height: 30%;
            margin: auto;
            margin-top: 180px;
            margin-right: auto;
            margin-bottom: auto;
            margin-left: auto;
            padding-left: 30px;
            background-color: darkgrey;
        }



        #canvasVerifyCode {
            margin-left: 30px;
        }


    </style>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/verifycode.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        $(function () {
            verifycode("canvasVerifyCode", "verifyCode");

        });
    </script>
</head>
<body>
<div>
    <form id="login" action="${pageContext.request.contextPath}/user/login" method="post">
        <h2 style="text-align: center;;font-size: 30px">Sign In</h2>
        <label class="loginError">${loginError}</label>
        <%--账号--%>
        <div>
            <label class="fieldError">${fieldError.account}<br/></label>
            <label>账　号：<input name="account" type="text" value=""></label>
        </div>
        <%--密码--%>
        <div>
            <label class="fieldError">${fieldError.password}<br/></label>
            <label>密　码：<input name="password" type="password" value=""></label>
        </div>
        <%--验证码--%>
        <div>
            <label class="fieldError">${fieldError.verifyCode}<br/></label>
            <input id="verifyCode" type="hidden" name="verifyCode"/>
            <label style="float: left">验证码：<input name="canvasVerifyCode" type="text"></label>
            <canvas id="canvasVerifyCode" height="40px" width="100px"
                    onclick="verifycode('canvasVerifyCode','verifyCode')"></canvas>
        </div>
        <br/>
        <div>
            <input class="btn" type="reset" value="清空"/>
            <input class="btn" type="submit" value="确认"/>
        </div>
        <br/>
    </form>
</div>
</body>
</html>
