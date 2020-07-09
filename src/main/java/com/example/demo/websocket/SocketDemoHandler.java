package com.example.demo.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.*;

/**
 * @author demo
 * @date 2020-07-09
 */
@Data
@Slf4j
@Component
@ServerEndpoint(value = "/websocket/{param}")
public class SocketDemoHandler {
    /**
     * socket链接集合
     */
    private static CopyOnWriteArraySet<SocketDemoHandler> connections = new CopyOnWriteArraySet<>();

    private Session session;

    private static final int MAX_LOSE_HEART_COUNT = 3;

    private static final long ALLOW_ALIVE_TIME = 3000;
    /**
     * 记录下次发送时间的时间戳
     */
    private long timeStamp;
    /**
     * 是否收到了心跳
     */
    private boolean isHeart=false;
    /**
     * 心跳丢失次数
     */
    private int loseHeartCounter;

    @OnClose
    public void onClose(Session session) {
        //从set中删除
        connections.remove(session);
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("param") String param, Session session) {
        log.debug("建立websocket连接开始，传入参数 -----------> {}", param);
        if (ObjectUtils.isEmpty(param)) {
            log.error("参数异常");
            return;
        }
        this.session = session;
        log.debug("------------ 欢迎id = {} 成功加入 --------------", session.getId());
        //测试
        connections.add(this);
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("websocket received message:" + message);
        try {
            if (message.equals("ping")) {
                session.getBasicRemote().sendText("pong");
            } else {
                session.getBasicRemote().sendText(message + ",已收到");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    public void sendNotifyToAll(String message) {
        log.info("webSocketSet ------------> {}", JSON.toJSONString(message));
        for (SocketDemoHandler item : connections) {
            try {
                item.sendMessage(JSON.toJSONString(message));
                log.info("发送内容 -------------> {}", JSON.toJSONString(message));
            } catch (IOException e) {
                continue;
            }
        }
    }

    /**
     * 仅启动一次
     */
    @PostConstruct
    public void startCodeOrderHeart(){
        startHeart();
        System.out.println("启动心跳线程");
    }

    /**
     * 启动心跳包
     */
    private synchronized void startHeart() {
        // 心跳检测
        CodeOrderHeartThread codeOrderHeartThread = new CodeOrderHeartThread();
        ExecutorService codeOrderHeartExecutorService = Executors.newScheduledThreadPool(1);
        ((ScheduledExecutorService) codeOrderHeartExecutorService).scheduleAtFixedRate(codeOrderHeartThread, 1, 5, TimeUnit.SECONDS);

        // 心跳保持
        KeepHeartThread keepHeart = new KeepHeartThread();
        ExecutorService keepHeartExecutorService = Executors.newScheduledThreadPool(1);
        ((ScheduledExecutorService) keepHeartExecutorService).scheduleAtFixedRate(keepHeart, 1, 5, TimeUnit.SECONDS);
    }

    /**
     * @param @return
     * @return long
     * @Description: 获取时间戳
     * @author fuzl
     */
    private static long getTimeInMillis() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + 8);
        return c.getTimeInMillis();
    }

    /**
     * @description server发送心跳包 2秒一次
     */
    private class KeepHeartThread implements Runnable {
        @Override
        public void run() {
            JSONObject heartJson = new JSONObject();
            heartJson.put("服务端心跳", "保持心跳");
            heartJson.put("commandCode", 1);
            heartJson.put("timeStamp", getTimeInMillis());
            try {
                log.debug("发送心跳包当前人数为:" + connections.size() + "当前时间:" + DateUtil.formatDate2YYYYMMDDHHMISS(DateUtil.fullFormatNow()));
                sendMessage(heartJson.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 检测是否收到client心跳 每秒一次
     */
    private class CodeOrderHeartThread implements Runnable {

        @Override
        public void run() {
            try {
                // 服务器当前时间戳
                long currentTimeMillis = getTimeInMillis();
                for (SocketDemoHandler socketDemoHandler : connections) {
                    long intervalTime = currentTimeMillis - socketDemoHandler.getTimeStamp();
//                    logger.debug("intervalTime:{},allow:{}", intervalTime, ALLOW_ALIVE_TIME);
                    // 客户端心跳未开启，时间戳非0，当前时间戳和上次ping时间戳大于允许的空闲时间
                    if (!socketDemoHandler.isHeart() && socketDemoHandler.getTimeStamp() != 0 && intervalTime > ALLOW_ALIVE_TIME) {
                        int loseHeartCounter = socketDemoHandler.getLoseHeartCounter();
                        loseHeartCounter++;
                        socketDemoHandler.setLoseHeartCounter(loseHeartCounter);
                        log.debug(socketDemoHandler.getSession().getId() + "- 心跳丢失次数: " + socketDemoHandler.getLoseHeartCounter());
                    }

                    if (socketDemoHandler.getLoseHeartCounter() > MAX_LOSE_HEART_COUNT) {
                        log.debug(socketDemoHandler.getSession().getId() + "- 挂了");
                        onClose(socketDemoHandler.getSession());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
