package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

public class MessagePK implements Serializable {
    private static final long serialVersionUID = 8092298892434780136L;
    private Integer id;
    private Integer fromId;
    private Integer toId;
    private Date sendTime;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "from_id", nullable = false)
    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    @Id
    @Column(name = "to_id", nullable = false)
    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    @Basic
    @Column(name = "send_time", nullable = false)
    public java.util.Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(java.util.Date sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 256)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessagePK messagePK = (MessagePK) o;

        if (id != null ? !id.equals(messagePK.id) : messagePK.id != null) return false;
        if (fromId != null ? !fromId.equals(messagePK.fromId) : messagePK.fromId != null) return false;
        if (toId != null ? !toId.equals(messagePK.toId) : messagePK.toId != null) return false;
        if (sendTime != null ? !sendTime.equals(messagePK.sendTime) : messagePK.sendTime != null) return false;
        return content != null ? content.equals(messagePK.content) : messagePK.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fromId != null ? fromId.hashCode() : 0);
        result = 31 * result + (toId != null ? toId.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
