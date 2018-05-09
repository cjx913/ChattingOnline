<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>chatting</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/custom.css">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/chatting.css.css">--%>
    <style type="text/css">
        #main {
            height: 600px;
            width: 980px;
            margin: auto;
            margin-top: 20px;
            margin-bottom: 10px;
            background-color: white;
            border: 3px solid #dddddd;
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
            border-radius: 5px;
        }

        #letfPanle {
            display: block;
            float: left;
            width: 300px;
            height: 600px;
            background-color: aqua;
        }

        #header {
            position: relative;
            height: 60px;
            width: 290px;
            padding: 5px;
            margin: 5px;
        }

        .avatar {
            margin-left: 5px;
            float: left;
            width: 50px;
            height: 50px
        }

        #info {
            height: 50px;
            float: left;
            margin-left: 20px;
            font-size: 30px;
            line-height: 40px;
        }

        #menu button {
            margin-left: 60px;
            width: 40px;
            height: 50px;
        }

        #menu_items {
            position: absolute;
            margin-left: 200px;
            margin-top: -20px;
            float: right;
            z-index: 100;
            width: 100px;
            background-color: aqua;
            display: none;
        }

        .menu_item {
            height: 30px;
            margin-top: 0px;
            border: 1px solid aqua;
            background-color: aqua;
            padding: auto 10px;
            color: white;
            text-align: center;
        }

        .menu_item a {
            text-decoration: none;
            margin: 0;
            padding: 0;
            color: white;
        }

        .menu_item:hover {
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
        }

        .menu_item a:hover {
            color: brown;
        }

        #menu:hover #menu_items {
            display: inline-block;
            box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);
            color: brown;
        }

        #search {
            position: relative;
            padding-left: 10px;
            width: 290px;
            height: 50px;
        }

        #search input {
            height: 30px;
            width: 200px;
            border: 0px;
            border-radius: 10px;
        }

        #btnsearch {
            margin-left: 10px;
            color: white;
        }

        #btnsearch:hover {
            color: brown;
        }

        #tab {
            position: relative;
            width: 300px;
            height: 50px;
            margin: 0;
        }

        .tab_items {
            height: 40px;
            width: 100px;
            margin-left: 25px;
        }

        #list {
            position: relative;
            width: 300px;
            height: 450px;
        }

        .item {
            position: relative;
            padding-top: 3px;
            padding-left: 10px;
            padding-bottom: 3px;
            height: 50px;
            width: 300px;
        }

        .item:hover {
            box-shadow: 5px 12px 16px 0 rgba(0, 0, 0, 0.24), 10px 17px 50px 0 rgba(0, 0, 0, 0.19);
            color: brown;
        }

        .name {
            height: 40px;
            line-height: 40px;
            font-size: 15px;
            padding-left: 15px;
        }

        #rightPanle {
            height: 600px;
            background-color: white;
        }

        #title_area {
            height: 30px;
            width: 680px;
            text-align: center;
            line-height: 30px;
            font-size: 20px;
            background-color: azure;
            border-bottom: 1px solid #eeeeee;
        }

        #message_area {
            height: 400px;
            width: 660px;
            padding: 10px 10px;
            font-size: 16px;
            line-height: 20px;

        }

        .message_item {
            float: left;
            width: 660px;
            position: relative;
        }

        .selfMessage {
            float: right;
            max-width: 400px;
            margin-right: 30px;
            padding: 5px;
            background-color: yellow;
            border: 1px solid chartreuse;
            border-radius: 10px;
            word-wrap: break-word
        }

        .othersMessage {
            float: left;
            max-width: 400px;
            margin-left: 30px;
            padding: 5px;
            background-color: lawngreen;
            border: 1px solid chartreuse;
            border-radius: 10px;
            word-wrap: break-word
        }

        #edit_area {
            height: 150px;
            width: 680px;

        }

        #edit_area textarea {
            box-sizing: border-box;
            font-size: 16px;
            padding: 10px;
            height: 40px;
            width: 680px;
            height: 100px;
            background-color: white;
            border: none;
            resize: none;
            border-radius: 10px;
            border-top: 3px solid #eeeeee;
            border-bottom: 1px solid #eeeeee;
            /*box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24), 0 17px 50px 0 rgba(0, 0, 0, 0.19);*/
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/websocket.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/chatting.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/webstorage.js"></script>
    <script type="text/javascript">
        var userId;
        var toId;
        var toGroupId;
        var message;
        var ws;
        var url = "ws://localhost:8088" + "${pageContext.request.contextPath}" + "/websocket";
        //页面加载时(在 DOM 结构绘制完成后)执行
        $(function () {
            //创建websocket连接
            ws = getWebsocket(url);

            //好友列表事件
            friendListEvent();
            //群组列表事件
            groupListEvent();
            //接受消息事件
            receiveMessageEvent();


        });

        //页面完全加载时执行
        window.onload = function (ev) {
            userId = $("#userId").val();

        }

        //页面关闭时执行
        window.onbeforeunload = function () {

            closeWebsocket(ws);

        }


        //好友列表事件
        function friendListEvent() {
            $("#friendChat .item").each(function () {
                //好友列表点击事件
                $(this).click(function () {
                    //切回聊天界面
                    $("#welcome_interface").css("display", "none")
                    $("#chatting_interface").css("display", "");
                    //title的内容更改（名字）
                    $("#title_area div").text($(this).find("span strong").text());
                    //设置toId的值
                    toId = $(this).find("input").val()
                    $("#toId").val(toId);
                    //设置toGroupId为null
                    toGroupId = null;
                    $("#toGroupId").val(null);
                    //清空信息数
                    var friendDivId = "#friend_" + toId;
                    $(friendDivId).find("span label").text("");
                    //清空消息框
                    $("#message_list").empty();
                    //加载好友消息
                    var messageJsonArray = getMessageJsonArray(getMessageArrayItemKey(userId, toId));
                    //历遍messageJsonArray
                    for (var i = 0; i < messageJsonArray.length; i++) {
                        //添加到消息框
                        appendMessageToMessageList(messageJsonArray[i]);
                    }
                });
            });
        }

        //群组列表事件
        function groupListEvent() {
            $("#groupChat .item").each(function () {
                //群组列表点击事件
                $(this).click(function () {
                    //切回聊天界面
                    $("#welcome_interface").css("display", "none")
                    $("#chatting_interface").css("display", "");
                    //title的内容更改（名字）
                    $("#title_area div").text($(this).find("span strong").text());
                    //设置toGroupId
                    $("#toGroupId").val($(this).find("input").val());
                    toGroupId = $(this).find("input").val();
                    //设置toId的值null
                    $("#toId").val(null);
                    $("#toId").val(null);
                });
            });
        }


        //清空按钮事件
        function btnClearAll() {
            $("#edit_area textarea").val(null);
        }

        //发送信息按钮点击事件
        function btnSendMessage() {
            //获取信息
            message = $("#edit_area textarea").val();
            if (message == null || message == "") {
                return false;
            }
            userId = $("#userId").val();
            toId = $("#toId").val();
            toGroupId = $("#toGroupId").val();
            var now = new Date().toJSON();
            //创建JSON对象
            var jsonData = {
                "fromId": userId,
                "toId": toId,
                "sendTime": now,
                "content": message
            };
            //发送信息
            sendWebsocket(ws, JSON.stringify(jsonData));
            //清空输入框
            $("#edit_area textarea").val(null);
        }

        //接受消息事件
        function receiveMessageEvent() {
            ws.onmessage = function (ev) {
                //获取接收的消息json字符串，转化为json对象
                var messageJsonString = ev.data;
                //消息添加消息框
                //messageJsonObject消息的json对象
                var messageJsonObject = JSON.parse(messageJsonString);
                appendMessageToMessageList(messageJsonObject);
                //保存消息到本地
                saveMessage(getMessageArrayItemKey(messageJsonObject.fromId, messageJsonObject.toId), messageJsonString);
            }
        }

        /**
         * 添加消息到消息框
         * @param messageJsonObject  消息的josn对象
         */
        function appendMessageToMessageList(messageJsonObject) {
            //是自己发送的
            if (messageJsonObject.fromId == $("#userId").val()) {
                $(friendDivId).find("span label").text("");
                //消息添加到消息框中
                $("#message_list").append(
                    "<div class='message_item'>" +
                    "<p class='selfMessage'>" +
                    messageJsonObject.content + "<br/>" + messageJsonObject.sendTime +
                    "</p>" +
                    "</div>");
            } else {//如果不是自己发送
                var friendDivId = "#friend_" + messageJsonObject.fromId;

                if (messageJsonObject.fromId == $("#toId").val()) {//同一聊天状态
                    $(friendDivId).find("span label").text("");
                    $("#message_list").append(
                        "<div class='message_item'>" +
                        "<p class='othersMessage'>" +
                        messageJsonObject.content + "<br/>" + messageJsonObject.sendTime +
                        "</p>" +
                        "</div>");
                } else {//不同一聊天状态
                    //判断信息数量，实现自增
                    var t = $(friendDivId).find("span label").text();
                    if (t == "") {
                        $(friendDivId).find("span label").text(1);
                    } else {
                        $(friendDivId).find("span label").text(parseInt(t) + 1);
                    }
                }
            }
        }

        //chat item
        function btnFriendChat() {
            $("#groupChat").css("display", "none");
            $("#friendChat").css("display", "");
        }

        //group chat item
        function btnGroupChat() {
            $("#friendChat").css("display", "none");
            $("#groupChat").css("display", "");
        }

    </script>
</head>
<body>
<input id="userId" type="hidden" name="userId" value="${user.id}"/>
<input id="toId" type="hidden" name="toId" value=""/>
<input id="toGroupId" type="hidden" name="toGroupId" value=""/>
<div id="main">
    <div id="letfPanle">
        <div id="header">
            <div id="avatar">
                <img src="${pageContext.request.contextPath}/${user.userInformation.headPortrait}"
                     style="float: left;width: 60px;height: 60px"/>
            </div>
            <div id="info">
                <span>${user.name}</span>
            </div>
            <div id="menu">
                <div>
                    <button style="background: url('${pageContext.request.contextPath}/static/images/menuioc.png') no-repeat center">
                        　　
                    </button>
                </div>
                <div id="menu_items">
                    <div class="menu_item">
                        <a href="${pageContext.request.contextPath}/user/toAddFriend">添加好友</a>
                    </div>
                    <hr/>
                    <div class="menu_item">
                        <a href="#">新建群聊</a>
                    </div>
                    <hr/>
                    <div class="menu_item">
                        <a href="${pageContext.request.contextPath}/user/toEditInformation">修改信息</a>
                    </div>
                    <hr/>
                    <div class="menu_item">
                        <a href="${pageContext.request.contextPath}/user/toEditPassword">修改密码</a>
                    </div>
                    <hr/>
                    <div class="menu_item">
                        <a href="${pageContext.request.contextPath}/user/toLogin">切换账号</a>
                    </div>
                    <hr/>
                    <div class="menu_item">
                        <a href="${pageContext.request.contextPath}/user/logout">退　　出</a>
                    </div>
                    <hr/>
                </div>
            </div>
        </div>
        <hr/>
        <div id="search">
            <input type="search" name="search" value=""/>
            <button id="btnsearch" onclick="">search</button>
        </div>
        <hr/>
        <div id="tab">
            <button class="tab_items"
                    style="background: url('${pageContext.request.contextPath}/static/images/chatico.png') no-repeat center"
                    onclick="btnFriendChat()">
                　　
            </button>
            <button class="tab_items"
                    style="background: url('${pageContext.request.contextPath}/static/images/groupico.png') no-repeat center"
                    onclick="btnGroupChat()">
                　　
            </button>
        </div>
        <hr/>
        <div id="list">
            <div id="friendChat" style="height: 400px;width: 300px;overflow-x: hidden;overflow-y: auto">
                <c:set var="friends" value="${user.friends}"></c:set>
                <c:forEach items="${friends}" var="friend" varStatus="sta">
                    <div id="friend_${friend.id}" class="item">
                        <div>
                            <img class="avatar"
                                 src="${pageContext.request.contextPath}/${friend.userInformation.headPortrait}"/>
                        </div>
                        <div class=" name">
                            <input type="hidden" name="friendId" value="${friend.id}"/>
                            <span><strong>${friend.name}</strong><label style="float:right;margin-right: 50px"></label></span>
                        </div>
                    </div>
                    <c:if test="${!sta.last}">
                        <hr class="hr_style"/>
                    </c:if>
                </c:forEach>

            </div>
            <div id="groupChat" style="display: none;height: 400px;width: 300px;overflow-x: hidden;overflow-y: auto">
                <c:set var="groups" value="${user.groups}"></c:set>
                <c:forEach items="${groups}" var="group" varStatus="sta">
                    <div class="item">
                        <div>
                            <img class="avatar"
                                 src="${pageContext.request.contextPath}/static/images/default.jpg"/>
                        </div>
                        <div class=" name">
                            <input type="hidden" name="friendId" value="${group.id}">
                            <span><strong>${group.name}</strong></span>
                        </div>
                    </div>
                    <c:if test="${!sta.last}">
                        <hr class="hr_style"/>
                    </c:if>
                </c:forEach>

            </div>
        </div>
    </div>
    <div id="rightPanle">
        <div style="width: auto;height:600px;margin-left: 300px">
            <div id="welcome_interface" style="text-align: center">
                <label style="color: crimson">欢迎使用！</label>
            </div>
            <div id="chatting_interface" style="display: none">
                <div id="title_area">
                    <div>title</div>
                </div>

                <div id="message_area" style="background-color: white">
                    <div id="message_list" style="width:inherit;height:inherit;overflow-x: hidden;overflow-y: auto">

                    </div>
                </div>

                <div id="edit_area">
                    <textarea name="context" style=""></textarea>
                    <div>
                        <button style="float: right; height:30px;width:80px;margin-right: 30px"
                                onclick="btnSendMessage()">发送
                        </button>
                        <button style="float: right; height:30px;width:80px;margin-right: 30px" onclick="btnClearAll()">
                            清空
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
