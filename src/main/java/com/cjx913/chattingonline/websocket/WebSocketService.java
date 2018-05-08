package com.cjx913.chattingonline.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cjx913.chattingonline.entity.Group;
import com.cjx913.chattingonline.entity.MessageJsonObject;
import com.cjx913.chattingonline.entity.User;
import com.cjx913.chattingonline.exception.WebsocketException;
import com.cjx913.chattingonline.service.impl.BaseServiceImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;



/**
 * /解为websocket的服务端，前端ws://XXXX/websocket由改服务器处理
 * /value为websocket的url
 * /configurator为配置类
 */
@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
@Service
@Scope("prototype")
@Transactional
public class WebSocketService extends BaseServiceImpl {

    private final static ConcurrentMap <Integer, Session> webSocketServiceMap = new ConcurrentHashMap <>();
    private Integer userId;
    private Session session;
    private static final String CLIENT_ID = "userId";

    public Session getSession() {
        return session;
    }

    /**
     * 当一个新用户连接时所调用的方法
     * @param session 该方法可能包含一个javax.websocket.Session可选参数,如果有这个参数，容器将会把当前发送消息客户端的连接Session注入进去
     * @param config  EndpointConfig 配置类
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        userId = (Integer) httpSession.getAttribute(CLIENT_ID);
        if (userId != null && !webSocketServiceMap.containsKey(userId)) {
            webSocketServiceMap.put(userId, session);
        }
    }

    /**
     * 出错时调用的方法
     * @param throwable
     */
    @OnError
    public void onError(Throwable throwable) {
        throw new WebsocketException(throwable.getMessage());
    }

    /**
     * 当一个用户断开连接时所调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketServiceMap.remove(userId);
        closeSession(getSession());
    }



    private void closeSession(Session session) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * * 当服务器接收到客户端发送的消息时所调用的方法
     * 该方法可能包含一个javax.websocket.Session可选参数
     * 如果有这个参数，容器将会把当前发送消息客户端的连接Session注入进去
     * 送客户端接受信息，对信息处理以及发送信息到指定客户端
     * @param message  要求json格式的数据，可以转化为MessageJsonObject对象
     * @return
     */
    @OnMessage
    public String onMessage(String message,Session session) {
        //信息处理，返回MessageJsonObject
        MessageJsonObject messageJsonObject = handleMessage(message);
        //发送信息
        sendMessage(messageJsonObject);
        //同样把消息返回
        return messageJsonObject.toJSONStringWithDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 消息处理
     * @param message 要求json格式的数据，可以转化为MessageJsonObject对象
     * @return
     */
    private MessageJsonObject handleMessage(String message) {
        MessageJsonObject messageJsonObject = JSON.parseObject(message, MessageJsonObject.class);
        return messageJsonObject == null ? null : messageJsonObject;
    }

    /**
     * 发送消息
     * @param messageJsonObject
     * @return 是否发送成功
     */
    private boolean sendMessage(MessageJsonObject messageJsonObject) {
        //判断
        if (messageJsonObject != null
                && messageJsonObject.getFromId() == userId
                &&webSocketServiceMap.containsKey(messageJsonObject.getFromId())) {
            //是一对一还是群发
            if (messageJsonObject.getToId() != null && messageJsonObject.getToGroupId() == null) {//一对一发送
                return sendMessageToUser(messageJsonObject);
            } else if (messageJsonObject.getToId() == null && messageJsonObject.getToGroupId() != null) {//群发
                return sendMessageToGroup(messageJsonObject);
            }
        }
        return false;
    }

    /**
     * 一对一发送消息
     * @param messageJsonObject
     * @return 是否发送成功
     */
    private boolean sendMessageToUser(MessageJsonObject messageJsonObject) {
        Integer toId = messageJsonObject.getToId();
        //是否发送给空用户，用户是否连接
        if (toId != null && webSocketServiceMap.containsKey(toId)) {
            Session session = webSocketServiceMap.get(toId);
            String json = messageJsonObject.toJSONStringWithDateFormat("yyyy-MM-dd HH;mm:ss");
            session.getAsyncRemote().sendText(json);
            return true;
        }
        return false;
    }

    /**
     * 群发消息
     * @param messageJsonObject
     * @return 是否全部发送成功
     */
    private boolean sendMessageToGroup(MessageJsonObject messageJsonObject) {
        boolean isAllSuccess = true;
        Integer toGroupId = messageJsonObject.getToGroupId();
        //查询群里成员的id
        //getGroupMenberId(Integer groupId);
        Set<Integer> groupMember = getGroupMenberId(toGroupId);
        //向每一个在线成员发送信息
        Iterator <Integer> iterator = groupMember.iterator();
        Integer next = null;
        while (iterator.hasNext()){
             next = iterator.next();
            if (next != null && webSocketServiceMap.containsKey(next)) {
                Session session = webSocketServiceMap.get(next);
                String json = JSON.toJSONStringWithDateFormat(messageJsonObject,"yyyy-MM-DD HH:mm;ss",SerializerFeature.WriteNullStringAsEmpty);
                session.getAsyncRemote().sendText(json);
            }else{
                isAllSuccess = false;
            }
        }
        return isAllSuccess;
    }

    private Set<Integer> getGroupMenberId(Integer groupId) {
        Set<Integer> ids = new HashSet <>();

        Optional<Group> groupOptional = getGroupRepository().findById(groupId);
        if(groupOptional.isPresent()){
            Group group = groupOptional.get();
            Set <User> users = group.getUsers();
            Iterator <User> iterator = users.iterator();
            User user = null;
            while (iterator.hasNext()){
                user = iterator.next();
                ids.add(user.getId());
            }
        }
        return ids;
    }


}


