package com.cjx913.chattingonline.action;

import com.cjx913.chattingonline.service.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

public class BaseAction extends ActionSupport {

    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;


    public GroupService getGroupService() {
        return groupService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

    public UserService getUserService() {
        return userService;
    }
}
