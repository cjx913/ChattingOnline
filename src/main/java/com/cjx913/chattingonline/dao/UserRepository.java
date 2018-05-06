package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByAccount(String account);
    User findByIdAndAccount(Integer id,String account);
    boolean existsByAccount(String account);
}
