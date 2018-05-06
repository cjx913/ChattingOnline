package com.cjx913.chattingonline.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.Date;

public class MessageJsonObject {

    private Integer fromId;
    private Integer toId;
    private Integer toGroupId;
    private Date sendTime;
    private String content;

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Integer getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(Integer toGroupId) {
        this.toGroupId = toGroupId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //提供转换json字符串的方法
    public String toJSONStringWithDateFormat(String format){
        return JSON.toJSONStringWithDateFormat(this,format, SerializerFeature.WriteNullStringAsEmpty);
    }
}
