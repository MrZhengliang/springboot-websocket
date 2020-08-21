package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.websocket.Session;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author demo
 */
@Data
public class DemoSessionRequest implements Serializable {

    /**
     * 连接id
     */
    private String sessionId;
    /**
     * 建立的连接
     */
    @JSONField(serialize = false)
    private Session session;
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
    /**
     * 指令码
     * @see
     */
    private int commandCode;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 消息状态： 1：初始化；2：成功；3：处理失败
     */
    private Integer status;

    //业务返回参数信息
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DemoSessionRequest that = (DemoSessionRequest) o;
        return timeStamp == that.timeStamp &&
                isHeart == that.isHeart &&
                loseHeartCounter == that.loseHeartCounter &&
                commandCode == that.commandCode &&
                Objects.equals(session, that.session) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), session, timeStamp, isHeart, loseHeartCounter, commandCode);
    }
}
