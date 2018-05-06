package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message,Integer> {

}
