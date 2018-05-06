package com.cjx913.chattingonline.service.impl;

import com.cjx913.chattingonline.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInformationRepository userInformationRepository;
    @Autowired
    private PasswordRepository passwordRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private FriendRepository friendRepository;


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserInformationRepository getUserInformationRepository() {
        return userInformationRepository;
    }

    public PasswordRepository getPasswordRepository() {
        return passwordRepository;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public FriendRepository getFriendRepository() {
        return friendRepository;
    }
}
