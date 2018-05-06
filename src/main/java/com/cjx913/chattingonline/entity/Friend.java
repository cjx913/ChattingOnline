package com.cjx913.chattingonline.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_friend")
@IdClass(FriendPK.class)
public class Friend implements Serializable {

    private static final long serialVersionUID = -7873384576835430379L;

    private Integer ownerId;
    private Integer friendId;

    private User user;

    public Friend() {
    }

    public Friend(Integer ownerId, Integer friendId) {
        this.ownerId = ownerId;
        this.friendId = friendId;
    }

    @Id
    @Column(name = "owner_id", nullable = false)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Id
    @Column(name = "friend_id", nullable = false)
    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (ownerId != null ? !ownerId.equals(friend.ownerId) : friend.ownerId != null) return false;
        return friendId != null ? friendId.equals(friend.friendId) : friend.friendId == null;
    }

    @Override
    public int hashCode() {
        int result = ownerId != null ? ownerId.hashCode() : 0;
        result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
        return result;
    }
}
