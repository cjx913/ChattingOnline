package com.cjx913.chattingonline.service;

import com.cjx913.chattingonline.SpringTest;
import com.cjx913.chattingonline.entity.User;
import com.cjx913.chattingonline.entity.UserInformation;
import com.cjx913.chattingonline.exception.ServiceException;
import org.junit.Test;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UserServiceTest extends SpringTest{
    @Test
    public void findAll(){
        List<User> users = getUserService().findUsersAll();
        assert users!=null;
        for(User u:users){
            System.out.println(u.getAccount());
        }
    }

    @Test
    public void findByAccount(){
        User user = getUserService().findUserByAccount("10000001");
        assert user!=null;
        System.out.println(user.getPassword().getPassword());

    }

    @Test
    public void save(){
        User user = getUserService().saveOrUpdateUser("fffsdasasaf");
        System.out.println(user.getId());
        System.out.println(user.getAccount());
    }

    @Test
    public void saveOrUpdatePassword(){
        getUserService().saveOrUpdatePassword("10000001","1cjx913");
    }

    @Test
    public void saveOrUpdateInformation() throws InvocationTargetException, IntrospectionException, ServiceException, IllegalAccessException {
        UserInformation information = new UserInformation();
        information.setAge(22);
        information.setAddress("1112sda3434aa");
        getUserService().saveOrUpdateInformation("1001",information);
    }

    @Test
    public void test(){
        User user = getUserService().findUserByAccount("67152532");


    }

}
