package com.cjx913.chattingonline.service;

import com.cjx913.chattingonline.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> findGroupsById(Integer id);
    
}
