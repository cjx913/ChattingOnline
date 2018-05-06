package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<Password,Integer> {
    Password findPasswordByUserId(Integer id);
    boolean existsPasswordByUserIdAndPassword(Integer id,String password);

}
