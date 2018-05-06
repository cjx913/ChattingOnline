package com.cjx913.chattingonline.dao;

import com.cjx913.chattingonline.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation,Integer> {
    UserInformation findByUserId(Integer userId);
}
