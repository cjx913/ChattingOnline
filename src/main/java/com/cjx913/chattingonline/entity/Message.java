package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_message")
@IdClass(MessagePK.class)
public class Message implements Serializable {


    private static final long serialVersionUID = -2329619518100460959L;

    private Integer id;
    private Integer fromId;
    private Integer toId;
    private Date sendTime;
    private String content;

    private User send;
    private User receive;

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
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
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

    @ManyToOne
    @JoinColumn(name = "to_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getSend() {
        return send;
    }

    public void setSend(User send) {
        this.send = send;
    }

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getReceive() {
        return receive;
    }

    public void setReceive(User receive) {
        this.receive = receive;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (fromId != null ? !fromId.equals(message.fromId) : message.fromId != null) return false;
        if (toId != null ? !toId.equals(message.toId) : message.toId != null) return false;
        if (sendTime != null ? !sendTime.equals(message.sendTime) : message.sendTime != null) return false;
        return content != null ? content.equals(message.content) : message.content == null;
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
