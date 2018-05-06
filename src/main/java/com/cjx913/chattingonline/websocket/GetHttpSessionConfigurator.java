package com.cjx913.chattingonline.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        //获取HttpSession
        HttpSession session = (HttpSession) request.getHttpSession();
        //HttpSession放入服务终端，可以让websocket服务端获取连接对象的httpsession
        sec.getUserProperties().put(HttpSession.class.getName(),session);
    }
}
