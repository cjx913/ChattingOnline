package com.cjx913.chattingonline.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FriendPK implements Serializable {

        private static final long serialVersionUID = -2234130148157325659L;
        private Integer ownerId;
        private Integer friendId;

        private User user;

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

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                FriendPK friendPK = (FriendPK) o;

                if (ownerId != null ? !ownerId.equals(friendPK.ownerId) : friendPK.ownerId != null) return false;
                if (friendId != null ? !friendId.equals(friendPK.friendId) : friendPK.friendId != null) return false;
                return user != null ? user.equals(friendPK.user) : friendPK.user == null;
        }

        @Override
        public int hashCode() {
                int result = ownerId != null ? ownerId.hashCode() : 0;
                result = 31 * result + (friendId != null ? friendId.hashCode() : 0);
                result = 31 * result + (user != null ? user.hashCode() : 0);
                return result;
        }
}
