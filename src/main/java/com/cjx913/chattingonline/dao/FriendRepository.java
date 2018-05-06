package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FriendRepository extends JpaRepository<Friend,Integer> {
    Friend findByOwnerIdAndFriendId(Integer ownerId,Integer friendId);
    List<Friend> findFriendsByOwnerId(Integer id);
}
