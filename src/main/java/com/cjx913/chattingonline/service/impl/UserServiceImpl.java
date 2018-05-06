package com.cjx913.chattingonline.service.impl;

import com.cjx913.chattingonline.entity.Friend;
import com.cjx913.chattingonline.entity.Password;
import com.cjx913.chattingonline.entity.User;
import com.cjx913.chattingonline.entity.UserInformation;
import com.cjx913.chattingonline.exception.ServiceException;
import com.cjx913.chattingonline.service.UserService;
import com.cjx913.chattingonline.utils.MD5Encryption;
import com.cjx913.chattingonline.utils.ObjectReplace;
import com.cjx913.chattingonline.utils.UserAccountGenerate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    /*User CRUD Operation*/
    @Override
    public List <User> findUsersAll() {
        return getUserRepository().findAll();
    }

    @Override
    public User findUserById(Integer id) {
        Optional <User> userOptional = getUserRepository().findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public User findUserByAccount(String account) {
        return getUserRepository().findByAccount(account);
    }

    @Override
    public User findUserByIdAndAccount(Integer id, String account) {
        return getUserRepository().findByIdAndAccount(id, account);
    }

    @Override
    public User findUserByAccountAndPassword(String account, String password) {
        if (getUserRepository().existsByAccount(account)) {
            User user = getUserRepository().findByAccount(account);
            Password p = user.getPassword();
            password = MD5Encryption.getMD5Value(password);
            if (p.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public User saveOrUpdateUser(String name) {
        /*獲取唯一的account*/
        String account = UserAccountGenerate.getUniqueAccount(8);
        while (getUserRepository().existsByAccount(account)) {
            account = UserAccountGenerate.getUniqueAccount(8);
        }
        User user = new User();
        user.setName(name);
        user.setAccount(account);
        return getUserRepository().saveAndFlush(user);
    }


    /*Password CRUD Operation*/
    /*saveOrUpdate*/
    @Override
    public Password saveOrUpdatePassword(Integer id, String password) {
        if (getUserRepository().existsById(id)) {
            password = MD5Encryption.getMD5Value(password);
            Password p = new Password(id, password);
            return getPasswordRepository().saveAndFlush(p);
        }
        return null;
    }

    @Override
    public Password saveOrUpdatePassword(String account, String password) {
        if (getUserRepository().existsByAccount(account)) {
            User user = getUserRepository().findByAccount(account);
            password = MD5Encryption.getMD5Value(password);
            Password p = new Password(user.getId(), password);
            return getPasswordRepository().saveAndFlush(p);
        }
        return null;
    }

    @Override
    public boolean existsPasswordByUserIdAndPassword(Integer id,String password){
        password = MD5Encryption.getMD5Value(password);
        return getPasswordRepository().existsPasswordByUserIdAndPassword(id,password);
    }

    /*UserInformation CRUD Operation*/
    /*find*/
    @Override
    public UserInformation findUserInformationByUserId(Integer userId) {
        return getUserInformationRepository().findByUserId(userId);
    }

    @Override
    public UserInformation findUserInformationByUserAccount(String account) {
        User user = getUserRepository().findByAccount(account);
        return getUserInformationRepository().findByUserId(user.getId());
    }

    /*saveOrUpdate*/
    @Override
    public UserInformation saveOrUpdateInformation(Integer userId, UserInformation userInformation) throws ServiceException {
        if (getUserRepository().existsById(userId)) {
            userInformation.setUserId(userId);
            UserInformation information = getUserInformationRepository().findByUserId(userId);
            return userInformationSaveOrUpdate(information, userInformation);
        }
        return null;
    }

    @Override
    public UserInformation saveOrUpdateInformation(Integer userId, String gender, Integer age, Date birth, String address, String headPortrait, String eMail, String phone) throws ServiceException {
        if (getUserRepository().existsById(userId)) {
            UserInformation userInformation = new UserInformation(userId, gender, age, birth, address, headPortrait, eMail, phone);
            UserInformation information = getUserInformationRepository().findByUserId(userId);
            return userInformationSaveOrUpdate(information, userInformation);
        }
        return null;
    }



    @Override
    public UserInformation saveOrUpdateInformation(String userAccount, UserInformation userInformation) throws ServiceException {
        if (getUserRepository().existsByAccount(userAccount)) {
            Integer userId = getUserRepository().findByAccount(userAccount).getId();
            userInformation.setUserId(userId);
            UserInformation information = getUserInformationRepository().findByUserId(userId);
            return userInformationSaveOrUpdate(information, userInformation);
        }

        return null;
    }

    @Override
    public UserInformation saveOrUpdateInformation(String userAccount, String gender, Integer age, Date birth, String address, String headPortrait, String eMail, String phone) throws ServiceException {
        if (getUserRepository().existsByAccount(userAccount)) {
            Integer userId = getUserRepository().findByAccount(userAccount).getId();
            UserInformation userInformation = new UserInformation(userId, gender, age, birth, address, headPortrait, eMail, phone);
            UserInformation information = getUserInformationRepository().findByUserId(userId);
            return userInformationSaveOrUpdate(information, userInformation);
        }
        return null;
    }

    private UserInformation userInformationSaveOrUpdate(UserInformation original, UserInformation replace) throws ServiceException {
        try {
            original = ObjectReplace.replacePropertyExceptNull(original, replace);
        } catch (IntrospectionException e) {
            throw new ServiceException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new ServiceException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new ServiceException(e.getMessage());
        } catch (InstantiationException e) {
            throw new ServiceException(e.getMessage());
        }
        return getUserInformationRepository().saveAndFlush(original);
    }

    //Friend


    @Override
    public List<Friend> findFriendsByOwnerId(Integer id) {
        return getFriendRepository().findFriendsByOwnerId(id);
    }

    @Override
    public boolean addFriend(Integer id, String account) {
        if (getUserRepository().existsByAccount(account)) {
            Integer friendId = getUserRepository().findByAccount(account).getId();
            if(getFriendRepository().findByOwnerIdAndFriendId(id,friendId)==null) {
                Friend friend = new Friend(id, friendId);
                getFriendRepository().save(friend);
                friend = new Friend(friendId,id);
                getFriendRepository().saveAndFlush(friend);
                return true;
            }
        }
        return false;
    }
}
