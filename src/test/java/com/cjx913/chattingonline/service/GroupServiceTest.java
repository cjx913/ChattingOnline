package com.cjx913.chattingonline.service;

import com.cjx913.chattingonline.SpringTest;
import org.junit.Test;

public class GroupServiceTest extends SpringTest {
    @Test
    public void getGroup(){
        getGroupService().findGroupsById(1);
    }
}
