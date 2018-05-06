
function getWebsocket(url) {
    var ws;
    if (ws == null) {
        if ('WebSocket' in window) {
            ws = new WebSocket(url);
            console.info("已经创建websocket："+url);
        } else {
            alert("请换个浏览器或升级浏览器");
        }
    }

    initWebsocket(ws,url);

    return ws;
}

//初始化websocket
function initWebsocket(websocket,url){
    websocket.onopen = function (ev) {
        console.info("Websocket OnOpen:"+url);
    }

    websocket.onmessage = function (ev) {
        console.info("Websocket OnMessage:"+url);
    }

    websocket.onclose = function (ev) {
        console.info("Websocket OnClose"+url);
    }

    websocket.onerror = function (ev) {
        console.info("Websocket OnError"+url);
    }
}



//关闭websocket
function closeWebsocket(websocket) {
    websocket.close()
}

//websocket发送信息
function sendWebsocket(websocket, message) {
        websocket.send(message);
}

