package com.cjx913.chattingonline.service;

import com.cjx913.chattingonline.entity.Friend;
import com.cjx913.chattingonline.entity.Password;
import com.cjx913.chattingonline.entity.User;
import com.cjx913.chattingonline.entity.UserInformation;
import com.cjx913.chattingonline.exception.ServiceException;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserService {
//    User
    List<User> findUsersAll();
    User findUserById(Integer id);
    User findUserByAccount(String account);
    User findUserByIdAndAccount(Integer id,String account);
    User findUserByAccountAndPassword(String account,String password);
    User saveOrUpdateUser(String name);

//    Password
    Password saveOrUpdatePassword(Integer id, String password);
    Password saveOrUpdatePassword(String account,String password);
    boolean existsPasswordByUserIdAndPassword(Integer id,String password);

//    UserInformation
    UserInformation findUserInformationByUserId(Integer userId);
    UserInformation findUserInformationByUserAccount(String account);
    UserInformation saveOrUpdateInformation(Integer userId,UserInformation userInformation) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ServiceException;
    UserInformation saveOrUpdateInformation(Integer userId, String gender, Integer age, Date birth, String address, String headPortrait,String eMail,String phone) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ServiceException;
    UserInformation saveOrUpdateInformation(String userAccount,UserInformation userInformation) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ServiceException;
    UserInformation saveOrUpdateInformation(String userAccount, String gender, Integer age, Date birth, String address, String headPortrait,String eMail,String phone) throws IllegalAccessException, IntrospectionException, InvocationTargetException, ServiceException, InstantiationException;

    //friend
    List<Friend> findFriendsByOwnerId(Integer id);
    boolean addFriend(Integer id,String account);
}
