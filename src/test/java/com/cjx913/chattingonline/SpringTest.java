package com.cjx913.chattingonline;

import com.cjx913.chattingonline.service.GroupService;
import com.cjx913.chattingonline.service.MessageService;
import com.cjx913.chattingonline.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*使用spring的单元测试，加载spring配置文件*/
@ContextConfiguration(locations = "classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MessageService messageService;

    public UserService getUserService() {
        return userService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
