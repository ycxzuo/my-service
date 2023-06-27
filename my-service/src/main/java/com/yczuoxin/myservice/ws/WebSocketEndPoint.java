package com.yczuoxin.myservice.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/echo")
@Component
@Slf4j
public class WebSocketEndPoint {

    private static final ConcurrentHashMap<String, Session> SESSION_MAPPING = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        SESSION_MAPPING.put(session.getId(), session);
        System.out.println(this.hashCode());
        System.out.println(session.getId() + "web socket 已创建连接");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println(this.hashCode());
        System.out.println(session.getId() + "WebSocketEndPoint - onMessage receive message: " + msg);
    }

    @OnClose
    public void onClose(Session session) {
        SESSION_MAPPING.remove(session.getId());
        System.out.println(this.hashCode());
        System.out.println(session.getId() + "web socket 断开");
    }

    public void sendMessage(String id, String message) throws IOException {
        SESSION_MAPPING.get(id).getBasicRemote().sendText("发送消息：" + message);
    }

}
